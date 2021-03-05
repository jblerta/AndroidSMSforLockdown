package com.example.smsforlockdown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Maps;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterAcitvity extends AppCompatActivity {

    EditText name,surname,address;
    private FirebaseFirestore firebaseFirestore;
    DocumentSnapshot documentSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acitvity);
        name=findViewById(R.id.name);
        surname=findViewById(R.id.surname);
        address=findViewById(R.id.address);
        firebaseFirestore=FirebaseFirestore.getInstance();

    }

    public void saveBtn(View view){
        Map<String, Object> userData=new HashMap<>();
        userData.put("name",name.getText().toString());
        userData.put("surname",surname.getText().toString());
        userData.put("address",address.getText().toString());

        firebaseFirestore.collection("user").document().set(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    Toast.makeText(RegisterAcitvity.this, "Success", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(RegisterAcitvity.this,NameAddressActivity.class);
                    startActivity(intent);


                }
                else{
                    String error=task.getException().getMessage();
                    Toast.makeText(RegisterAcitvity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}