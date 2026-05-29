package com.ecommerce.controller;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;
import com.ecommerce.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("register.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (fullName == null || fullName.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                password == null || password.length() < 6) {

            request.setAttribute(
                    "error",
                    "Geçerli bilgiler giriniz. Şifre en az 6 karakter olmalıdır."
            );

            request.getRequestDispatcher("register.jsp")
                    .forward(request, response);

            return;
        }
        String hashedPassword =
                PasswordUtil.hashPassword(password);
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        UserDAO userDAO = new UserDAO();

        if (userDAO.emailExists(email)) {

            request.setAttribute(
                    "error",
                    "Bu e-posta adresi zaten kayıtlı."
            );

            request.getRequestDispatcher("register.jsp")
                    .forward(request, response);

            return;
        }

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setPhone(phone);
        user.setAddress(address);

        boolean success = userDAO.registerUser(user);

        if (success) {
            response.sendRedirect("login");
        } else {
            request.setAttribute(
                    "error",
                    "Kayıt sırasında bir hata oluştu."
            );

            request.getRequestDispatcher("register.jsp")
                    .forward(request, response);
        }
    }
}