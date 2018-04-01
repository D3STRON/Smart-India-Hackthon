package com.example.sihagriculture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static android.content.ContentValues.TAG;

public class FarmerAdActivity extends AppCompatActivity {
    Spinner productname;
    EditText priceperkg, maxweight, description;
    Double latitude, longitude;
    CardView cardView;
    LinearLayout form;
    Boolean frm=false;
    Button advertise;
    String type="";
    CardView seeds, fertilizers, pesticides, tools , others ,crops;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_ad);

       /* getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.mystatusbarcolor));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#228B22")));
*/
        productname= findViewById(R.id.cropname);
        cardView=findViewById(R.id.cardView);
        seeds= findViewById(R.id.seeds);
        fertilizers= findViewById(R.id.fertilizers);
        pesticides= findViewById(R.id.pesticides);
        tools= findViewById(R.id.tools);
        others= findViewById(R.id.others);
        form= findViewById(R.id.form);
        crops= findViewById(R.id.crops);
        productname.setSelection(1);
        description= findViewById(R.id.description);
        priceperkg= findViewById( R.id.priceperkg);
        maxweight= findViewById(R.id.maxweight);
        advertise= findViewById(R.id.advertise);
        advertise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key= FirebaseDatabase.getInstance().getReference("farmer").child("demoFarmer").child("myAds").push().getKey();

                Date currentLocalTime = Calendar.getInstance().getTime();
                Long dat = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
                String datestring = sdf.format(dat);

               /* FarmerAdModel farmerAdModel = new FarmerAdModel(productname.getSelectedItem().toString(),
                        maxweight.getText().toString(),priceperkg.getText().toString(),
                        key,description.getText().toString(),datestring);
                MarketSellModel marketSellModel = new MarketSellModel(Double.toString(latitude),Double.toString(longitude),
                        "0",key,key,
                              "demoFarmer","Panvel",description.getText().toString(),//hard coded here
                        datestring,priceperkg.getText().toString());*/
                Toast.makeText(FarmerAdActivity.this, "Your Product is Places in the Market!", Toast.LENGTH_SHORT).show();
                SharedPreferences prefs = getSharedPreferences("USER_DETAILS", MODE_PRIVATE);
                String aadhar= prefs.getString("aadhar", "No name defined");
                SendAdtoDb sendAdtoDb= new SendAdtoDb(aadhar,type,productname.getSelectedItem().toString(),description.getText().toString(),
                        maxweight.getText().toString(),priceperkg.getText().toString());
                sendAdtoDb.execute();
            }
        });

        crops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frm=true;
                type="crops";
                ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(FarmerAdActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.cropnames));
                mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                productname.setAdapter(mAdapter);
                cardView.setVisibility(View.GONE);
                form.setVisibility(View.VISIBLE);
            }
        });

        seeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frm=true;
                type="seeds";
                ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(FarmerAdActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.seednames));
                mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                productname.setAdapter(mAdapter);
                cardView.setVisibility(View.GONE);
                form.setVisibility(View.VISIBLE);
            }
        });

        fertilizers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frm=true;
                type="fertilizers";
                ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(FarmerAdActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.fertilizernames));
                mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                productname.setAdapter(mAdapter);
                cardView.setVisibility(View.GONE);
                form.setVisibility(View.VISIBLE);
            }
        });

        pesticides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frm=true;
                type="pesticides";
                ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(FarmerAdActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.pesticidenames));
                mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                productname.setAdapter(mAdapter);
                cardView.setVisibility(View.GONE);
                form.setVisibility(View.VISIBLE);
            }
        });

        tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frm=true;
                type="tools";
                ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(FarmerAdActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.toolnames));
                mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                productname.setAdapter(mAdapter);
                cardView.setVisibility(View.GONE);
                form.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(frm==false)
        {
            finish();
        }
        else{
            frm=false;
            cardView.setVisibility(View.VISIBLE);
            form.setVisibility(View.GONE);
        }
    }



}
