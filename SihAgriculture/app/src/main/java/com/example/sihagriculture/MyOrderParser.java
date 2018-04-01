package com.example.sihagriculture;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.http.RequestException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Anurag on 31-03-2018.
 */

public class MyOrderParser  extends AsyncTask<String, Integer, ArrayList<MyOdersModel>> {
    public String errormessage,aadhar;
    public MyOrderinterface myOrderinterface;
    MyOrderParser(MyOrderinterface myOrderinterface, String aadhar){
        this.myOrderinterface=myOrderinterface;
        this.aadhar= aadhar;
    }

    @Override
    protected ArrayList<MyOdersModel> doInBackground(String... params) {
        ArrayList<MyOdersModel> myOdersModels= new ArrayList<>();
        OkHttpClient okHttpClient = new OkHttpClient();

        //172.28.24.95:32000
        Request request = new Request.Builder().url("http://172.28.24.30:32000/market/viewBuyerOrderRequests?aadhar="+aadhar).addHeader("Content-Type", "application/json").build();
        //System.out.println(url);
        try {
            Response response = okHttpClient.newCall(request).execute();


            String responseBody = response.body().string();

            if(response.code()== 200){

                JSONObject hgh= new JSONObject(responseBody);
                JSONArray asd = new JSONArray(hgh.getString("data"));
                JSONObject asda= new JSONObject(asd.get(0).toString());
                //Log.d("sex", asda.toString());

                //JSONArray asds = new JSONArray(hgh.getString("data"));
              //  JSONObject OBJ= new JSONObject(response.body().string());
               // JSONArray jsonArray= OBJ.getJSONArray("data");
               for(int i=0;i<asd.length();i++){

                   JSONObject temp = asd.getJSONObject(i);
                   MyOdersModel myOdersModel =new MyOdersModel(temp.getString("productType")
                           ,temp.getString("productName"),temp.getString("quantity"),
                           temp.getString("orderId"),temp.getString("approved"));
                   myOdersModels.add(myOdersModel);
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
        MyOdersModel myOdersModel = new MyOdersModel("seeds","Mint","23","233333","aprooved");
        myOdersModels.add(myOdersModel);
        MyOdersModel myOdersModel2 = new MyOdersModel("crops","Mint","23","233333","aprooved");
        myOdersModels.add(myOdersModel2);
        return myOdersModels;
    }

    @Override
    protected void onPostExecute(ArrayList<MyOdersModel> myOdersModels) {

        super.onPostExecute(myOdersModels);
        myOrderinterface.success(myOdersModels);
    }
}
