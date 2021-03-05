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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddNewCategory extends AppCompatActivity {

    EditText codeNr,messageText;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_category);

        codeNr=findViewById(R.id.codeNumber);
        messageText=findViewById(R.id.messageTxt);
        firebaseFirestore=FirebaseFirestore.getInstance();
    }


    public void add(View view){
        Map<String, Object> textMsg=new HashMap<>();
        textMsg.put("text",messageText.getText().toString());
        firebaseFirestore.collection("message").document(codeNr.getText().toString()).set(textMsg).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AddNewCategory.this, "Successfully added", Toast.LENGTH_SHORT).show();
                }
                else{
                    String error=task.getException().getMessage();
                    Toast.makeText(AddNewCategory.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void back(View view){
        Intent intent=new Intent(AddNewCategory.this,MainActivity.class);
        startActivity(intent);
    }
}