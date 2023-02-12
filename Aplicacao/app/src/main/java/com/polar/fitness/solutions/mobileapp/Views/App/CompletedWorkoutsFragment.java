package com.polar.fitness.solutions.mobileapp.Views.App;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        return view;
    }
}