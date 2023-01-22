package com.polar.fitness.solutions.mobileapp.Views.Drawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.polar.fitness.solutions.mobileapp.R;

public class NutritionBookingFragment extends Fragment {

    public NutritionBookingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nutrition_booking, container, false);
    }
}