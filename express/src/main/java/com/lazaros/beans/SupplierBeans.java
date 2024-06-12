package com.lazaros.beans;

public class SupplierBeans {
    private int supplier_id;
    private String supplier_firstName;
    private String supplier_lastName;
    private String supplier_shopName;
    private String supplier_iban;
    private String supplier_eMail;
    private String supplier_password;
    private String supplier_phoneNumber;

    public SupplierBeans(String supplier_firstName, String supplier_lastName, String supplier_shopName,
            String supplier_iban, String supplier_eMail, String supplier_password, String supplier_phoneNumber) {
        this.supplier_firstName = supplier_firstName;
        this.supplier_lastName = supplier_lastName;
        this.supplier_shopName = supplier_shopName;
        this.supplier_iban = supplier_iban;
        this.supplier_eMail = supplier_eMail;
        this.supplier_password = supplier_password;
        this.supplier_phoneNumber = supplier_phoneNumber;
    }

    public SupplierBeans(String supplier_eMail, String supplier_password) {
        this.supplier_eMail = supplier_eMail;
        this.supplier_password = supplier_password;
    }

    public SupplierBeans(int supplier_id, String supplier_firstName, String supplier_lastName, String supplier_shopName,
            String supplier_iban, String supplier_eMail, String supplier_password, String supplier_phoneNumber) {
        this.supplier_id = supplier_id;
        this.supplier_firstName = supplier_firstName;
        this.supplier_lastName = supplier_lastName;
        this.supplier_shopName = supplier_shopName;
        this.supplier_iban = supplier_iban;
        this.supplier_eMail = supplier_eMail;
        this.supplier_password = supplier_password;
        this.supplier_phoneNumber = supplier_phoneNumber;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_firstName() {
        return supplier_firstName;
    }

    public void setSupplier_firstName(String supplier_firstName) {
        this.supplier_firstName = supplier_firstName;
    }

    public String getSupplier_lastName() {
        return supplier_lastName;
    }

    public void setSupplier_lastName(String supplier_lastName) {
        this.supplier_lastName = supplier_lastName;
    }

    public String getSupplier_shopName() {
        return supplier_shopName;
    }

    public void setSupplier_shopName(String supplier_shopName) {
        this.supplier_shopName = supplier_shopName;
    }

    public String getSupplier_iban() {
        return supplier_iban;
    }

    public void setSupplier_iban(String supplier_iban) {
        this.supplier_iban = supplier_iban;
    }

    public String getSupplier_eMail() {
        return supplier_eMail;
    }

    public void setSupplier_eMail(String supplier_eMail) {
        this.supplier_eMail = supplier_eMail;
    }

    public String getSupplier_password() {
        return supplier_password;
    }

    public void setSupplier_password(String supplier_password) {
        this.supplier_password = supplier_password;
    }

    public String getSupplier_phoneNumber() {
        return supplier_phoneNumber;
    }

    public void setSupplier_phoneNumber(String supplier_phoneNumber) {
        this.supplier_phoneNumber = supplier_phoneNumber;
    }
}
