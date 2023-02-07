package com.polar.fitness.solutions.mobileapp;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.polar.fitness.solutions.mobileapp.Adapters.ListWorkout_planAdapter;
import com.polar.fitness.solutions.mobileapp.Models.SingletonGestorUsers;
import com.polar.fitness.solutions.mobileapp.Models.Workout_plan;
import com.polar.fitness.solutions.mobileapp.Views.App.Workout_PlanDetailsFragment;

import java.util.ArrayList;

public class StartWorkoutFragment extends Fragment {

    ListView lvWorkout_plans;
    private ListWorkout_planAdapter adapter;
    TextView tvStartWorkoutName, tvWorkoutPlanId, tvWorkoutPlanTitle;
    Button btStartWorkout;

    public StartWorkoutFragment() {
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
        View view = inflater.inflate(R.layout.fragment_start_workout, container, false);
        setHasOptionsMenu(true);

        lvWorkout_plans = (ListView) view.findViewById(R.id.lvWorkout_planss);
        tvStartWorkoutName = (TextView) view.findViewById(R.id.tvStartWorkoutName);
        btStartWorkout = (Button) view.findViewById(R.id.btStartWorkout);
        tvWorkoutPlanTitle = (TextView) view.findViewById(R.id.tvWorkoutPlanTitle);
        tvWorkoutPlanId = (TextView) view.findViewById(R.id.tvWorkoutPlanId);
        tvWorkoutPlanTitle.setVisibility(View.INVISIBLE);
        tvWorkoutPlanId.setVisibility(View.INVISIBLE);
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

        if(lvWorkout_plans != null)
        {
            lvWorkout_plans.setAdapter(adapter);
        }

        lvWorkout_plans.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Workout_plan selectedPlan = (Workout_plan) adapter.getItem(position);
                String Title = selectedPlan.getWorkout_name();
                int planId = selectedPlan.getId();
                String plaid = String.valueOf(planId);
                tvWorkoutPlanId.setText(plaid);
                tvWorkoutPlanTitle.setText(Title);
                tvStartWorkoutName.setText(Title);

            }
        });

        lvWorkout_plans.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                startWorkoutPlanList();
                return false;
            }
        });

        btStartWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringPlano = tvStartWorkoutName.getText().toString();
                if(!stringPlano.equals("Nenhum plano selecionado")){
                    String Title = tvWorkoutPlanTitle.getText().toString();
                    String planId = tvWorkoutPlanId.getText().toString();
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    String s1 = sharedPreferences.getString("wkPlanId", "");
                    String s2 = sharedPreferences.getString("wkPlanName", "");
                    myEdit.putString("wkPlanId", planId);
                    myEdit.putString("wkPlanName", Title);
                    myEdit.apply();
                    startWorkout();
                } else {Toast.makeText(getContext(), "Selecione um plano de treino", Toast.LENGTH_SHORT).show();}

            }
        });

        return view;
    }
    private void startWorkoutPlanList(){
        Fragment newFragment = new Workout_PlanDetailsFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void startWorkout(){
        Fragment newFragment = new OngoingWorkoutFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}