package com.ecommerce.dao;

import com.ecommerce.model.OrderItem;
import com.ecommerce.model.Product;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.User;
import com.ecommerce.util.DBConnection;
import com.ecommerce.model.Order;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class OrderDAO {

    public boolean createOrder(User user, List<CartItem> cart, double total) {

        String orderSql =
                "INSERT INTO orders(user_id, total_amount, status) VALUES (?, ?, ?)";

        String itemSql =
                "INSERT INTO order_items(order_id, product_id, quantity, unit_price, subtotal) VALUES (?, ?, ?, ?, ?)";

        String stockSql =
                "UPDATE products SET stock = stock - ? WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement orderPs =
                    conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);

            orderPs.setInt(1, user.getId());
            orderPs.setDouble(2, total);
            orderPs.setString(3, "Beklemede");

            int result = orderPs.executeUpdate();

            if (result == 0) {
                conn.rollback();
                return false;
            }

            ResultSet generatedKeys = orderPs.getGeneratedKeys();

            int orderId;

            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            } else {
                conn.rollback();
                return false;
            }

            PreparedStatement itemPs =
                    conn.prepareStatement(itemSql);

            for (CartItem item : cart) {

                double subtotal =
                        item.getProduct().getPrice()
                                * item.getQuantity();

                itemPs.setInt(1, orderId);
                itemPs.setInt(2, item.getProduct().getId());
                itemPs.setInt(3, item.getQuantity());
                itemPs.setDouble(4, item.getProduct().getPrice());
                itemPs.setDouble(5, subtotal);

                itemPs.addBatch();
            }
            PreparedStatement stockPs =
                    conn.prepareStatement(stockSql);

            for (CartItem item : cart) {

                stockPs.setInt(1, item.getQuantity());
                stockPs.setInt(2, item.getProduct().getId());

                stockPs.addBatch();
            }

            stockPs.executeBatch();

            itemPs.executeBatch();

            conn.commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> orderList = new ArrayList<>();

        String sql =
                "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = new Order();

                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));

                orderList.add(order);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return orderList;
    }

    public List<Order> getAllOrders() {

        List<Order> orderList = new ArrayList<>();

        String sql = "SELECT * FROM orders ORDER BY order_date DESC";

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = new Order();

                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));

                orderList.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderList;
    }
    public boolean updateOrderStatus(int orderId, String status) {

        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, orderId);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {

        List<OrderItem> itemList = new ArrayList<>();

        String sql =
                "SELECT oi.*, p.name, p.description, p.image_url " +
                        "FROM order_items oi " +
                        "JOIN products p ON oi.product_id = p.id " +
                        "WHERE oi.order_id = ?";

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                OrderItem item = new OrderItem();

                item.setId(rs.getInt("id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getDouble("unit_price"));
                item.setSubtotal(rs.getDouble("subtotal"));

                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setImageUrl(rs.getString("image_url"));

                item.setProduct(product);

                itemList.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemList;
    }
}