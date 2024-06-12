package com.lazaros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lazaros.beans.NeighbourhoodBeans;
import com.lazaros.beans.SubDistrictsBeans;
import com.lazaros.utils.DatabaseUtil;

public class NeighbourhoodDAO {
    public List<SubDistrictsBeans> getAllNeighbourhood() {
        List<SubDistrictsBeans> subDistrictsList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "SELECT * FROM neighbourhood";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SubDistrictsBeans subDistricts = new SubDistrictsBeans(
                        rs.getInt("subDistricts_id"),
                        rs.getString("subDistricts_name"));
                subDistrictsList.add(subDistricts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return subDistrictsList;
    }

    public List<NeighbourhoodBeans> getNeighbourhoodsBySubDistricts(int subDistrictsId) {
        List<NeighbourhoodBeans> neighbourhoodList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "SELECT * FROM neighbourhood WHERE subDistricts_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, subDistrictsId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NeighbourhoodBeans neighbourhood = new NeighbourhoodBeans(
                        rs.getInt("neighbourhood_id"),
                        rs.getString("neighbourhood_name"),
                        rs.getInt("subDistricts_id"));
                neighbourhoodList.add(neighbourhood);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return neighbourhoodList;
    }
}
