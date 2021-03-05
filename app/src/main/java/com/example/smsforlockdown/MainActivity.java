package com.example.smsforlockdown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseFirestore=FirebaseFirestore.getInstance();



        firebaseFirestore.collection("message").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        String id=document.getId().toString();
                        String text = document.get("text").toString();
                      //  creat(id,text);

                    }
                }
                else{
                    String error = task.getException().getMessage();
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }





    public void btn1(View view){
        firebaseFirestore.collection("message").document("1").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    String meassge=task.getResult().get("text").toString();
                    String code=task.getResult().getId();
                    sms(code,meassge);
                }
                else{
                    String error = task.getException().getMessage();
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });



    }



    public void btn2(View view){
        firebaseFirestore.collection("message").document("2").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    String meassge=task.getResult().get("text").toString();
                    String code=task.getResult().getId();
                    sms(code,meassge);
                }
                else{
                    String error = task.getException().getMessage();
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void btn3(View view){
        firebaseFirestore.collection("message").document("3").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    String meassge=task.getResult().get("text").toString();
                    String code=task.getResult().getId();
                    sms(code,meassge);
                }
                else{
                    String error = task.getException().getMessage();
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    public void btn4(View view){
        firebaseFirestore.collection("message").document("4").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    String meassge=task.getResult().get("text").toString();
                    String code=task.getResult().getId();
                    sms(code,meassge);
                }
                else{
                    String error = task.getException().getMessage();
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    public void btn5(View view){
        firebaseFirestore.collection("message").document("5").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    String meassge=task.getResult().get("text").toString();
                    String code=task.getResult().getId();
                    sms(code,meassge);
                }
                else{
                    String error = task.getException().getMessage();
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    public void btn6(View view){
        firebaseFirestore.collection("message").document("6").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    String meassge=task.getResult().get("text").toString();
                    String code=task.getResult().getId();
                    sms(code,meassge);
                }
                else{
                    String error = task.getException().getMessage();
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void sms(final String msgCode,final String msg){


        String name=getIntent().getStringExtra("NAME");
        String lastname=getIntent().getStringExtra("LASTNAME");
        String address=getIntent().getStringExtra("ADDRESS");

                    String phoneNumber="111222333";
                    String code=msgCode;
                    String message=msg;

                    if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)!=
                            PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},1234);

                    }else{
                        SmsManager manager=SmsManager.getDefault();
                        manager.sendTextMessage(phoneNumber, null, "Code: "+code+" - "+message+", \n"+name+" "+lastname+", "+address,null,null);
                        Toast.makeText(MainActivity.this, "Message is sent ", Toast.LENGTH_SHORT).show();
                    }

                }


public void edit(View view){

    String name=getIntent().getStringExtra("NAME");
    String lastname=getIntent().getStringExtra("LASTNAME");
    String address=getIntent().getStringExtra("ADDRESS");

        Intent editIntent=new Intent(this,EditeAcitivity.class);
        editIntent.putExtra("NAME",name);
        editIntent.putExtra("LASTNAME",lastname);
        editIntent.putExtra("ADDRESS",address);
        startActivity(editIntent);
}
  /*  public void creat(final String id, final String text){
        ConstraintLayout constraintLayout=findViewById(R.id.cons1);
        Button button=new Button(this);
        button.setText(id);
        button.setBackgroundColor(Color.parseColor("#d085a8"));


        ConstraintLayout.LayoutParams layoutParams= new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);

       layoutParams.setMargins(20,20,20,20);
        button.setLayoutParams(layoutParams);

        constraintLayout.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sms(id,text);
            }
        });

    }


    public void openAdd(View view){
        Intent intent=new Intent(MainActivity.this,AddNewCategory.class);
        startActivity(intent);
    }*/
}