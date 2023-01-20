package com.polar.fitness.solutions.mobileapp.Utils;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;

import kotlin.contracts.Returns;

public class UserJsonParser {

    public static String parserJsonLogin(String resposta){
        String token = null;
        String response;
        try{
            JSONObject jsonObject = new JSONObject(resposta);
            JSONObject success = jsonObject.getJSONObject("success");
            token = success.getString("valor");
            if (!token.equals("yes"))
                return null;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return token;
    }
}