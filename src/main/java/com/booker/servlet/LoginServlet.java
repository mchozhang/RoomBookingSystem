package com.booker.servlet;

import com.booker.domain.Customer;
import com.booker.domain.Staff;
import com.booker.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = User.authenticate(username, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            if (user instanceof Customer) {
                response.sendRedirect("/hotelListServlet");

            } else if(user instanceof Staff) {
                response.sendRedirect("/hotelServlet");

            }
        } else {
            request.setAttribute("errorMessage", "Either username or password is wrong.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
