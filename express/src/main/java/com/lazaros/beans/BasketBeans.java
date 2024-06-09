package com.lazaros.beans;

public class BasketBeans {
    private int basket_id;
    private int basket_qty;
    private int customer_id;
    private int product_id;
    public BasketBeans(int basket_id, int basket_qty, int customer_id, int product_id) {
        this.basket_id = basket_id;
        this.basket_qty = basket_qty;
        this.customer_id = customer_id;
        this.product_id = product_id;
    }

    private ProductBeans product;

    public BasketBeans(int basket_id, int basket_qty, int customer_id, int product_id, ProductBeans product) {
        this.basket_id = basket_id;
        this.basket_qty = basket_qty;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.product = product;
    }

    public int getBasket_id() {
        return basket_id;
    }

    public void setBasket_id(int basket_id) {
        this.basket_id = basket_id;
    }

    public int getBasket_qty() {
        return basket_qty;
    }

    public void setBasket_qty(int basket_qty) {
        this.basket_qty = basket_qty;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public ProductBeans getProduct() {
        return product;
    }

    public void setProduct(ProductBeans product) {
        this.product = product;
    }
}
