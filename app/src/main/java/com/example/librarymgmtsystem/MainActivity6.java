package com.example.librarymgmtsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Button btn8=findViewById(R.id.button8);
        btn8.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(in);

                    }
                }
        );
        Button btn6=findViewById(R.id.button6);
        btn6.setOnClickListener(
                new
                        View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in1=new Intent(getApplicationContext(),MainActivity7.class);
                                startActivity(in1);
                            }
                        }
        );
    }
}