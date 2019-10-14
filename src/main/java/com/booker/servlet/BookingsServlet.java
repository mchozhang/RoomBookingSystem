package com.booker.servlet;

import com.booker.domain.Booking;
import com.booker.domain.Staff;
import com.booker.domain.User;
import com.booker.util.session.AppSession;

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
        if (!AppSession.isAuthenticated()) {
            response.sendRedirect("/loginServlet");
            return;
        }
        User user = AppSession.getUser();
        String bookingId = request.getParameter("id");
        String action = request.getParameter("action");
        String startDate = request.getParameter("sd");
        String endDate = request.getParameter("ed");

        // confirm booking
        if (AppSession.hasRole(AppSession.STAFF_ROLE) && action.equals("confirm")) {
            boolean result = Booking.confirmBooking(Integer.parseInt(bookingId));
            if (result) {
                response.getWriter().write("success");
            } else {
                response.getWriter().write("Failed to confirmed booking");
            }
        }
        if (AppSession.hasRole(AppSession.CUSTOMER_ROLE)) {
            // customer can edit and delete booking
            if (action.equals("edit")) {
                boolean result = Booking.updateBooking(Integer.parseInt(bookingId), startDate, endDate);
                if (result) {
                    response.getWriter().write("success");
                } else {
                    response.getWriter().write("That time has been reserved.");
                }
            } else if (action.equals("delete")) {
                boolean result = Booking.deleteBooking(Integer.parseInt(bookingId));
                if (result) {
                    response.getWriter().write("success");
                } else {
                    response.getWriter().write("Failed to delete booking");
                }
            }
        }
        response.getWriter().close();
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
        } else if (AppSession.hasRole(AppSession.STAFF_ROLE)) {
            Staff staff = (Staff) user;
            bookings = Booking.getHotelBookings(staff.getHotelId());
        }

        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("/bookings.jsp").forward(request, response);
    }
}
