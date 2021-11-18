package com.example.assignment_2;

public class model {
    private  int id;
    private String address,longitude, latitude;

    public model(int id, String address, String longitude, String latitude) {
        this.id = id;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public model(){

    }

    @Override
    //toString()
    public String toString() {
        return
                "id=" + id +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude+"";
    }

    //getters and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
