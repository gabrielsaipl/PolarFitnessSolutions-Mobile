package com.polar.fitness.solutions.mobileapp.Listeners;

import android.content.Context;

public interface LoginListener {
    void onValidateLogin(final String token, final String username, Context contexto);
}
