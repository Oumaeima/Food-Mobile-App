package com.example.myfoodapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfoodapplication.Adapter.PlatAdapter;
import com.example.myfoodapplication.Model.Plat;
import com.example.myfoodapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Plats extends AppCompatActivity {

    ImageView img,profile1,home;
    RecyclerView recyclerView;
    DatabaseReference database;
    PlatAdapter adapter;
    ArrayList<Plat> list;
   String name,image;
   Plat plat;
   ConstraintLayout constraintLayout;
   FloatingActionButton cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plats);

        name = getIntent().getStringExtra("name");
        image = getIntent().getStringExtra("image");
        constraintLayout = findViewById(R.id.mainLayout);

        cart = findViewById(R.id.cart);
        profile1 = findViewById(R.id.profilep);
        home = findViewById(R.id.homep);
        img = findViewById(R.id.head);
        recyclerView = findViewById(R.id.recyclerViewPlat);
        database = FirebaseDatabase.getInstance().getReference("Plat");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        list = new ArrayList<>();
        adapter = new PlatAdapter(this, list);
        recyclerView.setAdapter(adapter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                    {

                        if(name.equals(dataSnapshot.child("category").getValue()))
                        {
                            plat =dataSnapshot.getValue(Plat.class);
                            list.add(plat);
                        }
                        try
                        {
                            Picasso.get().load(image).into(img);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                }
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Plats.this, Home.class));
            }
        });

        profile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Plats.this, profile.class));
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Plats.this, CartActivity.class));
            }
        });
    }
}