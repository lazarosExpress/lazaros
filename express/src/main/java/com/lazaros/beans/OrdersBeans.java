package com.lazaros.beans;

import java.sql.Date;

public class OrdersBeans {
    private int order_id;
    private Date order_date;
    private int order_state;
    private double order_totalPrice;
    private int customer_id;
    private int address_id;
    private String customerFirstName;
    private String customerLastName;
    private String productName;
    private String productImgUrl;
    private double productPrice;
    private int productQty;
    private String sellerName;
    private int sellerId;
    private int paymentId;
    private String paymentName;

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public OrdersBeans(int order_id, int sellerId, Date order_date, int order_state, double order_totalPrice,
            String customerFirstName, String customerLastName) {
        this.order_id = order_id;
        this.sellerId = sellerId;
        this.order_date = order_date;
        this.order_state = order_state;
        this.order_totalPrice = order_totalPrice;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
    }

    public OrdersBeans(int order_id, Date order_date, int order_state, double order_totalPrice, int customer_id,
            int address_id, String customerFirstName, String customerLastName) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.order_state = order_state;
        this.order_totalPrice = order_totalPrice;
        this.customer_id = customer_id;
        this.address_id = address_id;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
    }

    public OrdersBeans(int order_id, Date order_date, int order_state, double order_totalPrice, int customer_id,
            int address_id, String productName, String productImgUrl, double productPrice, int productQty,
            String sellerName, int sellerId, String customerFirstName) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.order_state = order_state;
        this.order_totalPrice = order_totalPrice;
        this.customer_id = customer_id;
        this.address_id = address_id;
        this.productName = productName;
        this.productImgUrl = productImgUrl;
        this.productPrice = productPrice;
        this.productQty = productQty;
        this.sellerName = sellerName;
        this.sellerId = sellerId;
        this.customerFirstName = customerFirstName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public OrdersBeans(int order_id, Date order_date, int order_state, double order_totalPrice, int customer_id,
            int address_id) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.order_state = order_state;
        this.order_totalPrice = order_totalPrice;
        this.customer_id = customer_id;
        this.address_id = address_id;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public OrdersBeans() {
        this.order_id = order_id;
        this.order_date = order_date;
        this.order_state = order_state;
        this.order_totalPrice = order_totalPrice;
        this.customer_id = customer_id;
        this.address_id = address_id;
    }

    public OrdersBeans(int order_id, Date order_date, int order_state, int customer_id, int address_id,
            double order_totalPrice) {
        this(order_id, order_date, order_state, order_totalPrice, customer_id, address_id);
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public int isOrder_state() {
        return order_state;
    }

    public void setOrder_state(int order_state) {
        this.order_state = order_state;
    }

    public double getOrder_totalPrice() {
        return order_totalPrice;
    }

    public void setOrder_totalPrice(double order_totalPrice) {
        this.order_totalPrice = order_totalPrice;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
