package com.example.myfoodapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.example.myfoodapplication.Category.Main_Plats_Resp;
import com.example.myfoodapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ajout_Plats extends AppCompatActivity {

    EditText cat, url, plat, prix;
    ConstraintLayout btnAdd;
    ArrayList<String> arrayList;
    AutoCompleteTextView autoCompleteTextView;
    ImageView returnIcon;



    //FirebaseDatabase root;
    DatabaseReference reference, reference2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_plats);

        arrayList = new ArrayList<>();
        returnIcon = findViewById(R.id.returnIcon2);
        returnIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ajout_Plats.this, Main_Plats_Resp.class);
                startActivity(intent);
            }
        });
        //cat = findViewById(R.id.cat);
        url = findViewById(R.id.url);
        plat = findViewById(R.id.platNom);
        prix = findViewById(R.id.prix);
        btnAdd = findViewById(R.id.btn_login);
        autoCompleteTextView = findViewById(R.id.autoComplete);

        reference2 = FirebaseDatabase.getInstance().getReference();
        showDataSpinner();



        reference = FirebaseDatabase.getInstance().getReference().child("Plat");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              insertData();
            }
        });
    }

    private void showDataSpinner() {
        reference2.child("category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot item: snapshot.getChildren())
                {
                    arrayList.add(item.child("name").getValue(String.class));
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Ajout_Plats.this, R.layout.option_item, arrayList);
                autoCompleteTextView.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void insertData() {

        String category = autoCompleteTextView.getText().toString().trim();
        String urlImg = url.getText().toString().trim();
        String name = plat.getText().toString().trim();
        String price = prix.getText().toString().trim();

        if (category.isEmpty())
        {
            autoCompleteTextView.setError("address is required!");
            autoCompleteTextView.requestFocus();
            return;
        }

        if (urlImg.isEmpty())
        {
            url.setError("address is required!");
            url.requestFocus();
            return;
        }

        if (name.isEmpty())
        {
            plat.setError("address is required!");
            plat.requestFocus();
            return;
        }

        if (price.isEmpty())
        {
            prix.setError("address is required!");
            prix.requestFocus();
            return;
        }

        /*Glide.with(this).load(urlImg);

        Plats plats = new Plats(category,name,urlImg,price);
        reference.push().setValue(plats);
        Toast.makeText(Ajout_Plats.this, "Data inserted", Toast.LENGTH_SHORT).show();*/

        Map<String , Object> map = new HashMap<>();
        map.put("name" , name);
        map.put("image" ,urlImg);
        map.put("category" ,category);
        map.put("price" ,price);



        FirebaseDatabase.getInstance().getReference().child("Plat").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        url.setText("");
                        plat.setText("");
                        prix.setText("");

                        Toast.makeText(Ajout_Plats.this , "Data Inserted" , Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure( Exception e) {

                Toast.makeText(Ajout_Plats.this , "error" , Toast.LENGTH_SHORT).show();

            }
        });

        Glide.with(this).load(urlImg);
    }
}