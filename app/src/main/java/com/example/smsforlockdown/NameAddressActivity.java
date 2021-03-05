package com.example.smsforlockdown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NameAddressActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_address);


        recyclerView=findViewById(R.id.recyclerView);


        final List<UserDataModel> userDataModelList=new ArrayList<>();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        final UserDataAdapter userDataAdapter=new UserDataAdapter(userDataModelList);
        recyclerView.setAdapter(userDataAdapter);

        firebaseFirestore=FirebaseFirestore.getInstance();

        firebaseFirestore.collection("user").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                userDataModelList.add(new UserDataModel(documentSnapshot.get("name").toString(), documentSnapshot.get("surname").toString(), documentSnapshot.get("address").toString()));
                            }
                            userDataAdapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(NameAddressActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }

                });
       // userDataAdapter.notifyDataSetChanged();
    }
    public void add(View view){
        Intent registerIntent=new Intent(this,RegisterAcitvity.class);
        startActivity(registerIntent);
        finish();

    }

    public void deleteBtn(View view){


        firebaseFirestore.collection("user").document().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(NameAddressActivity.this, "succesfully deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent intent=new Intent(this,NameAddressActivity.class);

        startActivity(intent);
        finish();

    }


}