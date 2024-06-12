package com.lazaros.dao;

import com.lazaros.beans.DistrictsBeans;
import com.lazaros.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DistrictsDAO {

    public List<DistrictsBeans> getDistrictsByProvince(int provinceId) {
        List<DistrictsBeans> districtsList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "SELECT * FROM districts WHERE provinces_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, provinceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DistrictsBeans district = new DistrictsBeans(
                        rs.getInt("districts_id"),
                        rs.getString("districts_name"),
                        rs.getInt("provinces_id"));
                districtsList.add(district);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return districtsList;
    }
}
