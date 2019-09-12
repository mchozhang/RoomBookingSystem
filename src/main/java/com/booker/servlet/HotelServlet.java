package com.booker.servlet;

import com.booker.database.HotelMapper;
import com.booker.database.ServiceMapper;
import com.booker.database.impl.HotelMapperImpl;
import com.booker.database.impl.ServiceMapperImpl;
import com.booker.domain.Hotel;
import com.booker.domain.Service;
import com.booker.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HotelServlet extends HttpServlet {
    private HotelMapper hotelMapper;
    private ServiceMapper serviceMapper;

    public HotelServlet() {
        super();
        hotelMapper = new HotelMapperImpl();
        serviceMapper = new ServiceMapperImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int hotelId = 0;
        User user = (User) request.getSession().getAttribute("user");
        try {
            hotelId = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            // no hotel id found
            request.getRequestDispatcher("/hotelListServlet").forward(request, response);
            return;
        }

        Hotel hotel = hotelMapper.findHotelById(hotelId);
        List<Service> services = serviceMapper.findServicesByHotelId(hotelId);

        // set attributes
        request.setAttribute("user", user);
        request.setAttribute("hotel", hotel);
        request.setAttribute("services", services);
        request.getRequestDispatcher("/hotel.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
