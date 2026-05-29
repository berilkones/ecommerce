package com.ecommerce.controller;

import com.ecommerce.dao.DashboardDAO;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

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

        DashboardDAO dashboardDAO =
                new DashboardDAO();

        int productCount =
                dashboardDAO.getCount("products");

        int categoryCount =
                dashboardDAO.getCount("categories");

        int userCount =
                dashboardDAO.getCount("users");

        int orderCount =
                dashboardDAO.getCount("orders");

        int pendingOrderCount =
                dashboardDAO.getPendingOrderCount();

        request.setAttribute(
                "productCount",
                productCount
        );

        request.setAttribute(
                "categoryCount",
                categoryCount
        );

        request.setAttribute(
                "userCount",
                userCount
        );

        request.setAttribute(
                "orderCount",
                orderCount
        );

        request.setAttribute(
                "pendingOrderCount",
                pendingOrderCount
        );

        request.getRequestDispatcher(
                "/admin/dashboard.jsp"
        ).forward(request, response);
    }
}