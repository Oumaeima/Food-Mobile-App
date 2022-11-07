package com.example.myfoodapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.myfoodapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_tab_fragment extends Fragment implements View.OnClickListener {

    EditText email, pass;
    TextView forget;
    ConstraintLayout login;
    ProgressBar progressBar;
    float v = 0;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();

        email = root.findViewById(R.id.mail);
        pass = root.findViewById(R.id.p);
        forget = root.findViewById(R.id.forget);
        forget.setOnClickListener(this);
        login = root.findViewById(R.id.s);
        progressBar = root.findViewById(R.id.progress2);

        login.setOnClickListener(this);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        forget.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forget.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forget.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.s:
                userLogin();
                break;
            case R.id.forget:
                startActivity(new Intent(getActivity(), Forget_Password.class));
        }
    }

    private void userLogin() {
        String mail = email.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (mail.isEmpty()) {
            email.setError("email is required!");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("email invalid!");
            email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            pass.setError("password is required!");
            pass.requestFocus();
            return;
        }

        if (password.length() < 6) {
            pass.setError("min password length should be 6 characters !");
            pass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            if (mail.equals("admin@gmail.com"))
                            {
                                Intent intent = new Intent(getActivity(), Responsable.class);
                                intent.putExtra("mail",email.getText() );
                                intent.putExtra("passwd",email.getText() );

                                startActivity(intent);
                                progressBar.setVisibility(View.GONE);
                            }
                            else
                            {

                                Intent intent2 = new Intent(getActivity(), Home.class);
                                intent2.putExtra("email",email.getText() );
                                intent2.putExtra("password",pass.getText() );

                                startActivity(intent2);
                                progressBar.setVisibility(View.GONE);
                            }


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }

}