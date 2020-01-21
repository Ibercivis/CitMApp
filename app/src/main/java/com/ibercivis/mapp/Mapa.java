package com.ibercivis.mapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView;

import org.osmdroid.api.IMapController;


import org.osmdroid.config.Configuration;
// import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import static android.content.ContentValues.TAG;

public class Mapa extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    public MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        /*-----Hooks-----*/

        drawerLayout = findViewById(R.id.drawer_layout4);
        navigationView = findViewById(R.id.nav_view4);
        toolbar = findViewById(R.id.toolbar);

        /*-----Toolbar-----*/
        setSupportActionBar(toolbar);

        /*-----Navigation Drawer Menu -----*/

        //Hide or show items

        Menu menu = navigationView.getMenu();

        menu.findItem(R.id.nav_logout).setVisible(true);

        //Fit Navigation Drawer

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open_drawer, R.string.navigation_close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        /*-----MAPA OSMDROID-----*/

        isWriteStoragePermissionGranted();


      //  OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);

        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);


        // Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getContext()));

        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(9);
        GeoPoint startPoint = new GeoPoint(48.8583, 2.2944);
        mapController.setCenter(startPoint);

        /*-----MÉTODOS PROPIOS DE ESTA ACTIVITY-----*/
        addMarcadores(map);

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
                Intent intent2 = new Intent(getApplicationContext(), ListadoProyectos.class);
                startActivity(intent2);
                break;
            case R.id.nav_crear:
                Intent intent3 = new Intent(getApplicationContext(), CrearProyecto.class);
                startActivity(intent3);
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

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(Mapa.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted2");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked2");
                ActivityCompat.requestPermissions(Mapa.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted2");
            return true;
        }
    }

    public void addMarcadores(MapView mapa){

        GeoPoint startPoint = new GeoPoint(48.8583, 2.2944);
        Marker startMarker = new Marker(mapa);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle("Marcador 1");
        startMarker.setSnippet("Aquí podemos añadir cosas <br>" +
                " y <br>tal vez<br> saltos de<br> línea"); // Confirmado que los saltos de línea se hacen con <br>
        mapa.getOverlays().add(startMarker);

    }
}
