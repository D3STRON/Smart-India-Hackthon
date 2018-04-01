package com.example.sihagriculture;

import java.util.ArrayList;

/**
 * Created by Anurag on 30-03-2018.
 */

public interface RegisterInterface {
    public void success(ArrayList<UserModel> userModels);
    public  void error(String errormessage);
}
