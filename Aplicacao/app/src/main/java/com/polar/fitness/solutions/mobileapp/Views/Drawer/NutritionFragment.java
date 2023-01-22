package com.polar.fitness.solutions.mobileapp.Views.Drawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.polar.fitness.solutions.mobileapp.Adapters.ListNutrition_planAdapter;
import com.polar.fitness.solutions.mobileapp.Listeners.nutrition_plansListener;
import com.polar.fitness.solutions.mobileapp.Models.Nutrition_plan;
import com.polar.fitness.solutions.mobileapp.Models.SingletonGestorUsers;
import com.polar.fitness.solutions.mobileapp.Models.User;
import com.polar.fitness.solutions.mobileapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class NutritionFragment extends Fragment implements nutrition_plansListener {

    public static final int CODE_REQUEST_ADICIONAR = 1;

    private ListView lvListNutrition;
    private ListNutrition_planAdapter adaptador;
    private SearchView searchView;
    public static final String ID= "id";
    private static final int CODE_REQUEST_EDITAR = 2;


    public  NutritionFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nutrition, container, false);

        setHasOptionsMenu(true);

        //obter instancia da listview
        lvListNutrition = view.findViewById(R.id.lvListNutritionPlan);

        //criar adaptador para listview
        adaptador = new ListNutrition_planAdapter(getContext(), SingletonGestorUsers.getInstance(getContext()).getNutrition_plansBD());
        //Associar adaptador á listview
        lvListNutrition.setAdapter(adaptador);

        SingletonGestorUsers.getInstance(getContext()).setNutrition_plansListener(this);
        SingletonGestorUsers.getInstance((getContext())).getAllNutrition_plansAPI(getContext());
        return view;
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

    }
}