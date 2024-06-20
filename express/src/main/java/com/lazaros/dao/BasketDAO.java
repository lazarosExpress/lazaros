package com.lazaros.dao;

import com.lazaros.beans.BasketBeans;
import com.lazaros.beans.CustomerOrderProductsBeans;
import com.lazaros.beans.OrdersBeans;
import com.lazaros.beans.ProductBeans;
import com.lazaros.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BasketDAO {
    public List<BasketBeans> getBasketByCustomerId(int customerId) {
        List<BasketBeans> basketList = new ArrayList<>();
        String query = "SELECT b.basket_id, b.basket_qty, b.customer_id, b.product_id, p.product_name, p.product_prize, p.product_imgUrl, s.supplier_shopName "
                +
                "FROM basket b " +
                "JOIN products p ON b.product_id = p.product_id " +
                "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                "WHERE b.customer_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductBeans product = new ProductBeans();
                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_prize(rs.getDouble("product_prize"));
                product.setProduct_imgUrl(rs.getString("product_imgUrl"));
                product.setSupplier_shopName(rs.getString("supplier_shopName"));

                BasketBeans basket = new BasketBeans(
                        rs.getInt("basket_id"),
                        rs.getInt("basket_qty"),
                        rs.getInt("customer_id"),
                        rs.getInt("product_id"),
                        product);
                basketList.add(basket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basketList;
    }

    public BasketBeans getBasketItem(int customerId, int productId) {
        BasketBeans basket = null;
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM basket WHERE customer_id = ? AND product_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                basket = new BasketBeans(
                        rs.getInt("basket_id"),
                        rs.getInt("basket_qty"),
                        rs.getInt("customer_id"),
                        rs.getInt("product_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basket;
    }

    public boolean addBasketItem(BasketBeans basket) {
        boolean rowInserted = false;
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO basket (basket_qty, customer_id, product_id) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, basket.getBasket_qty());
            ps.setInt(2, basket.getCustomer_id());
            ps.setInt(3, basket.getProduct_id());
            rowInserted = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    public boolean increaseQuantity(int productId, int customerId) {
        boolean rowUpdated = false;
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "UPDATE basket SET basket_qty = basket_qty + 1 WHERE customer_id = ? AND product_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerId);
            ps.setInt(2, productId);
            rowUpdated = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public boolean decreaseQuantity(int productId, int customerId) {
        boolean rowUpdated = false;
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "UPDATE basket SET basket_qty = basket_qty - 1 WHERE customer_id = ? AND product_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerId);
            ps.setInt(2, productId);
            rowUpdated = ps.executeUpdate() > 0;

            // Miktar sıfırsa öğeyi sepetten kaldır
            if (rowUpdated) {
                String deleteQuery = "DELETE FROM basket WHERE customer_id = ? AND product_id = ? AND basket_qty = 0";
                PreparedStatement deletePs = connection.prepareStatement(deleteQuery);
                deletePs.setInt(1, customerId);
                deletePs.setInt(2, productId);
                deletePs.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public boolean updateQuantity(int productId, int customerId, int quantity) {
        boolean rowUpdated = false;
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "UPDATE basket SET basket_qty = ? WHERE customer_id = ? AND product_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, quantity);
            ps.setInt(2, customerId);
            ps.setInt(3, productId);
            rowUpdated = ps.executeUpdate() > 0;

            // Miktar sıfırsa öğeyi sepetten kaldır
            if (quantity == 0) {
                String deleteQuery = "DELETE FROM basket WHERE customer_id = ? AND product_id = ?";
                PreparedStatement deletePs = connection.prepareStatement(deleteQuery);
                deletePs.setInt(1, customerId);
                deletePs.setInt(2, productId);
                deletePs.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public boolean deleteBasketItem(int basketId) {
        boolean rowDeleted = false;
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "DELETE FROM basket WHERE basket_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, basketId);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public int createOrder(OrdersBeans order) throws SQLException {
        String sql = "INSERT INTO orders (order_date, order_state, order_totalPrize, customer_id, address_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, order.getOrder_date());
            stmt.setBoolean(2, order.isOrder_state());
            stmt.setDouble(3, order.getOrder_totalPrize());
            stmt.setInt(4, order.getCustomer_id());
            stmt.setInt(5, order.getAddress_id());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return 0;
    }

    public void addOrderProduct(CustomerOrderProductsBeans orderProduct) throws SQLException {
        String sql = "INSERT INTO customer_order_products (order_id, product_id, product_qty) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderProduct.getOrder_id());
            stmt.setInt(2, orderProduct.getProduct_id());
            stmt.setInt(3, orderProduct.getProduct_qty());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public double getBasketTotal(int customerId) throws SQLException {
        String sql = "SELECT SUM(p.product_prize * b.basket_qty) " +
                "FROM basket b " +
                "JOIN products p ON b.product_id = p.product_id " +
                "WHERE b.customer_id = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return 0;
    }

    public void clearBasket(int customerId) throws SQLException {
        String sql = "DELETE FROM basket WHERE customer_id = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
