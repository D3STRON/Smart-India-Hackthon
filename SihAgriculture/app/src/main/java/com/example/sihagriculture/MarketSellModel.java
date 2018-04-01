package com.example.sihagriculture;

/**
 * Created by Anurag on 28-03-2018.
 */

public class MarketSellModel {
    public String priceperunit, sellid, maxquantity, productname, producttype, productdesc;

    public MarketSellModel(String priceperunit, String sellid, String maxquantity, String productname, String producttype, String productdesc) {
        this.priceperunit = priceperunit;
        this.sellid = sellid;
        this.maxquantity = maxquantity;
        this.productname = productname;
        this.producttype = producttype;
        this.productdesc = productdesc;
    }

    public String getPriceperunit() {
        return priceperunit;
    }
MarketSellModel()
{

}
    public void setPriceperunit(String priceperunit) {
        this.priceperunit = priceperunit;
    }

    public String getSellid() {
        return sellid;
    }

    public void setSellid(String sellid) {
        this.sellid = sellid;
    }

    public String getMaxquantity() {
        return maxquantity;
    }

    public void setMaxquantity(String maxquantity) {
        this.maxquantity = maxquantity;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }
}
