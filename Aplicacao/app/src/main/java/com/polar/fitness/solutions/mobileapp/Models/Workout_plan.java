package com.polar.fitness.solutions.mobileapp.Models;

import java.io.Serializable;

public class Workout_plan implements Serializable {
    private int id;
    private String workout_name;
    private int client_id;
    private int worker_id;

    public Workout_plan(int id, String workout_name, int client_id, int worker_id){
        this.id = id;
        this.workout_name = workout_name;
        this.client_id = client_id;
        this.worker_id = worker_id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }
}