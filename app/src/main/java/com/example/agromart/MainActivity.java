package com.example.agromart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        final Handler handler = new Handler();
        if(currentuser!= null){
            handler.postDelayed(new Runnable() {
                public void run() {
                    // TODO: Your application init goes here.
                    Intent mInHome = new Intent(MainActivity.this, Homescreen.class);
                    MainActivity.this.startActivity(mInHome);
                    MainActivity.this.finish();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }, 2000);
        }
        else{
            handler.postDelayed(new Runnable() {
                public void run() {
                    // TODO: Your application init goes here.
                    Intent mInHome = new Intent(MainActivity.this, Loginscreen2.class);
                    MainActivity.this.startActivity(mInHome);
                    MainActivity.this.finish();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }, 2000);
        }

    }
}