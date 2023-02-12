package com.polar.fitness.solutions.mobileapp.Models;

public class Workout {
    private int id;
    private String Name;
    private String Date;
    private String Duration;
    private String Workout_planName;
    private int Workout_planId;

    public Workout(int id, String name, String date, String duration, String workout_planName, int workout_planId) {
        this.id = id;
        Name = name;
        Date = date;
        Duration = duration;
        Workout_planName = workout_planName;
        Workout_planId = workout_planId;
    }

    public Workout(String name, String date, String duration, String workout_planName, int workout_planId) {
        Name = name;
        Date = date;
        Duration = duration;
        Workout_planName = workout_planName;
        Workout_planId = workout_planId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getWorkout_planName() {
        return Workout_planName;
    }

    public void setWorkout_planName(String workout_planName) {
        Workout_planName = workout_planName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getWorkout_planId() {
        return Workout_planId;
    }

    public void setWorkout_planId(int workout_planId) {
        Workout_planId = workout_planId;
    }
}
