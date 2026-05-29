package com.ecommerce.dao;

import com.ecommerce.model.Product;
import com.ecommerce.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getAllProducts() {

        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setId(rs.getInt("id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setImageUrl(rs.getString("image_url"));
                product.setActive(rs.getBoolean("is_active"));

                productList.add(product);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }


        return productList;
    }

    public Product getProductById(int id){
        Product product = null;
        String sql = "SELECT * FROM products WHERE id = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                product = new Product();

                product.setId(rs.getInt("id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("Stock"));
                product.setImageUrl(rs.getString("image_url"));
                product.setActive(rs.getBoolean("is_active"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }

    public boolean addProduct(Product product) {

        String sql =
                "INSERT INTO products(category_id, name, description, price, stock, image_url, is_active) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getStock());
            ps.setString(6, product.getImageUrl());
            ps.setBoolean(7, product.isActive());

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteProduct(int productId) {

        String sql =
                "DELETE FROM products WHERE id = ?";

        try {

            Connection conn =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, productId);

            int result =
                    ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
    public boolean updateProduct(Product product) {

        String sql =
                "UPDATE products SET category_id = ?, name = ?, description = ?, price = ?, stock = ?, image_url = ?, is_active = ? WHERE id = ?";

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getStock());
            ps.setString(6, product.getImageUrl());
            ps.setBoolean(7, product.isActive());
            ps.setInt(8, product.getId());

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<Product> getProductsByCategoryId(int categoryId) {

        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE category_id = ? AND is_active = true";

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setId(rs.getInt("id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setImageUrl(rs.getString("image_url"));
                product.setActive(rs.getBoolean("is_active"));

                productList.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }
    public List<Product> searchProducts(String keyword) {

        List<Product> productList = new ArrayList<>();

        String sql =
                "SELECT * FROM products " +
                        "WHERE LOWER(name) LIKE ? " +
                        "OR LOWER(description) LIKE ?";

        try {

            Connection conn =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            String searchKeyword =
                    "%" + keyword.toLowerCase() + "%";

            ps.setString(1, searchKeyword);
            ps.setString(2, searchKeyword);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setId(rs.getInt("id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setImageUrl(rs.getString("image_url"));
                product.setActive(rs.getBoolean("is_active"));
                product.setCreatedAt(rs.getTimestamp("created_at"));

                productList.add(product);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return productList;
    }

}