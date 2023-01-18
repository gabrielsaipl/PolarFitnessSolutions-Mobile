package com.polar.fitness.solutions.mobileapp.Views.Drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SlidingDrawer;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.polar.fitness.solutions.mobileapp.R;

public class MainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar ,R.string.OpenNav, R.string.CloseNav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WorkoutFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_treinos);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
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
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }
}