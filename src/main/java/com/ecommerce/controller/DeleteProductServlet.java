package com.ecommerce.controller;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/delete-product")
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession();

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null ||
                !"ADMIN".equals(loggedInUser.getRole())) {

            response.sendRedirect("../login");

            return;
        }

        int productId =
                Integer.parseInt(
                        request.getParameter("id")
                );

        ProductDAO productDAO =
                new ProductDAO();

        productDAO.deleteProduct(productId);

        response.sendRedirect("products");
    }
}