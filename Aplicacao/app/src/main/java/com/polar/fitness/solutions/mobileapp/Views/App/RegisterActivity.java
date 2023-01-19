package com.polar.fitness.solutions.mobileapp.Views.App;


import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import com.polar.fitness.solutions.mobileapp.R;

public class RegisterActivity extends AppCompatActivity {


    private EditText etUsername, etEmail, etPassword, etRua, etLocalidade, etTelefone, etNIF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRua = findViewById(R.id.etRua);
        etLocalidade = findViewById(R.id.etLocalidade);
        etTelefone = findViewById(R.id.etTelefone);
        etNIF = findViewById(R.id.etNIF);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Aceder a sharedPreferences
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("etUsername", "");
        String s2 = sh.getString("etEmail", "");
        // Definir os valores do username de acordo com as chaves da sharedPreferences
        etUsername.setText(s1);
        etEmail.setText(s2);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Aceder a sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        //definir o Editor para permitir guardar / alterar o valor
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        String s1 = sharedPreferences.getString("etUsername", "");
        String s2 = sharedPreferences.getString("etEmail", "");
        //Inserir valores do editText nas chaves da sharedPreferences
        myEdit.putString("etUsername", etUsername.getText().toString());
        myEdit.putString("etEmail", etEmail.getText().toString());
        myEdit.apply();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Aceder a sharedPreferences
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("etUsername", "");
        String s2 = sh.getString("etEmail", "");
        // Definir os valores do username de acordo com as vars da sharedPreferences
        etUsername.setText(s1);
        etEmail.setText(s2);
    }

}