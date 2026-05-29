package com.ecommerce.controller;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {

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

        int productId = Integer.parseInt(request.getParameter("id"));


        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart != null) {
            cart.removeIf(item ->
                    item.getProduct().getId() == productId);
        }

        session.setAttribute("cart", cart);

        response.sendRedirect("cart");
    }
}