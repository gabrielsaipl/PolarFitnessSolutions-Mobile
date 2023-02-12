package com.polar.fitness.solutions.mobileapp.Views.App;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.polar.fitness.solutions.mobileapp.Listeners.RegisterListener;
import com.polar.fitness.solutions.mobileapp.Models.SingletonGestorUsers;
import com.polar.fitness.solutions.mobileapp.R;

public class RegisterActivity extends AppCompatActivity implements RegisterListener {

    private Button btRegister;
    private EditText etUsername, etEmail, etPassword, etRua, etLocalidade, etTelefone, etNIF, etCodigoPostal;
    private Spinner spinnerGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRua = findViewById(R.id.etRua);
        etCodigoPostal = findViewById(R.id.etCondigoPostal);
        etLocalidade = findViewById(R.id.etLocalidade);
        etTelefone = findViewById(R.id.etTelefone);
        etNIF = findViewById(R.id.etNIF);
        spinnerGenero = findViewById(R.id.spinnerGenero);
        btRegister = findViewById(R.id.btRegistar);
        codPostal();

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registar(v);
            }
        });

        SingletonGestorUsers.getInstance(this).setRegisterListener(this);

    }

    private void Registar(View v) {
        String username = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        String pass = etPassword.getText().toString();
        String rua = etRua.getText().toString();
        String codigoPostal = etCodigoPostal.getText().toString();
        String localidade = etLocalidade.getText().toString();
        String telefone = etTelefone.getText().toString();
        String NIF = etNIF.getText().toString();
        String genero = spinnerGenero.getSelectedItem().toString();

        if (!isEtEmpty(username, rua, codigoPostal, localidade, telefone, NIF, genero)){
            Toast.makeText(this, R.string.StringVazio, Toast.LENGTH_SHORT).show();
            return;
        }

        else if (!mailValidator(email)) {
            etEmail.setError(getString(R.string.StringMailInvalido));
            return;
        }
        else if (!passwordValidator(pass)) {
            etPassword.setError(getString(R.string.StringPassInvalida));
            return;
        }

        SingletonGestorUsers.getInstance(this).RegisterAPI(username, email, pass, rua, codigoPostal, localidade, telefone, NIF, genero, this);

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

    @Override
    public void onValidateSignup(String username, String email, String password, String rua, String codigoPostal, String localidade, String telefone, String nif, String genero, Context contexto) {
        if (username != null) {
            Intent intentLogin = new Intent(this, LoginActivity.class);
            //put extra para mandar o username
            startActivity(intentLogin);
        } else {
            Toast.makeText(this, R.string.StringRegistoInvalido, Toast.LENGTH_SHORT).show();
            etPassword.setText("");
        }
    }

    private boolean mailValidator(String email) {
        if (email == null || email.isEmpty())
            return false;
        boolean valido = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        return valido;
    }

    private boolean passwordValidator(String password) {
        if (password == null)
            return false;
        return password.length() >= 8;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public void codPostal() {
        etCodigoPostal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 5) {
                    s.insert(4, "-");
                }
            }
        });
    }
    //verificar se campos estao vazios
    private boolean isEtEmpty(String username, String rua, String cdPostal, String localidade, String telefone, String NIF, String genero) {

        if (username.equals("") || rua.equals("") || cdPostal.equals("") || localidade.equals("") || telefone.equals("") || NIF.equals("") || genero.equals("")) {
            return false;
        }
        return true;
    }
}