package com.example.sihagriculture;

/**
 * Created by Anurag on 30-03-2018.
 */

public class SellerModel {
   public String lat, lon, distance, priceperunit, name, productdesc, maxquantity;

    public SellerModel(String lat, String lon, String distance, String priceperunit, String name, String productdesc, String maxquantity) {
        this.lat = lat;
        this.lon = lon;
        this.distance = distance;
        this.priceperunit = priceperunit;
        this.name = name;
        this.productdesc = productdesc;
        this.maxquantity = maxquantity;
    }

    public SellerModel() {
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPriceperunit() {
        return priceperunit;
    }

    public void setPriceperunit(String priceperunit) {
        this.priceperunit = priceperunit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public String getMaxquantity() {
        return maxquantity;
    }

    public void setMaxquantity(String maxquantity) {
        this.maxquantity = maxquantity;
    }
}
