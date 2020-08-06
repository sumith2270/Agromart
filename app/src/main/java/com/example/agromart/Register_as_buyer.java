package com.example.agromart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_as_buyer extends AppCompatActivity {
    EditText mname,mmail,mmobile,mpassword,mconfirm_password;
    Button register;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

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
                }
                else if(mail.isEmpty()){
                    mmail.setError("enter valid mail id");
                }
                else if(mobile.isEmpty() || mobile.length()!=10){
                    mmobile.setError("enter valid mobile number");
                }
                else if(password.isEmpty() || password.length()<6){
                    mpassword.setError("Password should atleast be 6 characters");
                }
                else if(!(confirm_password.contentEquals(password))){
                    mconfirm_password.setError("Passwords dosen't match");
                }
                else{
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("buyer");
                    Buyerhelperclass buyerhelperclass = new Buyerhelperclass(name,mail,mobile,password);
                    //reference.child(mobile).setValue(buyerhelperclass);
                    reference.child(mobile).setValue(buyerhelperclass);
                }



            }
        });

    }


}