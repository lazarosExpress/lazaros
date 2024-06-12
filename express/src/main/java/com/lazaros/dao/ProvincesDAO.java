package com.lazaros.dao;

import com.lazaros.beans.ProvincesBeans;
import com.lazaros.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProvincesDAO {

    public List<ProvincesBeans> getAllProvinces() {
        List<ProvincesBeans> provincesList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "SELECT * FROM provinces";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProvincesBeans province = new ProvincesBeans(
                        rs.getInt("provinces_id"),
                        rs.getString("provinces_name")
                );
                provincesList.add(province);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return provincesList;
    }

    public ProvincesBeans getProvinceById(int id) {
        ProvincesBeans province = null;
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            String query = "SELECT * FROM provinces WHERE provinces_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                province = new ProvincesBeans(
                        rs.getInt("provinces_id"),
                        rs.getString("provinces_name")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(connection);
        }
        return province;
    }
}
