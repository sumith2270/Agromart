package com.example.agromart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_as_buyer extends AppCompatActivity {
    private EditText mname,mmail,mmobile,mpassword,mconfirm_password;
    private Button register;

    //FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_as_buyer);

        mname = findViewById(R.id.editTextTextPersonName);
        mmail = findViewById(R.id.editTextTextEmailAddress);
        mmobile = findViewById(R.id.editTextPhone2);
        mpassword = findViewById(R.id.editTextTextPassword2);
        mconfirm_password = findViewById(R.id.editTextTextPassword3);
        register = findViewById(R.id.button4);

        reference = FirebaseDatabase.getInstance().getReference();
        mauth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mname.getText().toString().trim();
                String mail = mmail.getText().toString().trim();
                String mobile = mmobile.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                String confirm_password = mconfirm_password.getText().toString().trim();
                if(name.isEmpty() || name.length() <5){
                    mname.setError("enter full name");
                    mname.requestFocus();
                    return;
                }
                if(mail.isEmpty()){
                    mmail.setError("enter valid mail id");
                    mmail.requestFocus();
                    return;
                }
                if(mobile.isEmpty() || mobile.length()!=10){
                    mmobile.setError("enter valid mobile number");
                    mmobile.requestFocus();
                    return;
                }
                if(password.isEmpty() || password.length()<6){
                    mpassword.setError("Password should atleast be 6 characters");
                    mpassword.requestFocus();
                    return;
                }
                if(!(confirm_password.contentEquals(password))){
                    mconfirm_password.setError("Passwords dosen't match");
                    mconfirm_password.requestFocus();
                    return;
                }

                Registerhelperclass buyer = new Registerhelperclass(name,mail,mobile,password);
                reference.child("users").child("buyers").child("BUYER"+ mobile).setValue(buyer);

                mauth.createUserWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(Register_as_buyer.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mauth.getCurrentUser();
                                    Toast.makeText(Register_as_buyer.this,"Registration Successful!!!",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Register_as_buyer.this,Homescreen.class));
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register_as_buyer.this,"Registration Failed!!!",Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });


            }
        });

    }

    public void show_hide_password(View view){
        if(view.getId()==R.id.show_hide_password){

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


}