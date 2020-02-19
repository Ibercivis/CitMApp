package com.ibercivis.mapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.ibercivis.mapp.clases.SessionManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    CardView card_proyectos, card_crear, card_usuario, card_quienes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*-----Hooks-----*/

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        card_proyectos = findViewById(R.id.proyectos_card);
        card_crear = findViewById(R.id.crear_card);
        card_quienes = findViewById(R.id.quienes_card);
        card_usuario = findViewById(R.id.usuario_card);

        /*-----Toolbar-----*/
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        /*-----Navigation Drawer Menu -----*/

        //Hide or show items

        Menu menu = navigationView.getMenu();

        menu.findItem(R.id.nav_logout).setVisible(true);

        //Fit Navigation Drawer

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open_drawer, R.string.navigation_close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        /*-----MÉTODOS PROPIOS DE ESTA ACTIVITY-----*/

        card_proyectos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListadoProyectos.class);
                startActivity(intent);
            }
        });

        card_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), CrearProyecto.class);
                startActivity(intent2);
            }
        });

        card_quienes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), QuienesSomos.class);
                startActivity(intent3);
            }
        });

        card_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), Usuario.class);
                startActivity(intent4);
            }
        });

    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_projects:
                Intent intent = new Intent(getApplicationContext(), ListadoProyectos.class);
                startActivity(intent);
                break;
            case R.id.nav_crear:
                Intent intent2 = new Intent(getApplicationContext(), CrearProyecto.class);
                startActivity(intent2);
                break;
            case R.id.nav_logout:
                SessionManager session = new SessionManager(getApplicationContext());
                session.setClear();
                Intent intent4 = new Intent(getApplicationContext(), Login.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent4);
                finish();
                break;
            case R.id.nav_misproyectos:
                Intent intent5 = new Intent(getApplicationContext(), Usuario.class);
                startActivity(intent5);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
