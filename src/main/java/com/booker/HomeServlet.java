package com.booker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeServlet extends HttpServlet {
    public HomeServlet() {
        super();
        System.out.println("!11");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("home servlet");
        String path = request.getServletPath();
        request.setAttribute("title", "Title of our application");
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
