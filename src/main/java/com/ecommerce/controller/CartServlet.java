package com.ecommerce.controller;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.IOException;

import com.ecommerce.model.CartItem;
import java.util.List;

@WebServlet("/cart")

public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
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

        double total = 0;

        if (cart != null) {
            for (CartItem item : cart) {
                total += item.getProduct().getPrice()
                        * item.getQuantity();
            }
        }

        request.setAttribute("cart", cart);
        request.setAttribute("total", total);

        request.getRequestDispatcher("cart.jsp")
                .forward(request, response);
    }
}
