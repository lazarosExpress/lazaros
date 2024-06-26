package com.lazaros.dao;

import com.lazaros.beans.CustomerBeans;
import com.lazaros.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private static final Connection connection = null;

    public List<CustomerBeans> getAllCustomers() {
        List<CustomerBeans> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                String customerEMail = resultSet.getString("customer_eMail");
                String firstName = resultSet.getString("customer_firstName");
                String lastName = resultSet.getString("customer_lastName");
                String number = resultSet.getString("customer_phoneNumber");

                CustomerBeans customer = new CustomerBeans(customerId, customerEMail, firstName, lastName, number,
                        number);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void insertCustomer(CustomerBeans customer) {
        String query = "INSERT INTO customer (customer_eMail, customer_firstName, customer_lastName, customer_password, customer_phoneNumber) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, customer.getCustomer_eMail());
            preparedStatement.setString(2, customer.getCustomer_firstName());
            preparedStatement.setString(3, customer.getCustomer_lastName());
            preparedStatement.setString(4, customer.getCustomer_password());
            preparedStatement.setString(5, customer.getCustomer_phoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CustomerBeans getCustomerById(int id) {
        CustomerBeans customer = null;
        String query = "SELECT * FROM customer WHERE customer_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String eMail = resultSet.getString("customer_eMail");
                    String firstName = resultSet.getString("customer_firstName");
                    String lastName = resultSet.getString("customer_lastName");
                    String password = resultSet.getString("customer_password");
                    String number = resultSet.getString("customer_phoneNumber");

                    customer = new CustomerBeans(id, eMail, firstName, lastName, password, number);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public boolean updateCustomerWithSupplier(CustomerBeans customer) {
        String query = "UPDATE customer SET customer_firstName = ?, customer_lastName = ?, customer_phoneNumber = ? WHERE customer_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, customer.getCustomer_firstName());
            preparedStatement.setString(2, customer.getCustomer_lastName());
            preparedStatement.setString(3, customer.getCustomer_phoneNumber());
            preparedStatement.setInt(4, customer.getCustomer_id());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomerPassword(String password, int customerId) {
        String query = "UPDATE customer SET customer_password = ? WHERE customer_id = ?";
        try (Connection connection = DatabaseUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, customerId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public void deleteCustomer(int id) {
        String deleteOrderProductsQuery = "DELETE FROM customer_order_products WHERE order_id IN (SELECT order_id FROM orders WHERE customer_id = ?)";
        String deleteOrderQuery = "DELETE FROM orders WHERE customer_id = ?";
        String deleteOrderAddressQuery = "DELETE FROM orders WHERE address_id IN (SELECT address_id FROM address WHERE customer_id = ?)";
        String deleteAddressQuery = "DELETE FROM address WHERE customer_id = ?";
        String deleteCustomerQuery = "DELETE FROM customer WHERE customer_id = ?";
        String deleteBasketQuery = "DELETE FROM basket WHERE customer_id = ?";
        String selectAddressIdsQuery = "SELECT address_id FROM address WHERE customer_id = ?";
    
        try (Connection connection = DatabaseUtil.getConnection()) {
            // Otomatik commit özelliğini kapatın, böylece işlemi yönetebilirsiniz
            connection.setAutoCommit(false);
    
            // 1. Adım: Customer'a ait tüm address_id'leri alın
            List<Integer> addressIds = new ArrayList<>();
            try (PreparedStatement selectAddressIdsStmt = connection.prepareStatement(selectAddressIdsQuery)) {
                selectAddressIdsStmt.setInt(1, id);
                try (ResultSet rs = selectAddressIdsStmt.executeQuery()) {
                    while (rs.next()) {
                        addressIds.add(rs.getInt("address_id"));
                    }
                }
            }
    
            // 2. Adım: Alınan address_id'lere göre orders tablosunda silme işlemi yapın
            try (PreparedStatement deleteOrderAddressStmt = connection.prepareStatement(deleteOrderAddressQuery)) {
                for (int addressId : addressIds) {
                    deleteOrderAddressStmt.setInt(1, addressId);
                    deleteOrderAddressStmt.executeUpdate();
                }
            }
    
            // 3. Adım: Customer'a ait order product kayıtlarını silin
            try (PreparedStatement deleteOrderProductsStmt = connection.prepareStatement(deleteOrderProductsQuery)) {
                deleteOrderProductsStmt.setInt(1, id);
                deleteOrderProductsStmt.executeUpdate();
            }
    
            // 4. Adım: Customer'a ait order'ları silin
            try (PreparedStatement deleteOrderStmt = connection.prepareStatement(deleteOrderQuery)) {
                deleteOrderStmt.setInt(1, id);
                deleteOrderStmt.executeUpdate();
            }
    
            // 5. Adım: Customer'a ait address'leri silin
            try (PreparedStatement deleteAddressStmt = connection.prepareStatement(deleteAddressQuery)) {
                deleteAddressStmt.setInt(1, id);
                deleteAddressStmt.executeUpdate();
            }
            // 6. Adım: Customer'a ait basket'leri silin
            try (PreparedStatement deleteBasketStmt = connection.prepareStatement(deleteBasketQuery)) {
                deleteBasketStmt.setInt(1, id);
                deleteBasketStmt.executeUpdate();
            }
    
            // 7. Adım: Customer'ı silin
            try (PreparedStatement deleteCustomerStmt = connection.prepareStatement(deleteCustomerQuery)) {
                deleteCustomerStmt.setInt(1, id);
                deleteCustomerStmt.executeUpdate();
            }
    
            // İşlem başarılı olduğunda commit yapın
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // Eğer bir hata oluşursa işlemi geri alın
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    }

    public boolean loginCustomer(String eMail, String password) {
        String query = "SELECT * FROM customer WHERE customer_eMail = ? AND customer_password = ?";
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

    public CustomerBeans getCustomerByEmail(String eMail) {
        CustomerBeans customer = null;
        String query = "SELECT * FROM customer WHERE customer_eMail = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, eMail);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("customer_id");
                    String firstName = resultSet.getString("customer_firstName");
                    String lastName = resultSet.getString("customer_lastName");
                    String password = resultSet.getString("customer_password");
                    String number = resultSet.getString("customer_phoneNumber");
                    customer = new CustomerBeans(id, eMail, firstName, lastName, password, number);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public CustomerBeans getCustomerByIdWithDetail(int customerId) {
        CustomerBeans customer = null;
        String sql = "SELECT c.* FROM customer c WHERE c.customer_id=?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customer = new CustomerBeans();
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setCustomer_firstName(resultSet.getString("customer_firstName"));
                customer.setCustomer_lastName(resultSet.getString("customer_lastName"));
                customer.setCustomer_eMail(resultSet.getString("customer_eMail"));
                customer.setCustomer_phoneNumber(resultSet.getString("customer_phoneNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void updateCustomer(CustomerBeans customerToUpdate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCustomer'");
    }
}
