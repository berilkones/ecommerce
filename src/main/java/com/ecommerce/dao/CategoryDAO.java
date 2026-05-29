package com.ecommerce.dao;

import com.ecommerce.model.Category;
import com.ecommerce.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public List<Category> getAllCategories() {

        List<Category> categoryList = new ArrayList<>();

        String sql = "SELECT * FROM categories ORDER BY id";

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Category category = new Category();

                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setActive(rs.getBoolean("is_active"));

                categoryList.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return categoryList;
    }
    public boolean addCategory(Category category) {

        String sql =
                "INSERT INTO categories(name, description, is_active) VALUES (?, ?, ?)";

        try {

            Connection conn =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setBoolean(3, category.isActive());

            int result =
                    ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
    public boolean deactivateCategory(int categoryId) {

        String sql =
                "UPDATE categories SET is_active = false WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, categoryId);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}