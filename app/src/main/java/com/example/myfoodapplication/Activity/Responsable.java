package com.example.myfoodapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.myfoodapplication.Category.MainActivity2;
import com.example.myfoodapplication.Category.Main_Plats_Resp;
import com.example.myfoodapplication.Order_Responsable.OrderStatus;
import com.example.myfoodapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class Responsable extends AppCompatActivity {

    CardView category, plats, reservation, order;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responsable2);

        category = findViewById(R.id.userCard);
        order = findViewById(R.id.orderCard);
        plats = findViewById(R.id.platCard);
        reservation = findViewById(R.id.resCard);

        toolbar = findViewById(R.id.toolbarRes);
        setSupportActionBar(toolbar);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Responsable.this, OrderStatus.class);
                startActivity(intent);
            }
        });

        plats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Responsable.this, Main_Plats_Resp.class);
                startActivity(intent);
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Responsable.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Responsable.this, Reservation_Admin.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item1:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Responsable.this, Login2.class));
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}