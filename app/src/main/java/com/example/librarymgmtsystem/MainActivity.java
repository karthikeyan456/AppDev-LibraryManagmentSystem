package com.example.librarymgmtsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=findViewById(R.id.login);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseFirestore db = FirebaseFirestore.getInstance();

                        TextView uname=findViewById(R.id.username);
                        TextView password=findViewById(R.id.password);
                        String user=uname.getText().toString();
                        String pass=password.getText().toString();

                        db.collection("user").get().addOnSuccessListener(
                                new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        if(queryDocumentSnapshots.isEmpty()){
                                            Toast.makeText(MainActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT);

                                        }
                                        else{
                                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                             for(DocumentSnapshot d:list){
                                                 if(d.get("uname").toString().equals(user) && d.get("pass").toString().equals(pass) && user.equals("admin")==false){
                                                     Intent user=new Intent(getApplicationContext(),MainActivity2.class);
                                                     user.putExtra("name",d.get("name").toString());

                                                     startActivity(user);
                                                 }
                                                 else if(d.get("uname").toString().equals(user) && d.get("pass").toString().equals(pass) && user.equals("admin")==true){
                                                     Intent adm=new Intent(getApplicationContext(),MainActivity6.class);
                                                     startActivity(adm);
                                                 }
                                             }
                                        }
                                    }
                                }
                        );

                    }
                }
        );

    }

}