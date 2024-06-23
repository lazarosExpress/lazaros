package com.lazaros.beans;

public class CustomerBeans {
    private int customer_id;
    private String customer_eMail;
    private String customer_firstName;
    private String customer_lastName;
    private String customer_password;
    private String customer_phoneNumber;

    public CustomerBeans(int customer_id, String customer_eMail, String customer_firstName, String customer_lastName,
            String customer_phoneNumber) {
        this.customer_id = customer_id;
        this.customer_eMail = customer_eMail;
        this.customer_firstName = customer_firstName;
        this.customer_lastName = customer_lastName;
        this.customer_phoneNumber = customer_phoneNumber;
    }

    public CustomerBeans(String customer_eMail, String customer_password) {
        this.customer_eMail = customer_eMail;
        this.customer_password = customer_password;
    }

    public CustomerBeans(int customer_id, String customer_eMail, String customer_firstName, String customer_lastName,
            String customer_password, String customer_phoneNumber) {
        this.customer_id = customer_id;
        this.customer_eMail = customer_eMail;
        this.customer_firstName = customer_firstName;
        this.customer_lastName = customer_lastName;
        this.customer_password = customer_password;
        this.customer_phoneNumber = customer_phoneNumber;
    }

    public CustomerBeans() {
        //TODO Auto-generated constructor stub
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_eMail() {
        return customer_eMail;
    }

    public void setCustomer_eMail(String customer_eMail) {
        this.customer_eMail = customer_eMail;
    }

    public String getCustomer_firstName() {
        return customer_firstName;
    }

    public void setCustomer_firstName(String customer_firstName) {
        this.customer_firstName = customer_firstName;
    }

    public String getCustomer_lastName() {
        return customer_lastName;
    }

    public void setCustomer_lastName(String customer_lastName) {
        this.customer_lastName = customer_lastName;
    }

    public String getCustomer_password() {
        return customer_password;
    }

    public void setCustomer_password(String customer_password) {
        this.customer_password = customer_password;
    }

    public String getCustomer_phoneNumber() {
        return customer_phoneNumber;
    }

    public void setCustomer_phoneNumber(String customer_phoneNumber) {
        this.customer_phoneNumber = customer_phoneNumber;
    }

}
