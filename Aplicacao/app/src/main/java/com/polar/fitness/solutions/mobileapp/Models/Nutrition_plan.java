package com.polar.fitness.solutions.mobileapp.Models;

import java.io.Serializable;

public class Nutrition_plan implements Serializable
{
    private int id;
    private String nutritionname;
    private String content;
    private int client_id;
    private int worker_id;

    //Construtor
    public Nutrition_plan(int id, String nutritionname, String content, int client_id, int worker_id){
        this.id = id;
        this.nutritionname = nutritionname;
        this.content = content;
        this.client_id = client_id;
        this.worker_id = worker_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNutritionname() {
        return nutritionname;
    }

    public void setNutritionname(String nutritionname) {
        this.nutritionname = nutritionname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
