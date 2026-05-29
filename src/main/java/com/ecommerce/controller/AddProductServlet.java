package com.ecommerce.controller;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/add-product")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/add-product.jsp")
                .forward(request, response);
    }

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

        int categoryId =
                Integer.parseInt(request.getParameter("categoryId"));

        String name = request.getParameter("name");
        String description = request.getParameter("description");

        double price =
                Double.parseDouble(request.getParameter("price"));


        int stock =
                Integer.parseInt(request.getParameter("stock"));

        if (price <= 0 || stock < 0) {

            request.setAttribute(
                    "error",
                    "Fiyat ve stok değerleri geçersiz."
            );

            request.getRequestDispatcher("/add-product.jsp")
                    .forward(request, response);

            return;
        }

        String imageUrl =
                request.getParameter("imageUrl");

        Product product = new Product();

        product.setCategoryId(categoryId);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setImageUrl(imageUrl);
        product.setActive(true);

        ProductDAO productDAO = new ProductDAO();

        boolean success =
                productDAO.addProduct(product);

        if (success) {
            response.sendRedirect("products");
        } else {
            request.setAttribute("error", "Ürün eklenemedi.");
            request.getRequestDispatcher("/add-product.jsp")
                    .forward(request, response);
        }
    }
}