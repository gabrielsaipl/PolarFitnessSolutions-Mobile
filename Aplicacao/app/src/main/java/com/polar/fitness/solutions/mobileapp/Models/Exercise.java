package com.polar.fitness.solutions.mobileapp.Models;

import java.io.Serializable;

public class Exercise implements Serializable {
    private int id;
    private String exercise_name;
    private int max_rep;
    private int min_rep;

    public Exercise(int id, String exercise_name, int max_rep, int min_rep){
        this.id = id;
        this.exercise_name = exercise_name;
        this.max_rep = max_rep;
        this.min_rep = min_rep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public int getMax_rep() {
        return max_rep;
    }

    public void setMax_rep(int max_rep) {
        this.max_rep = max_rep;
    }

    public int getMin_rep() {
        return min_rep;
    }

    public void setMin_rep(int min_rep) {
        this.min_rep = min_rep;
    }
}
