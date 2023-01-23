package com.polar.fitness.solutions.mobileapp.Views.App;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.polar.fitness.solutions.mobileapp.Adapters.ListWorkout_planAdapter;
import com.polar.fitness.solutions.mobileapp.Adapters.ListWorkout_planDetailsAdapter;
import com.polar.fitness.solutions.mobileapp.Adapters.Workout_Plan_Exercise_RelationAdapter;
import com.polar.fitness.solutions.mobileapp.Listeners.ExercisesListener;
import com.polar.fitness.solutions.mobileapp.Listeners.Workout_Plan_Exercise_RelationListener;
import com.polar.fitness.solutions.mobileapp.Listeners.Workout_plansListener;
import com.polar.fitness.solutions.mobileapp.Models.Exercise;
import com.polar.fitness.solutions.mobileapp.Models.SingletonGestorUsers;
import com.polar.fitness.solutions.mobileapp.Models.Workout_Plan_Exercise_Relation;
import com.polar.fitness.solutions.mobileapp.Models.Workout_plan;
import com.polar.fitness.solutions.mobileapp.R;

import java.util.ArrayList;


public class Workout_PlanDetailsFragment extends Fragment implements ExercisesListener {

    public TextView tvTitulo;
    private ListView lvListExercises;
    private Workout_Plan_Exercise_RelationAdapter adapter;
    private ListWorkout_planDetailsAdapter adapter2;
    public Workout_PlanDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout__plan_details, container, false);
        setHasOptionsMenu(true);
        tvTitulo = view.findViewById(R.id.tvWkName);
        System.out.println("4 loop");
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int s1 = Integer.parseInt(sharedPreferences.getString("wkPlanId", ""));
        String s2 = sharedPreferences.getString("wkPlanName", "");
        tvTitulo.setText(s2);

        lvListExercises = view.findViewById(R.id.lvWorkout_planDetails);

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
        lvListExercises.setAdapter(adapter2);
        SingletonGestorUsers.getInstance(getContext()).setExercisesListener(this);
        SingletonGestorUsers.getInstance(getContext()).getAllExercisesAPI(getContext());
        SingletonGestorUsers.getInstance(getContext()).getAllWorkout_plan_exercise_relationAPI(getContext());
        return view;

    }




    @Override
    public void onRefreshListExercises(ArrayList<Exercise> exerciseList) {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int s1 = Integer.parseInt(sharedPreferences.getString("wkPlanId", ""));
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
        lvListExercises.setAdapter(new ListWorkout_planDetailsAdapter(getContext(), aux2));



    }
}