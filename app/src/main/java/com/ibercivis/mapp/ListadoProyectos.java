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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ListadoProyectos extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Comunes: Navigation y Toolbar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Bitmap bitmap_logo;

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

        toolbar.setBackgroundColor(getColor(R.color.colorBlanco));

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

        getInfoRequest();

        /* recyclerLista = findViewById(R.id.recyclerproyectos);
        //llenarLista(ListaProyectos);
        getInfoRequest();
        recyclerLista.setHasFixedSize(true);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerLista.setAdapter(new Adaptador(ListaProyectos));
        recyclerLista.setLayoutManager(layout); */
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

    private void llenarLista(ArrayList<proyectos> lista) {
     /*   lista.add(new proyectos("OdourCollect", "Proyecto para reportar malos olores en tu ciudad", getRandomColor(), 20, 0, false));
        lista.add(new proyectos("Stop Amianto", "Proyecto para reportar amianto", getRandomColor(), 250, 54, true));
        lista.add(new proyectos("OSM", "Ayuda a elaborar un mapa de tu ciudad", getRandomColor(), 76, 7, false));
        lista.add(new proyectos("D-NOSES", "Proyecto para reportar malos olores en tu continente", getRandomColor(), 47, 4, false));
        lista.add(new proyectos("Movilidad Inteligente", "Localiza puntos críticos para personas con movilidad reducida", getRandomColor(), 121, 19, true));
        lista.add(new proyectos("Vigilantes del Aire", "Mide la calidad del aire en tu barrio", getRandomColor(), 19, 6, false));
        lista.add(new proyectos("Amigos de las Plantas", "Proyecto para reportar especies insectívoras", getRandomColor(), 7, 2, false));
        lista.add(new proyectos("Luces para las Sombras", "Proyecto para localizar sitios de interés", getRandomColor(), 476, 145, false));
        lista.add(new proyectos("Almozara Intoxicada", "Proyecto para reportar residuos toxicos", getRandomColor(), 55, 5, false));
        lista.add(new proyectos("Localiza tu Almendro", "Proyecto para localizar almendros", getRandomColor(), 25, 13, false)); */
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
                        recyclerLista.setAdapter(new Adaptador(ListaProyectos));
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

   /* public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        Bitmap bitmap;
        Bitmap bitmap_para_logo;
        proyectos proyecto;
        public GetImageFromUrl(proyectos proyecto){

            this.proyecto = proyecto;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String stringUrl = url[0];
            bitmap = null;
            InputStream inputStream;
            try {
                inputStream = new java.net.URL(stringUrl).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                bitmap_logo = bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            // imageView.setImageBitmap(bitmap);

            bitmap_para_logo = bitmap;
            bitmap_logo = bitmap;
            proyecto.setLogo(bitmap);


        }
    } */

    public Bitmap devolver_bitmap(Bitmap bm){




        return bm;
    }


}
