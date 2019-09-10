package com.booker.servlet;

import com.booker.database.HotelMapper;
import com.booker.database.impl.HotelMapperImpl;
import com.booker.domain.Hotel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeServlet extends HttpServlet {
    private  HotelMapper hotelMapper;
    public HomeServlet() {
        super();
        hotelMapper = new HotelMapperImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hotel hotel = hotelMapper.findHotelByName("Marriott");
        System.out.println(hotel.toString());
        String path = request.getServletPath();
        request.setAttribute("title", "Title of our application");
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
