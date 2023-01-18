package com.polar.fitness.solutions.mobileapp.Views.App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.polar.fitness.solutions.mobileapp.R;
import com.polar.fitness.solutions.mobileapp.Views.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private Button btRegister,btLogin;
    private EditText etUsername, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //button and editText attribution
        btRegister = findViewById(R.id.btnLogin2);
        btLogin = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registar();
            }
        });

    }
    public void Registar(){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}