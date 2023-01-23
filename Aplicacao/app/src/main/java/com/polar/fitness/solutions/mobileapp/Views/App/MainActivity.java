package com.polar.fitness.solutions.mobileapp.Views.App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.polar.fitness.solutions.mobileapp.R;

public class MainActivity extends AppCompatActivity {

    private Button btnWorkout, btnNutrition;
    private TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnWorkout = findViewById(R.id.btnWorkout);
        btnNutrition = findViewById(R.id.btnNutrition);

        username = findViewById(R.id.tvUsername);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        // Aceder a sharedPreferences
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("etUsername", "");
        // Definir os valores do username de acordo com as vars da sharedPreferences
        username.setText(s1);


        btnWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                String fragment = "WorkoutFragment";
                intent.putExtra("fragment", fragment);
                startActivity(intent);
            }
        });

        btnNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                String fragment = "NutritionFragment";
                intent.putExtra("fragment", fragment);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        // Aceder a sharedPreferences
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("etUsername", "");
        // Definir os valores do username de acordo com as vars da sharedPreferences
        username.setText(s1);
    }
}