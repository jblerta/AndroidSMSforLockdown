package com.example.smsforlockdown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class EditeAcitivity extends AppCompatActivity {


    private EditText name,surname,address;
   private FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
   DocumentSnapshot documentSnapshot;
    QueryDocumentSnapshot queryDocumentSnapshot;

    private Button edit,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_acitivity);


        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        address = findViewById(R.id.address);
        edit = findViewById(R.id.btnEdit);
        delete = findViewById(R.id.btnDelete);

        final String nameUser = getIntent().getStringExtra("NAME");
        System.out.println("nnnnn " + nameUser);
        String lastnameUser = getIntent().getStringExtra("LASTNAME");
        String addressUser = getIntent().getStringExtra("ADDRESS");

        name.setText(nameUser);
        surname.setText(lastnameUser);
        address.setText(addressUser);

        final Map<String, Object> userData = new HashMap<>();
        userData.put("name", name.getText().toString());
        userData.put("surname", surname.getText().toString());
        userData.put("address", address.getText().toString());


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("user").whereEqualTo("name", nameUser).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {



                            firebaseFirestore.collection("user").document().set(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {


                                        Toast.makeText(EditeAcitivity.this, "Edit Successfully", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(EditeAcitivity.this, NameAddressActivity.class);
                                        startActivity(intent);


                                    } else {
                                        String error = task.getException().getMessage();
                                        Toast.makeText(EditeAcitivity.this, error, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                        }
                    }
                });
            }
        });
    }

}