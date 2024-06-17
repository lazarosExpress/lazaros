package com.lazaros.beans;

public class CustomerOrderProductsBeans {
    private int order_id;
    private int product_id;
    private int product_qty;

    public CustomerOrderProductsBeans(int order_id, int product_id, int product_qty) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.product_qty = product_qty;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(int product_qty) {
        this.product_qty = product_qty;
    }
}
