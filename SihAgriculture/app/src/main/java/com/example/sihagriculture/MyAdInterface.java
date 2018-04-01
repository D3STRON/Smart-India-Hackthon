package com.example.sihagriculture;

import java.util.ArrayList;

/**
 * Created by Anurag on 31-03-2018.
 */

public interface MyAdInterface {
    public void success(ArrayList<MarketSellModel> marketSellModels);
    public  void error(String errormessage);
}
