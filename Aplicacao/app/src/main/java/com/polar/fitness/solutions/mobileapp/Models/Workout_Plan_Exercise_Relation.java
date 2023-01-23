package com.polar.fitness.solutions.mobileapp.Models;

import java.io.Serializable;

public class Workout_Plan_Exercise_Relation implements Serializable
{
    private int id;
    private int workout_plan_id;
    private int exercise_id;

    //Construtor
    public Workout_Plan_Exercise_Relation(int id, int workout_plan_id, int exercise_id)
    {
        this.id = id;
        this.workout_plan_id = workout_plan_id;
        this.exercise_id = exercise_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkout_plan_id() {
        return workout_plan_id;
    }

    public void setWorkout_plan_id(int workout_plan_id) {
        this.workout_plan_id = workout_plan_id;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }
}
