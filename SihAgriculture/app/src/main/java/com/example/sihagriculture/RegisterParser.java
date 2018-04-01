package com.example.sihagriculture;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Anurag on 30-03-2018.
 */

public class RegisterParser  extends AsyncTask<String, Integer, ArrayList<UserModel>> {
    public String errormessage;
    public RegisterInterface registerInterface;
    UserModel userModel;
    RegisterParser(RegisterInterface registerInterface, UserModel userModel){
        this.registerInterface=registerInterface;
        this.userModel=userModel;
    }

    @Override
    protected ArrayList<UserModel> doInBackground(String... params) {
        ArrayList<UserModel> userModels= new ArrayList<>();
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("aadhar", userModel.getAadhar())
                .add("latitude",userModel.getLatitude())
                .add("longitude",userModel.getLongitude())
                .add("distance",userModel.getDistance())
                .add("password",userModel.getPassword())
                .add("number",userModel.getNumber())
                .build();
        Request request = new Request.Builder().url("http://krishivigyan.localtunnel.me/registerFarmer").post(requestBody).addHeader("Content-Type", "application/json").build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if(response.code()==200){
                JSONObject OBJ= new JSONObject(response.body().string());
                Log.e(TAG, OBJ.toString(),null);
                /*for(int i =0 ;i<jsonArray.length();i++){

                }*/
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
        return userModels;
    }

    @Override
    protected void onPostExecute(ArrayList<UserModel> userModels) {

        super.onPostExecute(userModels);
        registerInterface.success(userModels);
    }
}
