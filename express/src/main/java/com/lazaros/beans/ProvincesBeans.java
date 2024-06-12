package com.lazaros.beans;

public class ProvincesBeans {
    private int provinces_id;
    private String provinces_name;

    public ProvincesBeans(int provinces_id, String provinces_name) {
        this.provinces_id = provinces_id;
        this.provinces_name = provinces_name;
    }

    public int getProvinces_id() {
        return provinces_id;
    }

    public void setProvinces_id(int provinces_id) {
        this.provinces_id = provinces_id;
    }

    public String getProvinces_name() {
        return provinces_name;
    }

    public void setProvinces_name(String provinces_name) {
        this.provinces_name = provinces_name;
    }
}
