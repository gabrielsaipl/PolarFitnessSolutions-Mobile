package com.polar.fitness.solutions.mobileapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.polar.fitness.solutions.mobileapp.R;
import com.polar.fitness.solutions.mobileapp.Views.App.LoginActivity;
import com.polar.fitness.solutions.mobileapp.Views.Drawer.MainMenuActivity;
import com.polar.fitness.solutions.mobileapp.Views.Drawer.NutritionFragment;
import com.polar.fitness.solutions.mobileapp.Views.Drawer.WorkoutFragment;
import com.polar.fitness.solutions.mobileapp.Views.Workout.WorkoutActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnWorkout, btnNutrition, btnPhysicalEvaluation, btnMessages;
    private TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnWorkout = findViewById(R.id.btnWorkout);
        btnNutrition = findViewById(R.id.btnNutrition);
        btnPhysicalEvaluation = findViewById(R.id.btnPhysicalEvaluation);
        btnMessages = findViewById(R.id.btnMessages);
        username = findViewById(R.id.tvUsername);
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

        btnPhysicalEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                String fragment = "PhysicalEvaluationFragment";
                intent.putExtra("fragment", fragment);
                startActivity(intent);
            }
        });

        btnMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                String fragment = "MessagesFragment";
                intent.putExtra("fragment", fragment);
                startActivity(intent);
            }
        });

    }
    //Impedir ao utilizador de voltar a Login/Register Activity
    
    /*public void onBackPressed() {
        Toast.makeText(this, "Nao pode voltar para tras", Toast.LENGTH_SHORT).show();
    }*/

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