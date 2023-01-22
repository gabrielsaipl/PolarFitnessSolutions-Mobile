package com.polar.fitness.solutions.mobileapp.Views.Drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.polar.fitness.solutions.mobileapp.R;

public class MainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private String username, email;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        navigationView = findViewById(R.id.nav_view);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenNav, R.string.CloseNav);
        fragmentManager = getSupportFragmentManager();
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WorkoutFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_treinos);
        }
        loadFragmentsMain();
        loadUserData();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_treinos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WorkoutFragment()).commit();
                break;
            case R.id.nav_aval_fisicas:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PhysicalEvaluationFragment()).commit();
                break;
            case R.id.nav_nutricao:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NutritionFragment()).commit();
                break;
            case R.id.nav_mensagens:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MessagesFragment()).commit();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Adeus", Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed()
    {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void loadUserData()
    {
        // Aceder a sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sharedPreferences.getString("etUsername", "");
        String s2 = sharedPreferences.getString("etEmail", "");
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.usernameTvNav);
        navUsername.setText(s1);
        if (email != null) {
            TextView emailTvNav = headerView.findViewById(R.id.emailTvNav);
            emailTvNav.setText(s2);
        } else {
            TextView emailTvNav = headerView.findViewById(R.id.emailTvNav);
            emailTvNav.setText ("Email não definido");
        }
    }

    public void loadFragmentsMain()
    {
        Intent intent = getIntent();
        String fragmentName = intent.getStringExtra("fragment");
        String workout = "WorkoutFragment";
        String nutrition = "NutritionFragment";
        String physical = "PhysicalEvaluationFragment";
        String messages = "MessagesFragment";
        Log.e("Test1", "Test1" + fragmentName);
        if (fragmentName != null && fragmentName.equals(workout)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WorkoutFragment()).commit();
        } else if (fragmentName != null && fragmentName.equals(nutrition)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NutritionFragment()).commit();
        } else if (fragmentName != null && fragmentName.equals(physical)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PhysicalEvaluationFragment()).commit();
        } else if (fragmentName != null && fragmentName.equals(messages)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MessagesFragment()).commit();
        } else Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
    }

}