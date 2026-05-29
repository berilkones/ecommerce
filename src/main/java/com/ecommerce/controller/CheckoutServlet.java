package com.ecommerce.controller;

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.User;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            response.sendRedirect("login");
            return;
        }

        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart");
            return;
        }

        ProductDAO productDAO = new ProductDAO();

        for (CartItem item : cart) {

            Product currentProduct =
                    productDAO.getProductById(item.getProduct().getId());

            if (currentProduct == null ||
                    item.getQuantity() > currentProduct.getStock()) {

                session.setAttribute(
                        "cartError",
                        "Sepetteki bazı ürünler için yeterli stok yok."
                );

                response.sendRedirect("cart");
                return;
            }
        }

        double total = 0;

        for (CartItem item : cart) {
            total += item.getProduct().getPrice()
                    * item.getQuantity();
        }

        OrderDAO orderDAO = new OrderDAO();

        boolean success =
                orderDAO.createOrder(loggedInUser, cart, total);

        if (success) {
            session.removeAttribute("cart");
            response.sendRedirect("order-success.jsp");
        } else {
            session.setAttribute(
                    "cartError",
                    "Sipariş oluşturulurken hata oluştu."
            );
            response.sendRedirect("cart");
        }
    }
}