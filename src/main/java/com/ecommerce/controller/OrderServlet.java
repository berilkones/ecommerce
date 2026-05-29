package com.ecommerce.controller;

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.model.Order;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession();

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            response.sendRedirect("login");

            return;
        }

        OrderDAO orderDAO = new OrderDAO();

        List<Order> orderList =
                orderDAO.getOrdersByUserId(
                        loggedInUser.getId()
                );

        request.setAttribute(
                "orderList",
                orderList
        );

        request.getRequestDispatcher("orders.jsp")
                .forward(request, response);
    }
}