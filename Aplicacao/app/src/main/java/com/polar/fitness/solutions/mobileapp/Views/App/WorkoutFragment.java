package com.polar.fitness.solutions.mobileapp.Views.App;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
    Button btTreinar;
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

        //Instanciar componentes
        btTreinar = view.findViewById(R.id.btTreinar);
        lvWorkout_plan = view.findViewById(R.id.lvWorkout_plans);

        //Shared Preferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String s1 = sharedPreferences.getString("client_id", "");
        int s2 = Integer.parseInt(s1);
        ArrayList<Workout_plan> auxWorkout_plan = new ArrayList<>();
        for (Workout_plan workout_plan: SingletonGestorUsers.getInstance(getContext()).getWorkout_plansBD())
        {
            if (workout_plan.getClient_id() == s2)
            {
                auxWorkout_plan.add(workout_plan);
            }
        }

        adapter = new ListWorkout_planAdapter(getContext(), auxWorkout_plan);
        lvWorkout_plan.setAdapter(adapter);

        btTreinar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startWorkoutFragment();
            }
        });
        lvWorkout_plan.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Workout_plan selectedPlan = (Workout_plan) adapter.getItem(position);
                String Title = selectedPlan.getWorkout_name();
                int planId = selectedPlan.getId();
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                String s1 = sharedPreferences.getString("wkPlanId", "");
                String s2 = sharedPreferences.getString("wkPlanName", "");
                myEdit.putString("wkPlanId", String.valueOf(planId));
                myEdit.putString("wkPlanName", String.valueOf(Title));
                myEdit.apply();
                startChildFragment();
            }
        });
        
        SingletonGestorUsers.getInstance(getContext()).setWorkout_plansListener(this);
        SingletonGestorUsers.getInstance(getContext()).getAllWorkout_plansAPI(getContext());
        return view;
    }
    @Override
    public void onRefreshListWorkout_plans(ArrayList<Workout_plan> listWorkout_plan){

                if (getActivity() != null) {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    String s1 = sharedPreferences.getString("client_id", "");
                    int s2 = Integer.parseInt(s1);
                    ArrayList<Workout_plan> auxWorkout_plan = new ArrayList<>();
                    for (Workout_plan workout_plan : SingletonGestorUsers.getInstance(getContext()).getWorkout_plansBD()) {
                        if (workout_plan.getClient_id() == s2) {
                            auxWorkout_plan.add(workout_plan);
                        }
                    }
                    adapter = new ListWorkout_planAdapter(getContext(), auxWorkout_plan);

                    if (lvWorkout_plan != null) {
                        lvWorkout_plan.setAdapter(adapter);
                    }
                }
                else {
                }
            }

    @Override
    public void onResume(ArrayList<Workout_plan> listWorkout_plan) {
        super.onResume();
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String s1 = sharedPreferences.getString("client_id", "");
        int s2 = Integer.parseInt(s1);
        ArrayList<Workout_plan> auxWorkout_plan = new ArrayList<>();
        for (Workout_plan workout_plan: SingletonGestorUsers.getInstance(getContext()).getWorkout_plansBD())
        {
            if (workout_plan.getClient_id() == s2)
            {
                auxWorkout_plan.add(workout_plan);
            }
        }
        adapter = new ListWorkout_planAdapter(getContext(), auxWorkout_plan);

        if(lvWorkout_plan != null)
        {
            lvWorkout_plan.setAdapter(adapter);
        }
    }

    private void startChildFragment(){
        Fragment newFragment = new Workout_PlanDetailsFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void startWorkoutFragment(){
        Fragment newFragment = new StartWorkoutFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}