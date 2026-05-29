package com.ecommerce.dao;

import java.util.List;
import java.util.ArrayList;
import com.ecommerce.model.User;
import com.ecommerce.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public boolean emailExists(String email) {
        String sql = "SELECT id FROM users WHERE email =?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users(full_name,email,password,phone,address,role) VALUES(?,?,?,?,?,?)";

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getAddress());
            ps.setString(6,"CUSTOMER");

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public User loginUser(String email, String password) {

        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        String sql =
                "SELECT * FROM users ORDER BY id";

        try {

            Connection conn =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));

                userList.add(user);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return userList;
    }
}
