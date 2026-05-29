package com.ecommerce.controller;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product-detail")
public class ProductDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws ServletException, IOException{

        int id = Integer.parseInt(request.getParameter("id"));
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(id);
        request.setAttribute("product", product);
        request.getRequestDispatcher(("product-detail.jsp"))
                .forward(request,response);

    }
}