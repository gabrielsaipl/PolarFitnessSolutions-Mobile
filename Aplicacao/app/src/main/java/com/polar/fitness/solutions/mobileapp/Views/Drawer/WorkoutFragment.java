package com.polar.fitness.solutions.mobileapp.Views.Drawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.polar.fitness.solutions.mobileapp.Adapters.ListWorkout_planAdapter;
import com.polar.fitness.solutions.mobileapp.Listeners.Workout_plansListener;
import com.polar.fitness.solutions.mobileapp.Models.SingletonGestorUsers;
import com.polar.fitness.solutions.mobileapp.Models.Workout_plan;
import com.polar.fitness.solutions.mobileapp.R;

import java.util.ArrayList;

public class WorkoutFragment extends Fragment implements Workout_plansListener {
    public static final int CODE_REQUEST_ADICIONAR = 1;
    private ListView lvWorkout_plan;
    private ListWorkout_planAdapter adapter;
    private SearchView searchView;
    public static final String ID = "id";
    private static final int CODE_REQUEST_EDITAR = 2;

    public WorkoutFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        setHasOptionsMenu(true);

        lvWorkout_plan = view.findViewById(R.id.lvWorkout_plans);
        adapter = new ListWorkout_planAdapter(getContext(), SingletonGestorUsers.getInstance(getContext()).getWorkout_plans());
        lvWorkout_plan.setAdapter(adapter);
        SingletonGestorUsers.getInstance(getContext()).setWorkout_plansListener(this);
        SingletonGestorUsers.getInstance(getContext()).getAllExercisesAPI(getContext());
        return view;
    }
    @Override
    public void onRefreshListWorkout_plans(ArrayList<Workout_plan> listWorkout_plan){
        if (listWorkout_plan != null){
            lvWorkout_plan.setAdapter(new ListWorkout_planAdapter(getContext(), listWorkout_plan));
        }
    }
}