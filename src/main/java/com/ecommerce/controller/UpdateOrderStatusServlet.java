package com.ecommerce.controller;

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/update-order-status")
public class UpdateOrderStatusServlet extends HttpServlet {

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

        int orderId =
                Integer.parseInt(request.getParameter("id"));

        String status =
                request.getParameter("status");

        OrderDAO orderDAO = new OrderDAO();

        orderDAO.updateOrderStatus(orderId, status);

        response.sendRedirect("orders");
    }
}