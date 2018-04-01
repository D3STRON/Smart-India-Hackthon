package com.example.sihagriculture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewSellersActivity extends AppCompatActivity implements ViewSellerInterface {
     RecyclerView sellerlist;
     RecyclerAdapter recyclerAdapter;
     ArrayList<SellerModel> sellerModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sellers);
        sellerlist= findViewById(R.id.sellerlist);
        sellerlist.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        sellerlist.setLayoutManager(mLayoutManager);
        sellerModels.add(new SellerModel("","","","","","",""));
        recyclerAdapter= new RecyclerAdapter(sellerModels);
        sellerlist.setAdapter(recyclerAdapter);
    }

    @Override
    public void success(ArrayList<SellerModel> sellerModels) {
          this.sellerModels= sellerModels;
          recyclerAdapter = new RecyclerAdapter(sellerModels);
          sellerlist.setAdapter(recyclerAdapter);
    }

    @Override
    public void error(String errormessage) {

    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    {
        ArrayList<SellerModel> sellerModels = new ArrayList<>();

        RecyclerAdapter(ArrayList<SellerModel> sellerModels){
            this.sellerModels=sellerModels;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView sellername,maxquantity, productdesc,priceperunit, distance;
            public ViewHolder(View v) {
                super(v);
                sellername =v.findViewById(R.id.sellername);
                maxquantity=v.findViewById(R.id.maxquantity);
                productdesc= v.findViewById(R.id.productdesc);
                priceperunit= v.findViewById(R.id.priceperunit);
                distance= v.findViewById(R.id.distance);
                }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.seller_row, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((ViewHolder)holder).sellername.setText(sellerModels.get(position).getName());
            ((ViewHolder)holder).productdesc.setText(sellerModels.get(position).getProductdesc());
            ((ViewHolder)holder).maxquantity.setText(sellerModels.get(position).getMaxquantity());
            ((ViewHolder)holder).productdesc.setText(sellerModels.get(position).getProductdesc());
            ((ViewHolder)holder).priceperunit.setText(sellerModels.get(position).getPriceperunit());
        }

        @Override
        public int getItemCount() {
            return sellerModels.size();
        }
    }
}
