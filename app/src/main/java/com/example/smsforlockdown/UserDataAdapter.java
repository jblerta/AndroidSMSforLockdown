package com.example.smsforlockdown;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.ViewHolder> {

    private List<UserDataModel> userDataModelList;
    private FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();

    public UserDataAdapter(List<UserDataModel> userDataModels) {
        this.userDataModelList=userDataModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        String lastName=userDataModelList.get(position).getSurname();
        final String firstName=userDataModelList.get(position).getName();
        String address=userDataModelList.get(position).getAddress();


        holder.setData(firstName,lastName,address);

    }

    @Override
    public int getItemCount() {
        return userDataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView firstName;
        private TextView lastName;
        private TextView address;
        private Button btnDelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName=itemView.findViewById(R.id.name);
            lastName=itemView.findViewById(R.id.surname);
            address=itemView.findViewById(R.id.address);
            btnDelete=itemView.findViewById(R.id.btnDelete);



        }


        private void setData(String userFirstName, String userLastName, String userAddress){

            firstName.setText(userFirstName);
            lastName.setText(userLastName);

            address.setText(userAddress);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mainIntent=new Intent(itemView.getContext(),MainActivity.class);
                    mainIntent.putExtra("NAME",firstName.getText().toString());
                    mainIntent.putExtra("LASTNAME",lastName.getText().toString());
                    mainIntent.putExtra("ADDRESS",address.getText().toString());
                    itemView.getContext().startActivity(mainIntent);
                }
            });

        }

    }

}
