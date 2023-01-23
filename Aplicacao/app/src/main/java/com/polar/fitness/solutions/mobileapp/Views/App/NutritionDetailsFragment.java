package com.polar.fitness.solutions.mobileapp.Views.App;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.polar.fitness.solutions.mobileapp.Listeners.nutrition_plansListener;
import com.polar.fitness.solutions.mobileapp.Models.Nutrition_plan;
import com.polar.fitness.solutions.mobileapp.R;

import java.util.ArrayList;


public class NutritionDetailsFragment extends Fragment implements nutrition_plansListener {


    public TextView tvTitulo;
    public TextView tvConteudo;
    public NutritionDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nutrition_details, container, false);
        setHasOptionsMenu(true);
        tvTitulo = (TextView) view.findViewById(R.id.teste2);
        tvConteudo = (TextView) view.findViewById(R.id.teste3);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String s1 = sharedPreferences.getString("NutTitle", "");
        String s2 = sharedPreferences.getString("NutContent", "");
        tvTitulo.setText(s1);
        tvConteudo.setText(s2);


        return view;
    }


    @Override
    public void onResume(ArrayList<Nutrition_plan> listNutrition_plan) {

    }

    @Override
    public void onRefreshListNutrition_plans(ArrayList<Nutrition_plan> listNutrition_plan) {

    }
}