package com.polar.fitness.solutions.mobileapp.Utils;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;

public class UserJsonParser {

    public static String parserJsonLogin(String resposta){
        String token = null;
        String response;
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
            String inscricao = users.get(9);
            if (inscricao.equals("Inativo")){
                return null;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return token;
    }

    public static ArrayList<Nutrition_plan> parserJsonNutrition_plan(JSONArray resposta)
    {
        ArrayList<Nutrition_plan> list = new ArrayList<>();
        try {
            for(int i = 0; i <resposta.length(); i++)
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

    public static boolean isConnectionInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}