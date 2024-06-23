package com.lazaros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lazaros.beans.PaymentBeans;
import com.lazaros.utils.DatabaseUtil;

public class PaymentDAO {
    public List<PaymentBeans> getAllPayment() {
        List<PaymentBeans> paymentList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "SELECT payment_id, payment_name FROM payment"; // Alan adlarını kontrol edin
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PaymentBeans payment = new PaymentBeans(
                        rs.getInt("payment_id"),
                        rs.getString("payment_name")
                );
                paymentList.add(payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return paymentList;
    }

    public PaymentBeans getPaymentById(int id) {
        PaymentBeans payment = null;
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "SELECT payment_id, payment_name FROM payment WHERE payment_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                payment = new PaymentBeans(
                        rs.getInt("payment_id"),
                        rs.getString("payment_name")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return payment;
    }
}
