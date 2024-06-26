package com.lazaros.beans;

public class ProductBeans {
    private int product_id;
    private String product_name;
    private double product_oldPrice;
    private double product_price;
    private String product_imgUrl;
    private int product_stock;
    private String product_explanation;
    private String product_properties;
    private int supplier_id;
    private String supplier_shopName;
    private String brand_name;
    private int category_id;
    private String category_name;
    private String displayCategory;

    public double getProduct_oldPrice() {
        return product_oldPrice;
    }

    public void setProduct_oldPrice(double product_oldPrice) {
        this.product_oldPrice = product_oldPrice;
    }

    public String getDisplayCategory() {
        return displayCategory;
    }

    public void setDisplayCategory(String displayCategory) {
        this.displayCategory = displayCategory;
    }

    public String getSupplier_shopName() {
        return supplier_shopName;
    }

    public void setSupplier_shopName(String supplier_shopName) {
        this.supplier_shopName = supplier_shopName;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public ProductBeans(int product_id, String product_name, double product_oldPrice, double product_price, String product_imgUrl,
            int product_stock, String product_explanation, String product_properties,
            String supplier_shopName, int category_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_oldPrice =  product_oldPrice;
        this.product_price = product_price;
        this.product_imgUrl = product_imgUrl;
        this.product_stock = product_stock;
        this.product_explanation = product_explanation;
        this.product_properties = product_properties;
        this.supplier_shopName = supplier_shopName;
        this.category_id = category_id;
    }

    public ProductBeans(int product_id, String product_name, double product_oldPrice, double product_price, String product_imgUrl,
            int product_stock, String product_explanation, String product_properties, String brand_name,
            int supplier_id, int category_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_oldPrice =  product_oldPrice;
        this.product_price = product_price;
        this.product_imgUrl = product_imgUrl;
        this.product_stock = product_stock;
        this.product_explanation = product_explanation;
        this.brand_name = brand_name;
        this.product_properties = product_properties;
        this.supplier_id = supplier_id;
        this.category_id = category_id;
    }

    public ProductBeans(int product_id, String product_name, double product_oldPrice, double product_price, String product_imgUrl,
            int product_stock, String product_explanation, String product_properties) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_oldPrice = product_oldPrice;
        this.product_price = product_price;
        this.product_imgUrl = product_imgUrl;
        this.product_stock = product_stock;
        this.product_explanation = product_explanation;
        this.product_properties = product_properties;
    }

    public ProductBeans() {
        // TODO Auto-generated constructor stub
    }

    public ProductBeans(int productId, String productName,double productOldPrice, double productPrice, String productImgUrl, int productStock,
            String productExplanation, String productProperties, int supplier_id, int categoryId) {
        this.product_id = productId;
        this.product_name = productName;
        this.product_oldPrice = productOldPrice;
        this.product_price = productPrice;
        this.product_imgUrl = productImgUrl;
        this.product_stock = productStock;
        this.product_explanation = productExplanation;
        this.product_properties = productProperties;
        this.supplier_id = supplier_id;
        this.category_id = categoryId;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public String getProduct_imgUrl() {
        return product_imgUrl;
    }

    public void setProduct_imgUrl(String product_imgUrl) {
        this.product_imgUrl = product_imgUrl;
    }

    public int getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(int product_stock) {
        this.product_stock = product_stock;
    }

    public String getProduct_explanation() {
        return product_explanation;
    }

    public void setProduct_explanation(String product_explanation) {
        this.product_explanation = product_explanation;
    }

    public String getProduct_properties() {
        return product_properties;
    }

    public void setProduct_properties(String product_properties) {
        this.product_properties = product_properties;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
