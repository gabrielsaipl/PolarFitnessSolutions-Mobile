package com.polar.fitness.solutions.mobileapp.Views.App;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.polar.fitness.solutions.mobileapp.Adapters.ListWorkout_planDetailsAdapter;
import com.polar.fitness.solutions.mobileapp.Adapters.Workout_Plan_Exercise_RelationAdapter;
import com.polar.fitness.solutions.mobileapp.Models.Exercise;
import com.polar.fitness.solutions.mobileapp.Models.SingletonGestorUsers;
import com.polar.fitness.solutions.mobileapp.Models.Workout;
import com.polar.fitness.solutions.mobileapp.Models.Workout_Plan_Exercise_Relation;
import com.polar.fitness.solutions.mobileapp.R;

import java.util.ArrayList;


public class CompletedWorkoutDetailsFragment extends Fragment {

    public CompletedWorkoutDetailsFragment() {
        // Required empty public constructor
    }
    TextView tvWorkoutName, tvWorkoutDate, tvWorkoutDuration, tvWorkoutPlanName;
    ListView lvListExercises;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_completed_workout_details, container, false);
        tvWorkoutName = view.findViewById(R.id.tvCompletedWorkoutName);
        tvWorkoutDate = view.findViewById(R.id.tvCompletedWorkoutDate);
        tvWorkoutDuration = view.findViewById(R.id.tvCompletedWorkoutDuration);
        tvWorkoutPlanName = view.findViewById(R.id.tvCompletedWorkout_planName);
        lvListExercises = view.findViewById(R.id.lvCompletedWorkout_plan);

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String s1 = sharedPreferences.getString("workoutName", "");
        String s2 = sharedPreferences.getString("workoutDate", "");
        String s3 = sharedPreferences.getString("workoutDuration", "");
        String s4 = sharedPreferences.getString("workout_workoutPlanName", "");
        int s5 = Integer.parseInt(sharedPreferences.getString("workout_workoutPlanId", ""));
        tvWorkoutName.setText(s1);
        tvWorkoutDate.setText(s2);
        tvWorkoutDuration.setText(s3);
        tvWorkoutPlanName.setText(s4);

        ArrayList<Workout_Plan_Exercise_Relation> aux = new ArrayList<>();
        for (Workout_Plan_Exercise_Relation relations: SingletonGestorUsers.getInstance(getContext()).getWorkout_plan_exercise_relations()){
            if (relations.getWorkout_plan_id() == s5){
                aux.add(relations);
            }
        }
        Workout_Plan_Exercise_RelationAdapter adapter = new Workout_Plan_Exercise_RelationAdapter(getContext(), aux);
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

        ListWorkout_planDetailsAdapter adapter2 = new ListWorkout_planDetailsAdapter(getContext(), aux2);
        lvListExercises.setAdapter(adapter2);

        return view;

    }

}