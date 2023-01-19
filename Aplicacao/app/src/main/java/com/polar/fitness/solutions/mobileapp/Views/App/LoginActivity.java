package com.polar.fitness.solutions.mobileapp.Views.App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        btRegister = findViewById(R.id.btnLogin2);
        btLogin = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        // Aceder a sharedPreferences
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("etUsername", "");
        // Definir os valores do username de acordo com as vars da sharedPreferences
        etUsername.setText(s1);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registar(v);
            }
        });

    }
    public void Registar(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Aceder a sharedPreferences
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("etUsername", "");
        // Definir os valores do username de acordo com as vars da sharedPreferences
        etUsername.setText(s1);
    }
}