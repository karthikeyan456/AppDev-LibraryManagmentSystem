package com.example.librarymgmtsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        TextView tv=new TextView(this);
        LinearLayout ll=findViewById(R.id.borrowbooklst);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("transact").get().addOnSuccessListener(
                new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(queryDocumentSnapshots.isEmpty()){


                            tv.setText("No books found");
                            ll.addView(tv);



                        }
                        else{
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            String s="\n\n\n\n"+"Books Borrowed"+"\n\n\n\n";
                            for(DocumentSnapshot d:list){
                                if(getIntent().getStringExtra("name").equals(d.get("name"))){
                                    s=s+"Title: "+d.get("title")+"\n"+"Author: "+d.get("author")+"\n";
                                    s=s+"Due Date: "+d.get("due")+"\n"+"Status: "+d.get("return")+"\n"
                                            +"Transaction ID: "+d.get("txnid")+"\n\n\n";

                                }


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