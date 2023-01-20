package com.polar.fitness.solutions.mobileapp.Models;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.polar.fitness.solutions.mobileapp.Listeners.LoginListener;
import com.polar.fitness.solutions.mobileapp.R;
import com.polar.fitness.solutions.mobileapp.Utils.UserJsonParser;
import com.polar.fitness.solutions.mobileapp.Views.App.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SingletonGestorUsers {
    
    private static SingletonGestorUsers instancia = null;
    private static RequestQueue volleyQueue = null;
    private UserDBHelper usersDB = null;
    private ArrayList<User> users;
    private final static String mUrlLogin = "http://10.0.2.2/github/polarfitnesssolutions-portal/polarfitnesssolutions/backend/web/api/user/login";
    private LoginListener loginListener;
    
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


    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

}
