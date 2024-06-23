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
                        rs.getDouble("order_totalPrice"),
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
                        rs.getDouble("order_totalPrice"),
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
        String sql = "SELECT o.*, a.address_customerFirstName, a.address_customerLastName " +
                "FROM orders o " +
                "JOIN address a ON o.address_id = a.address_id " +
                "WHERE o.customer_id = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrdersBeans order = new OrdersBeans(
                        rs.getInt("order_id"),
                        rs.getDate("order_date"),
                        rs.getBoolean("order_state"),
                        rs.getDouble("order_totalPrice"),
                        rs.getInt("customer_id"),
                        rs.getInt("address_id"),
                        rs.getString("address_customerFirstName"),
                        rs.getString("address_customerLastName"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<OrdersBeans> getOrdersBySupplierId(int supplierId) {
        List<OrdersBeans> orders = new ArrayList<>();
        String sql = "SELECT o.order_id, o.order_date, o.order_state, " +
                     "SUM(p.product_price * cop.product_qty) AS order_totalPrice, " +
                     "a.address_customerFirstName, a.address_customerLastName, " +
                     "p.supplier_id " +
                     "FROM orders o " +
                     "JOIN address a ON o.address_id = a.address_id " +
                     "JOIN customer_order_products cop ON o.order_id = cop.order_id " +
                     "JOIN products p ON cop.product_id = p.product_id " +
                     "WHERE p.supplier_id = ? " +
                     "GROUP BY o.order_id, o.order_date, o.order_state, " +
                     "a.address_customerFirstName, a.address_customerLastName, p.supplier_id";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, supplierId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrdersBeans order = new OrdersBeans(
                        rs.getInt("order_id"),
                        rs.getInt("supplier_id"),
                        rs.getDate("order_date"),
                        rs.getBoolean("order_state"),
                        rs.getDouble("order_totalPrice"),
                        rs.getString("address_customerFirstName"),
                        rs.getString("address_customerLastName"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    

    public List<OrdersBeans> getOrderDetailsById(int orderId) {
        List<OrdersBeans> orderDetails = new ArrayList<>();
        String sql = "SELECT o.*, p.product_name, p.product_imgUrl, p.product_price, op.product_qty, s.supplier_shopName, s.supplier_id, c.customer_firstName "
                +
                "FROM orders o " +
                "JOIN customer_order_products op ON o.order_id = op.order_id " +
                "JOIN products p ON op.product_id = p.product_id " +
                "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                "JOIN customer c ON c.customer_id = o.customer_id " +
                "WHERE o.order_id = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrdersBeans detail = new OrdersBeans(
                        rs.getInt("order_id"),
                        rs.getDate("order_date"),
                        rs.getBoolean("order_state"),
                        rs.getDouble("order_totalPrice"),
                        rs.getInt("customer_id"),
                        rs.getInt("address_id"),
                        rs.getString("product_name"),
                        rs.getString("product_imgUrl"),
                        rs.getDouble("product_price"),
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
    public List<OrdersBeans> getOrderManagementDetailsById(int orderId, int supplierId) {
        List<OrdersBeans> orderDetails = new ArrayList<>();
        String sql = "SELECT o.*, p.product_name, p.product_imgUrl, p.product_price, op.product_qty, s.supplier_shopName, s.supplier_id, c.customer_firstName "
                +
                "FROM orders o " +
                "JOIN customer_order_products op ON o.order_id = op.order_id " +
                "JOIN products p ON op.product_id = p.product_id " +
                "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                "JOIN customer c ON c.customer_id = o.customer_id " +
                "WHERE o.order_id = ? AND p.supplier_id = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, supplierId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrdersBeans detail = new OrdersBeans(
                        rs.getInt("order_id"),
                        rs.getDate("order_date"),
                        rs.getBoolean("order_state"),
                        rs.getDouble("order_totalPrice"),
                        rs.getInt("customer_id"),
                        rs.getInt("address_id"),
                        rs.getString("product_name"),
                        rs.getString("product_imgUrl"),
                        rs.getDouble("product_price"),
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
