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

                CustomerBeans customer = new CustomerBeans(customerId, customerEMail, firstName, lastName, number, number);
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

    public void updateCustomer(CustomerBeans customer) {
        String query = "UPDATE customer SET customer_firstName = ?, customer_lastName = ?, customer_password = ?, customer_phoneNumber = ? WHERE customer_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, customer.getCustomer_firstName());
            preparedStatement.setString(2, customer.getCustomer_lastName());
            preparedStatement.setString(3, customer.getCustomer_password());
            preparedStatement.setString(4, customer.getCustomer_phoneNumber());
            preparedStatement.setInt(5, customer.getCustomer_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int id) {
        String query = "DELETE FROM customer WHERE customer_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
}
