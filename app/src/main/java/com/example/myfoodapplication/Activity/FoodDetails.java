package com.example.myfoodapplication.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.myfoodapplication.Model.Plat;
import com.example.myfoodapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;

public class FoodDetails extends AppCompatActivity {

    TextView food_name, food_price;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton bntCart;
    ElegantNumberButton numberButton;
    String foodId="", nom, prix, image;
    FirebaseDatabase database;
    DatabaseReference foods,cartReference;

    FirebaseUser user;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Plat");
        cartReference = FirebaseDatabase.getInstance().getReference("Cart");

        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();


        food_name = findViewById(R.id.food_name);
        food_price = findViewById(R.id.food_price);
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        food_image = findViewById(R.id.img_food);
        bntCart = findViewById(R.id.btnCart);
        numberButton = findViewById(R.id.number_button);

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapseAppBar);

       // foodId = getIntent().getStringExtra("id");
        foodId = foods.getKey();
        nom = getIntent().getStringExtra("name");
        prix = getIntent().getStringExtra("price");
        image = getIntent().getStringExtra("image");

        if (!foodId.isEmpty())
        {
            getDetailFood(foodId);
        }
        
        bntCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String , Object> map = new HashMap<>();
                map.put("name" , nom);
                map.put("price" ,prix);
                map.put("Quantity" ,numberButton.getNumber());
                map.put("userId" , userId);

                cartReference.push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(FoodDetails.this , "Data Inserted" , Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure( Exception e) {

                                Toast.makeText(FoodDetails.this , "error" , Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
    }

    private void getDetailFood(String foodId) {
        foods.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Plat food = snapshot.getValue(Plat.class);

                Glide.with(getBaseContext()).load(image)
                        .into(food_image);

                collapsingToolbarLayout.setTitle(nom);
                food_price.setText(prix);
                food_name.setText(nom);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}