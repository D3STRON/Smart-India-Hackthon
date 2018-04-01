package com.example.sihagriculture;

import java.util.ArrayList;

/**
 * Created by Anurag on 31-03-2018.
 */

public interface ViewSellerInterface {
    public void success(ArrayList<SellerModel> sellerModels);
    public  void error(String errormessage);
}
