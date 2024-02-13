package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderScreen extends AppCompatActivity {
    ListView list;
    CheckBox deliveryBox;
    EditText addressTf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_screen);

        deliveryBox = findViewById(R.id.deliveryBox);
        addressTf = findViewById(R.id.addressTf);


        list = findViewById(R.id.list);
//        Order order = (Order) getIntent().getSerializableExtra("order");
//        orderList.add(order);
        Intent intent = new Intent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        if(args != null) {
            ArrayList<Order> orderList = (ArrayList<Order>) args.getSerializable("ARRAYLIST");
            ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(OrderScreen.this, android.R.layout.simple_list_item_1, orderList);
            list.setAdapter(adapter);
        }



    }
//    // button
//    public void deliverCheck(View view){
//        deliveryBox.setChecked(true);
//        if(deliveryBox.isChecked()){
//            addressTf.setEnabled(true);
//            addressTf.setVisibility(View.VISIBLE);
//        }else{
//            addressTf.setEnabled(false);
//            addressTf.setVisibility(View.INVISIBLE);
//        }
//    }

    public void addOrder(View view){
        String address = null;
        if(deliveryBox.isChecked()){
            address = addressTf.getText().toString();
            Toast.makeText(this, "del", Toast.LENGTH_SHORT).show();
        }else{
            address = "Pick Up";
            Toast.makeText(this, "pick", Toast.LENGTH_SHORT).show();
        }

    //    placeOrder("Lam","4 CheeseBurger", "cooking", 35.0, address );
    }


    public void placeOrder(String customerId, String orderStr, String status, double payment, String address){
        String url = "http://192.168.1.111/Restaurant/addOrder.php";
        RequestQueue queue = Volley.newRequestQueue(OrderScreen.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(OrderScreen.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(OrderScreen.this,
                        "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our
                // key and value pair to our parameters.
                params.put("customerId", customerId);
                params.put("orderStr", orderStr);
                params.put("status", status);
                params.put("payment", String.valueOf(payment));
                params.put("address", address);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }


}