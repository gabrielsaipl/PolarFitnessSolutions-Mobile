package com.polar.fitness.solutions.mobileapp.Listeners;

import android.content.Context;

import java.util.ArrayList;

public interface LoginListener {
    void onValidateLogin(final ArrayList<String> token, final String username, Context contexto);
}
