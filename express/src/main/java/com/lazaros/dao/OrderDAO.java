package com.lazaros.dao;

import com.lazaros.beans.OrdersBeans;
import com.lazaros.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public List<OrdersBeans> getOngoingOrders() {
        List<OrdersBeans> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE order_state = false";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrdersBeans order = new OrdersBeans(
                        rs.getInt("order_id"),
                        rs.getDate("order_date"),
                        rs.getBoolean("order_state"),
                        rs.getDouble("order_totalPrize"),
                        rs.getInt("customer_id"),
                        rs.getInt("address_id"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<OrdersBeans> getCompletedOrders() {
        List<OrdersBeans> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE order_state = true";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrdersBeans order = new OrdersBeans(
                        rs.getInt("order_id"),
                        rs.getDate("order_date"),
                        rs.getBoolean("order_state"),
                        rs.getDouble("order_totalPrize"),
                        rs.getInt("customer_id"),
                        rs.getInt("address_id"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<OrdersBeans> getOrdersByCustomerId(int customerId) {
        List<OrdersBeans> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customer_id = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrdersBeans order = new OrdersBeans(
                        rs.getInt("order_id"),
                        rs.getDate("order_date"),
                        rs.getBoolean("order_state"),
                        rs.getDouble("order_totalPrize"),
                        rs.getInt("customer_id"),
                        rs.getInt("address_id"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
