package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    String registered_username,registered_pass,Entered_username,Entered_password;
    EditText e3,e4;

    Button b3;
    boolean passwordVisible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        e3=(EditText) findViewById(R.id.ed3);
        e4=(EditText) findViewById(R.id.ed4);
        b3=(Button) findViewById(R.id.bt3);
        Bundle extras=getIntent().getExtras();
        registered_username=extras.getString("un");
        registered_pass=extras.getString("pwd");
        Toast.makeText(MainActivity2.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
        System.out.println(registered_username);
        System.out.println(registered_pass);
        e4.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint({"ClickableViewAccessibility", "NewApi"})
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final  int Right=2;
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if (motionEvent.getRawX() >= e4.getRight() - e4.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=e4.getSelectionEnd();
                        if(passwordVisible){
                            e4.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
                            //to hide password
                            e4.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else{
                            e4.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
                            //to show password
                            e4.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;

                        }
                        e4.setSelection(selection);
                        return true;
                    }
                }


                return false;
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Entered_username=e3.getText().toString();
                final String Entered_password=e4.getText().toString();

                if(Entered_username.equals(registered_username)&& Entered_password.equals(registered_pass)){
                    Toast.makeText(MainActivity2.this,"Login successful",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity2.this,Dashboard.class));
                }
                else{
                    Toast.makeText(MainActivity2.this,"Invalid",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}