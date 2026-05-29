package com.ecommerce.controller;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.model.Category;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/add-category")
public class AddCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
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

        String name =
                request.getParameter("name");

        String description =
                request.getParameter("description");

        if (name == null || name.trim().isEmpty()) {

            response.sendRedirect("categories");
            return;
        }

        Category category =
                new Category();

        category.setName(name);
        category.setDescription(description);
        category.setActive(true);

        CategoryDAO categoryDAO =
                new CategoryDAO();

        boolean success =
                categoryDAO.addCategory(category);

        response.sendRedirect("categories");
    }
}