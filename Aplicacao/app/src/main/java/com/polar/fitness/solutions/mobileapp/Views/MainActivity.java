package com.polar.fitness.solutions.mobileapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.polar.fitness.solutions.mobileapp.R;
import com.polar.fitness.solutions.mobileapp.Views.Workout.WorkoutActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnWorkout, btnNutrition, btnPhysicalEvaluation, btnMessages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWorkout = findViewById(R.id.btnWorkout);
        btnNutrition = findViewById(R.id.btnNutrition);
        btnPhysicalEvaluation = findViewById(R.id.btnPhysicalEvaluation);
        btnMessages = findViewById(R.id.btnMessages);

        btnWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWorkout = new Intent(MainActivity.this, WorkoutActivity.class);
                startActivity(intentWorkout);
            }
        });

        btnNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNutrition = new Intent();//colocar a nova activity no intent
                startActivity(intentNutrition);
            }
        });

        btnPhysicalEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPhysicalEvaluation = new Intent();//colocar a nova activity no intent
                startActivity(intentPhysicalEvaluation);
            }
        });

        btnMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMessages = new Intent();//colocar a nova activity no intent
                startActivity(intentMessages);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Não pode voltar atrás", Toast.LENGTH_SHORT).show();
    }
}