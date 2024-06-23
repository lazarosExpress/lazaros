package com.lazaros.beans;

public class PaymentBeans {
    private int payment_id;
    private String payment_name;

    public PaymentBeans(int payment_id, String payment_name) {
        this.payment_id = payment_id;
        this.payment_name = payment_name;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public String getPayment_name() {
        return payment_name;
    }

    public void setPayment_name(String payment_name) {
        this.payment_name = payment_name;
    }
}
