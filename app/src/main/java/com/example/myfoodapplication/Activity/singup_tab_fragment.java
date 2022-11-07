package com.example.myfoodapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.myfoodapplication.Model.users;
import com.example.myfoodapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class singup_tab_fragment extends Fragment implements View.OnClickListener{

    EditText email,pass,tel,nom,address;
    ConstraintLayout sing;
    ProgressBar progressBar;
    float v=0;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.singup_tab_fragment, container , false);

        nom = root.findViewById(R.id.name);
        email = root.findViewById(R.id.mail);
        pass = root.findViewById(R.id.p);
        tel = root.findViewById(R.id.tel);
        sing = root.findViewById(R.id.signup);
        address = root.findViewById(R.id.address2);
        progressBar = root.findViewById(R.id.progress);
        sing.setOnClickListener(this);

        nom.setTranslationX(800);
        email.setTranslationX(800);
        pass.setTranslationX(800);
        tel.setTranslationX(800);
        address.setTranslationX(800);
        sing.setTranslationX(800);

        nom.setAlpha(v);
        email.setAlpha(v);
        pass.setAlpha(v);
        tel.setAlpha(v);
        address.setAlpha(v);
        sing.setAlpha(v);

        nom.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        tel.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        address.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        sing.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return root;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.signup:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String mail = email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String name = nom.getText().toString().trim();
        String tele = tel.getText().toString().trim();
        String addr = address.getText().toString().trim();

        if (name.isEmpty())
        {
            nom.setError("name is required!");
            nom.requestFocus();
            return;
        }

        if (mail.isEmpty())
        {
            email.setError("email is required!");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches())
        {
            email.setError("email invalid!");
            email.requestFocus();
            return;
        }

        if (password.isEmpty())
        {
            pass.setError("password is required!");
            pass.requestFocus();
            return;
        }

        if (password.length() < 6)
        {
            pass.setError("min password length should be 6 characters !");
            pass.requestFocus();
            return;
        }

        if (tele.isEmpty())
        {
            tel.setError("phone number is required!");
            tel.requestFocus();
            return;
        }

        if (tele.length() < 8)
        {
            tel.setError("min phone length should be 8 characters !");
            tel.requestFocus();
            return;
        }

        if (addr.isEmpty())
        {
            address.setError("phone number is required!");
            address.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    users user = new users(name,mail,addr,tele,password);
                    FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText (getActivity(), "user has been registred successfully!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getContext(), Home.class));
                                progressBar.setVisibility(View.GONE);

                            }else {
                                Toast.makeText (getActivity(), "failed to register!", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });

                }else {
                    Toast.makeText (getActivity(), "failed to register!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}