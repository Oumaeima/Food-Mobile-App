package com.example.myfoodapplication.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapplication.Adapter.CartAdapter;
import com.example.myfoodapplication.Model.CartModel;
import com.example.myfoodapplication.Model.Order;
import com.example.myfoodapplication.Model.users;
import com.example.myfoodapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    List<CartModel> list;
    CartAdapter cartAdapter;
    DatabaseReference database, database2, database3;
    CartModel cartModel;
    FirebaseUser user;
    ImageView home,profile1;
    TextView total, quantity, txtTotal, txtDT;
    Button addOrder;
    String email, userId;
    FirebaseStorage firebaseStorage;
    double subTotal = 0.0;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);

      firebaseStorage = FirebaseStorage.getInstance();

        email = getIntent().getStringExtra("email");


        home = findViewById(R.id.imageViewHome2);
        profile1 = findViewById(R.id.imageViewProfile2);
        recyclerView = findViewById(R.id.recyclerViewcart2);
        quantity = findViewById(R.id.quantity);
        recyclerView.setHasFixedSize(true);
        total = findViewById(R.id.total);
        addOrder = findViewById(R.id.addOrder);
        txtTotal = findViewById(R.id.tot);
        txtDT = findViewById(R.id.dt);

        total.setVisibility(View.GONE);
        txtDT.setVisibility(View.GONE);
        txtTotal.setVisibility(View.GONE);
        addOrder.setVisibility(View.GONE);


        database = FirebaseDatabase.getInstance().getReference("Cart");
        database2 = FirebaseDatabase.getInstance().getReference("Order");
        database3 = FirebaseDatabase.getInstance().getReference("user");

        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        cartAdapter = new CartAdapter(list, this);
        recyclerView.setAdapter(cartAdapter);

            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        if (userId.equals(dataSnapshot.child("userId").getValue())) {

                            database3.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    users userPhone = snapshot.getValue(users.class);
                                    phone = userPhone.tel;
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            cartModel = dataSnapshot.getValue(CartModel.class);
                            cartModel.setId(dataSnapshot.getKey());
                            cartModel.setQuantity(dataSnapshot.child("Quantity").getValue().toString());
                            cartModel.setUser_phone(phone);


                            list.add(cartModel);

                            subTotal += (Double.parseDouble(cartModel.getPrice())) * (Integer.parseInt(cartModel.getQuantity()));
                            total.setText(String.valueOf(subTotal));

                            total.setVisibility(View.VISIBLE);
                            txtDT.setVisibility(View.VISIBLE);
                            txtTotal.setVisibility(View.VISIBLE);
                            addOrder.setVisibility(View.VISIBLE);
                        }
                    }
                    cartAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, Home.class));
            }
        });

        profile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, profile.class));
            }
        });

        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               for (CartModel list1 : list)
                {


                    Map<String , Object> map2 = new HashMap<>();
                    map2.put("name" , list1.getName());
                    map2.put("price" ,list1.getPrice());
                    map2.put("Quantity" ,list1.getQuantity());
                    map2.put("userId" , list1.getUserId());

                    database2.push()
                            .setValue(map2)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(CartActivity.this , "Data Inserted" , Toast.LENGTH_SHORT).show();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure( Exception e) {

                                    Toast.makeText(CartActivity.this , "error" , Toast.LENGTH_SHORT).show();

                                }
                            });
                }


               database.removeValue();
               list.clear();
               total.setVisibility(View.GONE);
               txtDT.setVisibility(View.GONE);
               txtTotal.setVisibility(View.GONE);
               addOrder.setVisibility(View.GONE);
            }
        });



    }


}