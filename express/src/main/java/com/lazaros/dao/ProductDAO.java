package com.lazaros.dao;

import com.lazaros.beans.ProductBeans;
import com.lazaros.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<ProductBeans> getProductsBySupplierId(int supplierId) {
        List<ProductBeans> products = new ArrayList<>();
        String query = "SELECT p.product_id, p.product_name, p.product_prize, p.product_imgUrl, p.product_stock, " +
                "p.product_explanation, p.product_properties, p.supplier_id, p.category_id, c.category_name, p.brand_name "
                +
                "FROM products p " +
                "JOIN categories c ON p.category_id = c.category_id " +
                "WHERE p.supplier_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, supplierId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ProductBeans product = new ProductBeans();
                    product.setProduct_id(resultSet.getInt("product_id"));
                    product.setProduct_name(resultSet.getString("product_name"));
                    product.setProduct_prize(resultSet.getDouble("product_prize"));
                    product.setProduct_imgUrl(resultSet.getString("product_imgUrl"));
                    product.setProduct_stock(resultSet.getInt("product_stock"));
                    product.setProduct_explanation(resultSet.getString("product_explanation"));
                    product.setProduct_properties(resultSet.getString("product_properties"));
                    product.setSupplier_id(resultSet.getInt("supplier_id"));
                    product.setCategory_id(resultSet.getInt("category_id"));
                    product.setCategory_name(resultSet.getString("category_name"));
                    product.setBrand_name(resultSet.getString("brand_name"));

                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<ProductBeans> getAllProducts(int supplierId) {
        List<ProductBeans> products = new ArrayList<>();
        String query = "SELECT p.product_id, p.product_name, p.product_prize, p.product_imgUrl, p.product_stock, " +
                "p.product_explanation, p.product_properties, p.supplier_id, p.category_id, c.category_name, p.brand_name "
                +
                "FROM products p " +
                "JOIN categories c ON p.category_id = c.category_id ";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, supplierId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ProductBeans product = new ProductBeans();
                    product.setProduct_id(resultSet.getInt("product_id"));
                    product.setProduct_name(resultSet.getString("product_name"));
                    product.setProduct_prize(resultSet.getDouble("product_prize"));
                    product.setProduct_imgUrl(resultSet.getString("product_imgUrl"));
                    product.setProduct_stock(resultSet.getInt("product_stock"));
                    product.setProduct_explanation(resultSet.getString("product_explanation"));
                    product.setProduct_properties(resultSet.getString("product_properties"));
                    product.setSupplier_id(resultSet.getInt("supplier_id"));
                    product.setCategory_id(resultSet.getInt("category_id"));
                    product.setCategory_name(resultSet.getString("category_name"));
                    product.setBrand_name(resultSet.getString("brand_name"));

                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<ProductBeans> getFullProduct() {
        List<ProductBeans> products = new ArrayList<>();
        String query = "SELECT p.product_id, p.product_name, p.product_prize, p.product_imgUrl, p.product_stock, " +
                "p.product_explanation, p.product_properties, p.supplier_id, s.supplier_shopName, p.category_id, c.category_name, p.brand_name "
                +
                "FROM products p " +
                "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                "JOIN categories c ON p.category_id = c.category_id";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ProductBeans product = new ProductBeans();
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setProduct_name(resultSet.getString("product_name"));
                product.setProduct_prize(resultSet.getDouble("product_prize"));
                product.setProduct_imgUrl(resultSet.getString("product_imgUrl"));
                product.setProduct_stock(resultSet.getInt("product_stock"));
                product.setProduct_explanation(resultSet.getString("product_explanation"));
                product.setProduct_properties(resultSet.getString("product_properties"));
                product.setSupplier_id(resultSet.getInt("supplier_id"));
                product.setCategory_id(resultSet.getInt("category_id"));
                product.setCategory_name(resultSet.getString("category_name"));
                product.setBrand_name(resultSet.getString("brand_name"));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public ProductBeans getProductByIdWithDetail(int id) {
        ProductBeans product = null;
        String sql = "SELECT p.*, c.category_name, s.supplier_shopName FROM products p " +
                "JOIN categories c ON p.category_id = c.category_id " +
                "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                "WHERE p.product_id=?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = new ProductBeans();
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setProduct_name(resultSet.getString("product_name"));
                product.setProduct_prize(resultSet.getDouble("product_prize"));
                product.setProduct_stock(resultSet.getInt("product_stock"));
                product.setBrand_name(resultSet.getString("brand_name"));
                product.setProduct_explanation(resultSet.getString("product_explanation"));
                product.setProduct_properties(resultSet.getString("product_properties"));
                product.setCategory_name(resultSet.getString("category_name")); // Kategori adı ekleniyor
                product.setSupplier_shopName(resultSet.getString("supplier_shopName")); // Satıcı adı ekleniyor
                product.setProduct_imgUrl(resultSet.getString("product_imgUrl"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void addProduct(ProductBeans product) {
        String sql = "INSERT INTO products (product_name, product_prize, product_stock, brand_name, product_explanation, product_properties, category_id, product_imgUrl, supplier_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getProduct_name());
            preparedStatement.setDouble(2, product.getProduct_prize());
            preparedStatement.setInt(3, product.getProduct_stock());
            preparedStatement.setString(4, product.getBrand_name());
            preparedStatement.setString(5, product.getProduct_explanation());
            preparedStatement.setString(6, product.getProduct_properties());
            preparedStatement.setInt(7, product.getCategory_id());
            preparedStatement.setString(8, product.getProduct_imgUrl());
            preparedStatement.setInt(9, product.getSupplier_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(ProductBeans product) {
        String sql = "UPDATE products SET product_name = ?, product_prize = ?, product_imgUrl = ?, product_stock = ?, product_explanation = ?, product_properties = ?, supplier_id = ?, category_id = ?, brand_name = ? WHERE product_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getProduct_name());
            preparedStatement.setDouble(2, product.getProduct_prize());
            preparedStatement.setString(3, product.getProduct_imgUrl());
            preparedStatement.setInt(4, product.getProduct_stock());
            preparedStatement.setString(5, product.getProduct_explanation());
            preparedStatement.setString(6, product.getProduct_properties());
            preparedStatement.setInt(7, product.getSupplier_id());
            preparedStatement.setInt(8, product.getCategory_id());
            preparedStatement.setString(9, product.getBrand_name());
            preparedStatement.setInt(10, product.getProduct_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        String query = "DELETE FROM products WHERE product_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProductBeans getProductById(int id) {
        ProductBeans product = null;
        String query = "SELECT p.product_id, p.product_name, p.product_prize, p.product_imgUrl, p.product_stock, p.product_explanation, p.product_properties, p.supplier_id, s.supplier_name, p.category_id, c.category_name, p.brand_name "
                +
                "FROM products p " +
                "JOIN suppliers s ON p.supplier_id = s.supplier_id " +
                "JOIN categories c ON p.category_id = c.category_id " +
                "WHERE p.product_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int productId = resultSet.getInt("product_id");
                    String productName = resultSet.getString("product_name");
                    double productPrize = resultSet.getDouble("product_prize");
                    String productImgUrl = resultSet.getString("product_imgUrl");
                    int productStock = resultSet.getInt("product_stock");
                    String productExplanation = resultSet.getString("product_explanation");
                    String productProperties = resultSet.getString("product_properties");
                    int supplierId = resultSet.getInt("supplier_id");
                    String supplierName = resultSet.getString("supplier_name");
                    int categoryId = resultSet.getInt("category_id");
                    String categoryName = resultSet.getString("category_name");
                    String brandName = resultSet.getString("brand_name");

                    product = new ProductBeans(productId, productName, productPrize, productImgUrl, productStock,
                            productExplanation, productProperties, brandName, supplierId, categoryId);
                    product.setSupplier_shopName(supplierName);
                    product.setCategory_name(categoryName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<ProductBeans> searchProductsByName(String query) {
        List<ProductBeans> products = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("SELECT * FROM products WHERE product_name LIKE ?")) {
            preparedStatement.setString(1, "%" + query + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductBeans product = new ProductBeans();
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setProduct_name(resultSet.getString("product_name"));
                // Set other fields...
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
