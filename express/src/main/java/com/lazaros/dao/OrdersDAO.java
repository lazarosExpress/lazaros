package com.lazaros.dao;

import com.lazaros.beans.CustomerOrderProductsBeans;
import com.lazaros.beans.OrdersBeans;
import com.lazaros.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO {

    public int createOrder(OrdersBeans order) {
        int orderId = -1;
        String query = "INSERT INTO orders (order_date, order_state, customer_id, order_totalPrize) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, order.getOrder_date());
            ps.setBoolean(2, order.isOrder_state());
            ps.setInt(3, order.getCustomer_id());
            ps.setDouble(4, order.getOrder_totalPrize());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error creating order: " + e.getMessage());
            e.printStackTrace();
        }
        return orderId;
    }

    public boolean addOrderProduct(CustomerOrderProductsBeans orderProduct) {
        boolean rowInserted = false;
        String query = "INSERT INTO customer_order_products (order_id, product_id, product_qty) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderProduct.getOrder_id());
            ps.setInt(2, orderProduct.getProduct_id());
            ps.setInt(3, orderProduct.getProduct_qty());
            rowInserted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding order product: " + e.getMessage());
            e.printStackTrace();
        }
        return rowInserted;
    }

    public OrdersBeans getOrderItem(int customerId, int productId) {
        OrdersBeans order = null;
        String query = "SELECT * FROM orders WHERE customer_id = ? AND product_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, customerId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = new OrdersBeans(
                        rs.getInt("order_id"),
                        rs.getDate("order_date"),
                        rs.getBoolean("order_state"),
                        rs.getInt("customer_id"),
                        rs.getDouble("order_totalPrize"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting order item: " + e.getMessage());
            e.printStackTrace();
        }
        return order;
    }

    public boolean increaseQuantity(int productId, int customerId) {
        boolean success = false;
        String query = "UPDATE customer_order_products SET product_qty = product_qty + 1 WHERE order_id = (SELECT order_id FROM orders WHERE customer_id = ? AND product_id = ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, customerId);
            ps.setInt(2, productId);
            success = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error increasing quantity: " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

    public boolean decreaseQuantity(int productId, int customerId) {
        boolean success = false;
        String query = "UPDATE customer_order_products SET product_qty = product_qty - 1 WHERE order_id = (SELECT order_id FROM orders WHERE customer_id = ? AND product_id = ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, customerId);
            ps.setInt(2, productId);
            success = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error decreasing quantity: " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

    public boolean deleteOrderItem(int orderId) {
        boolean success = false;
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderId);
            success = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting order item: " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

    public List<OrdersBeans> getOrdersByCustomerId(int customerId) {
        List<OrdersBeans> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE customer_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrdersBeans order = new OrdersBeans(
                        rs.getInt("order_id"),
                        rs.getDate("order_date"),
                        rs.getBoolean("order_state"),
                        rs.getInt("customer_id"),
                        rs.getDouble("order_totalPrize"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.err.println("Error getting orders by customer ID: " + e.getMessage());
            e.printStackTrace();
        }
        return orders;
    }
}
