package com.polar.fitness.solutions.mobileapp.Listeners;


import com.polar.fitness.solutions.mobileapp.Models.Workout_plan;

import java.util.ArrayList;

public interface Workout_plansListener {
    void onRefreshListWorkout_plans(ArrayList<Workout_plan> listWorkout_plan);

    void onResume(ArrayList<Workout_plan> listWorkout_plan);
}
