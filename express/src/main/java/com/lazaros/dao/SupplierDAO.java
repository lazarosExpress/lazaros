package com.lazaros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lazaros.beans.SupplierBeans;
import com.lazaros.utils.DatabaseUtil;

public class SupplierDAO {
    public static List<SupplierBeans> getAllSuppliers() {

        List<SupplierBeans> suppliers = new ArrayList<>();

        String query = "SELECT * FROM supplier";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("supplier_id");
                String firstName = resultSet.getString("supplier_firstName");
                String lastName = resultSet.getString("supplier_lastName");
                String shopName = resultSet.getString("supplier_shopName");
                String iban = resultSet.getString("supplier_iban");
                String eMail = resultSet.getString("supplier_eMail");
                String password = resultSet.getString("supplier_password");
                String phoneNumber = resultSet.getString("supplier_phoneNumber");

                SupplierBeans supplier = new SupplierBeans(id, firstName, lastName, shopName, iban, eMail, password,
                        phoneNumber);
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public void insertSupplier(SupplierBeans supplier) {
        String query = "INSERT INTO supplier (supplier_firstName, supplier_lastName, supplier_shopName, supplier_iban, supplier_eMail, supplier_password, supplier_phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, supplier.getSupplier_firstName());
            preparedStatement.setString(2, supplier.getSupplier_lastName());
            preparedStatement.setString(3, supplier.getSupplier_shopName());
            preparedStatement.setString(4, supplier.getSupplier_iban());
            preparedStatement.setString(5, supplier.getSupplier_eMail());
            preparedStatement.setString(6, supplier.getSupplier_password());
            preparedStatement.setString(7, supplier.getSupplier_phoneNumber());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new supplier was inserted successfully!");
            } else {
                System.out.println("Supplier insertion failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public SupplierBeans getSupplierById(int id) {
        SupplierBeans supplier = null;
        String query = "SELECT * FROM supplier WHERE supplier_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String firstName = resultSet.getString("supplier_firstName");
                    String lastName = resultSet.getString("supplier_lastName");
                    String shopName = resultSet.getString("supplier_shopName");
                    String iban = resultSet.getString("supplier_iban");
                    String eMail = resultSet.getString("supplier_eMail");
                    String password = resultSet.getString("supplier_password");
                    String phoneNumber = resultSet.getString("supplier_phoneNumber");

                    supplier = new SupplierBeans(id, firstName, lastName, shopName, iban, eMail, password, phoneNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }

    public boolean updateSupplier(SupplierBeans supplier) {
        String query = "UPDATE supplier SET supplier_firstName = ?, supplier_lastName = ?, supplier_shopName = ?, supplier_iban = ?, supplier_eMail = ?, supplier_password = ?, supplier_phoneNumber = ? WHERE supplier_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, supplier.getSupplier_firstName());
            preparedStatement.setString(2, supplier.getSupplier_lastName());
            preparedStatement.setString(3, supplier.getSupplier_shopName());
            preparedStatement.setString(4, supplier.getSupplier_iban());
            preparedStatement.setString(5, supplier.getSupplier_eMail());
            preparedStatement.setString(6, supplier.getSupplier_password());
            preparedStatement.setString(7, supplier.getSupplier_phoneNumber());
            preparedStatement.setInt(8, supplier.getSupplier_id());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteSupplier(int supplierId) {
        String deleteSupplierQuery = "DELETE FROM supplier WHERE supplier_id = ?";
        String deleteProductQuery = "DELETE FROM products WHERE supplier_id = ?";
        String deleteOrderProductQuery = "DELETE FROM customer_order_products WHERE product_id = ?";
        String selectProductIdsQuery = "SELECT product_id FROM products WHERE supplier_id = ?";

        try (Connection connection = DatabaseUtil.getConnection()) {
            // Otomatik commit özelliğini kapatın, böylece işlemi yönetebilirsiniz
            connection.setAutoCommit(false);

            // 1. Adım: Supplier'a ait tüm product_id'leri alın
            List<Integer> productIds = new ArrayList<>();
            try (PreparedStatement selectProductIdsStmt = connection.prepareStatement(selectProductIdsQuery)) {
                selectProductIdsStmt.setInt(1, supplierId);
                try (ResultSet rs = selectProductIdsStmt.executeQuery()) {
                    while (rs.next()) {
                        productIds.add(rs.getInt("product_id"));
                    }
                }
            }

            // 2. Adım: Alınan product_id'lere göre customer_order_products tablosunda silme
            // işlemi yapın
            try (PreparedStatement deleteOrderProductStmt = connection.prepareStatement(deleteOrderProductQuery)) {
                for (int productId : productIds) {
                    deleteOrderProductStmt.setInt(1, productId);
                    deleteOrderProductStmt.executeUpdate();
                }
            }

            // 3. Adım: Supplier'a ait ürünleri silin
            try (PreparedStatement deleteProductStmt = connection.prepareStatement(deleteProductQuery)) {
                deleteProductStmt.setInt(1, supplierId);
                deleteProductStmt.executeUpdate();
            }

            // 4. Adım: Supplier'ı silin
            try (PreparedStatement deleteSupplierStmt = connection.prepareStatement(deleteSupplierQuery)) {
                deleteSupplierStmt.setInt(1, supplierId);
                deleteSupplierStmt.executeUpdate();
            }

            // İşlem başarılı olduğunda commit yapın
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try (Connection connection = DatabaseUtil.getConnection()) {
                // Eğer bir hata oluşursa işlemi geri alın
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    }

    public boolean loginSupplier(String eMail, String password) {
        String query = "SELECT * FROM supplier WHERE supplier_eMail = ? AND supplier_password = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, eMail);
            preparedStatement.setString(2, password);

            // Log prepared statement parameters
            System.out.println("Executing query: " + preparedStatement.toString());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("User found, login successful.");
                    return true;
                } else {
                    System.out.println("User not found, login failed.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }
    }

    public SupplierBeans getSupplierByEmail(String eMail) {
        SupplierBeans supplier = null;
        String query = "SELECT * FROM supplier WHERE supplier_eMail = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, eMail);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("supplier_id");
                    String firstName = resultSet.getString("supplier_firstName");
                    String lastName = resultSet.getString("supplier_lastName");
                    String shopName = resultSet.getString("supplier_shopName");
                    String iban = resultSet.getString("supplier_iban");
                    String password = resultSet.getString("supplier_password");
                    String phoneNumber = resultSet.getString("supplier_phoneNumber");

                    supplier = new SupplierBeans(id, firstName, lastName, shopName, iban, eMail, password, phoneNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }

    public boolean updateSupplierPassword(String password, int supplierId) {
        String query = "UPDATE supplier SET supplier_password = ? WHERE supplier_id = ?";
        try (Connection connection = DatabaseUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, supplierId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            return false;
        }
    }

}
