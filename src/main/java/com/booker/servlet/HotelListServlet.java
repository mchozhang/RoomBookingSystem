package com.booker.servlet;

<<<<<<< HEAD
=======
import com.booker.database.HotelMapper;
import com.booker.database.impl.HotelMapperImpl;
>>>>>>> ef5fa25ec4ac063113172a2f242b810f74ff20f3
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
<<<<<<< HEAD

    public HotelListServlet(){
        super();
=======
    private HotelMapper hotelMapper;

    public HotelListServlet(){
        super();
        hotelMapper = new HotelMapperImpl();
>>>>>>> ef5fa25ec4ac063113172a2f242b810f74ff20f3
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
<<<<<<< HEAD

        List<Hotel> hotels =  Hotel.getHotelList();

        // set attributes to jsp
=======
        List<Hotel> hotels =  hotelMapper.findAllHotels();
>>>>>>> ef5fa25ec4ac063113172a2f242b810f74ff20f3
        request.setAttribute("hotels", hotels);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/hotel_list.jsp").forward(request, response);
    }
}