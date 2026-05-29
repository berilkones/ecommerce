package com.ecommerce.controller;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;
import com.ecommerce.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/admin-login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String hashedPassword =
                PasswordUtil.hashPassword(password);

        UserDAO userDAO = new UserDAO();

        User user =
                userDAO.loginUser(email, hashedPassword);

        if (user != null && "ADMIN".equals(user.getRole())) {

            HttpSession session = request.getSession();

            session.setAttribute("loggedInUser", user);

            response.sendRedirect(request.getContextPath() + "/admin/dashboard");

        } else {

            request.setAttribute(
                    "error",
                    "Admin bilgileri hatalı."
            );

            request.getRequestDispatcher("/admin-login.jsp")
                    .forward(request, response);
        }
    }
}