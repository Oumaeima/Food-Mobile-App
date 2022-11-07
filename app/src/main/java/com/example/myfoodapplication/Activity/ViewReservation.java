package com.example.myfoodapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myfoodapplication.Adapter.HomeAdapter;
import com.example.myfoodapplication.Adapter.Reservation_Client_Adapter;
import com.example.myfoodapplication.Model.HomeModel;
import com.example.myfoodapplication.Model.ReservationModel;
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

public class ViewReservation extends AppCompatActivity {

    Reservation_Client_Adapter adapter;
    RecyclerView recyclerView;
    List<ReservationModel> list;
    DatabaseReference database;
    ReservationModel reservationModel;

    ImageView cart, home, profile, reservation;

    FirebaseUser user;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation);

        recyclerView = findViewById(R.id.recyclerViewR);

        cart = findViewById(R.id.panierR);
        home = findViewById(R.id.HomeR);
        profile = findViewById(R.id.ProfileR);
        reservation = findViewById(R.id.Res);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();
        adapter = new Reservation_Client_Adapter(this, list);

        recyclerView.setAdapter(adapter);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        database = FirebaseDatabase.getInstance().getReference("Reservation");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if (userId.equals(dataSnapshot.child("user_id").getValue())) {

                        reservationModel = dataSnapshot.getValue(ReservationModel.class);
                        list.add(reservationModel);
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
                startActivity(new Intent(ViewReservation.this, profile.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewReservation.this, Home.class));
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewReservation.this, CartActivity.class));
            }
        });

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewReservation.this, Reservation.class));
            }
        });

    }
}