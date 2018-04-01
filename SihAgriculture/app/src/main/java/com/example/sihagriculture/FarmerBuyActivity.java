package com.example.sihagriculture;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static java.security.AccessController.getContext;

public class FarmerBuyActivity extends AppCompatActivity implements Interface{
    CardView fertilizers, seeds, pesticides, tools, others, crops;
    CardView cardView;
    String type="";
    String name="";
    String quantity="";
    ListView typename;
    ArrayList<String> seednames= new ArrayList<>();
    ArrayList<String> fertilizernames= new ArrayList<>();
    ArrayList<String> pesticidenames= new ArrayList<>();
    ArrayList<String> toolnames= new ArrayList<>();
    ArrayList<String> othername= new ArrayList<>();
    ArrayList<String> current= new ArrayList<>();
    ArrayList<String> cropnames= new ArrayList<>();
    CustomAdapter customAdapter;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_buy);
        fertilizers=findViewById(R.id.fertilizers);
        seeds=findViewById(R.id.seeds);
        pesticides=findViewById(R.id.pesticides);
        cardView= findViewById(R.id.cardview);
        tools=findViewById(R.id.tools);
        others=findViewById(R.id.others);
        typename= findViewById(R.id.typenames);
        crops= findViewById(R.id.crops);



        fertilizers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="fertilizers";
                setter();//setting the values of the list
                fertilizers.setVisibility(View.GONE);
                seeds.setVisibility(View.GONE);
                pesticides.setVisibility(View.GONE);
                tools.setVisibility(View.GONE);
                others.setVisibility(View.GONE);
                crops.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                customAdapter= new CustomAdapter(FarmerBuyActivity.this,fertilizernames);
                typename.setAdapter(customAdapter);
                typename.setVisibility(View.VISIBLE);
            }
        });

        seeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="seeds";
                setter();//setting the values of the list
                fertilizers.setVisibility(View.GONE);
                seeds.setVisibility(View.GONE);
                pesticides.setVisibility(View.GONE);
                tools.setVisibility(View.GONE);
                others.setVisibility(View.GONE);
                crops.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                customAdapter= new CustomAdapter(FarmerBuyActivity.this,seednames);
                typename.setAdapter(customAdapter);
                typename.setVisibility(View.VISIBLE);
            }
        });
        pesticides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="pesticides";
                setter();//setting the values of the list
                fertilizers.setVisibility(View.GONE);
                seeds.setVisibility(View.GONE);
                pesticides.setVisibility(View.GONE);
                tools.setVisibility(View.GONE);
                others.setVisibility(View.GONE);
                crops.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                customAdapter= new CustomAdapter(FarmerBuyActivity.this,pesticidenames);
                typename.setAdapter(customAdapter);
                typename.setVisibility(View.VISIBLE);
            }
        });

        tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="tools";
                setter();//setting the values of the list
                fertilizers.setVisibility(View.GONE);
                seeds.setVisibility(View.GONE);
                pesticides.setVisibility(View.GONE);
                tools.setVisibility(View.GONE);
                others.setVisibility(View.GONE);
                crops.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                customAdapter= new CustomAdapter(FarmerBuyActivity.this,toolnames);
                typename.setAdapter(customAdapter);
                typename.setVisibility(View.VISIBLE);
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="others";
                setter();//setting the values of the list
                fertilizers.setVisibility(View.GONE);
                seeds.setVisibility(View.GONE);
                pesticides.setVisibility(View.GONE);
                tools.setVisibility(View.GONE);
                others.setVisibility(View.GONE);
                crops.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                customAdapter= new CustomAdapter(FarmerBuyActivity.this,othername);
                typename.setAdapter(customAdapter);
                typename.setVisibility(View.VISIBLE);
            }
        });

        crops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="crops";
                setter();//setting the values of the list
                fertilizers.setVisibility(View.GONE);
                seeds.setVisibility(View.GONE);
                pesticides.setVisibility(View.GONE);
                tools.setVisibility(View.GONE);
                others.setVisibility(View.GONE);
                crops.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                customAdapter= new CustomAdapter(FarmerBuyActivity.this,cropnames);
                typename.setAdapter(customAdapter);
                typename.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void success(ArrayList<SellerModel> sellerModels) {
        //Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error(String errormessage) {

    }

    public class CustomAdapter extends BaseAdapter implements ListAdapter{
        Context context;
        ArrayList<String> currentlist= new ArrayList<>();
        CustomAdapter(Context context, ArrayList<String> currentlist)
        {
            this.context=context;
            this.currentlist=currentlist;
        }

        CustomAdapter(){

        }
        @Override
        public int getCount() {
            return currentlist.size();
        }

        @Override
        public Object getItem(int i) {
            return currentlist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.row, null);
            }
            TextView productname= view.findViewById(R.id.productname);
            FloatingActionButton fillamount= view.findViewById(R.id.fillamount);
            productname.setText(currentlist.get(i));

            fillamount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    name=currentlist.get(i);
                    conformation();
                }
            });
            return view;
        }


    }
    public void setter(){
        seednames.clear();
        fertilizernames.clear();
        cropnames.clear();
        toolnames.clear();
        seednames.add("Mint");
        seednames.add("Balmrosa");
        seednames.add("Lemon grass");
        fertilizernames.add("Potash ");
        fertilizernames.add("Urea ");
        fertilizernames.add("Nitrogen ");
        cropnames.add("Mint");
        cropnames.add("Balmrosa");
        cropnames.add("Lemon grass");
        toolnames.add("Tractor");
        toolnames.add("Grain Saperator");
        toolnames.add("Hand Tractor");
    }

    @Override
    public void onBackPressed() {

        if(type.equals(""))
        {
            finish();
        }else{
            type="";
            fertilizers.setVisibility(View.VISIBLE);
            seeds.setVisibility(View.VISIBLE);
            pesticides.setVisibility(View.VISIBLE);
            tools.setVisibility(View.VISIBLE);
            others.setVisibility(View.VISIBLE);
            crops.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
        }
    }

    public void conformation() {
        final AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        final View conformationview = layoutInflater.inflate(R.layout.fill_amount, null);
        dialogbuilder.setView(conformationview);
        dialogbuilder.setTitle("PLACE ORDER");
        dialogbuilder.setMessage("Are You Sure?");
        final EditText quantity= (EditText) conformationview.findViewById(R.id.amount);
        Button placeorder = (Button) conformationview.findViewById(R.id.placeorder);
        Button cancel = (Button) conformationview.findViewById(R.id.cancel);
        final AlertDialog conformationdialog = dialogbuilder.create();
        conformationdialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conformationdialog.dismiss();
            }
        });
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aadhar = "",number="";
                SharedPreferences prefs = getSharedPreferences("USER_DETAILS", MODE_PRIVATE);
                String restoredText = prefs.getString("aadhar", null);
                if (restoredText != null) {
                     aadhar = prefs.getString("aadhar", "No name defined");//"No name defined" is the default value.
                     number = prefs.getString("number", null);
                }
                //Toast.makeText(FarmerBuyActivity.this, number+" "+aadhar+" "+quantity.getText().toString(), Toast.LENGTH_SHORT).show();
               BuyParser buyParser= new BuyParser( FarmerBuyActivity.this,name,type,aadhar, number,quantity.getText().toString());
               buyParser.execute();
            }
        });
    }

}
/*
if(type.equals("seeds")){
            }
            else if(type.equals("fertilizers")){

            }
            else if(type.equals("pesticides")){

            }
            else if(type.equals("tools"))
            {

            }
            else if(type.equals("others"))
            {

            }
            else
            {

            }
 */