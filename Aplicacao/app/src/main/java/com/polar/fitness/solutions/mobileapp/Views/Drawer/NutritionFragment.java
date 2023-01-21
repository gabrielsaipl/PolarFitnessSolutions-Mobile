package com.polar.fitness.solutions.mobileapp.Views.Drawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private ListView lvListNutrition;
    //private ArrayAdapter<Livro> adaptador;
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
        //adaptador = new ArrayAdapter<Livro>(getContext(), android.R.layout.simple_list_item_1, SingletonGestorLivros.getInstance().getLivros());
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
        if(listNutrition_plan != null)
        {
            lvListNutrition.setAdapter(new ListNutrition_planAdapter(getContext(), listNutrition_plan));
        }

    }
}