package com.booker.servlet;

import com.booker.domain.User;
import com.booker.util.AppSession;
import com.booker.util.DTOAssembler;
import com.booker.util.UserInfoDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!AppSession.isAuthenticated()){
            response.sendRedirect("/loginServlet");
            return;
        }
        User user  = AppSession.getUser();
        UserInfoDTO dto = DTOAssembler.writeDTO(user);
        response.getWriter().write(dto.toJsonString());
        response.getWriter().close();
    }
}
