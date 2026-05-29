package com.ecommerce.controller;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/products")
public class AdminProductServlet extends HttpServlet {

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

        ProductDAO productDAO = new ProductDAO();

        List<Product> productList =
                productDAO.getAllProducts();

        request.setAttribute("productList", productList);

        request.getRequestDispatcher("/admin/products.jsp")
                .forward(request, response);
    }
}