package com.ecommerce.controller;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        ProductDAO productDAO = new ProductDAO();

        String categoryIdParam =
                request.getParameter("categoryId");

        String keyword =
                request.getParameter("keyword");

        List<Product> productList;

        if (keyword != null && !keyword.trim().isEmpty()) {

            productList =
                    productDAO.searchProducts(keyword);

        } else if (categoryIdParam != null &&
                !categoryIdParam.isEmpty()) {

            int categoryId =
                    Integer.parseInt(categoryIdParam);

            productList =
                    productDAO.getProductsByCategoryId(categoryId);

        } else {

            productList =
                    productDAO.getAllProducts();
        }

        request.setAttribute("productList", productList);

        request.getRequestDispatcher("products.jsp")
                .forward(request, response);
    }
}

