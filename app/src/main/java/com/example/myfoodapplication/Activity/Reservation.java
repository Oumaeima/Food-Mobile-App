package com.example.myfoodapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myfoodapplication.Model.ReservationModel;
import com.example.myfoodapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Reservation extends AppCompatActivity {

    AutoCompleteTextView date,phone,person;
    DatePickerDialog.OnDateSetListener setListener;
    ConstraintLayout btnAdd;
    FirebaseUser user;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        date = findViewById(R.id.date);
        phone = findViewById(R.id.phone);
        person = findViewById(R.id.person);
        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Reservation.this, R.layout.option_item,
                getResources().getStringArray(R.array.number));
        person.setAdapter(arrayAdapter);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Reservation.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        setListener =  new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date2 = dayOfMonth+"/"+month+"/"+year;
                date.setText(date2);
            }
        };

    }

    private void insertData() {

        String date2 = date.getText().toString().trim();
        String person2 = person.getText().toString().trim();
        String phone2 = phone.getText().toString().trim();


        if (date2.isEmpty())
        {
            date.setError("date is required!");
            date.requestFocus();
            return;
        }

        if (person2.isEmpty())
        {
            person.setError("number of person is required!");
            person.requestFocus();
            return;
        }

        if (phone2.isEmpty())
        {
            phone.setError("phone is required!");
            phone.requestFocus();
            return;
        }

        if (phone2.length() < 8)
        {
            phone.setError("min phone length should be 8 characters !");
            phone.requestFocus();
            return;
        }

        for (int i = 0; i < phone2.length(); i++) {
            if (!Character.isDigit(phone2.charAt(i))) {

                phone.setError("phone number must be digit !");
                phone.requestFocus();
                return;
            }

        }


      /*  Map<String , Object> map = new HashMap<>();
        map.put("Date" , date2);
        map.put("NbPerson" ,person2);
        map.put("Phone" ,phone2);*/

        ReservationModel model = new ReservationModel(date2, person2, phone2, userId);

        FirebaseDatabase.getInstance().getReference().child("Reservation").push()
                .setValue(model)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Reservation.this , "Data Inserted" , Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Reservation.this, Home.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure( Exception e) {

                Toast.makeText(Reservation.this , "error" , Toast.LENGTH_SHORT).show();

            }
        });

    }
}