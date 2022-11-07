package com.example.myfoodapplication.Order_Responsable;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

public class OrderStatus extends AppCompatActivity {

    OrderAdapterRes adapter;
    RecyclerView recyclerView;
    OrderR order;
    DatabaseReference database;
    List<OrderR> list;
    String  userId;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        recyclerView = findViewById(R.id.recyclerViewR);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new OrderAdapterRes(list, this);
        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance().getReference("Order");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        order = dataSnapshot.getValue(OrderR.class);
                        order.setId(dataSnapshot.getKey());
                        order.setQuantity(dataSnapshot.child("Quantity").getValue().toString());
                        order.setItemprice(dataSnapshot.child("price").getValue().toString());
                        list.add(order);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}