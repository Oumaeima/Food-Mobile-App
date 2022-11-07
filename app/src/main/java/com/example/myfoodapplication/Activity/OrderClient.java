package com.example.myfoodapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapplication.Adapter.OrderAdapter;
import com.example.myfoodapplication.Model.Order;
import com.example.myfoodapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderClient extends AppCompatActivity {

    OrderAdapter adapter;
    RecyclerView recyclerView;
    ImageView home, profile, returnO;
    TextView total;
    Order order;
    DatabaseReference database;
    List<Order> list;
    String  userId;
    FirebaseUser user;
    double subTotal = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_client);

        recyclerView = findViewById(R.id.recyclerViewOrder);
        recyclerView.setHasFixedSize(true);
        home = findViewById(R.id.homeOrder);
        profile = findViewById(R.id.profileOrder);
        total = findViewById(R.id.totalOrder);
        returnO = findViewById(R.id.returno);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new OrderAdapter(list, this);
        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance().getReference("Order");

        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if (userId.equals(dataSnapshot.child("userId").getValue())) {

                        order = dataSnapshot.getValue(Order.class);
                        order.setId(dataSnapshot.getKey());
                        order.setQuantity(dataSnapshot.child("Quantity").getValue().toString());
                        order.setItemprice(dataSnapshot.child("price").getValue().toString());
                        list.add(order);

                        subTotal += (Double.parseDouble(order.getItemprice())) * (Integer.parseInt(order.getQuantity()));
                        total.setText(String.valueOf(subTotal));
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderClient.this, profile.class));
            }
        });

        returnO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderClient.this, profile.class));
            }
        });


    }
}