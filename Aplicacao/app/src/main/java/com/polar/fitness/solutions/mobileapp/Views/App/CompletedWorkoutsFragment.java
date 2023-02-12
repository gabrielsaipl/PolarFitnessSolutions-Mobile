package com.polar.fitness.solutions.mobileapp.Views.App;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.polar.fitness.solutions.mobileapp.Adapters.ListWorkoutAdapter;
import com.polar.fitness.solutions.mobileapp.Adapters.ListWorkout_planAdapter;
import com.polar.fitness.solutions.mobileapp.Models.SingletonGestorUsers;
import com.polar.fitness.solutions.mobileapp.Models.Workout;
import com.polar.fitness.solutions.mobileapp.Models.Workout_plan;
import com.polar.fitness.solutions.mobileapp.R;

import java.util.ArrayList;

public class CompletedWorkoutsFragment extends Fragment {

    private ListView lvWorkout;
    private ListWorkoutAdapter adapter;

    public CompletedWorkoutsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_completed_workouts, container, false);

        lvWorkout = view.findViewById(R.id.lvWorkouts);
        ArrayList<Workout> auxWorkout = new ArrayList<>(SingletonGestorUsers.getInstance(getContext()).getWorkoutsBD());

        adapter = new ListWorkoutAdapter(getContext(), auxWorkout);

        if(lvWorkout != null)
        {
            lvWorkout.setAdapter(adapter);
        }

        lvWorkout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Workout workout = (Workout) adapter.getItem(position);
                String workoutName = workout.getName();
                String workoutDate = workout.getDate();
                String workoutDuration = workout.getDuration();
                String workout_workoutPlanName = workout.getWorkout_planName();
                int workout_workoutPlanId = workout.getWorkout_planId();
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("workoutName", workoutName);
                myEdit.putString("workoutDate", workoutDate);
                myEdit.putString("workoutDuration", workoutDuration);
                myEdit.putString("workout_workoutPlanName", workout_workoutPlanName);
                myEdit.putString("workout_workoutPlanId", String.valueOf(workout_workoutPlanId));
                myEdit.apply();
                startDetailsFragment();
            }
        });

        return view;
    }
    private void startDetailsFragment(){
        Fragment newFragment = new CompletedWorkoutDetailsFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}