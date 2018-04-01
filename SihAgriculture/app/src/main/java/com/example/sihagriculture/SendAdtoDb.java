package com.example.sihagriculture;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Anurag on 31-03-2018.
 */

public class SendAdtoDb extends AsyncTask{
    String aadhar,type,productname,description,maxweight,priceperkg;

    SendAdtoDb(String aadhar,String type , String productname,String description, String maxweight, String priceperkg){
        this.aadhar=aadhar;
        this.type=type;
        this.productname=productname;
        this.maxweight=maxweight;
        this.description=description;
        this.priceperkg=priceperkg;
    }
    @Override
    protected Object doInBackground(Object[] objects) {

        String errormessage="";
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("aadhar", aadhar)
                .add("productType",type)
                .add("productName", productname)
                .add("maxweight",maxweight)
                .add("description",maxweight)
                .add("priceperunit",priceperkg)
                .build();
        //172.28.24.95:32000
        Request request = new Request.Builder().url("http://krishivigyan.localtunnel.me/market/addSellerProduct").post(requestBody).addHeader("Content-Type", "application/json").build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if(response.code()==200){
                JSONObject OBJ= new JSONObject(response.body().string());
                Log.e(TAG, OBJ.toString(),null);
            }else if (response.code() == 404) {

                errormessage="Server not found";
            } else if (response.code() == 500)
            {
                errormessage="Service error";
            }
        } catch (IOException e)
        {
            errormessage="Problem in connection";
        }
        catch (JSONException e)
        {
            errormessage="Error while parsing";
        }

        return null;
    }
}
