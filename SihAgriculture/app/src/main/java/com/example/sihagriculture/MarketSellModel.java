package com.example.sihagriculture;

/**
 * Created by Anurag on 28-03-2018.
 */

public class MarketSellModel {
    public String latitude , longitude, distance, id, sellerid, sellername, sellerlocation, description, dateuploaded, priceperunit, maxamount;

    public MarketSellModel(String latitude, String longitude, String distance, String id, String sellerid, String sellername, String sellerlocation, String description, String dateuploaded, String priceperunit, String maxamount) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.id = id;
        this.sellerid = sellerid;
        this.sellername = sellername;
        this.sellerlocation = sellerlocation;
        this.description = description;
        this.dateuploaded = dateuploaded;
        this.priceperunit = priceperunit;
        this.maxamount = maxamount;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public String getSellerlocation() {
        return sellerlocation;
    }

    public void setSellerlocation(String sellerlocation) {
        this.sellerlocation = sellerlocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateuploaded() {
        return dateuploaded;
    }

    public void setDateuploaded(String dateuploaded) {
        this.dateuploaded = dateuploaded;
    }

    public String getPriceperunit() {
        return priceperunit;
    }

    public void setPriceperunit(String priceperunit) {
        this.priceperunit = priceperunit;
    }

    public String getMaxamount() {
        return maxamount;
    }

    public void setMaxamount(String maxamount) {
        this.maxamount = maxamount;
    }
}
