package com.polar.fitness.solutions.mobileapp.Views.App;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
import com.polar.fitness.solutions.mobileapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OngoingWorkoutFragment extends Fragment {

    ListView lvOngoingWorkoutPlanDetails;
    private Workout_Plan_Exercise_RelationAdapter adapter;
    private ListWorkout_planDetailsAdapter adapter2;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int hours, Seconds, Minutes, MilliSeconds ;
    Button Stop, Cancel;
    TextView tvTimer,tvOngoingWorkoutName;
    String dataInicio;
    public OngoingWorkoutFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ongoing_workout, container, false);
        setHasOptionsMenu(true);
        lvOngoingWorkoutPlanDetails = view.findViewById(R.id.lvOngoingWorkout_planDetailss);
        tvOngoingWorkoutName = view.findViewById(R.id.tvOngoingWorkoutName);
        Stop = (Button) view.findViewById(R.id.btStop);
        Cancel = (Button) view.findViewById(R.id.btCancel);
        tvTimer = (TextView) view.findViewById(R.id.textViewStopWatch);
        StartTime = SystemClock.uptimeMillis();
        handler = new Handler() ;
        handler.postDelayed(runnable, 0);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dataInicio = dateFormat.format(currentTime);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("dataInicioTreino", dataInicio);


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

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Cancelar Treino");
                alert.setMessage("De certeza que deseja cancelar o treino?");
                alert.setPositiveButton("Cancelar Treino", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TimeBuff += MillisecondTime;
                        handler.removeCallbacks(runnable);
                        StartWorkout();
                    }
                });
                alert.setNegativeButton("Resumir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // close dialog
                        dialog.cancel();
                    }
                });
                alert.show();
            }
        });

        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeBuff += MillisecondTime;
                handler.removeCallbacks(runnable);
                String TempoDecorrido = tvTimer.getText().toString();
                myEdit.putString("TempoTreinoDecorrido", TempoDecorrido);
                myEdit.apply();
                finishWorkout();
            }
        });

        return view;
    }


    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            hours = Minutes / 60;

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            tvTimer.setText(String.format("%02d", hours) + ":" + String.format("%02d", Minutes) + ":"
                    + String.format("%02d", Seconds));

            handler.postDelayed(this, 0);
        }

    };

    private void finishWorkout(){
        Fragment newFragment = new FinishedWorkoutFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void StartWorkout(){
        Fragment newFragment = new StartWorkoutFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}