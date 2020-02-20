package com.ibercivis.mapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.ibercivis.mapp.clases.Adaptador;
import com.ibercivis.mapp.clases.SessionManager;
import com.ibercivis.mapp.clases.proyectos;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ListadoProyectos extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    //Comunes: Navigation y Toolbar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Bitmap bitmap_logo;

    //Propios de esta Activity
    RecyclerView recyclerLista;
    Adaptador recyclerAdapter;
    ArrayList<proyectos> ListaProyectos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_proyectos);

        /*-----Hooks-----*/

        drawerLayout = findViewById(R.id.drawer_layout2);
        navigationView = findViewById(R.id.nav_view2);
        toolbar = findViewById(R.id.toolbar2);

        toolbar.setBackgroundColor(getColor(R.color.colorBlanco));

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
        navigationView.setCheckedItem(R.id.nav_projects);

        /*-----MÉTODOS PROPIOS DE ESTA ACTIVITY-----*/

        getInfoRequest();

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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public void getInfoRequest () {
        final LinearLayout cargar = findViewById(R.id.cargando);
        String idUser, toktok;
        int item;
        SessionManager session = new SessionManager(ListadoProyectos.this);
        cargar.setVisibility(View.VISIBLE);
        recyclerLista = findViewById(R.id.recyclerproyectos);

        // Input data ok, so go with the request

        // Url for the webservice
        idUser = String.valueOf(session.getIdUser());
        toktok = session.getToken();

        String url = getString(R.string.base_url) + "/listaDeProyectos.php?idUser=" + idUser +"&token=" + toktok;

        RequestQueue queue = Volley.newRequestQueue(ListadoProyectos.this);
        queue.getCache().clear();
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println(response.toString());

                    JSONObject responseJSON = new JSONObject(response);

                    if ((int) responseJSON.get("result") == 1){

                        int value;
                        int i = 0;
                        JSONArray jsonArray = responseJSON.getJSONArray("data");

                        for (i=0; i < jsonArray.length(); i++){

                            // JSONArray jsonArray1 = jsonArray.getJSONArray(i); //Diferentes proyectos


                            int id = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("id")));
                            int voted = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("voted")));
                            int aportaciones = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("marcadores")));
                            int likes = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("votos")));
                            int privado = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("privado")));
                            int hasLogo = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("logo")));
                            int idUser = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("idUser")));
                            String title = String.valueOf(jsonArray.getJSONObject(i).get("titulo"));
                            String description = String.valueOf(jsonArray.getJSONObject(i).get("descripcion"));
                            String password = String.valueOf(jsonArray.getJSONObject(i).get("password"));
                            String web = String.valueOf(jsonArray.getJSONObject(i).get("web"));
                            if(hasLogo == 1) {

                                String URL_logo = "https://citmapp.ibercivis.es/uploads/proyectos/"+String.valueOf(id)+".jpg";


                                ListaProyectos.add(new proyectos(id, idUser, title, description, web, getRandomColor(), aportaciones, likes, voted, privado, password, hasLogo, URL_logo));

                            } else {
                                ListaProyectos.add(new proyectos(id, idUser, title, description, web, getRandomColor(), aportaciones, likes, voted, privado, password, hasLogo));
                            }



                        }
                        recyclerLista.setHasFixedSize(true);
                        LinearLayoutManager layout = new LinearLayoutManager(ListadoProyectos.this);
                        layout.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerAdapter = new Adaptador(ListaProyectos);
                        //recyclerLista.setAdapter(new Adaptador(ListaProyectos));
                        recyclerLista.setAdapter(recyclerAdapter);
                        recyclerLista.setLayoutManager(layout);
                        cargar.setVisibility(View.GONE);


                        // usernametxt.setText(user);


                    }
                    else {
                        Log.println(Log.ASSERT, "Error", "Algo ha fallado que la respuesta es 0");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> signup_params = new HashMap<String, String>();


                return signup_params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        sr.setShouldCache(false);
        queue.add(sr);
    }

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_buscador, menu);
        MenuItem item = menu.findItem(R.id.buscador);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(this);

        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                recyclerAdapter.setFilter(ListaProyectos);

                return true;
            }
        });

    return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        try{

            ArrayList<proyectos> listaFiltrada = filter(ListaProyectos,newText);
            recyclerAdapter.setFilter(listaFiltrada);

        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    private ArrayList<proyectos> filter(ArrayList<proyectos> proyecto, String texto) {

        ArrayList<proyectos> listaFiltrada = new ArrayList<>();

        try{
            texto=texto.toLowerCase();

            for(proyectos project: proyecto){

                String project2 = project.getTitulo().toLowerCase();

                if(project2.contains(texto)){
                    listaFiltrada.add(project);
                }

            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return listaFiltrada;
    }
}

