package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Selection_Sort extends AppCompatActivity {

    Button sort;
    EditText input, output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_sort);
        getSupportActionBar().hide();
        sort = (Button)findViewById(R.id.b1);
        input = (EditText)findViewById(R.id.ed1);
        output = (EditText)findViewById(R.id.ed2);

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no = input.getText().toString();
                //Toast.makeText(getApplicationContext(),no,Toast.LENGTH_SHORT).show();
                int[] a = new int[no.length()];
                for(int i = 0; i<no.length(); i++)
                {
                    char ch = no.charAt(i);
                    a[i] = ch-'0';
                }

                sort(a);

                String n = "";
                for(int i =0; i<no.length(); i++)
                {
                    n += a[i];
                }
                Toast.makeText(getApplicationContext(),n,Toast.LENGTH_SHORT).show();
                output.setText(n);

            }
        });
    }
    public void sort(int arr[])
    {
        for (int i = 0; i < arr.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[index]){
                    index = j;//searching for lowest index
                }
            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
    }
}