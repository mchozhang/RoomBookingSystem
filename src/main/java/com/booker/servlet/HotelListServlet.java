package com.booker.servlet;

import com.booker.domain.Customer;
import com.booker.domain.Hotel;
import com.booker.domain.Staff;
import com.booker.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HotelListServlet extends HttpServlet {

    public HotelListServlet(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        if (user instanceof Customer) {
            System.out.println("customer");
        } else if (user instanceof Staff){
            System.out.println("staff");
        } else {
            response.sendRedirect("/index.jsp");
            return;
        }

        List<Hotel> hotels =  Hotel.getHotelList();

        // set attributes to jsp
        request.setAttribute("hotels", hotels);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/hotel_list.jsp").forward(request, response);
    }
}