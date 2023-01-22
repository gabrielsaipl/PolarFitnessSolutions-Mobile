package com.polar.fitness.solutions.mobileapp.Listeners;

import android.content.Context;

public interface RegisterListener {

    void onValidateSignup(final String username, final String email, final String password, final String rua, final String codigoPostal, final String localidade, final String telefone, final String nif, final String genero, Context contexto);
}