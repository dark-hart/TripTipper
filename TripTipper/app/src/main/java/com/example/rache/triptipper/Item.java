package com.example.rache.triptipper;

public class Item {
    private String station;
    private Double lat, lng;

    public Item() {
        station = " ";
        lat = 0.0;
        lng = 0.0;
    }


    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLat() {
        return lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLng() {
        return lng;
    }
}



