package com.ibercivis.mapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.ibercivis.mapp.clases.Adaptador;
import com.ibercivis.mapp.clases.proyectos;

import java.util.ArrayList;
import java.util.Random;

public class ListadoProyectos extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Comunes: Navigation y Toolbar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    //Propios de esta Activity
    RecyclerView recyclerLista;
    ArrayList<proyectos> ListaProyectos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_proyectos);

        /*-----Hooks-----*/

        drawerLayout = findViewById(R.id.drawer_layout2);
        navigationView = findViewById(R.id.nav_view2);
        toolbar = findViewById(R.id.toolbar2);

        /*-----Toolbar-----*/
        setSupportActionBar(toolbar);

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
        navigationView.setCheckedItem(R.id.nav_projects);

        /*-----MÉTODOS PROPIOS DE ESTA ACTIVITY-----*/

        recyclerLista = findViewById(R.id.recyclerproyectos);
        llenarLista(ListaProyectos);
        recyclerLista.setHasFixedSize(true);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerLista.setAdapter(new Adaptador(ListaProyectos));
        recyclerLista.setLayoutManager(layout);
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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_projects:

                break;
            case R.id.nav_crear:
                Intent intent2 = new Intent(getApplicationContext(), CrearProyecto.class);
                startActivity(intent2);
                break;

            case R.id.nav_logout:
                Intent intent4 = new Intent(getApplicationContext(), Login.class);
                startActivity(intent4);
                finish();
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void llenarLista(ArrayList<proyectos> lista) {
        lista.add(new proyectos("OdourCollect", "Proyecto para reportar malos olores en tu ciudad", getRandomColor(), 20, 0, false));
        lista.add(new proyectos("Stop Amianto", "Proyecto para reportar amianto", getRandomColor(), 250, 54, true));
        lista.add(new proyectos("OSM", "Ayuda a elaborar un mapa de tu ciudad", getRandomColor(), 76, 7, false));
        lista.add(new proyectos("D-NOSES", "Proyecto para reportar malos olores en tu continente", getRandomColor(), 47, 4, false));
        lista.add(new proyectos("Movilidad Inteligente", "Localiza puntos críticos para personas con movilidad reducida", getRandomColor(), 121, 19, true));
        lista.add(new proyectos("Vigilantes del Aire", "Mide la calidad del aire en tu barrio", getRandomColor(), 19, 6, false));
        lista.add(new proyectos("Amigos de las Plantas", "Proyecto para reportar especies insectívoras", getRandomColor(), 7, 2, false));
        lista.add(new proyectos("Luces para las Sombras", "Proyecto para localizar sitios de interés", getRandomColor(), 476, 145, false));
        lista.add(new proyectos("Almozara Intoxicada", "Proyecto para reportar residuos toxicos", getRandomColor(), 55, 5, false));
        lista.add(new proyectos("Localiza tu Almendro", "Proyecto para localizar almendros", getRandomColor(), 25, 13, false));
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}

