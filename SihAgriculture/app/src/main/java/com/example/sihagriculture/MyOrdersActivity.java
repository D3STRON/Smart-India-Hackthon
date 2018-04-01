package com.example.sihagriculture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MyOrdersActivity extends AppCompatActivity implements MyOrderinterface {
    RecyclerView orderlist;
    ArrayList<MyOdersModel> myOdersModels= new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        SharedPreferences prefs = getSharedPreferences("USER_DETAILS", MODE_PRIVATE);
        String aadhar= prefs.getString("aadhar", "No name defined");
        MyOrderParser myOrderParser = new MyOrderParser(MyOrdersActivity.this,aadhar);
        myOrderParser.execute();

        /*getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.mystatusbarcolor));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#228B22")));
*/
        orderlist= findViewById(R.id.orderlist);
        orderlist.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        orderlist.setLayoutManager(mLayoutManager);
        recyclerAdapter= new RecyclerAdapter(myOdersModels);
        orderlist.setAdapter(recyclerAdapter);
    }

    @Override
    public void success(ArrayList<MyOdersModel> myOdersModels) {
        this.myOdersModels=myOdersModels;
        recyclerAdapter = new RecyclerAdapter(myOdersModels);
        orderlist.setAdapter(recyclerAdapter);
    }

    @Override
    public void error(String errormessage) {

    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    {
        ArrayList<MyOdersModel> myOdersModels = new ArrayList<>();

        RecyclerAdapter(ArrayList<MyOdersModel> myOdersModels){
            this.myOdersModels=myOdersModels;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView producttype,productname,quantity;
            public Button remove, viewsellers;
            public ViewHolder(View v) {
                super(v);
                producttype =v.findViewById(R.id.producttype);
                quantity=v.findViewById(R.id.quantity);
                productname=v.findViewById(R.id.productname);
                remove=v.findViewById(R.id.remove);
                viewsellers= v.findViewById(R.id.viewsellers);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.order_row, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((ViewHolder)holder).productname.setText(myOdersModels.get(position).getProductName());
            ((ViewHolder)holder).producttype.setText(myOdersModels.get(position).getProductType());
            ((ViewHolder)holder).quantity.setText(myOdersModels.get(position).getQuantity());
            ((ViewHolder)holder).remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RemoveFromDb removeFromDb = new RemoveFromDb(myOdersModels.get(position).getId());
                    removeFromDb.execute();
                    myOdersModels.remove(position);
                    recyclerAdapter= new RecyclerAdapter(myOdersModels);
                    orderlist.setAdapter(recyclerAdapter);
                }
            });
            ((ViewHolder)holder).viewsellers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(MyOrdersActivity.this,ViewSellersActivity.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return myOdersModels.size();
        }
    }
    public class RemoveFromDb extends AsyncTask{

        String errormessage="", orderId;
        RemoveFromDb(String orderId){
            this.orderId=orderId;
        }
        @Override
        protected Object doInBackground(Object[] objects) {

            OkHttpClient okHttpClient = new OkHttpClient();
            SharedPreferences prefs = getSharedPreferences("USER_DETAILS", MODE_PRIVATE);
            String aadhar= prefs.getString("aadhar", "No name defined");
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("aadhar", aadhar)
                    .add("orderId", orderId)
                    .build();
            //172.28.24.95:32000
            Request request = new Request.Builder().url("http://krishivigyan.localtunnel.me/market/removeOrderRequest").post(requestBody).addHeader("Content-Type", "application/json").build();
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

            return null;
        }

    }
}
