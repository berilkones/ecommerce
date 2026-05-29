package com.ecommerce.controller;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.model.Category;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/categories")
public class AdminCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null ||
                !"ADMIN".equals(loggedInUser.getRole())) {

            response.sendRedirect("../login");
            return;
        }

        CategoryDAO categoryDAO =
                new CategoryDAO();

        List<Category> categoryList =
                categoryDAO.getAllCategories();

        request.setAttribute(
                "categoryList",
                categoryList
        );

        request.getRequestDispatcher("/admin/categories.jsp")
                .forward(request, response);
    }
}