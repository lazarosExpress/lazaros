package com.lazaros.beans;

public class SubDistrictsBeans {
    private int subDistricts_id;
    private String subDistricts_name;
    private int districts_id;

    public SubDistrictsBeans(int subDistricts_id, String subDistricts_name, int districts_id) {
        this.subDistricts_id = subDistricts_id;
        this.subDistricts_name = subDistricts_name;
        this.districts_id = districts_id;
    }

    public SubDistrictsBeans(int subDistricts_id, String subDistricts_name) {
        this.subDistricts_id = subDistricts_id;
        this.subDistricts_name = subDistricts_name;
    }

    public int getDistricts_id() {
        return subDistricts_id;
    }

    public void setDistricts_id(int subDistricts_id) {
        this.subDistricts_id = subDistricts_id;
    }

    public String getDistricts_name() {
        return subDistricts_name;
    }

    public void setDistricts_name(String subDistricts_name) {
        this.subDistricts_name = subDistricts_name;
    }

    public int getDistrict_id() {
        return districts_id;
    }

    public void setDistrict_id(int districts_id) {
        this.districts_id = districts_id;
    }
}
