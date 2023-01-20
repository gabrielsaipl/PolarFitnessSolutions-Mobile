package com.polar.fitness.solutions.mobileapp.Models;

import android.app.DownloadManager;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.polar.fitness.solutions.mobileapp.Listeners.LoginListener;
import com.polar.fitness.solutions.mobileapp.Listeners.nutrition_plansListener;
import com.polar.fitness.solutions.mobileapp.R;
import com.polar.fitness.solutions.mobileapp.Utils.UserJsonParser;
import com.polar.fitness.solutions.mobileapp.Views.App.LoginActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SingletonGestorUsers {
    
    private static SingletonGestorUsers instancia = null;
    private static RequestQueue volleyQueue = null;
    private UserDBHelper usersDB = null;
    private ArrayList<User> users;
    private ArrayList<Nutrition_plan> nutrition_plans;
    private final static String mUrlLogin = "http://10.0.2.2/github/polarfitnesssolutions-portal/polarfitnesssolutions/backend/web/api/user/login";
    private final static String mUrlnutrition_plan = "http://10.0.2.2/github/polarfitnesssolutions-portal/polarfitnesssolutions/backend/web/api/nutrition_plan";

    private LoginListener loginListener;
    private nutrition_plansListener nutrition_plansListener;

    
    public static synchronized SingletonGestorUsers getInstance(Context contexto){
        if (instancia == null) {
            instancia = new SingletonGestorUsers(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }
    
    private SingletonGestorUsers(Context contexto) {
        users = new ArrayList<>();
        usersDB = new UserDBHelper(contexto);
        nutrition_plans = new ArrayList<>();
    }

    public ArrayList<Nutrition_plan> getNutrition_plansBD() {
        return nutrition_plans = usersDB.getAllNutrition_planBD();
    }

    //Buscar os livros do ficheiro criado para a lista
    public void setLivros(ArrayList<Nutrition_plan> list) {
        this.nutrition_plans = list;
    }
    public Nutrition_plan getNutrition_plan(long id) {
        for (Nutrition_plan nutrition_plan : nutrition_plans) {
            return nutrition_plan;
        }
        return null;
    }

    public void addNutrition_plansBD(ArrayList<Nutrition_plan> list) {
        usersDB.removeAllNutrition_planBD();
        for (Nutrition_plan nutrition_plan : list) {
            addNutrition_planBD(nutrition_plan);
        }
    }

    public void addNutrition_planBD(Nutrition_plan nutrition_plan)
    {
        usersDB.addNutrition_planBD(nutrition_plan);
    }
    public void setNutrition_plansListener(nutrition_plansListener nutrition_plansListener)
    {
        this.nutrition_plansListener = nutrition_plansListener;
    }

    public void loginAPI(final String username,final String password, Context contexto){

        StringRequest req = new StringRequest(Request.Method.POST, mUrlLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String token = UserJsonParser.parserJsonLogin(response);
                if(loginListener!=null){
                    loginListener.onValidateLogin(token,username,contexto);
                }
            }
            }, new Response.ErrorListener(){
                @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void getAllNutrition_plansAPI(final Context contexto)
    {
        if(!UserJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlnutrition_plan, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                nutrition_plans = UserJsonParser.parserJsonNutrition_plan(response);
                addNutrition_plansBD(nutrition_plans);
                if(nutrition_plansListener!=null)
                {
                    nutrition_plansListener.onRefreshListNutrition_plans(nutrition_plans);
                }

            }
        }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
    });
    volleyQueue.add(req);
}



    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

}
