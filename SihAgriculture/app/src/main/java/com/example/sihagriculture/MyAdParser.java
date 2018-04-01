package com.example.sihagriculture;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Anurag on 31-03-2018.
 */

public class MyAdParser  extends AsyncTask<String, Integer, ArrayList<MarketSellModel>> {
    public String errormessage,aadhar;
    public MyAdInterface myAdInterface;
    public ArrayList<MarketSellModel> marketSellModels = new ArrayList<>();
    MyAdParser(MyAdInterface myAdInterface, String aadhar){
        this.myAdInterface=myAdInterface;
        this.aadhar= aadhar;
    }

    @Override
    protected ArrayList<MarketSellModel> doInBackground(String... params) {
        OkHttpClient okHttpClient = new OkHttpClient();

        //172.28.24.95:32000
        Request request = new Request.Builder().url("http://172.28.24.30:32000/market/viewUserListings?aadhar="+aadhar).addHeader("Content-Type", "application/json").build();
       // System.out.println(url);
        try {
            Response response = okHttpClient.newCall(request).execute();


            String responseBody = response.body().string();

            if(response.code()== 200){

                JSONObject hgh= new JSONObject(responseBody);
                JSONArray asd = new JSONArray(hgh.getString("data"));
               // JSONObject asda= new JSONObject(asd.get(0).toString());

                //JSONArray asds = new JSONArray(hgh.getString("data"));
                //  JSONObject OBJ= new JSONObject(response.body().string());
                // JSONArray jsonArray= OBJ.getJSONArray("data");
                for(int i=0;i<asd.length();i++){

                    JSONObject temp = asd.getJSONObject(i);
                    Log.d(TAG, temp.toString(),null);
                    MarketSellModel marketSellModel = new MarketSellModel(temp.getString("price"),temp.getString("adId"),
                            temp.getString("quantity"),temp.getString("productName"),temp.getString("productType"),
                            temp.getString("description"));

                    marketSellModels.add(marketSellModel);
                }

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
       return marketSellModels;
    }

    @Override
    protected void onPostExecute(ArrayList<MarketSellModel> marketSellModels) {

        super.onPostExecute(marketSellModels);
        myAdInterface.success(marketSellModels);
    }
}
