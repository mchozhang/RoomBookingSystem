package com.booker.servlet;

import com.booker.domain.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        List<Catalogue> catalogues = hotel.getCatalogues();

        // set attributes
        request.setAttribute("user", user);
        request.setAttribute("hotel", hotel);
        request.setAttribute("services", services);
        request.setAttribute("catalogues", catalogues);
        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
