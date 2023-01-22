package com.polar.fitness.solutions.mobileapp.Listeners;

import com.polar.fitness.solutions.mobileapp.Models.Exercise;

import java.util.ArrayList;

public interface ExercisesListener {
    void onRefreshListExercises(ArrayList<Exercise> exerciseList);
}
