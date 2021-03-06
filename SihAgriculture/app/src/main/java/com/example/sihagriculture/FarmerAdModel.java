package com.example.sihagriculture;

/**
 * Created by Anurag on 28-03-2018.
 */

public class FarmerAdModel {
    public String productname;
    public String maxamount;
    public String priceperunit;
    public String id;
    public String description;

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

    public String dateuploaded;

    public FarmerAdModel(String productname, String maxamount, String priceperunit, String id, String description, String dateuploaded) {
        this.productname = productname;
        this.maxamount = maxamount;
        this.priceperunit = priceperunit;
        this.id = id;
        this.description = description;
        this.dateuploaded = dateuploaded;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getMaxamount() {
        return maxamount;
    }

    public void setMaxamount(String maxamount) {
        this.maxamount = maxamount;
    }

    public String getPriceperunit() {
        return priceperunit;
    }

    public void setPriceperunit(String priceperunit) {
        this.priceperunit = priceperunit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
