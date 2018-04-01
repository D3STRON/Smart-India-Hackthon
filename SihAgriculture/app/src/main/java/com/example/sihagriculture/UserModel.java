package com.example.sihagriculture;

/**
 * Created by Anurag on 30-03-2018.
 */

public class UserModel {
    public String aadhar,latitude, longitude, distance,password, number;

    public UserModel(String aadhar, String latitude, String longitude, String distance, String password, String number) {
        this.aadhar = aadhar;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.password = password;
        this.number= number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
