package com.ecommerce.controller;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

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

        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);


        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean productExists = false;

        for (CartItem item : cart) {
            if (item.getProduct().getId() == productId) {

                if (item.getQuantity() < item.getProduct().getStock()) {
                    item.setQuantity(item.getQuantity() + 1);
                } else {
                    session.setAttribute(
                            "cartError",
                            "Bu üründen stok miktarından fazla ekleyemezsiniz."
                    );
                }

                productExists = true;
                break;
            }
        }

        if (!productExists) {

            if (product.getStock() > 0) {
                CartItem cartItem = new CartItem(product, 1);
                cart.add(cartItem);
            } else {
                session.setAttribute(
                        "cartError",
                        "Bu ürün stokta yok."
                );
            }
        }

        session.setAttribute("cart", cart);

        response.sendRedirect("products");
    }
}