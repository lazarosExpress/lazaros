package com.lazaros.beans;

public class NeighbourhoodBeans {
    private int neighbourhood_id;
    private String neighbourhood_name;
    private int subDistricts_id;

    public NeighbourhoodBeans(int neighbourhood_id, String neighbourhood_name, int subDistricts_id) {
        this.neighbourhood_id = neighbourhood_id;
        this.neighbourhood_name = neighbourhood_name;
        this.subDistricts_id = subDistricts_id;
    }

    public int getNeighbourhood_id() {
        return neighbourhood_id;
    }

    public void setNeighbourhood_id(int neighbourhood_id) {
        this.neighbourhood_id = neighbourhood_id;
    }

    public String getNeighbourhood_name() {
        return neighbourhood_name;
    }

    public void setNeighbourhood_name(String neighbourhood_name) {
        this.neighbourhood_name = neighbourhood_name;
    }

    public int getSubDistricts_id() {
        return subDistricts_id;
    }

    public void setSubDistricts_id(int subDistricts_id) {
        this.subDistricts_id = subDistricts_id;
    }
}
