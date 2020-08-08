package com.example.agromart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Loginscreen2 extends AppCompatActivity {
    EditText mmail,mpassword;
    Button login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen2);

        mmail = findViewById(R.id.editTextTextPersonName);
        mpassword = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.button4);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = mmail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();

                if(mail.isEmpty()){
                    mmail.setError("enter valid mail id");
                    mmail.requestFocus();
                    return;
                }
                if(password.isEmpty() || password.length()<6){
                    mpassword.setError("Password should atleast be 6 characters");
                    mpassword.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(Loginscreen2.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(Loginscreen2.this, "Succesfully logged in.",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Loginscreen2.this,Homescreen.class));
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user
                                    Toast.makeText(Loginscreen2.this, "Login failed.",
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });

            }
        });
    }

    public void register_buyer(View view){
        startActivity(new Intent(Loginscreen2.this,Register_as_buyer.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void register_seller(View view){
        startActivity(new Intent(Loginscreen2.this,Register_as_seller.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void show_hide_password(View view){
        if(view.getId()==R.id.show_hide_password2){

            if(mpassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.icon_hide);

                //Show Password
                mpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.icon_show);

                //Hide Password
                mpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
    public void reset_password(View view){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        //String emailAddress = "user@example.com";
        String mail = mmail.getText().toString().trim();
        if(mail.isEmpty()){
            mmail.setError("Enter mail id where the password reset link is to be sent");
            mmail.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(mail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Loginscreen2.this,"Please check your email",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}