package com.booker.servlet;

import com.booker.domain.Booking;
import com.booker.domain.Staff;
import com.booker.domain.User;
import com.booker.util.AppSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookingsServlet extends HttpServlet {
    public BookingsServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!AppSession.isAuthenticated()) {
            response.sendRedirect("/loginServlet");
            return;
        }
        User user = AppSession.getUser();
        List<Booking> bookings = null;
        if (AppSession.hasRole(AppSession.CUSTOMER_ROLE)) {
            bookings = Booking.getCustomerBookings(user.getId());
        } else if (AppSession.hasRole(AppSession.STAFF_ROLE)){
            Staff staff  = (Staff) user;
            bookings = Booking.getHotelBookings(staff.getHotelId());
        }

        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("/bookings.jsp").forward(request, response);
    }
}
