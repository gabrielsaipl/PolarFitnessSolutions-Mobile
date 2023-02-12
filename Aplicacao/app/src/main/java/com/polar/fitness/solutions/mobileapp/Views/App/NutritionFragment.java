package com.polar.fitness.solutions.mobileapp.Views.App;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.polar.fitness.solutions.mobileapp.Adapters.ListNutrition_planAdapter;
import com.polar.fitness.solutions.mobileapp.Listeners.nutrition_plansListener;
import com.polar.fitness.solutions.mobileapp.Models.Nutrition_plan;
import com.polar.fitness.solutions.mobileapp.Models.SingletonGestorUsers;
import com.polar.fitness.solutions.mobileapp.R;

import java.util.ArrayList;

public class NutritionFragment extends Fragment implements nutrition_plansListener {

    public static final int CODE_REQUEST_ADICIONAR = 1;
    ArrayList<Nutrition_plan> items;

    private ListView lvListNutrition;
    private ListNutrition_planAdapter adaptador;
    private SearchView searchView;
    public static final String ID= "id";
    private static final int CODE_REQUEST_EDITAR = 2;


    public  NutritionFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nutrition, container, false);
                setHasOptionsMenu(true);

        //obter instancia da listview
        lvListNutrition = view.findViewById(R.id.lvListNutritionPlan);

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String s1 = sharedPreferences.getString("client_id", "");
        int s2 = Integer.parseInt(s1);
        ArrayList<Nutrition_plan> auxNutrition_plan = new ArrayList<>();
        for (Nutrition_plan nutrition_plan: SingletonGestorUsers.getInstance(getContext()).getNutrition_plansBD()) {
            if (nutrition_plan.getClient_id() == s2){
                auxNutrition_plan.add(nutrition_plan);
            }
        }

        adaptador = new ListNutrition_planAdapter(getContext(), auxNutrition_plan);
        lvListNutrition.setAdapter(adaptador);
        lvListNutrition.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                 Nutrition_plan selectedPlan = (Nutrition_plan) adaptador.getItem(position);
                 String Title = selectedPlan.getNutritionname();
                 String Content = selectedPlan.getContent();
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("NutTitle", Title);
                myEdit.putString("NutContent", Content);
                myEdit.apply();

                startChildFragment();
            }
        });
        SingletonGestorUsers.getInstance(getContext()).setNutrition_plansListener(this);
        SingletonGestorUsers.getInstance((getContext())).getAllNutrition_plansAPI(getContext());
        return view;
    }

    @Override
    public void onResume(ArrayList<Nutrition_plan> listNutrition_plan) {

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String s1 = sharedPreferences.getString("client_id", "");
        int s2 = Integer.parseInt(s1);
        ArrayList<Nutrition_plan> auxNutrition_plan = new ArrayList<>();
        for (Nutrition_plan nutrition_plan : SingletonGestorUsers.getInstance(getContext()).getNutrition_plansBD()) {
            if (nutrition_plan.getClient_id() == s2) {
                auxNutrition_plan.add(nutrition_plan);
            }
        }
        if (listNutrition_plan != null) {
            lvListNutrition.setAdapter(new ListNutrition_planAdapter(getContext(), auxNutrition_plan));
        }
        adaptador = new ListNutrition_planAdapter(getContext(), auxNutrition_plan);


        lvListNutrition.setAdapter(adaptador);
        super.onResume();
    }

    @Override
    public void onRefreshListNutrition_plans(ArrayList<Nutrition_plan> listNutrition_plan)
    {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String s1 = sharedPreferences.getString("client_id", "");
        int s2 = Integer.parseInt(s1);
        ArrayList<Nutrition_plan> auxNutrition_plan = new ArrayList<>();
        for (Nutrition_plan nutrition_plan: SingletonGestorUsers.getInstance(getContext()).getNutrition_plansBD()) {
            if (nutrition_plan.getClient_id() == s2){
                auxNutrition_plan.add(nutrition_plan);
            }
        }
        if(listNutrition_plan != null)
        {
            lvListNutrition.setAdapter(new ListNutrition_planAdapter(getContext(), auxNutrition_plan));
        }
        adaptador = new ListNutrition_planAdapter(getContext(), auxNutrition_plan);


        lvListNutrition.setAdapter(adaptador);

    }
    private void startChildFragment(){
        Fragment newFragment = new NutritionDetailsFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}