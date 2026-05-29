package com.ecommerce.controller;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;
import com.ecommerce.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {

            request.setAttribute(
                    "error",
                    "E-posta ve şifre zorunludur."
            );

            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);

            return;
        }
        String hashedPassword =
                PasswordUtil.hashPassword(password);

        UserDAO userDAO = new UserDAO();

        User user = userDAO.loginUser(email, hashedPassword);

        if (user != null) {

            HttpSession session = request.getSession();

            session.setAttribute("loggedInUser", user);

            response.sendRedirect("products");

        } else {

            request.setAttribute(
                    "error",
                    "E-posta veya şifre hatalı."
            );

            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);
        }
    }
}