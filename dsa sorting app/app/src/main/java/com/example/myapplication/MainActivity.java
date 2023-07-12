package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://srimadpro1-default-rtdb.firebaseio.com/");
    Button b1;
    String enter_user,enter_pass;
    private static final String pwd_reg="((?=.*[0-9])(?=.*[$@$!%*#?&])(?=.*[a-z])(?=.*[A-Z]).{8,})";
    Pattern pattern;
    Matcher matcher;
    //TextView textView;
    boolean passwordVisible;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        pattern=pattern.compile(pwd_reg);
        //textView=(TextView) findViewById(R.id.textView3);
        e1=(EditText) findViewById(R.id.ed1);
        e2=(EditText) findViewById(R.id.ed2);
        b1=(Button) findViewById(R.id.bt1);
       // btt2=(Button) findViewById(R.id.button2);
       /* btt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });
*/
        /*textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });*/
        e2.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint({"ClickableViewAccessibility", "NewApi"})
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final  int Right=2;
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if (motionEvent.getRawX() >= e2.getRight() - e2.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=e2.getSelectionEnd();
                        if(passwordVisible){
                            e2.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
                            //to hide password
                            e2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else{
                            e2.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
                            //to show password
                            e2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;

                        }
                        e2.setSelection(selection);
                        return true;
                    }
                }


                return false;
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String enter_user=e1.getText().toString();
                final String enter_pass=e2.getText().toString();
                matcher= pattern.matcher(enter_pass);
                if(enter_user.isEmpty() || enter_pass.isEmpty()){
                    Toast.makeText(MainActivity.this,"Enter Username/Email or Password",Toast.LENGTH_SHORT).show();
                }
                if(!matcher.matches()){
                    Toast.makeText(MainActivity.this,"Enter valid password",Toast.LENGTH_SHORT).show();
                }

                //if(TextUtils.isEmpty(enter_pass)){
                    //Toast.makeText(MainActivity.this,"Enter Password",Toast.LENGTH_SHORT).show();
               // }
                else{
                    databaseReference.child("users").child("email").setValue(enter_user);
                    databaseReference.child("users").child("password").setValue(enter_pass);
                    //Toast.makeText(MainActivity.this,"Password valid",Toast.LENGTH_LONG).show();
                    System.out.println(enter_user);
                    System.out.println(enter_pass);
                    Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                    intent.putExtra("un",enter_user);
                    intent.putExtra("pwd",enter_pass);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
