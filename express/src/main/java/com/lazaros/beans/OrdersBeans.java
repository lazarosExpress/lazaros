package com.lazaros.beans;

import java.sql.Date;

public class OrdersBeans {
    private int order_id;
    private Date order_date;
    private boolean order_state;
    private double order_totalPrize;
    private int customer_id;

    public OrdersBeans(int order_id, Date order_date, boolean order_state, double order_totalPrize, int customer_id) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.order_state = order_state;
        this.order_totalPrize = order_totalPrize;
        this.customer_id = customer_id;
    }

    public OrdersBeans(int order_id, Date order_date, boolean order_state, int customer_id, double order_totalPrize) {
        this(order_id, order_date, order_state, order_totalPrize, customer_id);
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

    public boolean isOrder_state() {
        return order_state;
    }

    public void setOrder_state(boolean order_state) {
        this.order_state = order_state;
    }

    public double getOrder_totalPrize() {
        return order_totalPrize;
    }

    public void setOrder_totalPrize(double order_totalPrize) {
        this.order_totalPrize = order_totalPrize;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
