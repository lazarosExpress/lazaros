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

    public List<OrdersBeans> getOrderDetailsById(int orderId) {
        List<OrdersBeans> orderDetails = new ArrayList<>();
        String sql = "SELECT o.*, p.product_name, p.product_imgUrl, p.product_prize, op.product_qty, s.supplier_shopName, s.supplier_id, c.customer_firstName "
                +
                "FROM orders o " +
                "JOIN customer_order_products op ON o.order_id = op.order_id " +
                "JOIN products p ON op.product_id = p.product_id " +
                "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                "JOIN customer c ON c.customer_id = s.customer_id " +
                "WHERE o.order_id = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrdersBeans detail = new OrdersBeans(
                        rs.getInt("order_id"),
                        rs.getDate("order_date"),
                        rs.getBoolean("order_state"),
                        rs.getDouble("order_totalPrize"),
                        rs.getInt("customer_id"),
                        rs.getInt("address_id"),
                        rs.getString("product_name"),
                        rs.getString("product_imgUrl"),
                        rs.getDouble("product_prize"),
                        rs.getInt("product_qty"),
                        rs.getString("supplier_shopName"),
                        rs.getInt("supplier_id"),
                        rs.getString("customer_firstName"));
                orderDetails.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }
}
