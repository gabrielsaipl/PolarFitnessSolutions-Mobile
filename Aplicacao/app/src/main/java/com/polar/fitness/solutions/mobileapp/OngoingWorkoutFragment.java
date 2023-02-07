package com.polar.fitness.solutions.mobileapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.SystemClock;
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
import com.polar.fitness.solutions.mobileapp.Models.Workout_Plan_Exercise_Relation;

import java.util.ArrayList;

public class OngoingWorkoutFragment extends Fragment {

    ListView lvOngoingWorkoutPlanDetails;
    private Workout_Plan_Exercise_RelationAdapter adapter;
    private ListWorkout_planDetailsAdapter adapter2;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;
    Button Stop;
    TextView tvTimer,tvOngoingWorkoutName;


    public OngoingWorkoutFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ongoing_workout, container, false);
        setHasOptionsMenu(true);
        lvOngoingWorkoutPlanDetails = view.findViewById(R.id.lvOngoingWorkout_planDetailss);
        tvOngoingWorkoutName = view.findViewById(R.id.tvOngoingWorkoutName);
        Stop = (Button) view.findViewById(R.id.btStop);
        tvTimer = (TextView) view.findViewById(R.id.textViewStopWatch);
        StartTime = SystemClock.uptimeMillis();
        handler = new Handler() ;
        handler.postDelayed(runnable, 0);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int s1 = Integer.parseInt(sharedPreferences.getString("wkPlanId", ""));
        String s2 = sharedPreferences.getString("wkPlanName", "");
        tvOngoingWorkoutName.setText(s2);

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
        lvOngoingWorkoutPlanDetails.setAdapter(adapter2);


        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeBuff += MillisecondTime;
                handler.removeCallbacks(runnable);
            }
        });

        return view;
    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            tvTimer.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };
}