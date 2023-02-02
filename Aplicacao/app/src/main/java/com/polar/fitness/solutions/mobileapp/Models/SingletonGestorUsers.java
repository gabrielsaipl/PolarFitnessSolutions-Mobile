package com.polar.fitness.solutions.mobileapp.Models;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.polar.fitness.solutions.mobileapp.Listeners.RegisterListener;
import com.polar.fitness.solutions.mobileapp.Listeners.Workout_Plan_Exercise_RelationListener;
import com.polar.fitness.solutions.mobileapp.Listeners.nutrition_plansListener;
import com.polar.fitness.solutions.mobileapp.Listeners.ExercisesListener;
import com.polar.fitness.solutions.mobileapp.Listeners.Workout_plansListener;
import com.polar.fitness.solutions.mobileapp.R;
import com.polar.fitness.solutions.mobileapp.Utils.UserJsonParser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingletonGestorUsers {

    private static SingletonGestorUsers instancia = null;
    private static RequestQueue volleyQueue = null;
    private final UserDBHelper usersDB;

    // Arraylists
    private ArrayList<User> users;
    private ArrayList<Nutrition_plan> nutrition_plans;
    private ArrayList<Exercise> exercises;
    private ArrayList<Workout_plan> workout_plans;
    private ArrayList<Workout_Plan_Exercise_Relation> workout_plan_exercise_relations;

    // API Urls
    private final static String mUrlLogin = "http://10.0.2.2/github/polarfitnesssolutions-portal/polarfitnesssolutions/backend/web/api/user/login";
    private final static String mUrlnutrition_plan = "http://10.0.2.2/github/polarfitnesssolutions-portal/polarfitnesssolutions/backend/web/api/nutrition_plans";
    private final static String mUrlSignup = "http://10.0.2.2/github/polarfitnesssolutions-portal/polarfitnesssolutions/backend/web/api/user/signup";
    private final static String mUrlAPIexercises = "http://10.0.2.2/github/PolarFitnessSolutions-Portal/PolarFitnessSolutions/backend/web/api/exercises";
    private final static String mUrlAPIworkout_plan = "http://10.0.2.2/github/PolarFitnessSolutions-Portal/PolarFitnessSolutions/backend/web/api/workoutplans";
    private final static String mUrlWorkout_Plan_Exercise_Relation = "http://10.0.2.2/github/PolarFitnessSolutions-Portal/PolarFitnessSolutions/backend/web/api/workout_plan_exercise_relation";

    //Listeners
    private LoginListener loginListener;
    private nutrition_plansListener nutrition_plansListener;
    private RegisterListener registerListener;
    private ExercisesListener exercisesListener;
    private Workout_plansListener workout_plansListener;
    private Workout_Plan_Exercise_RelationListener workout_plan_exercise_relationListener;
    
    public static synchronized SingletonGestorUsers getInstance(Context contexto)
    {
        if (instancia == null) {
            instancia = new SingletonGestorUsers(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }

    //Construtor para o singleton
    private SingletonGestorUsers(Context contexto)
    {
        users = new ArrayList<>();
        usersDB = new UserDBHelper(contexto);
        nutrition_plans = new ArrayList<>();
        workout_plans = new ArrayList<>();
        workout_plan_exercise_relations = new ArrayList<>();
    }

    //Metodos da API
    public void loginAPI(final String username,final String password, Context contexto)
    {
        //Request a API
        StringRequest req = new StringRequest(Request.Method.POST, mUrlLogin, new Response.Listener<String>() {
            //ao receber uma resposta valida da api
            @Override
            public void onResponse(String response) {
                ArrayList<String> token = UserJsonParser.parserJsonLogin(response);
                addUserBD(token);
                //aceder ao nome do utilizador
                /*users = usersDB.getUserBD();
                User user = users.get(0);
                String name = user.getUsername();
                System.out.println("NOME DO HOMEM " + name);*/
                String client_id = token.get(0);
                String email = token.get(2);
                System.out.println("before edit text");
                SharedPreferences sharedPreferences = contexto.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                String s1 = sharedPreferences.getString("client_id", "");
                String s2 = sharedPreferences.getString("email", "");
                myEdit.putString("client_id", client_id);
                myEdit.putString("email", email);
                myEdit.apply();
                String client_id2 = sharedPreferences.getString("client_id", "");
                System.out.println(client_id2);
                System.out.println(token);
                //verificar se o utilizador esta ativo
                if (token.get(9).equals("Ativo") && loginListener!=null){
                        loginListener.onValidateLogin(token,username,contexto);
                    }
                else if (token.get(9).equals("Inativo")){
                    Toast.makeText(contexto, R.string.StringAtivarConta, Toast.LENGTH_SHORT).show();
                }
            }
            //se a resposta da api falhar
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            }
        }) {
            //parametros para passsar para a api
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
    public void RegisterAPI(String username, String email, String password, String rua, String codigoPostal, String localidade, String telefone,  String nif,  String genero, Context contexto)
    {
        StringRequest reqRegister = new StringRequest(Request.Method.POST, mUrlSignup, new Response.Listener<String>() {
            //ao receber uma resposta valida da api
            @Override
            public void onResponse(String response) {

                if(registerListener!=null) {
                    registerListener.onValidateSignup(username, email, password, rua, codigoPostal, localidade, telefone, nif, genero, contexto);
                }
            }
            //se a resposta da api falhar
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            }
        }) {
            //parametros para passsar para a api
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("username",username);
                params.put("email",email);
                params.put("password",password);
                params.put("street",rua);
                params.put("zip_code",codigoPostal);
                params.put("area",localidade);
                params.put("phone_number", String.valueOf(telefone));
                params.put("nif", String.valueOf(nif));
                params.put("gender",genero);

                return params;
            }
        };
        volleyQueue.add(reqRegister);
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
    public void getAllExercisesAPI(final Context context)
    {
        if (!UserJsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, "Sem acesso à internet", Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mUrlAPIexercises, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                exercises = UserJsonParser.parserJsonExercise(response);
                addExercisesDB(exercises);
                if (exercisesListener != null) {
                    exercisesListener.onRefreshListExercises(exercises);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
        volleyQueue.add(request);
    }
    public void getAllWorkout_plansAPI(final Context context)
    {
        if (!UserJsonParser.isConnectionInternet(context)){
            Toast.makeText(context, "Sem acesso à internet", Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mUrlAPIworkout_plan, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                workout_plans = UserJsonParser.parserJsonWorkout_plan(response);
                addWorkout_plansDB(workout_plans);
                if (workout_plansListener != null) {
                    workout_plansListener.onRefreshListWorkout_plans(workout_plans);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
        volleyQueue.add(request);
    }
    public void getAllWorkout_plan_exercise_relationAPI(final Context context)
    {
        if (!UserJsonParser.isConnectionInternet(context)){
            Toast.makeText(context, "Sem acesso à internet", Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mUrlWorkout_Plan_Exercise_Relation, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                workout_plan_exercise_relations = UserJsonParser.parserJsonWorkout_Plan_Exercise_Relation(response);
                addWorkout_plan_exercise_relationsBD(workout_plan_exercise_relations);
                if (workout_plan_exercise_relationListener != null) {
                    workout_plan_exercise_relationListener.onRefreshListWorkout_Plan_Exercise_Relation(workout_plan_exercise_relations);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
        volleyQueue.add(request);
    }

    //Listeners
    public void setExercisesListener(ExercisesListener exercisesListener)
    {
        this.exercisesListener = exercisesListener;
    }
    public void setLoginListener(LoginListener loginListener)
    {
        this.loginListener = loginListener;
    }
    public void setRegisterListener(RegisterListener registerListener)
    {
        this.registerListener = registerListener;
    }
    public void setWorkout_plansListener(Workout_plansListener workout_plansListener)
    {
        this.workout_plansListener = workout_plansListener;
    }
    public void setNutrition_plansListener(nutrition_plansListener nutrition_plansListener)
    {
        this.nutrition_plansListener = nutrition_plansListener;
    }
    public void setWorkout_plan_exercise_relationListener(Workout_Plan_Exercise_RelationListener workout_plan_exercise_relationListener)
    {
        this.workout_plan_exercise_relationListener = workout_plan_exercise_relationListener;
    }

    //Getters
    public ArrayList<Nutrition_plan> getNutrition_plansBD()
    {
        return nutrition_plans = usersDB.getAllNutrition_planBD();
    }
    public Nutrition_plan getNutrition_plan(long id)
    {
        for (Nutrition_plan nutrition_plan : nutrition_plans) {
            return nutrition_plan;
        }
        return null;
    }
    public ArrayList<Workout_plan> getWorkout_planDB()
    {
        return workout_plans = usersDB.getAllWorkout_planDB();
    }
    public ArrayList<Workout_Plan_Exercise_Relation> getWorkout_plan_exercise_relations()
    {
        return workout_plan_exercise_relations = usersDB.getAllWorkout_Plan_Exercise_RelationBD();
    }
    public ArrayList<Workout_plan> getWorkout_plansBD()
    {
        return workout_plans = usersDB.getAllWorkout_planDB();
    }
    public Workout_plan getWorkout_plan()
    {
        for (Workout_plan workout_plan : workout_plans){
            return workout_plan;
        }
        return null;
    }
    public ArrayList<Exercise> getExercisesDB()
    {
        return exercises = usersDB.getAllExercisesDB();
    }
    public Exercise getExercise(long id)
    {
        for (Exercise exercise : exercises){
            return exercise;
        }
        return null;
    }

    //DB Helper
    public void addWorkout_plansDB(ArrayList<Workout_plan> list)
    {
        usersDB.removeAllWorkout_planDB();
        for (Workout_plan workout_plan : list){
            addWorkout_planDB(workout_plan);
        }
    }
    public void addWorkout_planDB(Workout_plan workout_plan)
    {
        usersDB.addWorkout_planDB(workout_plan);
    }
    public void addNutrition_plansBD(ArrayList<Nutrition_plan> list)
    {
        usersDB.removeAllNutrition_planBD();
        for (Nutrition_plan nutrition_plan : list) {
            addNutrition_planBD(nutrition_plan);
        }
    }
    public void addNutrition_planBD(Nutrition_plan nutrition_plan)
    {
        usersDB.addNutrition_planBD(nutrition_plan);
    }
    public void addExerciseDB(Exercise exercise)
    {
        usersDB.addExerciseBD(exercise);
    }
    public void addExercisesDB(ArrayList<Exercise> exercises)
    {
        usersDB.removeAllExercisesDB();
        for (Exercise exercise: exercises){
            addExerciseDB(exercise);
        }
    }

    public void addWorkout_plan_exercise_relationBD(Workout_Plan_Exercise_Relation workout_plan_exercise_relation){
        usersDB.addWorkout_Plan_Exercise_RelationBD(workout_plan_exercise_relation);
    }
    public void addWorkout_plan_exercise_relationsBD(ArrayList<Workout_Plan_Exercise_Relation> list){
        usersDB.removeAllWorkout_Plan_Exercise_Relation();
        for (Workout_Plan_Exercise_Relation workout_plan_exercise_relation: workout_plan_exercise_relations){
            addWorkout_plan_exercise_relationBD(workout_plan_exercise_relation);
        }
    }

    public void addUserBD(ArrayList<String> user){
        usersDB.addUserBD(user);
    }
}