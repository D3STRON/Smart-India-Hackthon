package com.example.sihagriculture;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Anurag on 30-03-2018.
 */

public class BuyParser extends AsyncTask<String, Integer, ArrayList<SellerModel>> {
    public String errormessage, productName, productType, aadhar, number, quantity;
    public Interface interfac;
    BuyParser(Interface interfac ,String productName, String productType, String aadhar, String number, String quantity){
        this.interfac=interfac;
        this.productName=productName;
        this.productType= productType;
        this.aadhar= aadhar;
        this.number= number;
        this.quantity=quantity;
    }

    @Override
    protected ArrayList<SellerModel> doInBackground(String... params) {
        ArrayList<SellerModel> sellerModels= new ArrayList<>();
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("aadhar", aadhar)
                .add("productType",productType)
                .add("productName",productName)
                .add("number",number)
                .add("quantity",quantity)
                .build();
        //172.28.24.95:32000
        Request request = new Request.Builder().url("http://krishivigyan.localtunnel.me/market/placeOrderRequest").post(requestBody).addHeader("Content-Type", "application/json").build();
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
        return sellerModels;
    }

    @Override
    protected void onPostExecute(ArrayList<SellerModel> sellerModels) {

        super.onPostExecute(sellerModels);
        interfac.success(sellerModels);
    }
}
