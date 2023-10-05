package com.example.librarymgmtsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Button bt=findViewById(R.id.button);
        bt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText et= findViewById(R.id.editTextText);
                        String bookid=et.getText().toString();
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("books").get().addOnSuccessListener(
                                new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        int flag = 0;
                                        DocumentSnapshot fin = null;

                                        if (queryDocumentSnapshots.isEmpty()) {
                                            Toast.makeText(MainActivity5.this, "Invalid Request", Toast.LENGTH_SHORT);

                                        } else {
                                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                                            for (DocumentSnapshot d : queryDocumentSnapshots) {
                                                if (d.get("id").toString().equals(bookid) && Integer.parseInt(d.get("count").toString())>0) {
                                                    flag = 1;
                                                    fin=d;
                                                }
                                            }
                                        }
                                        if(flag==1){
                                            Map<String,Object> mapa = new HashMap<>();
                                            mapa.put("id", fin.get("id").toString());
                                            mapa.put("name",getIntent().getStringExtra("name"));
                                            mapa.put("title",fin.get("title").toString());
                                            mapa.put("author",fin.get("author").toString());
                                            mapa.put("return","borrowed");
                                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                            Calendar c = Calendar.getInstance();
                                            c.setTime(new Date()); // Using today's date
                                            c.add(Calendar.DATE, 10); // Adding 5 days
                                            String ddate = sdf.format(c.getTime());
                                            mapa.put("due",ddate);
                                            mapa.put("txnid",getIntent().getStringExtra("name")+ddate);

                                            Map<String,Object> mapb = new HashMap<>();
                                            mapb.put("count",(Integer.parseInt(fin.get("count").toString())-1));
                                            mapb.put("title",fin.get("title").toString());
                                            mapb.put("author",fin.get("author").toString());
                                            mapb.put("id", fin.get("id").toString());

                                            db.collection("transact").document(getIntent().getStringExtra("name")+new Date()).set(
                                                    mapa
                                            );
                                             db.collection("books").document(fin.get("title").toString()).set(
                                                     mapb
                                             ).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                 @Override
                                                 public void onSuccess(Void unused) {
                                                     Toast.makeText(MainActivity5.this, "Transaction successfull", Toast.LENGTH_SHORT);

                                                 }
                                             });



                                        }
                                    }
                                }
                        );

                    }
                }
        );
        Button bt2=findViewById(R.id.button2);
        Intent in=new Intent(getApplicationContext(),MainActivity2.class);
        in.putExtra("name",getIntent().getStringExtra("name"));
        bt2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(in);
                    }
                }
        );
    }



}