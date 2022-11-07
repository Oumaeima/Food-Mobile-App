package com.example.myfoodapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfoodapplication.Adapter.HomeAdapter;
import com.example.myfoodapplication.Adapter.catAdapter;
import com.example.myfoodapplication.Model.HomeModel;
import com.example.myfoodapplication.Model.catRecommanded;
import com.example.myfoodapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    HomeAdapter adapter;
    RecyclerView recyclerViewCatList, recyclerView;
    List<HomeModel> homeModelList;
    ImageView profile,reservation;
    DatabaseReference database;
    HomeModel homeModel;
    FloatingActionButton shop;
    catAdapter catAdapter;
    List<catRecommanded> list2;

    @Nullable
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        shop = findViewById(R.id.panier);
        recyclerViewCatList = findViewById(R.id.recyclerView);
        recyclerView = findViewById(R.id.recyclerRec);
        profile = findViewById(R.id.imageViewProfile);
        reservation = findViewById(R.id.imageAvis);

        database = FirebaseDatabase.getInstance().getReference("category");
        recyclerViewCatList.setHasFixedSize(true);

        recyclerViewCatList.setLayoutManager(new LinearLayoutManager(Home.this, RecyclerView.HORIZONTAL, false));
        recyclerView.setLayoutManager(new LinearLayoutManager(Home.this, RecyclerView.HORIZONTAL, false));

        homeModelList = new ArrayList<>();

        list2 = new ArrayList<>();
        list2.add(new catRecommanded(R.drawable.pizza2));
        list2.add(new catRecommanded(R.drawable.pasta));

        catAdapter = new catAdapter(list2, this);
        recyclerView.setAdapter(catAdapter);

        adapter = new HomeAdapter(this, homeModelList);
        recyclerViewCatList.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    homeModel = dataSnapshot.getValue(HomeModel.class);
                    homeModelList.add(homeModel);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Reservation.class);
                startActivity(intent);
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, CartActivity.class);
                startActivity(intent);
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, profile.class);
                startActivity(intent);
            }
        });



    }
}