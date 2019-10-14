package com.booker.servlet;

import com.booker.domain.*;
import com.booker.util.interceptingFilter.*;
import com.booker.util.session.AppSession;
import com.booker.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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

        // filter booking information
        Booking booking = new Booking(user.getId(), roomId, Date.valueOf(startDate), Date.valueOf(endDate));
        FilterManager filterManager = new FilterManager(new CreateBookingTarget());
        filterManager.setFilter(new UserFilter());
        filterManager.setFilter(new RoomFilter());
        filterManager.setFilter(new DateRangeFilter());

        String result = filterManager.filterBooking(booking);
        response.getWriter().write(result);
        response.getWriter().close();
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

        // acquire room availability
        List<Boolean> roomAvailabilities = new ArrayList<>();
        for (Room room : rooms) {
            roomAvailabilities.add(room.isAvailable(startDate, endDate));
        }

        // set attributes
        request.setAttribute("catalogue", catalogue);
        request.setAttribute("hotel", hotel);
        request.setAttribute("roomAvailabilities", roomAvailabilities);
        request.setAttribute("rooms", rooms);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.getRequestDispatcher(view).forward(request, response);
    }
}
