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
        String query = "SELECT p.product_id, p.product_name, p.product_oldPrice, p.product_price, p.product_imgUrl, p.product_stock, " +
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
                    product.setProduct_oldPrice(resultSet.getDouble("product_oldPrice"));
                    product.setProduct_price(resultSet.getDouble("product_price"));
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
        String query = "SELECT p.product_id, p.product_name, p.product_oldPrice, p.product_price, p.product_imgUrl, p.product_stock, " +
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
                    product.setProduct_oldPrice(resultSet.getDouble("product_oldPrice"));
                    product.setProduct_price(resultSet.getDouble("product_price"));
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
        String query = "SELECT p.product_id, p.product_name, p.product_oldPrice, p.product_price, p.product_imgUrl, p.product_stock, " +
                "p.product_explanation, p.product_properties, p.supplier_id, s.supplier_shopName, p.category_id, c.category_name, p.brand_name "
                +
                "FROM products p " +
                "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                "JOIN categories c ON p.category_id = c.category_id " +
                "WHERE p.product_stock > 0";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ProductBeans product = new ProductBeans();
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setProduct_name(resultSet.getString("product_name"));
                product.setProduct_oldPrice(resultSet.getDouble("product_oldPrice"));
                product.setProduct_price(resultSet.getDouble("product_price"));
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

    public List<ProductBeans> getSupplierProduct(int supplierId) {
        List<ProductBeans> products = new ArrayList<>();
        String query = "SELECT p.product_id, p.product_name, p.product_oldPrice, p.product_price, p.product_imgUrl, p.product_stock, " +
                "p.product_explanation, p.product_properties, p.supplier_id, s.supplier_shopName, p.category_id, c.category_name, p.brand_name "
                +
                "FROM products p " +
                "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                "JOIN categories c ON p.category_id = c.category_id " +
                "WHERE s.supplier_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, supplierId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ProductBeans product = new ProductBeans();
                    product.setProduct_id(resultSet.getInt("product_id"));
                    product.setProduct_name(resultSet.getString("product_name"));
                    product.setProduct_oldPrice(resultSet.getDouble("product_oldPrice"));
                    product.setProduct_price(resultSet.getDouble("product_price"));
                    product.setProduct_imgUrl(resultSet.getString("product_imgUrl"));
                    product.setProduct_stock(resultSet.getInt("product_stock"));
                    product.setProduct_explanation(resultSet.getString("product_explanation"));
                    product.setProduct_properties(resultSet.getString("product_properties"));
                    int supplierIdFromDb = resultSet.getInt("supplier_id");
                    System.out.println("Supplier ID from DB: " + supplierIdFromDb); // Debugging için
                    product.setSupplier_id(supplierIdFromDb); // Bu satırın varlığını kontrol edin
                    product.setSupplier_shopName(resultSet.getString("supplier_shopName"));
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
    public List<ProductBeans> getCategoryProduct(int categoryID) {
        List<ProductBeans> products = new ArrayList<>();
        String query = "SELECT p.product_id, p.product_name, p.product_oldPrice, p.product_price, p.product_imgUrl, p.product_stock, " +
                "p.product_explanation, p.product_properties, p.supplier_id, s.supplier_shopName, p.category_id, c.category_name, p.brand_name "
                +
                "FROM products p " +
                "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                "JOIN categories c ON p.category_id = c.category_id " +
                "WHERE c.category_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, categoryID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ProductBeans product = new ProductBeans();
                    product.setProduct_id(resultSet.getInt("product_id"));
                    product.setProduct_name(resultSet.getString("product_name"));
                    product.setProduct_oldPrice(resultSet.getDouble("product_oldPrice"));
                    product.setProduct_price(resultSet.getDouble("product_price"));
                    product.setProduct_imgUrl(resultSet.getString("product_imgUrl"));
                    product.setProduct_stock(resultSet.getInt("product_stock"));
                    product.setProduct_explanation(resultSet.getString("product_explanation"));
                    product.setProduct_properties(resultSet.getString("product_properties"));
                    int supplierIdFromDb = resultSet.getInt("supplier_id");
                    System.out.println("Supplier ID from DB: " + supplierIdFromDb); // Debugging için
                    product.setSupplier_id(supplierIdFromDb); // Bu satırın varlığını kontrol edin
                    product.setSupplier_shopName(resultSet.getString("supplier_shopName"));
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
                product.setProduct_oldPrice(resultSet.getDouble("product_oldPrice"));
                product.setProduct_price(resultSet.getDouble("product_price"));
                product.setProduct_stock(resultSet.getInt("product_stock"));
                product.setBrand_name(resultSet.getString("brand_name"));
                product.setProduct_explanation(resultSet.getString("product_explanation"));
                product.setProduct_properties(resultSet.getString("product_properties"));
                product.setCategory_id(resultSet.getInt("category_id"));
                product.setCategory_name(resultSet.getString("category_name"));
                product.setSupplier_id(resultSet.getInt("supplier_id"));
                product.setSupplier_shopName(resultSet.getString("supplier_shopName"));
                product.setProduct_imgUrl(resultSet.getString("product_imgUrl"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void addProduct(ProductBeans product) {
        String sql = "INSERT INTO products (product_name, product_oldPrice, product_price, product_stock, brand_name, product_explanation, product_properties, category_id, product_imgUrl, supplier_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getProduct_name());
            preparedStatement.setDouble(2, product.getProduct_oldPrice());
            preparedStatement.setDouble(3, product.getProduct_price());
            preparedStatement.setInt(4, product.getProduct_stock());
            preparedStatement.setString(5, product.getBrand_name());
            preparedStatement.setString(6, product.getProduct_explanation());
            preparedStatement.setString(7, product.getProduct_properties());
            preparedStatement.setInt(8, product.getCategory_id());
            preparedStatement.setString(9, product.getProduct_imgUrl());
            preparedStatement.setInt(10, product.getSupplier_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(ProductBeans product) {
        String sql = "UPDATE products SET product_name = ?, product_oldPrice = ?, product_price = ?, product_imgUrl = ?, product_stock = ?, product_explanation = ?, product_properties = ?, category_id = ?, brand_name = ? WHERE product_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getProduct_name());
            preparedStatement.setDouble(2, product.getProduct_oldPrice());
            preparedStatement.setDouble(3, product.getProduct_price());
            preparedStatement.setString(4, product.getProduct_imgUrl());
            preparedStatement.setInt(5, product.getProduct_stock());
            preparedStatement.setString(6, product.getProduct_explanation());
            preparedStatement.setString(7, product.getProduct_properties());
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
        String query = "SELECT p.product_id, p.product_name, p.product_oldPrice, p.product_price, p.product_imgUrl, p.product_stock, p.product_explanation, p.product_properties, p.supplier_id, s.supplier_shopName, p.category_id, c.category_name, p.brand_name "
                +
                "FROM products p " +
                "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                "JOIN categories c ON p.category_id = c.category_id " +
                "WHERE p.product_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int productId = resultSet.getInt("product_id");
                    String productName = resultSet.getString("product_name");
                    double productOldPrice = resultSet.getDouble("product_oldPrice");
                    double productPrice = resultSet.getDouble("product_price");
                    String productImgUrl = resultSet.getString("product_imgUrl");
                    int productStock = resultSet.getInt("product_stock");
                    String productExplanation = resultSet.getString("product_explanation");
                    String productProperties = resultSet.getString("product_properties");
                    int supplierId = resultSet.getInt("supplier_id");
                    String supplierName = resultSet.getString("supplier_shopName");
                    int categoryId = resultSet.getInt("category_id");
                    String categoryName = resultSet.getString("category_name");
                    String brandName = resultSet.getString("brand_name");

                    product = new ProductBeans(productId, productName, productOldPrice, productPrice, productImgUrl, productStock,
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

    public ProductBeans getUpdateProductById(int id) {
        ProductBeans product = null;
        String query = "SELECT p.product_id, p.product_name, p.product_oldPrice, p.product_price, p.product_imgUrl, p.product_stock, p.product_explanation, p.product_properties, p.supplier_id, p.category_id, c.category_name, p.brand_name "
                +
                "FROM products p " +
                "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                "JOIN categories c ON p.category_id = c.category_id " +
                "WHERE p.product_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int productId = resultSet.getInt("product_id");
                    String productName = resultSet.getString("product_name");
                    double productOldPrice = resultSet.getDouble("product_oldPrice");
                    double productPrice = resultSet.getDouble("product_price");
                    String productImgUrl = resultSet.getString("product_imgUrl");
                    int productStock = resultSet.getInt("product_stock");
                    String productExplanation = resultSet.getString("product_explanation");
                    String productProperties = resultSet.getString("product_properties");
                    int supplierId = resultSet.getInt("supplier_id");
                    int categoryId = resultSet.getInt("category_id");
                    String categoryName = resultSet.getString("category_name");
                    String brandName = resultSet.getString("brand_name");

                    product = new ProductBeans(productId, productName, productOldPrice, productPrice, productImgUrl, productStock,
                            productExplanation, productProperties, brandName, supplierId, categoryId);
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
                product.setProduct_imgUrl(resultSet.getString("product_imgUrl"));
                product.setProduct_price(resultSet.getDouble("product_price"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public boolean updateProductStock(int productId, int quantityChange) {
        boolean rowUpdated = false;
        String sql = "UPDATE products SET product_stock = product_stock + ? WHERE product_id = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantityChange);
            stmt.setInt(2, productId);
            rowUpdated = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}
