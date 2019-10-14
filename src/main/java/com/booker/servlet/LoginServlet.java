package com.booker.servlet;

import com.booker.domain.Staff;
import com.booker.domain.User;
import com.booker.util.session.AppSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // go to main page if logged in
        if (AppSession.isAuthenticated()) {
            if (AppSession.hasRole(AppSession.CUSTOMER_ROLE)) {
                // redirect customer to hotel list page
                response.sendRedirect("/hotelListServlet");
            } else if(AppSession.hasRole(AppSession.STAFF_ROLE)) {
                // redirect staff to his hotel page
                Staff staff = (Staff)AppSession.getUser();
                response.sendRedirect("/hotelServlet?id=" + staff.getHotelId());
            }
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // handle user authentication
        User user = User.authenticate(username, password);
        if (user != null) {
            if (AppSession.hasRole(AppSession.CUSTOMER_ROLE)) {
                // redirect customer to hotel list page
                response.sendRedirect("/hotelListServlet");
            } else if(AppSession.hasRole(AppSession.STAFF_ROLE)) {
                // redirect staff to his hotel page
                Staff staff = (Staff)user;
                response.sendRedirect("/hotelServlet?id=" + staff.getHotelId());
            }
        } else {
            request.setAttribute("errorMessage", "Either username or password is wrong.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
