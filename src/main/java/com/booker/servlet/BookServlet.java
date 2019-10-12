package com.booker.servlet;

import com.booker.domain.*;
import com.booker.util.AppSession;
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
        if (!AppSession.isAuthenticated() || !AppSession.hasRole(AppSession.CUSTOMER_ROLE)) {
            response.sendRedirect("/loginServlet");
            return;
        }

        // parse start, end date params
        String startDate = request.getParameter("sd");
        String endDate = request.getParameter("ed");
        if (startDate != null && endDate != null) {
            startDate = DateUtil.parseDateParam(startDate);
            endDate = DateUtil.parseDateParam(endDate);
        } else {
            startDate = DateUtil.getToday();
            endDate = DateUtil.getTomorrow();
        }


        // parse room id, catalogue id params
        int roomId;
        int catalogueId;
        try {
            catalogueId = Integer.parseInt(request.getParameter("id"));
            roomId = Integer.parseInt(request.getParameter("room"));
        } catch (Exception e) {
            request.getRequestDispatcher("/hotelListServlet").forward(request, response);
            return;
        }

        // acquire login user from session
        User user = AppSession.getUser();
        boolean result = Booking.createBooking(user.getId(), catalogueId, roomId, startDate, endDate);
//        if (result) {
//            response.sendRedirect("/bookingsServlet");
//            System.out.println("444");
//
//            return;
//        } else {
//            doGet(request, response);
//        }
        response.setStatus(200);
        response.getWriter().write("hello");
        response.getWriter().close();
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check authentication and authorization
        if (!AppSession.isAuthenticated() || !AppSession.hasRole(AppSession.CUSTOMER_ROLE)) {
            response.sendRedirect("/loginServlet");
            return;
        }

        // acquire catalogue id, date from parameters
        int catalogueId;
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
        User user = AppSession.getUser();
        String view = "/hotel_book.jsp";

        // acquire catalogue information
        Catalogue catalogue = Catalogue.getCatalogueById(catalogueId);
        Hotel hotel = Hotel.getHotelById(catalogue.getHotelId());
        List<Room> rooms = catalogue.getRooms();

        // set attributes
        request.setAttribute("catalogue", catalogue);
        request.setAttribute("hotel", hotel);
        request.setAttribute("rooms", rooms);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.getRequestDispatcher(view).forward(request, response);
    }
}
