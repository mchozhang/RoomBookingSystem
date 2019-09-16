package com.booker.servlet;

import com.booker.domain.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class HotelServlet extends HttpServlet {

    public HotelServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // acquire hotel id from parameters
        int hotelId = 0;
        try {
            hotelId = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            // no hotel id found
            request.getRequestDispatcher("/hotelListServlet").forward(request, response);
            return;
        }

        // acquire login user from session
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        String page = "/hotel.jsp";
        if (user instanceof Staff) {
            Staff staff = (Staff) user;
            if (staff.getHotelId() == hotelId) {
                page = "/hotel_edit.jsp";
            }
        } else if (user == null) {
            response.sendRedirect("/index.jsp");
            return;
        }

        // acquire hotel information
        Hotel hotel = Hotel.getHotelById(hotelId);
        List<Service> services = hotel.getServices();
        List<String> serviceNameList = new ArrayList<>();
        for (Service service : services) {
            serviceNameList.add(service.getName());
        }
        String servicesStr = String.join(",", serviceNameList);
        List<Catalogue> catalogues = hotel.getCatalogues();
        Map<Integer, List<Room>> rooms = new HashMap<>();
        for (Catalogue catalogue : catalogues) {
            rooms.put(catalogue.getId(), catalogue.getRooms());
        }

        // set attributes
        request.setAttribute("user", user);
        request.setAttribute("hotel", hotel);
        request.setAttribute("services", services);
        request.setAttribute("serviceStr", servicesStr);
        request.setAttribute("catalogues", catalogues);
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // acquire hotel id from parameters
        int hotelId = 0;
        try {
            hotelId = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            // no hotel id found
            request.getRequestDispatcher("/hotelListServlet").forward(request, response);
            return;
        }

        // edit services
        Hotel hotel = Hotel.getHotelById(hotelId);
        String serviceStr = request.getParameter("serviceStr");
        if (serviceStr != null) {
            hotel.setServices(serviceStr);
            doGet(request, response);
            return;
        }

        // add catalogue
        String addName = request.getParameter("addName");
        String addDescription = request.getParameter("addDescription");
        String addPrice = request.getParameter("addPrice");
        String[] addRooms = request.getParameterValues("addRooms");
        if (addName != null) {
            Catalogue.addCatalogue(addName, addDescription, hotelId, addPrice, addRooms);
            doGet(request, response);
            return;
        }

        // edit catalogue
        String editId = request.getParameter("editId");
        String editName = request.getParameter("editName");
        String editDescription = request.getParameter("editDescription");
        String editPrice = request.getParameter("editPrice");
        String[] editRooms = request.getParameterValues("editRooms");
        if (editId != null) {
            Catalogue.editCatalogue(editId, editName, editDescription, editPrice, editRooms);
            doGet(request, response);
            return;
        }

        String deleteId = request.getParameter("deleteId");
        if (deleteId != null) {
            Catalogue.deleteCatalogue(deleteId);
            doGet(request, response);
        }

    }
}
