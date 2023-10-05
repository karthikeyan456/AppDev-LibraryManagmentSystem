package com.example.librarymgmtsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tv=findViewById(R.id.welcmsg);
        tv.setText("Welcome "+getIntent().getStringExtra("name"));

        Intent in1=new Intent(getApplicationContext(),MainActivity.class);
        Button b1=findViewById(R.id.logout);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(in1);
                    }
                }
        );

        Intent in2=new Intent(getApplicationContext(),MainActivity3.class);
        Button b2=findViewById(R.id.available);
        in2.putExtra("name",getIntent().getStringExtra("name"));
        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(in2);
                    }
                }
        );

        Intent in3=new Intent(getApplicationContext(),MainActivity4.class);
        Button b3=findViewById(R.id.booksborrowed);
        in3.putExtra("name",getIntent().getStringExtra("name"));
        b3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(in3);
                    }
                }
        );

        Intent in4=new Intent(getApplicationContext(),MainActivity5.class);
        in4.putExtra("name",getIntent().getStringExtra("name"));
        Button b4=findViewById(R.id.borrow);
        b4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(in4);
                    }
                }
        );


    }
}