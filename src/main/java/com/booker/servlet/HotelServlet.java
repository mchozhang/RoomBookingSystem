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
        String page = "/hotel.jsp";
        if (user instanceof Staff){
            Staff staff = (Staff) user;
            if (staff.getHotel().getId() == hotelId) {
                page = "/hotel_edit.jsp";
            }
        } else if (user == null){
            response.sendRedirect("/index.jsp");
            return;
        }

        // acquire hotel information
        Hotel hotel = Hotel.getHotelById(hotelId);
        List<Service> services = hotel.getServices();
        List<String> serviceNameList = new ArrayList<>();
        for (Service service: services){
            serviceNameList.add(service.getName());
        }
        String servicesStr = String.join(",", serviceNameList);
        List<Catalogue> catalogues = hotel.getCatalogues();
        Map<Integer, List<Room>> rooms = new HashMap<>();
        for (Catalogue catalogue: catalogues) {
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
        String serviceStr = request.getParameter("serviceStr");
        System.out.println(serviceStr);

        String addName = request.getParameter("addName");
        String addDescription = request.getParameter("addDescription");
        String addPrice = request.getParameter("addPrice");
//        String addRooms = request.getParameter("addRooms");
        String[] addRooms = request.getParameterValues("addRooms");
        System.out.println(addName);
        System.out.println(addDescription);
        System.out.println(addPrice);
//        System.out.println(addRooms);
        System.out.println(Arrays.toString(addRooms));

    }
}
