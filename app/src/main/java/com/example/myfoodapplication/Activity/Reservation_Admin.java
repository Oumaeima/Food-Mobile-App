package com.example.myfoodapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myfoodapplication.Adapter.Reservation_Admin_Adapter;
import com.example.myfoodapplication.Adapter.Reservation_Client_Adapter;
import com.example.myfoodapplication.Model.ReservationModel;
import com.example.myfoodapplication.Model.ReservationModel2;
import com.example.myfoodapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Reservation_Admin extends AppCompatActivity {

    Reservation_Admin_Adapter adapter;
    RecyclerView recyclerView;
    List<ReservationModel2> list;
    DatabaseReference database;
    ReservationModel2 reservationModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_admin);

        recyclerView = findViewById(R.id.recyclerViewRes);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();
        adapter = new Reservation_Admin_Adapter(this, list);

        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance().getReference("Reservation");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    reservationModel = dataSnapshot.getValue(ReservationModel2.class);
                    list.add(reservationModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}