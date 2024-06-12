package com.lazaros.dao;

import com.lazaros.beans.SubDistrictsBeans;
import com.lazaros.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubDistrictsDAO {
    public List<SubDistrictsBeans> getSubDistrictsByDistricts(int districtId) {
        List<SubDistrictsBeans> subDistricts = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("SELECT * FROM subdistricts WHERE districts_id = ?")) {
            preparedStatement.setInt(1, districtId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("subDistricts_id");
                String name = rs.getString("subDistricts_name");
                subDistricts.add(new SubDistrictsBeans(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subDistricts;
    }

    public List<SubDistrictsBeans> getAllDistricts() {
        List<SubDistrictsBeans> subDistrictsList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "SELECT * FROM subdistricts";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SubDistrictsBeans district = new SubDistrictsBeans(
                        rs.getInt("subDistricts_id"),
                        rs.getString("subDistricts_name"),
                        rs.getInt("district_id"));
                subDistrictsList.add(district);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return subDistrictsList;
    }

    public SubDistrictsBeans getDistrictById(int id) {
        SubDistrictsBeans district = null;
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "SELECT * FROM subdistricts WHERE subDistricts_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                district = new SubDistrictsBeans(
                        rs.getInt("subDistricts_id"),
                        rs.getString("subDistricts_name"),
                        rs.getInt("district_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return district;
    }
}
