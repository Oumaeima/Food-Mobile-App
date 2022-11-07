package com.example.myfoodapplication.Category;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.myfoodapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class AddActivity extends AppCompatActivity {
    EditText name , image ;
    ConstraintLayout btnAdd ;
    ImageView returnIcon;
    ElegantNumberButton numberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = (EditText)findViewById(R.id.txtname);
        image= (EditText)findViewById(R.id.txturl);
        btnAdd =  findViewById(R.id.add2);

        returnIcon = findViewById(R.id.returnIcon);
        returnIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertData();
            }
        });

    }

    private void insertData() {

        Map<String , Object> map = new HashMap<>();
        map.put("name" , name.getText().toString());
        map.put("image" ,image.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("category").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        name.setText("");
                        image.setText("");
                        Toast.makeText(AddActivity.this , "Data Inserted" , Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {

                        Toast.makeText(AddActivity.this , "error" , Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
