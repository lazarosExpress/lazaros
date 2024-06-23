package com.lazaros.dao;

import com.lazaros.beans.AddressBeans;
import com.lazaros.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {

    public List<AddressBeans> getAllAddresses() {
        List<AddressBeans> addressList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "SELECT a.address_id, a.address_customerFirstName, a.address_customerLastName, " +
                    "a.address_customerPhoneNumber, a.address_description, a.address_title, " +
                    "a.provinces_id, c.customer_id " +
                    "FROM address a " +
                    "JOIN customer c ON a.customer_id = c.customer_id";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AddressBeans address = new AddressBeans(
                        rs.getInt("address_id"),
                        rs.getString("address_customerFirstName"),
                        rs.getString("address_customerLastName"),
                        rs.getString("address_customerPhoneNumber"),
                        rs.getString("address_description"),
                        rs.getString("address_title"),
                        rs.getInt("provinces_id"),
                        rs.getInt("customer_id"));
                addressList.add(address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return addressList;
    }

    public List<AddressBeans> getAddressesByCustomerId(int customerId) {
        List<AddressBeans> addressesList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "SELECT a.*, p.provinces_name FROM address a JOIN provinces p ON a.provinces_id = p.provinces_id WHERE a.customer_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AddressBeans address = new AddressBeans(
                        rs.getInt("address_id"),
                        rs.getString("address_customerFirstName"),
                        rs.getString("address_customerLastName"),
                        rs.getString("address_customerPhoneNumber"),
                        rs.getString("address_description"),
                        rs.getString("address_title"),
                        rs.getInt("provinces_id"),
                        rs.getInt("customer_id"));
                address.setProvinces_name(rs.getString("provinces_name"));
                addressesList.add(address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return addressesList;
    }

    public boolean addAddress(AddressBeans address) {
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "INSERT INTO address (address_customerFirstName, address_customerLastName, address_customerPhoneNumber, address_description, address_title, provinces_id, districts_id, subDistricts_id, neighbourhood_id, customer_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, address.getAddress_customerFirstName());
            ps.setString(2, address.getAddress_customerLastName());
            ps.setString(3, address.getAddress_customerPhoneNumber());
            ps.setString(4, address.getAddress_description());
            ps.setString(5, address.getAddress_title());
            ps.setInt(6, address.getProvinces_id());
            ps.setInt(7, address.getDistricts_id());
            ps.setInt(8, address.getSubDistricts_id());
            ps.setInt(9, address.getNeighbourhood_id());
            ps.setInt(10, address.getCustomer_id());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return false;
    }

    public boolean deleteAddress(int id, int customerId) {
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "DELETE FROM address WHERE address_id = ? AND customer_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, customerId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return false;
    }

    public AddressBeans getAddressById(int id) {
        AddressBeans address = null;
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "SELECT * FROM address WHERE address_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                address = new AddressBeans(
                        rs.getInt("address_id"),
                        rs.getString("address_customerFirstName"),
                        rs.getString("address_customerLastName"),
                        rs.getString("address_customerPhoneNumber"),
                        rs.getString("address_description"),
                        rs.getString("address_title"),
                        rs.getInt("provinces_id"),
                        rs.getInt("districts_id"),
                        rs.getInt("subDistricts_id"),
                        rs.getInt("neighbourhood_id"),
                        rs.getInt("customer_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return address;
    }

    public boolean updateAddress(AddressBeans address) {
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "UPDATE address SET address_customerFirstName = ?, address_customerLastName = ?, address_customerPhoneNumber = ?, address_description = ?, address_title = ?, provinces_id = ?, districts_id = ?, subDistricts_id = ?, neighbourhood_id = ? WHERE address_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, address.getAddress_customerFirstName());
            ps.setString(2, address.getAddress_customerLastName());
            ps.setString(3, address.getAddress_customerPhoneNumber());
            ps.setString(4, address.getAddress_description());
            ps.setString(5, address.getAddress_title());
            ps.setInt(6, address.getProvinces_id());
            ps.setInt(7, address.getDistricts_id());
            ps.setInt(8, address.getSubDistricts_id());
            ps.setInt(9, address.getNeighbourhood_id());
            ps.setInt(10, address.getAddress_id());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return false;
    }
}
