package com.ecommerce.controller.admin;

import com.ecommerce.dao.CategoryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/delete-category")
public class DeleteCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int categoryId =
                Integer.parseInt(request.getParameter("id"));

        CategoryDAO categoryDAO =
                new CategoryDAO();

        categoryDAO.deactivateCategory(categoryId);

        response.sendRedirect(
                request.getContextPath() + "/admin/categories"
        );
    }
}