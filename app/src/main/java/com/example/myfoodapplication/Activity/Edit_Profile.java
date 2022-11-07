package com.example.myfoodapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myfoodapplication.Model.CartModel;
import com.example.myfoodapplication.Model.users;
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

public class Edit_Profile extends AppCompatActivity {

   private ImageView imgProfile;
   private Button btnSave, btnCancel;
   private EditText nom, mail, pass, add, tel;
    List<CartModel> list = new ArrayList<CartModel>();

   private DatabaseReference databaseReference;
   private FirebaseAuth mAuth;

    FirebaseUser user;
    DatabaseReference reference;

    String userId;
    String nom2, mail2, pass2, add2, tel2;

   private  static final String USER = "users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //Intent intent = getIntent();
       // email = intent.getStringExtra("email");
      //  passwd = intent.getStringExtra("password");


        nom = findViewById(R.id.nameModif);
        mail = findViewById(R.id.mailModif);
        pass = findViewById(R.id.passModif);
        add = findViewById(R.id.addressModif);
        tel = findViewById(R.id.telModif);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);



        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("user");
        userId = user.getUid();

        // display user info///////////
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users userProfile = snapshot.getValue(users.class);
                if (userProfile != null)
                {
                    String name = userProfile.nom;
                    String email = userProfile.email;
                    String Address = userProfile.address;
                    String tele = userProfile.tel;
                    String passwd = userProfile.password;

                    nom.setText(name);
                    mail.setText(email);
                    add.setText(Address);
                    tel.setText(tele);
                    pass.setText(passwd);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText (Edit_Profile.this, "something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nom2 = nom.getText().toString();
                mail2 = mail.getText().toString();
                add2 = add.getText().toString();
                tel2 = tel.getText().toString();
                pass2 = pass.getText().toString();

               // FirebaseUser firebaseUser =FirebaseAuth.getInstance().getCurrentUser();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Edit_Profile.this, profile.class));
            }
        });

    }



}