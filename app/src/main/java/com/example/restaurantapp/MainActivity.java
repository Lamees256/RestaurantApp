package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button guestBtn;
    private Button employeeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guestBtn = findViewById(R.id.guestBt);
        employeeBtn = findViewById(R.id.employeeBt);
    }
    public void guestBt(View view) {
        Intent intent = new Intent(this, menuHome.class);
        startActivity(intent);
    }
    public void employeeBt(View view) {
        Intent intent = new Intent(this, employeeLogin.class);
        startActivity(intent);
    }

}