package com.booker.servlet;

import com.booker.domain.Booking;
import com.booker.domain.Customer;
import com.booker.domain.Staff;
import com.booker.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookingsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        List<Booking> bookings;
        if (user instanceof Customer) {
            bookings = Booking.getCustomerBookings(user.getId());
        } else if (user instanceof Staff){
            Staff staff  = (Staff) user;
            bookings = Booking.getHotelBookings(staff.getHotelId());
        } else {
            response.sendRedirect("/index.jsp");
            return;
        }
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("/bookings.jsp").forward(request, response);
    }
}
