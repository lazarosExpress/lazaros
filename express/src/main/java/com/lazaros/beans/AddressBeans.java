package com.lazaros.beans;

public class AddressBeans {
    private int address_id;
    private String address_customerFirstName;
    private String address_customerLastName;
    private String address_customerPhoneNumber;
    private String address_description;
    private String address_title;
    private int provinces_id;
    private int districts_id;
    private int subDistricts_id;
    private int neighbourhood_id;
    private int customer_id;
    private String provinces_name;
    private String districts_name;
    private String subDistricts_name;
    private String neighbourhood_name;

    public String getNeighbourhood_name() {
        return neighbourhood_name;
    }

    public void setNeighbourhood_name(String neighbourhood_name) {
        this.neighbourhood_name = neighbourhood_name;
    }

    public AddressBeans(int address_id, String address_customerFirstName, String address_customerLastName,
            String address_customerPhoneNumber, String address_description, String address_title, int provinces_id,
            int districts_id, int subDistricts_id, int neighbourhood_id, int customer_id) {
        this.address_id = address_id;
        this.address_customerFirstName = address_customerFirstName;
        this.address_customerLastName = address_customerLastName;
        this.address_customerPhoneNumber = address_customerPhoneNumber;
        this.address_description = address_description;
        this.address_title = address_title;
        this.provinces_id = provinces_id;
        this.districts_id = districts_id;
        this.subDistricts_id = subDistricts_id;
        this.neighbourhood_id = neighbourhood_id;
        this.customer_id = customer_id;
    }

    public int getNeighbourhood_id() {
        return neighbourhood_id;
    }

    public void setNeighbourhood_id(int neighbourhood_id) {
        this.neighbourhood_id = neighbourhood_id;
    }


    public int getDistricts_id() {
        return districts_id;
    }

    public void setDistricts_id(int districts_id) {
        this.districts_id = districts_id;
    }

    public int getSubDistricts_id() {
        return subDistricts_id;
    }

    public void setSubDistricts_id(int subDistricts_id) {
        this.subDistricts_id = subDistricts_id;
    }

    public String getDistricts_name() {
        return districts_name;
    }

    public void setDistricts_name(String districts_name) {
        this.districts_name = districts_name;
    }

    public String getSubDistricts_name() {
        return subDistricts_name;
    }

    public void setSubDistricts_name(String subDistricts_name) {
        this.subDistricts_name = subDistricts_name;
    }

    public AddressBeans(int address_id, String address_customerFirstName, String address_customerLastName,
            String address_customerPhoneNumber, String address_description, String address_title, int provinces_id,
            int districts_id, int subDistricts_id, int customer_id) {
        this.address_id = address_id;
        this.address_customerFirstName = address_customerFirstName;
        this.address_customerLastName = address_customerLastName;
        this.address_customerPhoneNumber = address_customerPhoneNumber;
        this.address_description = address_description;
        this.address_title = address_title;
        this.provinces_id = provinces_id;
        this.districts_id = districts_id;
        this.subDistricts_id = subDistricts_id;
        this.customer_id = customer_id;
    }

    public AddressBeans(int address_id, String address_customerFirstName, String address_customerLastName,
            String address_customerPhoneNumber, String address_description, String address_title, int provinces_id,
            int customer_id) {
        this.address_id = address_id;
        this.address_customerFirstName = address_customerFirstName;
        this.address_customerLastName = address_customerLastName;
        this.address_customerPhoneNumber = address_customerPhoneNumber;
        this.address_description = address_description;
        this.address_title = address_title;
        this.provinces_id = provinces_id;
        this.customer_id = customer_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getAddress_customerFirstName() {
        return address_customerFirstName;
    }

    public void setAddress_customerFirstName(String address_customerFirstName) {
        this.address_customerFirstName = address_customerFirstName;
    }

    public String getAddress_customerLastName() {
        return address_customerLastName;
    }

    public void setAddress_customerLastName(String address_customerLastName) {
        this.address_customerLastName = address_customerLastName;
    }

    public String getAddress_customerPhoneNumber() {
        return address_customerPhoneNumber;
    }

    public void setAddress_customerPhoneNumber(String address_customerPhoneNumber) {
        this.address_customerPhoneNumber = address_customerPhoneNumber;
    }

    public String getAddress_description() {
        return address_description;
    }

    public void setAddress_description(String address_description) {
        this.address_description = address_description;
    }

    public String getAddress_title() {
        return address_title;
    }

    public void setAddress_title(String address_title) {
        this.address_title = address_title;
    }

    public int getProvinces_id() {
        return provinces_id;
    }

    public void setProvinces_id(int provinces_id) {
        this.provinces_id = provinces_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getProvinces_name() {
        return provinces_name;
    }

    public void setProvinces_name(String provinces_name) {
        this.provinces_name = provinces_name;
    }
}
