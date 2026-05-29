package com.ecommerce.controller;

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.model.OrderItem;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/order-detail")
public class AdminOrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
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

        OrderDAO orderDAO = new OrderDAO();

        List<OrderItem> itemList =
                orderDAO.getOrderItemsByOrderId(orderId);

        request.setAttribute("itemList", itemList);
        request.setAttribute("orderId", orderId);

        request.getRequestDispatcher("/admin/order-detail.jsp")
                .forward(request, response);
    }
}