package com.example.agromart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Loginscreen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen2);
    }

    public void register_buyer(View view){
        startActivity(new Intent(Loginscreen2.this,Register_as_buyer.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void register_seller(View view){
        startActivity(new Intent(Loginscreen2.this,Register_as_seller.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}