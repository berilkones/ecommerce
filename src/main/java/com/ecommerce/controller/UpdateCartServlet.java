package com.ecommerce.controller;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/update-cart")
public class UpdateCartServlet extends HttpServlet {

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

        int productId = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        if (quantity <= 0) {

            session.setAttribute(
                    "cartError",
                    "Ürün adedi en az 1 olmalıdır."
            );

            response.sendRedirect("cart");

            return;
        }


        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart != null) {
            for (CartItem item : cart) {
                if (item.getProduct().getId() == productId) {
                    if (quantity <= item.getProduct().getStock()) {

                        item.setQuantity(quantity);

                    } else {

                        session.setAttribute(
                                "cartError",
                                "Stok miktarından fazla ürün seçemezsiniz."
                        );

                        response.sendRedirect("cart");
                        return;
                    }
                    break;
                }
            }
        }

        session.setAttribute("cart", cart);

        response.sendRedirect("cart");
    }
}