package com.example.sihagriculture;

/**
 * Created by Anurag on 31-03-2018.
 */

public class MyOdersModel {
    public String productType, productName, quantity, id ,approved;

    public MyOdersModel(String productType, String productName, String quantity, String id, String approved) {
        this.productType = productType;
        this.productName = productName;
        this.quantity = quantity;
        this.id = id;
        this.approved= approved;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
