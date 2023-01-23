package com.polar.fitness.solutions.mobileapp.Listeners;

import com.polar.fitness.solutions.mobileapp.Models.Nutrition_plan;
import com.polar.fitness.solutions.mobileapp.Models.Workout_Plan_Exercise_Relation;

import java.util.ArrayList;

public interface Workout_Plan_Exercise_RelationListener
{
    void onRefreshListWorkout_Plan_Exercise_Relation(ArrayList<Workout_Plan_Exercise_Relation> listWorkout_Plan_Exercise_Relation);
}
