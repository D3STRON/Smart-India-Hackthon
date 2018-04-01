package com.example.sihagriculture;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdsActivity extends AppCompatActivity implements MyAdInterface{
    RecyclerView listings;
    ArrayList<MarketSellModel> marketSellModels = new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ads);
        listings= findViewById(R.id.listings);
        SharedPreferences prefs = getSharedPreferences("USER_DETAILS", MODE_PRIVATE);
        String aadhar= prefs.getString("aadhar", "No name defined");

        MyAdParser myAdParser= new MyAdParser(MyAdsActivity.this,aadhar);
        myAdParser.execute();
        listings.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        listings.setLayoutManager(mLayoutManager);
        recyclerAdapter= new RecyclerAdapter(marketSellModels);
        listings.setAdapter(recyclerAdapter);
    }

    @Override
    public void success(ArrayList<MarketSellModel> marketSellModels) {
        this.marketSellModels=marketSellModels;
      //  Toast.makeText(MyAdsActivity.this, marketSellModels.get(0).getPriceperunit(), Toast.LENGTH_SHORT).show();
        recyclerAdapter= new RecyclerAdapter(marketSellModels);
        listings.setAdapter(recyclerAdapter);
    }

    @Override
    public void error(String errormessage) {

    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    {
        ArrayList<MarketSellModel> marketSellModels = new ArrayList<>();

        RecyclerAdapter(ArrayList<MarketSellModel> marketSellModels){
            this.marketSellModels=marketSellModels;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView producttype,productname,maxquantity, productdesc,priceperunit;
            public Button remove, edit;
            public ViewHolder(View v) {
                super(v);
                producttype =v.findViewById(R.id.producttype);
                maxquantity=v.findViewById(R.id.maxquantity);
                productname=v.findViewById(R.id.productname);
                productdesc= v.findViewById(R.id.productdesc);
                priceperunit= v.findViewById(R.id.priceperunit);
                remove=v.findViewById(R.id.remove);
                edit=v.findViewById(R.id.edit);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ad_row, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((ViewHolder)holder).productname.setText(marketSellModels.get(position).getProductname());
            ((ViewHolder)holder).producttype.setText(marketSellModels.get(position).getProducttype());
            ((ViewHolder)holder).maxquantity.setText(marketSellModels.get(position).getMaxquantity());
             ((ViewHolder)holder).productdesc.setText(marketSellModels.get(position).getProductdesc());
            ((ViewHolder)holder).priceperunit.setText(marketSellModels.get(position).getPriceperunit());

            ((ViewHolder)holder).edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            ((ViewHolder)holder).remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     marketSellModels.remove(position);
                     recyclerAdapter =new RecyclerAdapter(marketSellModels);
                     listings.setAdapter(recyclerAdapter);
                }
            });

        }

        @Override
        public int getItemCount() {
            return marketSellModels.size();
        }
    }
}
