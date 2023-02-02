package com.polar.fitness.solutions.mobileapp.Views.App;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.window.OnBackInvokedDispatcher;


import com.polar.fitness.solutions.mobileapp.Listeners.LoginListener;
import com.polar.fitness.solutions.mobileapp.Models.SingletonGestorUsers;
import com.polar.fitness.solutions.mobileapp.R;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity implements LoginListener {

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
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarLogin(v);
            }
        });
        SingletonGestorUsers.getInstance(this).setLoginListener(this);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }

    private void validarLogin(View view) {
        String username = etUsername.getText().toString();
        String pass = etPassword.getText().toString();
        // Aceder a sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        //definir o Editor para permitir guardar / alterar o valor
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        String s1 = sharedPreferences.getString("etUsername", "");
        //Inserir valores do editText nas chaves da sharedPreferences
        myEdit.putString("etUsername", etUsername.getText().toString());
        myEdit.apply();

        if (!ValidarPassword(pass)) {
            etPassword.setError(getString(R.string.StringPassInvalida));
        }
        SingletonGestorUsers.getInstance(this).loginAPI(username,pass,this);
    }


    private boolean ValidarPassword(String pass){
        if (pass == null)
            return false;
        return pass.length() >= 8;
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

    @Override
    public void onValidateLogin(ArrayList<String> token, String username, Context contexto) {
        if (token != null){
            Intent intentLogin = new Intent(this, MainActivity.class);
            //put extra para mandar o username
            startActivity(intentLogin);
        }
        else {
            Toast.makeText(this, R.string.StringLoginInvalido, Toast.LENGTH_SHORT).show();
            etPassword.setText("");
        }
    }
}