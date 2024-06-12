package com.lazaros.beans;

public class DistrictsBeans {
    private int district_id;
    private String district_name;
    private int province_id;

    public DistrictsBeans(int district_id, String district_name, int province_id) {
        this.district_id = district_id;
        this.district_name = district_name;
        this.province_id = province_id;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }
}
