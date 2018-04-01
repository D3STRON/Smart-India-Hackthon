package com.example.sihagriculture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity implements RegisterInterface{
    EditText adharno, password, number;
    Button login;
    Double latitude=19.09, longitude=73.03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getApplicationContext(), GpsTrackerActivity.class);
        startActivityForResult(intent,1);

        SharedPreferences prefs = getSharedPreferences("USER_DETAILS", MODE_PRIVATE);
        String restoredText = prefs.getString("number", null);
        if (restoredText != null) {
            Toast.makeText(this, restoredText, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,DashBoardActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);
        adharno= findViewById(R.id.adharno);
        password= findViewById(R.id.password);
        number=findViewById(R.id.number);
        login= findViewById(R.id.login);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(password.getText().toString().length()>6) {
                   UserModel userModel = new UserModel(adharno.getText().toString(), Double.toString(latitude),
                           Double.toString(longitude), "0", password.getText().toString(),number.getText().toString());

                   SharedPreferences.Editor editor = getSharedPreferences("USER_DETAILS", MODE_PRIVATE).edit();
                   editor.putString("aadhar", adharno.getText().toString());
                   editor.putString("number", number.getText().toString());
                   editor.apply();

                   RegisterParser registerParser = new RegisterParser(LoginActivity.this, userModel);
                   registerParser.execute();
                   startActivity(new Intent(LoginActivity.this,DashBoardActivity.class));
               }else{
                   Toast.makeText(LoginActivity.this, "Your password Should be 6 characters atleat!", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Bundle extras = data.getExtras();
            longitude =extras.getDouble("longitude") ;
            //Toast.makeText(this, Double.toString(longitude), Toast.LENGTH_SHORT).show();
            latitude = extras.getDouble("latitude");
            //Toast.makeText(this, Double.toString(latitude), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void success(ArrayList<UserModel> userModels) {
        Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error(String errormessage) {

    }

}
