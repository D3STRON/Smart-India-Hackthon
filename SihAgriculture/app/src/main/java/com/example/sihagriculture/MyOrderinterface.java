package com.example.sihagriculture;

import java.util.ArrayList;

/**
 * Created by Anurag on 31-03-2018.
 */

public interface MyOrderinterface {
    public void success(ArrayList<MyOdersModel> myOdersModels);
    public  void error(String errormessage);
}
