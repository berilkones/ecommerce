package com.ecommerce.dao;

import com.ecommerce.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardDAO {

    public int getCount(String tableName) {

        String sql = "SELECT COUNT(*) FROM " + tableName;

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getPendingOrderCount() {

        String sql = "SELECT COUNT(*) FROM orders WHERE status = 'Beklemede'";

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}