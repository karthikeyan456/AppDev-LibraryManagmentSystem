package com.example.librarymgmtsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView tv=new TextView(this);
        LinearLayout ll=findViewById(R.id.booklst);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("books").get().addOnSuccessListener(
                new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(queryDocumentSnapshots.isEmpty()){


                                tv.setText("No books found");
                                ll.addView(tv);



                        }
                        else{
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            String s="\n\n\n\n"+"Books Available"+"\n\n\n\n";
                            for(DocumentSnapshot d:list){
                                s=s+"Id: "+d.get("id")+"\nTitle: "+d.get("title")+"\n"+"Author: "+d.get("author")+"\n"+"Availablity: "+d.get("count")+"\n\n\n";

                            }
                            tv.setText(s);
                            ll.addView(tv);

                        }
                    }
                }
        );
        Button bn=findViewById(R.id.gotohome);
        bn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in1=new Intent(getApplicationContext(),MainActivity2.class);
                        in1.putExtra("name",getIntent().getStringExtra("name"));
                        startActivity(in1);
                    }
                }
        );

    }
}