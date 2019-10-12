package com.booker.servlet;

import com.booker.domain.*;
import com.booker.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends HttpServlet {
    public BookServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomId = 0;
        int catalogueId = 0;

        String startDate = request.getParameter("sd");
        String endDate = request.getParameter("ed");
        if (startDate != null && endDate != null) {
            startDate = DateUtil.parseDateParam(startDate);
            endDate = DateUtil.parseDateParam(endDate);
        } else {
            startDate = DateUtil.getToday();
            endDate = DateUtil.getTomorrow();
        }

        try {
            catalogueId = Integer.parseInt(request.getParameter("id"));
            roomId = Integer.parseInt(request.getParameter("room"));
        } catch (Exception e) {
            // no room id found
            request.getRequestDispatcher("/hotelListServlet").forward(request, response);
            return;
        }

        // acquire login user from session
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        String page = "/hotel_book.jsp";
        if (user instanceof Staff) {
            page = "/hotel.jsp";
        } else if (user == null) {
            response.sendRedirect("/index.jsp");
            return;
        }

        boolean result = Booking.createBooking(user.getId(), catalogueId, roomId, startDate, endDate);
        if (result) {
            page = "/bookingsServlet";
        }
        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // acquire catalogue id from parameters
        int catalogueId = 0;
        String startDate = request.getParameter("sd");
        String endDate = request.getParameter("ed");
        if (startDate != null && endDate != null) {
            startDate = DateUtil.parseDateParam(startDate);
            endDate = DateUtil.parseDateParam(endDate);
        } else {
            startDate = DateUtil.getToday();
            endDate = DateUtil.getTomorrow();
        }

        try {
            catalogueId = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            // no hotel id found
            request.getRequestDispatcher("/hotelListServlet").forward(request, response);
            return;
        }

        // acquire login user from session
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        String page = "/hotel_book.jsp";
        if (user instanceof Staff) {
            page = "/hotel.jsp";
        } else if (user == null) {
            response.sendRedirect("/index.jsp");
            return;
        }

        // acquire catalogue information
        Catalogue catalogue = Catalogue.getCatalogueById(catalogueId);
        Hotel hotel = Hotel.getHotelById(catalogue.getHotelId());
        List<Room> rooms = catalogue.getRooms();

        // set attributes
        request.setAttribute("user", user);
        request.setAttribute("catalogue", catalogue);
        request.setAttribute("hotel", hotel);
        request.setAttribute("rooms", rooms);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.getRequestDispatcher(page).forward(request, response);
    }
}
