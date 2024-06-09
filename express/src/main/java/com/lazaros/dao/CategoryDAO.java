package com.lazaros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lazaros.beans.CategoryBeans;
import com.lazaros.utils.DatabaseUtil;

public class CategoryDAO {
    public List<CategoryBeans> getAllCategories() {
        List<CategoryBeans> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int categoryId = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");
                CategoryBeans category = new CategoryBeans(categoryId, categoryName);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
