package com.example.sihagriculture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class FarmerAdActivity extends AppCompatActivity {
    Spinner cropname;
    EditText priceperkg, maxweight, description;
    Double latitude, longitude;
    Button advertise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getApplicationContext(), GpsTrackerActivity.class);
        startActivityForResult(intent,1);
        setContentView(R.layout.activity_farmer_ad);
        cropname= findViewById(R.id.cropname);
        ArrayAdapter<String> mAdapterCrops = new ArrayAdapter<String>(FarmerAdActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.cropnames));
        mAdapterCrops.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cropname.setAdapter(mAdapterCrops);
        cropname.setSelection(1);
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

                FarmerAdModel farmerAdModel = new FarmerAdModel(cropname.getSelectedItem().toString(),
                        maxweight.getText().toString(),priceperkg.getText().toString(),
                        key,description.getText().toString(),datestring);
                MarketSellModel marketSellModel = new MarketSellModel(Double.toString(latitude),Double.toString(longitude),
                        "0",key,key,
                              "demoFarmer","Panvel",description.getText().toString(),//hard coded here
                        datestring,priceperkg.getText().toString(),maxweight.getText().toString());
                Toast.makeText(FarmerAdActivity.this, datestring, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Bundle extras = data.getExtras();
            longitude = 19.09;
            //Toast.makeText(this, Double.toString(longitude), Toast.LENGTH_SHORT).show();
            latitude = 73.12;
            //Toast.makeText(this, Double.toString(latitude), Toast.LENGTH_SHORT).show();
        }
    }
}
