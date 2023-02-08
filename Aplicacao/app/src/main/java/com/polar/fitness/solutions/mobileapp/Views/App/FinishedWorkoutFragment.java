package com.polar.fitness.solutions.mobileapp.Views.App;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.polar.fitness.solutions.mobileapp.Adapters.ListWorkout_planDetailsAdapter;
import com.polar.fitness.solutions.mobileapp.Adapters.Workout_Plan_Exercise_RelationAdapter;
import com.polar.fitness.solutions.mobileapp.Models.Exercise;
import com.polar.fitness.solutions.mobileapp.Models.SingletonGestorUsers;
import com.polar.fitness.solutions.mobileapp.Models.Workout_Plan_Exercise_Relation;
import com.polar.fitness.solutions.mobileapp.R;

import java.util.ArrayList;

public class FinishedWorkoutFragment extends Fragment {

    ListView lvFinishedWorkout_planDetails;
    TextView tvFinishedWorkout_planName, tvWorkoutDate, tvWorkoutDuration;
    EditText etFinishedWorkoutName;
    Button btVoltar;
    private Workout_Plan_Exercise_RelationAdapter adapter;

    private ListWorkout_planDetailsAdapter adapter2;


    public FinishedWorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_finished_workout, container, false);
        setHasOptionsMenu(true);

        lvFinishedWorkout_planDetails = view.findViewById(R.id.lvFinishedWorkout_plan);
        etFinishedWorkoutName = view.findViewById(R.id.etFinishedWorkoutName);
        tvWorkoutDate = view.findViewById(R.id.tvWorkoutDate);
        tvWorkoutDuration = view.findViewById(R.id.tvWorkoutDuration);
        tvFinishedWorkout_planName = view.findViewById(R.id.tvFinishedWorkout_planName);
        btVoltar = view.findViewById(R.id.btVoltar);

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishWorkout();
            }
        });

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int s1 = Integer.parseInt(sharedPreferences.getString("wkPlanId", ""));
        String s2 = sharedPreferences.getString("wkPlanName", "");
        String s3 = sharedPreferences.getString("dataInicioTreino","");
        String s4 = sharedPreferences.getString("TempoTreinoDecorrido","");
        tvFinishedWorkout_planName.setText(s2);
        tvWorkoutDuration.setText(s4);
        tvWorkoutDate.setText(s3);

        ArrayList<Workout_Plan_Exercise_Relation> aux = new ArrayList<>();
        for (Workout_Plan_Exercise_Relation relations: SingletonGestorUsers.getInstance(getContext()).getWorkout_plan_exercise_relations()){
            if (relations.getWorkout_plan_id() == s1){
                aux.add(relations);
            }
        }
        adapter = new Workout_Plan_Exercise_RelationAdapter(getContext(), aux);
        ArrayList<Exercise> aux2 = new ArrayList<>();
        int size = aux.size();
        int i = 0;
        while (i < size){
            Workout_Plan_Exercise_Relation relation = (Workout_Plan_Exercise_Relation) adapter.getItem(i);
            for (Exercise exercises: SingletonGestorUsers.getInstance(getContext()).getExercisesDB()){
                if (exercises.getId() == relation.getExercise_id()){
                    aux2.add(exercises);
                }
            }
            i++;
        }

        adapter2 = new ListWorkout_planDetailsAdapter(getContext(), aux2);
        lvFinishedWorkout_planDetails.setAdapter(adapter2);
        return view;
    }

    private void finishWorkout(){
        Intent intent = new Intent(this.getContext(), MainActivity.class);
        startActivity(intent);
    }
}