package com.polar.fitness.solutions.mobileapp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.polar.fitness.solutions.mobileapp.Models.Exercise;
import com.polar.fitness.solutions.mobileapp.Models.Nutrition_plan;
import com.polar.fitness.solutions.mobileapp.Models.Workout_plan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class UserJsonParser {

    public static String parserJsonLogin(String resposta){
        String token = null;
        String response;
        String monkey;
        ArrayList<String> users;
        users = new ArrayList<String>();
        try {
            JSONObject jsonObject = new JSONObject(resposta);
            Iterator<String> keys = jsonObject.keys();

            while (keys.hasNext()) {
                String keyValue = (String) keys.next();
                String valueString = jsonObject.getString(keyValue);

                users.add(valueString);
            }
            token = users.get(9);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return token;
    }

    public static String parserJsonLogin2(String resposta){
        String token = null;
        String response;
        String monkey;
        ArrayList<String> users;
        users = new ArrayList<String>();
        try {
            JSONObject jsonObject = new JSONObject(resposta);
            Iterator<String> keys = jsonObject.keys();

            while (keys.hasNext()) {
                String keyValue = (String) keys.next();
                String valueString = jsonObject.getString(keyValue);

                users.add(valueString);
            }
            token = users.get(0);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return token;
    }

    public static ArrayList<Nutrition_plan> parserJsonNutrition_plan(JSONArray resposta)
    {
        ArrayList<Nutrition_plan> list = new ArrayList<>();
        try {
            for(int i = 0; i < resposta.length(); i++)
            {
                JSONObject jsonNutrition_plan = (JSONObject) resposta.get(i);
                int id = jsonNutrition_plan.getInt("id");
                String nutritionname = jsonNutrition_plan.getString("nutritionname");
                String content = jsonNutrition_plan.getString("content");
                int client_id = jsonNutrition_plan.getInt("client_id");
                int worker_id = jsonNutrition_plan.getInt("worker_id");

                Nutrition_plan nutrition_plan = new Nutrition_plan(id, nutritionname, content, client_id, worker_id);
                list.add(nutrition_plan);
            }


        } catch (JSONException e)
        {
            e.printStackTrace();
        }

      return list;

    }

    public static ArrayList<Exercise> parserJsonExercise(JSONArray resposta)
    {
        ArrayList<Exercise> list = new ArrayList<>();
        try {
            for(int i = 0; i < resposta.length(); i++)
            {
                JSONObject jsonExercise = (JSONObject) resposta.get(i);
                int id = jsonExercise.getInt("id");
                String exercise_name = jsonExercise.getString("exercise_name");
                int max_rep = jsonExercise.getInt("max_rep");
                int min_rep = jsonExercise.getInt("min_rep");
                int sets = jsonExercise.getInt("sets");

                Exercise exercise = new Exercise(id, exercise_name, max_rep, min_rep, sets);
                list.add(exercise);
            }


        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return list;

    }

    public static ArrayList<Workout_plan> parserJsonWorkout_plan(JSONArray resposta)
    {
        ArrayList<Workout_plan> list = new ArrayList<>();
        try {
            for(int i = 0; i < resposta.length(); i++)
            {
                JSONObject jsonWorkout_plan = (JSONObject) resposta.get(i);
                int id = jsonWorkout_plan.getInt("id");
                String workout_name = jsonWorkout_plan.getString("workout_name");
                int client_id = jsonWorkout_plan.getInt("client_id");
                int worker_id = jsonWorkout_plan.getInt("worker_id");

                Workout_plan workout_plan = new Workout_plan(id, workout_name, client_id, worker_id);
                list.add(workout_plan);
            }


        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return list;

    }

    public static boolean isConnectionInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    public static String parserJsonRegister(String resposta){
        String token = null;
        ArrayList<String> users;
        users = new ArrayList<String>();
        try {
            JSONObject jsonObject = new JSONObject(resposta);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String keyValue = (String) keys.next();
                String valueString = jsonObject.getString(keyValue);
                users.add(valueString);
            }
            token = users.get(15);
            if (token.equals("")){
                return null;
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
            return token;
        }
    }
