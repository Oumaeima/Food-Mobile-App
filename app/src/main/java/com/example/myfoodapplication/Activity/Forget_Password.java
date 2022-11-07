package com.example.myfoodapplication.Activity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myfoodapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forget_Password extends AppCompatActivity {

    EditText email;
    TextView text;
    ConstraintLayout reset;
    ProgressBar progressBar;
    float v=0;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        text = findViewById(R.id.text);
        email = findViewById(R.id.email2);
        reset = findViewById(R.id.btn_reset);
        progressBar = findViewById(R.id.progress3);
        mAuth = FirebaseAuth.getInstance();

        email.setTranslationX(800);
        reset.setTranslationX(800);
        text.setTranslationX(800);

        email.setAlpha(v);
        reset.setAlpha(v);

        text.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        reset.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = email.getText().toString().trim();

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

                progressBar.setVisibility(View.VISIBLE);
                mAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(Forget_Password.this, "please check your email to reset password!",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }else{
                            Toast.makeText(Forget_Password.this, "try again! something wrong happened!",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


            }
        });

    }
}