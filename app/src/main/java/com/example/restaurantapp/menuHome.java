package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.sip.SipAudioCall;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.restaurantapp.Category;
import com.example.restaurantapp.MenuAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class menuHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_home);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        String [] captions = new String[Category.categories.length];
        int [] images = new int[Category.categories.length];

        for(int i = 0; i<captions.length; i++){
            captions[i] = Category.categories[i].getName();
            images[i] = Category.categories[i].getImage();
        }
        recycler.setLayoutManager(new GridLayoutManager(this,2));
        MenuAdapter adapter = new MenuAdapter(captions, images);
        recycler.setAdapter(adapter);
    }

}