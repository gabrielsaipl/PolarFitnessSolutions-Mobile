package com.polar.fitness.solutions.mobileapp.Listeners;

import com.polar.fitness.solutions.mobileapp.Models.Nutrition_plan;

import java.util.ArrayList;

public interface nutrition_plansListener {

    void onResume(ArrayList<Nutrition_plan> listNutrition_plan);

    void onRefreshListNutrition_plans(ArrayList<Nutrition_plan> listNutrition_plan);
}
