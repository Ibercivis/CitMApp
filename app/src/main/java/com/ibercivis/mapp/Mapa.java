package com.ibercivis.mapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ibercivis.mapp.clases.Adaptador;
import com.ibercivis.mapp.clases.DownloadFilesTask;
import com.ibercivis.mapp.clases.Marcador;
import com.ibercivis.mapp.clases.SessionManager;
import com.ibercivis.mapp.clases.marcadorTipo;
import com.ibercivis.mapp.clases.proyectos;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.api.IMapController;


import org.osmdroid.config.Configuration;
// import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Mapa extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LocationListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RelativeLayout paraMapa;
    LinearLayout layout_marcador, layout_foto, atri1, atri2, atri3, atri4, atri5, atri6, atri7, atri8, atri9, atri10, atri11, atri12, atri13, atri14, atri15, atri16;
    LinearLayout layout_atributo1, layout_atributo2, layout_atributo3, layout_atributo4, layout_atributo5, layout_atributo6, layout_atributo7, layout_atributo8, layout_atributo9, layout_atributo10, layout_atributo11, layout_atributo12, layout_atributo13, layout_atributo14, layout_atributo15, layout_atributo16;
    TextInputLayout titulo1, titulo2, titulo3, titulo4, titulo5, titulo6, titulo7, titulo8, titulo9, titulo10, titulo11, titulo12, titulo13, titulo14, titulo15, titulo16;
    TextInputEditText edit_atri1, edit_atri2, edit_atri3, edit_atri4, edit_atri5, edit_atri6, edit_atri7, edit_atri8, edit_atri9, edit_atri10, edit_atri11, edit_atri12, edit_atri13, edit_atri14, edit_atri15, edit_atri16;
    ImageView marco_foto;
    LinearLayout marcador_mostrado, layout_info;
    TextView enunciado_atri1, enunciado_atri2, enunciado_atri3, enunciado_atri4, enunciado_atri5, enunciado_atri6, enunciado_atri7, enunciado_atri8, enunciado_atri9, enunciado_atri10, enunciado_atri11, enunciado_atri12, enunciado_atri13, enunciado_atri14, enunciado_atri15, enunciado_atri16;
    TextView respuesta_atri1, respuesta_atri2, respuesta_atri3, respuesta_atri4, respuesta_atri5, respuesta_atri6, respuesta_atri7, respuesta_atri8, respuesta_atri9, respuesta_atri10, respuesta_atri11, respuesta_atri12, respuesta_atri13, respuesta_atri14, respuesta_atri15, respuesta_atri16;
    Button back, back_info;
    Button cancelar, aceptar, btnfoto, btndelete;
    double mLatitude, mLongitude;
    Location currentLocation;
    LocationManager lm;
    ImageView miniatura_camara;
    ImageButton btCenterMap;
    private MyLocationNewOverlay mLocationOverlay;
    int idProyecto;

    HashMap<String, Marcador> mapaMarcadores = new HashMap<String, Marcador>();
    Marcador marcador_pulsado;

    String urlfoto;
    TextView txt_info;
    ImageView btninfo1, btninfo2, btninfo3, btninfo4, btninfo5, btninfo6, btninfo7, btninfo8, btninfo9, btninfo10, btninfo11, btninfo12, btninfo13, btninfo14, btninfo15, btninfo16;

    String info_atributo1, info_atributo2, info_atributo3, info_atributo4, info_atributo5, info_atributo6, info_atributo7, info_atributo8, info_atributo9, info_atributo10, info_atributo11, info_atributo12, info_atributo13, info_atributo14, info_atributo15, info_atributo16;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private ImageView mPhotoImageView;

    public static final int REQUEST_CODE_TAKE_PHOTO = 0 /*1*/;
    private String mCurrentPhotoPath;
    private Uri photoURI;
    String base64String = "";
    double longitude = 0.0;
    double latitude = 0.0;

    ByteArrayOutputStream arrayParaBlob = new ByteArrayOutputStream();
    byte[] foto_blob;


   // Marcador marcador_prueba = new Marcador(16,1, "Atributo1: ", "Índice pH: ", "Indice cloro: ", "Color: ", "Indice cloro: ", "Indice cloro: ", "Indice cloro: ", "Indice cloro: ", "Indice cloro: ", "Indice cloro: ", "Indice cloro: ", "Indice cloro: ", "Indice cloro: ", "Indice cloro: ", "Indice cloro: ", "Indice cloro: ");
    Marcador marcador_descargado;
    marcadorTipo marcador_tipo;
    public MapView map;
    Marker markpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        /*-----Hooks-----*/
        map = findViewById(R.id.map);
        drawerLayout = findViewById(R.id.drawer_layout4);
        navigationView = findViewById(R.id.nav_view4);
        toolbar = findViewById(R.id.toolbar);

        marcador_mostrado = findViewById(R.id.mostrarMarker);
        marco_foto = findViewById(R.id.foto_marcador);

        back = findViewById(R.id.cerrar_marcador);
        btndelete = findViewById(R.id.borrar_marcador);
        enunciado_atri1 = findViewById(R.id.enunciado_at1); enunciado_atri2 = findViewById(R.id.enunciado_at2); enunciado_atri3 = findViewById(R.id.enunciado_at3); enunciado_atri4 = findViewById(R.id.enunciado_at4);
        enunciado_atri5 = findViewById(R.id.enunciado_at5); enunciado_atri6 = findViewById(R.id.enunciado_at6); enunciado_atri7 = findViewById(R.id.enunciado_at7); enunciado_atri8 = findViewById(R.id.enunciado_at8);
        enunciado_atri9 = findViewById(R.id.enunciado_at9); enunciado_atri10 = findViewById(R.id.enunciado_at10); enunciado_atri11 = findViewById(R.id.enunciado_at11); enunciado_atri12 = findViewById(R.id.enunciado_at12);
        enunciado_atri13 = findViewById(R.id.enunciado_at13); enunciado_atri14 = findViewById(R.id.enunciado_at14); enunciado_atri15 = findViewById(R.id.enunciado_at15); enunciado_atri16 = findViewById(R.id.enunciado_at16);


        respuesta_atri1 = findViewById(R.id.respuesta_at1); respuesta_atri2 = findViewById(R.id.respuesta_at2); respuesta_atri3 = findViewById(R.id.respuesta_at3); respuesta_atri4 = findViewById(R.id.respuesta_at4);
        respuesta_atri5 = findViewById(R.id.respuesta_at5); respuesta_atri6 = findViewById(R.id.respuesta_at6); respuesta_atri7 = findViewById(R.id.respuesta_at7); respuesta_atri8 = findViewById(R.id.respuesta_at8);
        respuesta_atri9 = findViewById(R.id.respuesta_at9); respuesta_atri10 = findViewById(R.id.respuesta_at10); respuesta_atri11 = findViewById(R.id.respuesta_at11); respuesta_atri12 = findViewById(R.id.respuesta_at12);
        respuesta_atri13 = findViewById(R.id.respuesta_at13); respuesta_atri14 = findViewById(R.id.respuesta_at14); respuesta_atri15 = findViewById(R.id.respuesta_at15); respuesta_atri16 = findViewById(R.id.respuesta_at16);

        layout_atributo1 = findViewById(R.id.marco_atributo1); layout_atributo2 = findViewById(R.id.marco_atributo2); layout_atributo3 = findViewById(R.id.marco_atributo3); layout_atributo4 = findViewById(R.id.marco_atributo4);
        layout_atributo5 = findViewById(R.id.marco_atributo5); layout_atributo6 = findViewById(R.id.marco_atributo6); layout_atributo7 = findViewById(R.id.marco_atributo7); layout_atributo8 = findViewById(R.id.marco_atributo8);
        layout_atributo9 = findViewById(R.id.marco_atributo9); layout_atributo10 = findViewById(R.id.marco_atributo10); layout_atributo11 = findViewById(R.id.marco_atributo11); layout_atributo12 = findViewById(R.id.marco_atributo12);
        layout_atributo13 = findViewById(R.id.marco_atributo13); layout_atributo14 = findViewById(R.id.marco_atributo14); layout_atributo15 = findViewById(R.id.marco_atributo15); layout_atributo16 = findViewById(R.id.marco_atributo16);

        layout_marcador = findViewById(R.id.marco_marker); layout_foto = findViewById(R.id.photo); paraMapa = findViewById(R.id.mapa); layout_info = findViewById(R.id.displayinfo);

        atri1 = findViewById(R.id.atributo1); atri2 = findViewById(R.id.atributo2); atri3 = findViewById(R.id.atributo3); atri4 = findViewById(R.id.atributo4);
        atri5 = findViewById(R.id.atributo5); atri6 = findViewById(R.id.atributo6); atri7 = findViewById(R.id.atributo7); atri8 = findViewById(R.id.atributo8);
        atri9 = findViewById(R.id.atributo9); atri10 = findViewById(R.id.atributo10); atri11 = findViewById(R.id.atributo11); atri12 = findViewById(R.id.atributo12);
        atri13 = findViewById(R.id.atributo13); atri14 = findViewById(R.id.atributo14); atri15 = findViewById(R.id.atributo15); atri16 = findViewById(R.id.atributo16);

        edit_atri1 = findViewById(R.id.edit_atributo1); edit_atri2 = findViewById(R.id.edit_atributo2); edit_atri3 = findViewById(R.id.edit_atributo3); edit_atri4 = findViewById(R.id.edit_atributo4);
        edit_atri5 = findViewById(R.id.edit_atributo5); edit_atri6 = findViewById(R.id.edit_atributo6); edit_atri7 = findViewById(R.id.edit_atributo7); edit_atri8 = findViewById(R.id.edit_atributo8);
        edit_atri9 = findViewById(R.id.edit_atributo9); edit_atri10 = findViewById(R.id.edit_atributo10); edit_atri11 = findViewById(R.id.edit_atributo11); edit_atri12 = findViewById(R.id.edit_atributo12);
        edit_atri13 = findViewById(R.id.edit_atributo13); edit_atri14 = findViewById(R.id.edit_atributo14); edit_atri15 = findViewById(R.id.edit_atributo15); edit_atri16 = findViewById(R.id.edit_atributo16);

        edit_atri1.setText(""); edit_atri2.setText(""); edit_atri3.setText(""); edit_atri4.setText(""); edit_atri5.setText(""); edit_atri6.setText(""); edit_atri7.setText(""); edit_atri8.setText("");
        edit_atri9.setText(""); edit_atri10.setText(""); edit_atri11.setText(""); edit_atri12.setText(""); edit_atri13.setText(""); edit_atri14.setText(""); edit_atri15.setText(""); edit_atri16.setText("");

        titulo1 = findViewById(R.id.txt_atributo1); titulo3 = findViewById(R.id.txt_atributo3); titulo4 = findViewById(R.id.txt_atributo4); titulo5 = findViewById(R.id.txt_atributo5);
        titulo6 = findViewById(R.id.txt_atributo6); titulo7 = findViewById(R.id.txt_atributo7); titulo2 = findViewById(R.id.txt_atributo2); titulo8 = findViewById(R.id.txt_atributo8);
        titulo9 = findViewById(R.id.txt_atributo9); titulo10 = findViewById(R.id.txt_atributo10); titulo11 = findViewById(R.id.txt_atributo11); titulo12 = findViewById(R.id.txt_atributo12);
        titulo13 = findViewById(R.id.txt_atributo13); titulo14 = findViewById(R.id.txt_atributo14); titulo15 = findViewById(R.id.txt_atributo15); titulo16 = findViewById(R.id.txt_atributo16);

        cancelar = findViewById(R.id.cancel_but); aceptar = findViewById(R.id.acept_but); btnfoto = findViewById(R.id.btn_foto);
        miniatura_camara = findViewById(R.id.camera);
        txt_info = findViewById(R.id.text_info);

        btninfo1 = findViewById(R.id.info1); btninfo2 = findViewById(R.id.info2); btninfo3 = findViewById(R.id.info3); btninfo4 = findViewById(R.id.info4);
        btninfo5 = findViewById(R.id.info5); btninfo6 = findViewById(R.id.info6); btninfo7 = findViewById(R.id.info7); btninfo8 = findViewById(R.id.info8);
        btninfo9 = findViewById(R.id.info9); btninfo10 = findViewById(R.id.info10); btninfo11 = findViewById(R.id.info11); btninfo12 = findViewById(R.id.info12);
        btninfo13 = findViewById(R.id.info13); btninfo14 = findViewById(R.id.info14); btninfo15 = findViewById(R.id.info15); btninfo16 = findViewById(R.id.info16);

        back_info = findViewById(R.id.volver_info);


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

        idProyecto = getIntent().getIntExtra("idProyecto", 1);

        if ( Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED  ){
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        REQUEST_CODE_ASK_PERMISSIONS);

                        recreate();

                return ;
            }
        }

        final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        currentLocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        GpsMyLocationProvider provider = new GpsMyLocationProvider(this.getApplicationContext());
        mLocationOverlay = new MyLocationNewOverlay(provider, map);

        map.getOverlays().add(mLocationOverlay);

        mLocationOverlay.enableMyLocation();
        mLocationOverlay.setOptionsMenuEnabled(true);

        //lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //getLocation();



      //  isWriteStoragePermissionGranted();


      //  OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);

        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);


        // Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getContext()));


        map.setTileSource(TileSourceFactory.MAPNIK);


        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(9);
        map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);

        GeoPoint myPoint = new GeoPoint(currentLocation.getLatitude(), currentLocation.getLongitude());
        mapController.setCenter(myPoint);

        mapController.setZoom(15.00);
        markpress = new Marker(map);
        /*-----MÉTODOS PROPIOS DE ESTA ACTIVITY-----*/
        //addMarcadores(map, marcador_prueba);
        getCustomMarkerRequest();

        btCenterMap = (ImageButton) findViewById(R.id.ic_center_map);

        btCenterMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLocation!=null) {
                    GeoPoint myPosition = new GeoPoint(currentLocation.getLatitude(), currentLocation.getLongitude());
                    map.getController().animateTo(myPosition);
                    map.getController().setZoom(17.00);
                } else {
                    Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    GeoPoint myPosition = new GeoPoint(lastLocation.getLatitude(), lastLocation.getLongitude());
                    map.getController().animateTo(myPosition);
                    map.getController().setZoom(17.00);
                }
            }
        });




        Overlay overlay = new Overlay(getBaseContext()) {

            ItemizedIconOverlay<OverlayItem> items = null;


            @Override
           // public boolean onSingleTapConfirmed(MotionEvent e, MapView mapView) {
                public boolean onLongPress(MotionEvent e, MapView mapView) {
                Projection proj = mapView.getProjection();
                GeoPoint loc = (GeoPoint) proj.fromPixels((int)e.getX(), (int)e.getY());
                longitude = loc.getLongitude();
                latitude = loc.getLatitude();
                mLatitude = latitude;
                mLongitude = longitude;

                ArrayList<OverlayItem> markers = new ArrayList<>();
                OverlayItem item = new OverlayItem("", "", new GeoPoint(latitude, longitude));
                // item.setMarker(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_clear_red_24dp));
                markers.add(item);

                if (items == null) {
                    items = new ItemizedIconOverlay<>(getBaseContext(), markers, null);
                    map.getOverlays().add(items);
                    map.invalidate();
                    //metodo para añadir marcadores
                    addmarker(marcador_tipo);
                } else {

                    items = new ItemizedIconOverlay<>(getBaseContext(), markers, null);
                    map.getOverlays().add(items);
                    //metodo para añadir marcadores
                    addmarker(marcador_tipo);
                }
                return true;
            }

        };

        map.getOverlays().add(overlay);

        back_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.GONE);
            }
        });

        btninfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo1.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo1);
                }
            }
        });

        btninfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo2.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo2);
                }
            }
        });

        btninfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo3.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo3);
                }
            }
        });

        btninfo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo4.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo4);
                }
            }
        });

        btninfo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo5.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo5);
                }
            }
        });

        btninfo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo6.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo6);
                }
            }
        });

        btninfo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo7.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo7);
                }
            }
        });

        btninfo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo8.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo8);
                }
            }
        });

        btninfo9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo9.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo9);
                }
            }
        });

        btninfo10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo10.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo10);
                }
            }
        });

        btninfo11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo11.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo11);
                }
            }
        });

        btninfo12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo12.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo12);
                }
            }
        });

        btninfo13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo13.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo13);
                }
            }
        });

        btninfo14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo14.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo14);
                }
            }
        });

        btninfo15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo15.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo15);
                }
            }
        });

        btninfo16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_info.setVisibility(View.VISIBLE);
                if(info_atributo16.equals("")){
                    txt_info.setText("El administrador del proyecto no ha añadido una descripción para este atributo.");
                } else {
                    txt_info.setText(info_atributo16);
                }
            }
        });



   /*     markpress.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {

                marcador_pulsado = mapaMarcadores.get(marker.getId());

                System.out.print(marcador_pulsado.getAtributo1());
                System.out.print(String.valueOf(marcador_pulsado.getAtributo2()));

                return true;
            }
        }); */

        int duration = Toast.LENGTH_SHORT;
        Toast toast;
        CharSequence text;

        text = "Mantén pulsado sobre el mapa en la posición en la que quieras añadir un Marcador.";
        toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();

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
                SessionManager session = new SessionManager(getApplicationContext());
                session.setClear();
                Intent intent4 = new Intent(getApplicationContext(), Login.class);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //getLocation();
                } else {
                    // Permission Denied
                    Toast.makeText( this,"your message" , Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //Get location
    public  void getLocation(){
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        currentLocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (currentLocation == null)
        {
           // currentLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation=location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public void addMarcadores(MapView mapa, Marcador marc, marcadorTipo marcadortipo){

        String snippet;

        GeoPoint startPoint = new GeoPoint(marc.getLatitud(), marc.getLongitud());
        Marker startMarker = new Marker(mapa);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle("");

       // startMarker.setSnippet(marcador_prueba.getAtributo1() + "Resultado 1" + "<br>" + " y <br>tal vez<br> saltos de<br> línea"); // Confirmado que los saltos de línea se hacen con <br>

        snippet = generarSnippet(marc, marcadortipo);
        startMarker.setSnippet(snippet);
        mapa.getOverlays().add(startMarker);

        startMarker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {



                return true;
            }
        });

    }

    public String generarSnippet(Marcador marcador, marcadorTipo marcadortipo){

        String resultado = "";

        if(marcadortipo.getNum_atributos()==1){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1();
        } else if (marcadortipo.getNum_atributos()==2){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2();
        } else if (marcadortipo.getNum_atributos()==3){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2() + "<br>" + marcadortipo.getAtributo3()+": "+marcador.getAtributo3();
        } else if (marcadortipo.getNum_atributos()==4){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2() + "<br>" + marcadortipo.getAtributo3()+": "+marcador.getAtributo3() + "<br>" + marcadortipo.getAtributo4()+": "+marcador.getAtributo4();
        } else if (marcadortipo.getNum_atributos()==5){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2() + "<br>" + marcadortipo.getAtributo3()+": "+marcador.getAtributo3() + "<br>" + marcadortipo.getAtributo4()+": "+marcador.getAtributo4() + "<br>" + marcadortipo.getAtributo5()+": "+marcador.getAtributo5();
        } else if (marcadortipo.getNum_atributos()==6){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2() + "<br>" + marcadortipo.getAtributo3()+": "+marcador.getAtributo3() + "<br>" + marcadortipo.getAtributo4()+": "+marcador.getAtributo4() + "<br>" + marcadortipo.getAtributo5()+": "+marcador.getAtributo5() + "<br>" + marcadortipo.getAtributo6()+": "+marcador.getAtributo6();
        } else if (marcadortipo.getNum_atributos()==7){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2() + "<br>" + marcadortipo.getAtributo3()+": "+marcador.getAtributo3() + "<br>" + marcadortipo.getAtributo4()+": "+marcador.getAtributo4() + "<br>" + marcadortipo.getAtributo5()+": "+marcador.getAtributo5() + "<br>" + marcadortipo.getAtributo6()+": "+marcador.getAtributo6() + "<br>" + marcadortipo.getAtributo7()+": "+marcador.getAtributo7();
        } else if (marcadortipo.getNum_atributos()==8){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2() + "<br>" + marcadortipo.getAtributo3()+": "+marcador.getAtributo3() + "<br>" + marcadortipo.getAtributo4()+": "+marcador.getAtributo4() + "<br>" + marcadortipo.getAtributo5()+": "+marcador.getAtributo5() + "<br>" + marcadortipo.getAtributo6()+": "+marcador.getAtributo6() + "<br>" + marcadortipo.getAtributo7()+": "+marcador.getAtributo7() + "<br>" + marcadortipo.getAtributo8()+": "+marcador.getAtributo8();
        } else if (marcadortipo.getNum_atributos()==9){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2() + "<br>" + marcadortipo.getAtributo3()+": "+marcador.getAtributo3() + "<br>" + marcadortipo.getAtributo4()+": "+marcador.getAtributo4() + "<br>" + marcadortipo.getAtributo5()+": "+marcador.getAtributo5() + "<br>" + marcadortipo.getAtributo6()+": "+marcador.getAtributo6() + "<br>" + marcadortipo.getAtributo7()+": "+marcador.getAtributo7() + "<br>" + marcadortipo.getAtributo8()+": "+marcador.getAtributo8() + "<br>" + marcadortipo.getAtributo9()+": "+marcador.getAtributo9();
        } else if (marcadortipo.getNum_atributos()==10){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2() + "<br>" + marcadortipo.getAtributo3()+": "+marcador.getAtributo3() + "<br>" + marcadortipo.getAtributo4()+": "+marcador.getAtributo4() + "<br>" + marcadortipo.getAtributo5()+": "+marcador.getAtributo5() + "<br>" + marcadortipo.getAtributo6()+": "+marcador.getAtributo6() + "<br>" + marcadortipo.getAtributo7()+": "+marcador.getAtributo7() + "<br>" + marcadortipo.getAtributo8()+": "+marcador.getAtributo8() + "<br>" + marcadortipo.getAtributo9()+": "+marcador.getAtributo9() + "<br>" + marcadortipo.getAtributo10()+": "+marcador.getAtributo10();
        } else if (marcadortipo.getNum_atributos()==11){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2() + "<br>" + marcadortipo.getAtributo3()+": "+marcador.getAtributo3() + "<br>" + marcadortipo.getAtributo4()+": "+marcador.getAtributo4() + "<br>" + marcadortipo.getAtributo5()+": "+marcador.getAtributo5() + "<br>" + marcadortipo.getAtributo6()+": "+marcador.getAtributo6() + "<br>" + marcadortipo.getAtributo7()+": "+marcador.getAtributo7() + "<br>" + marcadortipo.getAtributo8()+": "+marcador.getAtributo8() + "<br>" + marcadortipo.getAtributo9()+": "+marcador.getAtributo9() + "<br>" + marcadortipo.getAtributo10()+": "+marcador.getAtributo10() + "<br>" + marcadortipo.getAtributo11()+": "+marcador.getAtributo11();
        } else if (marcadortipo.getNum_atributos()==12){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2() + "<br>" + marcadortipo.getAtributo3()+": "+marcador.getAtributo3() + "<br>" + marcadortipo.getAtributo4()+": "+marcador.getAtributo4() + "<br>" + marcadortipo.getAtributo5()+": "+marcador.getAtributo5() + "<br>" + marcadortipo.getAtributo6()+": "+marcador.getAtributo6() + "<br>" + marcadortipo.getAtributo7()+": "+marcador.getAtributo7() + "<br>" + marcadortipo.getAtributo8()+": "+marcador.getAtributo8() + "<br>" + marcadortipo.getAtributo9()+": "+marcador.getAtributo9() + "<br>" + marcadortipo.getAtributo10()+": "+marcador.getAtributo10() + "<br>" + marcadortipo.getAtributo11()+": "+marcador.getAtributo11() + "<br>" + marcadortipo.getAtributo12()+": "+marcador.getAtributo12();
        } else if (marcadortipo.getNum_atributos()==13){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2() + "<br>" + marcadortipo.getAtributo3()+": "+marcador.getAtributo3() + "<br>" + marcadortipo.getAtributo4()+": "+marcador.getAtributo4() + "<br>" + marcadortipo.getAtributo5()+": "+marcador.getAtributo5() + "<br>" + marcadortipo.getAtributo6()+": "+marcador.getAtributo6() + "<br>" + marcadortipo.getAtributo7()+": "+marcador.getAtributo7() + "<br>" + marcadortipo.getAtributo8()+": "+marcador.getAtributo8() + "<br>" + marcadortipo.getAtributo9()+": "+marcador.getAtributo9() + "<br>" + marcadortipo.getAtributo10()+": "+marcador.getAtributo10() + "<br>" + marcadortipo.getAtributo11()+": "+marcador.getAtributo11() + "<br>" + marcadortipo.getAtributo12()+": "+marcador.getAtributo12() + "<br>" + marcadortipo.getAtributo13()+": "+marcador.getAtributo13();
        } else if (marcadortipo.getNum_atributos()==14){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2() + "<br>" + marcadortipo.getAtributo3()+": "+marcador.getAtributo3() + "<br>" + marcadortipo.getAtributo4()+": "+marcador.getAtributo4() + "<br>" + marcadortipo.getAtributo5()+": "+marcador.getAtributo5() + "<br>" + marcadortipo.getAtributo6()+": "+marcador.getAtributo6() + "<br>" + marcadortipo.getAtributo7()+": "+marcador.getAtributo7() + "<br>" + marcadortipo.getAtributo8()+": "+marcador.getAtributo8() + "<br>" + marcadortipo.getAtributo9()+": "+marcador.getAtributo9() + "<br>" + marcadortipo.getAtributo10()+": "+marcador.getAtributo10() + "<br>" + marcadortipo.getAtributo11()+": "+marcador.getAtributo11() + "<br>" + marcadortipo.getAtributo12()+": "+marcador.getAtributo12() + "<br>" + marcadortipo.getAtributo13()+": "+marcador.getAtributo13() + "<br>" + marcadortipo.getAtributo14()+": "+marcador.getAtributo14();
        } else if (marcadortipo.getNum_atributos()==15){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2() + "<br>" + marcadortipo.getAtributo3()+": "+marcador.getAtributo3() + "<br>" + marcadortipo.getAtributo4()+": "+marcador.getAtributo4() + "<br>" + marcadortipo.getAtributo5()+": "+marcador.getAtributo5() + "<br>" + marcadortipo.getAtributo6()+": "+marcador.getAtributo6() + "<br>" + marcadortipo.getAtributo7()+": "+marcador.getAtributo7() + "<br>" + marcadortipo.getAtributo8()+": "+marcador.getAtributo8() + "<br>" + marcadortipo.getAtributo9()+": "+marcador.getAtributo9() + "<br>" + marcadortipo.getAtributo10()+": "+marcador.getAtributo10() + "<br>" + marcadortipo.getAtributo11()+": "+marcador.getAtributo11() + "<br>" + marcadortipo.getAtributo12()+": "+marcador.getAtributo12() + "<br>" + marcadortipo.getAtributo13()+": "+marcador.getAtributo13() + "<br>" + marcadortipo.getAtributo14()+": "+marcador.getAtributo14() + "<br>" + marcadortipo.getAtributo15()+": "+marcador.getAtributo15();
        } else if (marcadortipo.getNum_atributos()==16){
            resultado = marcadortipo.getAtributo1()+": "+marcador.getAtributo1() + "<br>" + marcadortipo.getAtributo2()+": "+marcador.getAtributo2() + "<br>" + marcadortipo.getAtributo3()+": "+marcador.getAtributo3() + "<br>" + marcadortipo.getAtributo4()+": "+marcador.getAtributo4() + "<br>" + marcadortipo.getAtributo5()+": "+marcador.getAtributo5() + "<br>" + marcadortipo.getAtributo6()+": "+marcador.getAtributo6() + "<br>" + marcadortipo.getAtributo7()+": "+marcador.getAtributo7() + "<br>" + marcadortipo.getAtributo8()+": "+marcador.getAtributo8() + "<br>" + marcadortipo.getAtributo9()+": "+marcador.getAtributo9() + "<br>" + marcadortipo.getAtributo10()+": "+marcador.getAtributo10() + "<br>" + marcadortipo.getAtributo11()+": "+marcador.getAtributo11() + "<br>" + marcadortipo.getAtributo12()+": "+marcador.getAtributo12() + "<br>" + marcadortipo.getAtributo13()+": "+marcador.getAtributo13() + "<br>" + marcadortipo.getAtributo14()+": "+marcador.getAtributo14() + "<br>" + marcadortipo.getAtributo15()+": "+marcador.getAtributo15() + "<br>" + marcadortipo.getAtributo16()+": "+marcador.getAtributo16();
        }

        return resultado;
    }

    public void addmarker(marcadorTipo marcador){

        layout_marcador.setVisibility(View.VISIBLE);
        paraMapa.setVisibility(View.GONE);
        if(marcador_tipo.getFoto() == 1){
            layout_foto.setVisibility(View.VISIBLE);
            btnfoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    addPhoto();
                }
            });
        }

        if(marcador.getNum_atributos()==1){
            atri2.setVisibility(View.GONE); atri3.setVisibility(View.GONE); atri4.setVisibility(View.GONE); atri5.setVisibility(View.GONE);
            atri6.setVisibility(View.GONE); atri7.setVisibility(View.GONE); atri8.setVisibility(View.GONE); atri9.setVisibility(View.GONE);
            atri10.setVisibility(View.GONE); atri11.setVisibility(View.GONE); atri12.setVisibility(View.GONE); atri13.setVisibility(View.GONE);
            atri14.setVisibility(View.GONE); atri15.setVisibility(View.GONE); atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1());
        } else if (marcador.getNum_atributos()==2){
            atri3.setVisibility(View.GONE); atri4.setVisibility(View.GONE); atri5.setVisibility(View.GONE);
            atri6.setVisibility(View.GONE); atri7.setVisibility(View.GONE); atri8.setVisibility(View.GONE); atri9.setVisibility(View.GONE);
            atri10.setVisibility(View.GONE); atri11.setVisibility(View.GONE); atri12.setVisibility(View.GONE); atri13.setVisibility(View.GONE);
            atri14.setVisibility(View.GONE); atri15.setVisibility(View.GONE); atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2());
        } else if (marcador.getNum_atributos()==3){
            atri4.setVisibility(View.GONE); atri5.setVisibility(View.GONE);
            atri6.setVisibility(View.GONE); atri7.setVisibility(View.GONE); atri8.setVisibility(View.GONE); atri9.setVisibility(View.GONE);
            atri10.setVisibility(View.GONE); atri11.setVisibility(View.GONE); atri12.setVisibility(View.GONE); atri13.setVisibility(View.GONE);
            atri14.setVisibility(View.GONE); atri15.setVisibility(View.GONE); atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2()); titulo3.setHint(marcador.getAtributo3());
        } else if (marcador.getNum_atributos()==4){
            atri5.setVisibility(View.GONE);
            atri6.setVisibility(View.GONE); atri7.setVisibility(View.GONE); atri8.setVisibility(View.GONE); atri9.setVisibility(View.GONE);
            atri10.setVisibility(View.GONE); atri11.setVisibility(View.GONE); atri12.setVisibility(View.GONE); atri13.setVisibility(View.GONE);
            atri14.setVisibility(View.GONE); atri15.setVisibility(View.GONE); atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2()); titulo3.setHint(marcador.getAtributo3()); titulo4.setHint(marcador.getAtributo4());
        } else if (marcador.getNum_atributos()==5){
            atri6.setVisibility(View.GONE); atri7.setVisibility(View.GONE); atri8.setVisibility(View.GONE); atri9.setVisibility(View.GONE);
            atri10.setVisibility(View.GONE); atri11.setVisibility(View.GONE); atri12.setVisibility(View.GONE); atri13.setVisibility(View.GONE);
            atri14.setVisibility(View.GONE); atri15.setVisibility(View.GONE); atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2()); titulo3.setHint(marcador.getAtributo3()); titulo4.setHint(marcador.getAtributo4());
            titulo5.setHint(marcador.getAtributo5());
        } else if (marcador.getNum_atributos()==6){
            atri7.setVisibility(View.GONE); atri8.setVisibility(View.GONE); atri9.setVisibility(View.GONE);
            atri10.setVisibility(View.GONE); atri11.setVisibility(View.GONE); atri12.setVisibility(View.GONE); atri13.setVisibility(View.GONE);
            atri14.setVisibility(View.GONE); atri15.setVisibility(View.GONE); atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2()); titulo3.setHint(marcador.getAtributo3()); titulo4.setHint(marcador.getAtributo4());
            titulo5.setHint(marcador.getAtributo5()); titulo6.setHint(marcador.getAtributo6());
        } else if (marcador.getNum_atributos()==7){
            atri8.setVisibility(View.GONE); atri9.setVisibility(View.GONE);
            atri10.setVisibility(View.GONE); atri11.setVisibility(View.GONE); atri12.setVisibility(View.GONE); atri13.setVisibility(View.GONE);
            atri14.setVisibility(View.GONE); atri15.setVisibility(View.GONE); atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2()); titulo3.setHint(marcador.getAtributo3()); titulo4.setHint(marcador.getAtributo4());
            titulo5.setHint(marcador.getAtributo5()); titulo6.setHint(marcador.getAtributo6()); titulo7.setHint(marcador.getAtributo7());
        } else if (marcador.getNum_atributos()==8){
            atri9.setVisibility(View.GONE);
            atri10.setVisibility(View.GONE); atri11.setVisibility(View.GONE); atri12.setVisibility(View.GONE); atri13.setVisibility(View.GONE);
            atri14.setVisibility(View.GONE); atri15.setVisibility(View.GONE); atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2()); titulo3.setHint(marcador.getAtributo3()); titulo4.setHint(marcador.getAtributo4());
            titulo5.setHint(marcador.getAtributo5()); titulo6.setHint(marcador.getAtributo6()); titulo7.setHint(marcador.getAtributo7()); titulo8.setHint(marcador.getAtributo8());
        } else if (marcador.getNum_atributos()==9){

            atri10.setVisibility(View.GONE); atri11.setVisibility(View.GONE); atri12.setVisibility(View.GONE); atri13.setVisibility(View.GONE);
            atri14.setVisibility(View.GONE); atri15.setVisibility(View.GONE); atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2()); titulo3.setHint(marcador.getAtributo3()); titulo4.setHint(marcador.getAtributo4());
            titulo5.setHint(marcador.getAtributo5()); titulo6.setHint(marcador.getAtributo6()); titulo7.setHint(marcador.getAtributo7()); titulo8.setHint(marcador.getAtributo8());
            titulo9.setHint(marcador.getAtributo9());
        } else if (marcador.getNum_atributos()==10){
            atri11.setVisibility(View.GONE); atri12.setVisibility(View.GONE); atri13.setVisibility(View.GONE);
            atri14.setVisibility(View.GONE); atri15.setVisibility(View.GONE); atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2()); titulo3.setHint(marcador.getAtributo3()); titulo4.setHint(marcador.getAtributo4());
            titulo5.setHint(marcador.getAtributo5()); titulo6.setHint(marcador.getAtributo6()); titulo7.setHint(marcador.getAtributo7()); titulo8.setHint(marcador.getAtributo8());
            titulo9.setHint(marcador.getAtributo9()); titulo10.setHint(marcador.getAtributo10());
        } else if (marcador.getNum_atributos()==11){
            atri12.setVisibility(View.GONE); atri13.setVisibility(View.GONE);
            atri14.setVisibility(View.GONE); atri15.setVisibility(View.GONE); atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2()); titulo3.setHint(marcador.getAtributo3()); titulo4.setHint(marcador.getAtributo4());
            titulo5.setHint(marcador.getAtributo5()); titulo6.setHint(marcador.getAtributo6()); titulo7.setHint(marcador.getAtributo7()); titulo8.setHint(marcador.getAtributo8());
            titulo9.setHint(marcador.getAtributo9()); titulo10.setHint(marcador.getAtributo10()); titulo11.setHint(marcador.getAtributo11());
        } else if (marcador.getNum_atributos()==12){
            atri13.setVisibility(View.GONE);
            atri14.setVisibility(View.GONE); atri15.setVisibility(View.GONE); atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2()); titulo3.setHint(marcador.getAtributo3()); titulo4.setHint(marcador.getAtributo4());
            titulo5.setHint(marcador.getAtributo5()); titulo6.setHint(marcador.getAtributo6()); titulo7.setHint(marcador.getAtributo7()); titulo8.setHint(marcador.getAtributo8());
            titulo9.setHint(marcador.getAtributo9()); titulo10.setHint(marcador.getAtributo10()); titulo11.setHint(marcador.getAtributo11()); titulo12.setHint(marcador.getAtributo12());
        } else if (marcador.getNum_atributos()==13){
            atri14.setVisibility(View.GONE); atri15.setVisibility(View.GONE); atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2()); titulo3.setHint(marcador.getAtributo3()); titulo4.setHint(marcador.getAtributo4());
            titulo5.setHint(marcador.getAtributo5()); titulo6.setHint(marcador.getAtributo6()); titulo7.setHint(marcador.getAtributo7()); titulo8.setHint(marcador.getAtributo8());
            titulo9.setHint(marcador.getAtributo9()); titulo10.setHint(marcador.getAtributo10()); titulo11.setHint(marcador.getAtributo11()); titulo12.setHint(marcador.getAtributo12());
            titulo13.setHint(marcador.getAtributo13());
        } else if (marcador.getNum_atributos()==14){
            atri15.setVisibility(View.GONE); atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2()); titulo3.setHint(marcador.getAtributo3()); titulo4.setHint(marcador.getAtributo4());
            titulo5.setHint(marcador.getAtributo5()); titulo6.setHint(marcador.getAtributo6()); titulo7.setHint(marcador.getAtributo7()); titulo8.setHint(marcador.getAtributo8());
            titulo9.setHint(marcador.getAtributo9()); titulo10.setHint(marcador.getAtributo10()); titulo11.setHint(marcador.getAtributo11()); titulo12.setHint(marcador.getAtributo12());
            titulo13.setHint(marcador.getAtributo13()); titulo14.setHint(marcador.getAtributo14());
        } else if (marcador.getNum_atributos()==15){
            atri16.setVisibility(View.GONE);
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2()); titulo3.setHint(marcador.getAtributo3()); titulo4.setHint(marcador.getAtributo4());
            titulo5.setHint(marcador.getAtributo5()); titulo6.setHint(marcador.getAtributo6()); titulo7.setHint(marcador.getAtributo7()); titulo8.setHint(marcador.getAtributo8());
            titulo9.setHint(marcador.getAtributo9()); titulo10.setHint(marcador.getAtributo10()); titulo11.setHint(marcador.getAtributo11()); titulo12.setHint(marcador.getAtributo12());
            titulo13.setHint(marcador.getAtributo13()); titulo14.setHint(marcador.getAtributo14()); titulo15.setHint(marcador.getAtributo15());
        } else if (marcador.getNum_atributos()==16){
            titulo1.setHint(marcador.getAtributo1()); titulo2.setHint(marcador.getAtributo2()); titulo3.setHint(marcador.getAtributo3()); titulo4.setHint(marcador.getAtributo4());
            titulo5.setHint(marcador.getAtributo5()); titulo6.setHint(marcador.getAtributo6()); titulo7.setHint(marcador.getAtributo7()); titulo8.setHint(marcador.getAtributo8());
            titulo9.setHint(marcador.getAtributo9()); titulo10.setHint(marcador.getAtributo10()); titulo11.setHint(marcador.getAtributo11()); titulo12.setHint(marcador.getAtributo12());
            titulo13.setHint(marcador.getAtributo13()); titulo14.setHint(marcador.getAtributo14()); titulo15.setHint(marcador.getAtributo15()); titulo16.setHint(marcador.getAtributo16());
        }

        if(marcador_tipo.getIsText1() == 0) {
            edit_atri1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText2() == 0) {
            edit_atri2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText3() == 0) {
            edit_atri3.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText4() == 0) {
            edit_atri4.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText5() == 0) {
            edit_atri5.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText6() == 0) {
            edit_atri6.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText7() == 0) {
            edit_atri7.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText8() == 0) {
            edit_atri8.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText9() == 0) {
            edit_atri9.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText10() == 0) {
            edit_atri10.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText11() == 0) {
            edit_atri11.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText12() == 0) {
            edit_atri12.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText13() == 0) {
            edit_atri13.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText14() == 0) {
            edit_atri14.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText15() == 0) {
            edit_atri15.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(marcador_tipo.getIsText16() == 0) {
            edit_atri16.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_marcador.setVisibility(View.GONE);
                paraMapa.setVisibility(View.VISIBLE);

            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirMarcador();
            }
        });

    }

    public void addPhoto(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        225);
            }


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        226);
            }
        } else {
            dispatchTakePictureIntent();
        }
    }

        private void dispatchTakePictureIntent(){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {

                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "MyPicture");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
                    photoURI = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                    //Uri photoURI = FileProvider.getUriForFile(AddActivity.this, "com.example.android.fileprovider", photoFile);

                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
                }
            }
        }

        private File createImageFile() throws IOException{
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = image.getAbsolutePath();
            return image;
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {

                Bitmap bitmap;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoURI);
                    Bitmap scaledBitmap = getScaledBitmap(bitmap, 800, 600);
                    base64String = getBase64String(bitmap); // foto en base64

                    scaledBitmap.compress(Bitmap.CompressFormat.PNG, 80, arrayParaBlob);

                    foto_blob = arrayParaBlob.toByteArray();
                    String debugfoto = String.valueOf(foto_blob);
                    
                    miniatura_camara.setImageBitmap(bitmap);
                    miniatura_camara.setRotation(90);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            /*if (requestCode == REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras(); // Aquí es null
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mPhotoImageView.setImageBitmap(imageBitmap);
            }*/

            }
        }

    private Bitmap getScaledBitmap(Bitmap b, int reqWidth, int reqHeight)
    {
        int bWidth = b.getWidth();
        int bHeight = b.getHeight();

        int nWidth = bWidth;
        int nHeight = bHeight;

        if(nWidth > reqWidth)
        {
            int ratio = bWidth / reqWidth;
            if(ratio > 0)
            {
                nWidth = reqWidth;
                nHeight = bHeight / ratio;
            }
        }

        if(nHeight > reqHeight)
        {
            int ratio = bHeight / reqHeight;
            if(ratio > 0)
            {
                nHeight = reqHeight;
                nWidth = bWidth / ratio;
            }
        }

        return Bitmap.createScaledBitmap(b, nWidth, nHeight, true);
    }

    private String getBase64String(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);

        byte[] imageBytes = baos.toByteArray();

        String base64String = Base64.encodeToString(imageBytes, Base64.NO_WRAP);

        return base64String;
    }

    /*
    *  To decode the base64 string back to bitmap image:
    *  byte[] decodedByteArray = Base64.decode(base64String, Base64.NO_WRAP);
    *  Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedString.length);
    * */

    public void getCustomMarkerRequest () {
        final LinearLayout cargar = findViewById(R.id.cargando);
        String idUser, toktok;
        int item;
        SessionManager session = new SessionManager(Mapa.this);
        cargar.setVisibility(View.VISIBLE);

        // Input data ok, so go with the request

        // Url for the webservice
        idUser = String.valueOf(session.getIdUser());
        toktok = session.getToken();

        String url = getString(R.string.base_url) + "/proyecto.php?idUser=" + idUser +"&token=" + toktok + "&idProyecto=" + idProyecto;

        RequestQueue queue = Volley.newRequestQueue(Mapa.this);
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
                        int j=0;
                        JSONArray jsonArray = responseJSON.getJSONArray("data");

                        for (i=0; i < jsonArray.length(); i++){

                            // JSONArray jsonArray1 = jsonArray.getJSONArray(i); //Diferentes proyectos


                            int numeroatributos = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("numAtributos")));
                            int tieneFoto = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("foto")));
                            String atribu1 = String.valueOf(jsonArray.getJSONObject(i).get("atributo1"));
                            String atribu2 = String.valueOf(jsonArray.getJSONObject(i).get("atributo2"));
                            String atribu3 = String.valueOf(jsonArray.getJSONObject(i).get("atributo3"));
                            String atribu4 = String.valueOf(jsonArray.getJSONObject(i).get("atributo4"));
                            String atribu5 = String.valueOf(jsonArray.getJSONObject(i).get("atributo5"));
                            String atribu6 = String.valueOf(jsonArray.getJSONObject(i).get("atributo6"));
                            String atribu7 = String.valueOf(jsonArray.getJSONObject(i).get("atributo7"));
                            String atribu8 = String.valueOf(jsonArray.getJSONObject(i).get("atributo8"));
                            String atribu9 = String.valueOf(jsonArray.getJSONObject(i).get("atributo9"));
                            String atribu10 = String.valueOf(jsonArray.getJSONObject(i).get("atributo10"));
                            String atribu11 = String.valueOf(jsonArray.getJSONObject(i).get("atributo11"));
                            String atribu12 = String.valueOf(jsonArray.getJSONObject(i).get("atributo12"));
                            String atribu13 = String.valueOf(jsonArray.getJSONObject(i).get("atributo13"));
                            String atribu14 = String.valueOf(jsonArray.getJSONObject(i).get("atributo14"));
                            String atribu15 = String.valueOf(jsonArray.getJSONObject(i).get("atributo15"));
                            String atribu16 = String.valueOf(jsonArray.getJSONObject(i).get("atributo16"));
                            int esTexto1 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText1")));
                            int esTexto2 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText2")));
                            int esTexto3 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText3")));
                            int esTexto4 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText4")));
                            int esTexto5 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText5")));
                            int esTexto6 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText6")));
                            int esTexto7 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText7")));
                            int esTexto8 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText8")));
                            int esTexto9 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText9")));
                            int esTexto10 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText10")));
                            int esTexto11 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText11")));
                            int esTexto12 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText12")));
                            int esTexto13 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText13")));
                            int esTexto14 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText14")));
                            int esTexto15 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText15")));
                            int esTexto16 = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("isText16")));
                            info_atributo1 = String.valueOf(jsonArray.getJSONObject(i).get("desc1"));
                            info_atributo2 = String.valueOf(jsonArray.getJSONObject(i).get("desc2"));
                            info_atributo3 = String.valueOf(jsonArray.getJSONObject(i).get("desc3"));
                            info_atributo4 = String.valueOf(jsonArray.getJSONObject(i).get("desc4"));
                            info_atributo5 = String.valueOf(jsonArray.getJSONObject(i).get("desc5"));
                            info_atributo6 = String.valueOf(jsonArray.getJSONObject(i).get("desc6"));
                            info_atributo7 = String.valueOf(jsonArray.getJSONObject(i).get("desc7"));
                            info_atributo8 = String.valueOf(jsonArray.getJSONObject(i).get("desc8"));
                            info_atributo9 = String.valueOf(jsonArray.getJSONObject(i).get("desc9"));
                            info_atributo10 = String.valueOf(jsonArray.getJSONObject(i).get("desc10"));
                            info_atributo11 = String.valueOf(jsonArray.getJSONObject(i).get("desc11"));
                            info_atributo12 = String.valueOf(jsonArray.getJSONObject(i).get("desc12"));
                            info_atributo13 = String.valueOf(jsonArray.getJSONObject(i).get("desc13"));
                            info_atributo14 = String.valueOf(jsonArray.getJSONObject(i).get("desc14"));
                            info_atributo15 = String.valueOf(jsonArray.getJSONObject(i).get("desc15"));
                            info_atributo16 = String.valueOf(jsonArray.getJSONObject(i).get("desc16"));
                            marcador_tipo = new marcadorTipo(numeroatributos, tieneFoto, atribu1, atribu2, atribu3, atribu4, atribu5, atribu6, atribu7, atribu8, atribu9, atribu10, atribu11, atribu12, atribu13, atribu14, atribu15, atribu16, esTexto1, esTexto2, esTexto3, esTexto4, esTexto5, esTexto6, esTexto7, esTexto8, esTexto9, esTexto10, esTexto11, esTexto12, esTexto13, esTexto14, esTexto15, esTexto16);


                        }

                        cargar.setVisibility(View.GONE);
                        getMarkersRequest();

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

    public void getMarkersRequest () {
        final LinearLayout cargar = findViewById(R.id.cargando);
        String idUser, toktok;
        int item;
        SessionManager session = new SessionManager(Mapa.this);
        cargar.setVisibility(View.VISIBLE);


        // Input data ok, so go with the request

        // Url for the webservice
        idUser = String.valueOf(session.getIdUser());
        toktok = session.getToken();

        String url = getString(R.string.base_url) + "/marcadores.php?idUser=" + idUser +"&token=" + toktok  + "&idProyecto=" + idProyecto;

        RequestQueue queue = Volley.newRequestQueue(Mapa.this);
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
                            int idUser = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("idUser")));
                            double latitud = Double.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("latitud")));
                            double longitud = Double.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("longitud")));
                            int hasFoto = Integer.valueOf(String.valueOf(jsonArray.getJSONObject(i).get("hasphoto")));
                            String atributo1 = String.valueOf(jsonArray.getJSONObject(i).get("atributo1"));
                            String atributo2 = String.valueOf(jsonArray.getJSONObject(i).get("atributo2"));
                            String atributo3 = String.valueOf(jsonArray.getJSONObject(i).get("atributo3"));
                            String atributo4 = String.valueOf(jsonArray.getJSONObject(i).get("atributo4"));
                            String atributo5 = String.valueOf(jsonArray.getJSONObject(i).get("atributo5"));
                            String atributo6 = String.valueOf(jsonArray.getJSONObject(i).get("atributo6"));
                            String atributo7 = String.valueOf(jsonArray.getJSONObject(i).get("atributo7"));
                            String atributo8 = String.valueOf(jsonArray.getJSONObject(i).get("atributo8"));
                            String atributo9 = String.valueOf(jsonArray.getJSONObject(i).get("atributo9"));
                            String atributo10 = String.valueOf(jsonArray.getJSONObject(i).get("atributo10"));
                            String atributo11 = String.valueOf(jsonArray.getJSONObject(i).get("atributo11"));
                            String atributo12 = String.valueOf(jsonArray.getJSONObject(i).get("atributo12"));
                            String atributo13 = String.valueOf(jsonArray.getJSONObject(i).get("atributo13"));
                            String atributo14 = String.valueOf(jsonArray.getJSONObject(i).get("atributo14"));
                            String atributo15 = String.valueOf(jsonArray.getJSONObject(i).get("atributo15"));
                            String atributo16 = String.valueOf(jsonArray.getJSONObject(i).get("atributo16"));

                            marcador_descargado = new Marcador(id, idUser, hasFoto, latitud, longitud, atributo1, atributo2, atributo3, atributo4, atributo5, atributo6, atributo7, atributo8, atributo9, atributo10, atributo11, atributo12, atributo13, atributo14, atributo15, atributo16);

                            addMarcadores2(map, marcador_descargado, marcador_tipo, mapaMarcadores, i);



                        }

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

    public void subirMarcador () {
        final LinearLayout cargar = findViewById(R.id.cargando);


        cargar.setVisibility(View.VISIBLE);


            // Input data ok, so go with the request

            // Url for the webservice
            String url = getString(R.string.base_url) + "/crearMarcador.php";

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        System.out.println(response.toString());

                        JSONObject responseJSON = new JSONObject(response);

                        if ((int) responseJSON.get("result") == 1) {

                            int duration = Toast.LENGTH_SHORT;
                            Toast toast;
                            CharSequence text;
                            edit_atri1.setText(""); edit_atri2.setText("");
                            edit_atri3.setText(""); edit_atri4.setText("");
                            edit_atri5.setText(""); edit_atri6.setText("");
                            edit_atri7.setText(""); edit_atri8.setText("");
                            edit_atri9.setText(""); edit_atri10.setText("");
                            edit_atri11.setText(""); edit_atri12.setText("");
                            edit_atri13.setText(""); edit_atri14.setText("");
                            edit_atri15.setText(""); edit_atri16.setText("");
                            miniatura_camara.setImageDrawable(getResources().getDrawable(R.drawable.ic_photo_camera_black_24dp));

                            text = "Marcador subido correctamente";
                            toast = Toast.makeText(getApplicationContext(), text, duration);
                            toast.show();
                            recreate();


                        } else {
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast;
                            CharSequence text;

                            text = "Error while login: " + responseJSON.get("message") + ".";
                            toast = Toast.makeText(getApplicationContext(), text, duration);
                            toast.show();

                            // Clean the text fields for new entries

                            cargar.setVisibility(View.GONE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        cargar.setVisibility(View.GONE);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast;
                    CharSequence text;
                    text = "Error while login: " + error.getLocalizedMessage() + ".";
                    toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                    cargar.setVisibility(View.GONE);
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> login_params = new HashMap<String, String>();
                    SessionManager session = new SessionManager(getApplicationContext());
                    login_params.put("idUser", String.valueOf(session.getIdUser()));
                    login_params.put("token", session.getToken());
                    login_params.put("foto", String.valueOf(base64String));
                    login_params.put("idProyecto", String.valueOf(idProyecto));
                    login_params.put("latitud", String.valueOf(latitude));
                    login_params.put("longitud", String.valueOf(longitude));
                    login_params.put("atributo1", edit_atri1.getText().toString());
                    login_params.put("atributo2", edit_atri2.getText().toString());
                    login_params.put("atributo3", edit_atri3.getText().toString());
                    login_params.put("atributo4", edit_atri4.getText().toString());
                    login_params.put("atributo5", edit_atri5.getText().toString());
                    login_params.put("atributo6", edit_atri6.getText().toString());
                    login_params.put("atributo7", edit_atri7.getText().toString());
                    login_params.put("atributo8", edit_atri8.getText().toString());
                    login_params.put("atributo9", edit_atri9.getText().toString());
                    login_params.put("atributo10", edit_atri10.getText().toString());
                    login_params.put("atributo11", edit_atri11.getText().toString());
                    login_params.put("atributo12", edit_atri12.getText().toString());
                    login_params.put("atributo13", edit_atri13.getText().toString());
                    login_params.put("atributo14", edit_atri14.getText().toString());
                    login_params.put("atributo15", edit_atri15.getText().toString());
                    login_params.put("atributo16", edit_atri16.getText().toString());

                    return login_params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            queue.add(sr);


    }

    public void addMarcadores2(MapView mapa, Marcador marc, final marcadorTipo marcadortipo, final HashMap<String,Marcador> myMap, int contador){

        String snippet;
        String id = String.valueOf(contador);



        GeoPoint startPoint = new GeoPoint(marc.getLatitud(), marc.getLongitud());
        Marker startMarker = new Marker(mapa);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle("");

        // startMarker.setSnippet(marcador_prueba.getAtributo1() + "Resultado 1" + "<br>" + " y <br>tal vez<br> saltos de<br> línea"); // Confirmado que los saltos de línea se hacen con <br>

        snippet = generarSnippet(marc, marcadortipo);
        startMarker.setSnippet(snippet);
        startMarker.setId(id);
        mapa.getOverlays().add(startMarker);
        myMap.put(startMarker.getId(), marc);

        startMarker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                marcador_pulsado = myMap.get(marker.getId());
                System.out.println("Prueba de log");
                System.out.println(marcador_pulsado.getAtributo1());
                System.out.println(String.valueOf(marcador_pulsado.getAtributo2()));
                mostrarMarcador(marcador_pulsado, marcadortipo);

                return true;
            }
        });

    }

    public void mostrarMarcador(final Marcador marcador, marcadorTipo marcadorTipo){

            marcador_mostrado.setVisibility(View.VISIBLE);

        SessionManager session = new SessionManager(Mapa.this);



        if(marcador.getIdUser() == session.getIdUser()) {

            btndelete.setVisibility(View.VISIBLE);

            btndelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteMarkerRequest(marcador);
                }
            });

        } else {
            btndelete.setVisibility(View.GONE);
        }


            if (marcador.getHasPhoto() == 0){

                marco_foto.setVisibility(View.GONE);
            } else {
                mostrarFoto(marcador);
            }
            if(marcadorTipo.getNum_atributos() == 1){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE);
            } else if(marcadorTipo.getNum_atributos() == 2){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE);
            } else if(marcadorTipo.getNum_atributos() == 3){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
                enunciado_atri3.setText(marcadorTipo.getAtributo3()+":"); respuesta_atri3.setText(marcador.getAtributo3());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                enunciado_atri3.setVisibility(View.VISIBLE); respuesta_atri3.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE);
                layout_atributo3.setVisibility(View.VISIBLE);
            } else if(marcadorTipo.getNum_atributos() == 4){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
                enunciado_atri3.setText(marcadorTipo.getAtributo3()+":"); respuesta_atri3.setText(marcador.getAtributo3());
                enunciado_atri4.setText(marcadorTipo.getAtributo4()+":"); respuesta_atri4.setText(marcador.getAtributo4());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                enunciado_atri3.setVisibility(View.VISIBLE); respuesta_atri3.setVisibility(View.VISIBLE);
                enunciado_atri4.setVisibility(View.VISIBLE); respuesta_atri4.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE);
                layout_atributo3.setVisibility(View.VISIBLE);
                layout_atributo4.setVisibility(View.VISIBLE);
            } else if(marcadorTipo.getNum_atributos() == 5){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
                enunciado_atri3.setText(marcadorTipo.getAtributo3()+":"); respuesta_atri3.setText(marcador.getAtributo3());
                enunciado_atri4.setText(marcadorTipo.getAtributo4()+":"); respuesta_atri4.setText(marcador.getAtributo4());
                enunciado_atri5.setText(marcadorTipo.getAtributo5()+":"); respuesta_atri5.setText(marcador.getAtributo5());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                enunciado_atri3.setVisibility(View.VISIBLE); respuesta_atri3.setVisibility(View.VISIBLE);
                enunciado_atri4.setVisibility(View.VISIBLE); respuesta_atri4.setVisibility(View.VISIBLE);
                enunciado_atri5.setVisibility(View.VISIBLE); respuesta_atri5.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE); layout_atributo5.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE);
                layout_atributo3.setVisibility(View.VISIBLE);
                layout_atributo4.setVisibility(View.VISIBLE);
            } else if(marcadorTipo.getNum_atributos() == 6){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
                enunciado_atri3.setText(marcadorTipo.getAtributo3()+":"); respuesta_atri3.setText(marcador.getAtributo3());
                enunciado_atri4.setText(marcadorTipo.getAtributo4()+":"); respuesta_atri4.setText(marcador.getAtributo4());
                enunciado_atri5.setText(marcadorTipo.getAtributo5()+":"); respuesta_atri5.setText(marcador.getAtributo5());
                enunciado_atri6.setText(marcadorTipo.getAtributo6()+":"); respuesta_atri6.setText(marcador.getAtributo6());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                enunciado_atri3.setVisibility(View.VISIBLE); respuesta_atri3.setVisibility(View.VISIBLE);
                enunciado_atri4.setVisibility(View.VISIBLE); respuesta_atri4.setVisibility(View.VISIBLE);
                enunciado_atri5.setVisibility(View.VISIBLE); respuesta_atri5.setVisibility(View.VISIBLE);
                enunciado_atri6.setVisibility(View.VISIBLE); respuesta_atri6.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE); layout_atributo5.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE); layout_atributo6.setVisibility(View.VISIBLE);
                layout_atributo3.setVisibility(View.VISIBLE);
                layout_atributo4.setVisibility(View.VISIBLE);
            } else if(marcadorTipo.getNum_atributos() == 7){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
                enunciado_atri3.setText(marcadorTipo.getAtributo3()+":"); respuesta_atri3.setText(marcador.getAtributo3());
                enunciado_atri4.setText(marcadorTipo.getAtributo4()+":"); respuesta_atri4.setText(marcador.getAtributo4());
                enunciado_atri5.setText(marcadorTipo.getAtributo5()+":"); respuesta_atri5.setText(marcador.getAtributo5());
                enunciado_atri6.setText(marcadorTipo.getAtributo6()+":"); respuesta_atri6.setText(marcador.getAtributo6());
                enunciado_atri7.setText(marcadorTipo.getAtributo7()+":"); respuesta_atri7.setText(marcador.getAtributo7());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                enunciado_atri3.setVisibility(View.VISIBLE); respuesta_atri3.setVisibility(View.VISIBLE);
                enunciado_atri4.setVisibility(View.VISIBLE); respuesta_atri4.setVisibility(View.VISIBLE);
                enunciado_atri5.setVisibility(View.VISIBLE); respuesta_atri5.setVisibility(View.VISIBLE);
                enunciado_atri6.setVisibility(View.VISIBLE); respuesta_atri6.setVisibility(View.VISIBLE);
                enunciado_atri7.setVisibility(View.VISIBLE); respuesta_atri7.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE); layout_atributo5.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE); layout_atributo6.setVisibility(View.VISIBLE);
                layout_atributo3.setVisibility(View.VISIBLE); layout_atributo7.setVisibility(View.VISIBLE);
                layout_atributo4.setVisibility(View.VISIBLE);
            }  else if(marcadorTipo.getNum_atributos() == 8){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
                enunciado_atri3.setText(marcadorTipo.getAtributo3()+":"); respuesta_atri3.setText(marcador.getAtributo3());
                enunciado_atri4.setText(marcadorTipo.getAtributo4()+":"); respuesta_atri4.setText(marcador.getAtributo4());
                enunciado_atri5.setText(marcadorTipo.getAtributo5()+":"); respuesta_atri5.setText(marcador.getAtributo5());
                enunciado_atri6.setText(marcadorTipo.getAtributo6()+":"); respuesta_atri6.setText(marcador.getAtributo6());
                enunciado_atri7.setText(marcadorTipo.getAtributo7()+":"); respuesta_atri7.setText(marcador.getAtributo7());
                enunciado_atri8.setText(marcadorTipo.getAtributo8()+":"); respuesta_atri8.setText(marcador.getAtributo8());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                enunciado_atri3.setVisibility(View.VISIBLE); respuesta_atri3.setVisibility(View.VISIBLE);
                enunciado_atri4.setVisibility(View.VISIBLE); respuesta_atri4.setVisibility(View.VISIBLE);
                enunciado_atri5.setVisibility(View.VISIBLE); respuesta_atri5.setVisibility(View.VISIBLE);
                enunciado_atri6.setVisibility(View.VISIBLE); respuesta_atri6.setVisibility(View.VISIBLE);
                enunciado_atri7.setVisibility(View.VISIBLE); respuesta_atri7.setVisibility(View.VISIBLE);
                enunciado_atri8.setVisibility(View.VISIBLE); respuesta_atri8.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE); layout_atributo5.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE); layout_atributo6.setVisibility(View.VISIBLE);
                layout_atributo3.setVisibility(View.VISIBLE); layout_atributo7.setVisibility(View.VISIBLE);
                layout_atributo4.setVisibility(View.VISIBLE); layout_atributo8.setVisibility(View.VISIBLE);
            }  else if(marcadorTipo.getNum_atributos() == 9){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
                enunciado_atri3.setText(marcadorTipo.getAtributo3()+":"); respuesta_atri3.setText(marcador.getAtributo3());
                enunciado_atri4.setText(marcadorTipo.getAtributo4()+":"); respuesta_atri4.setText(marcador.getAtributo4());
                enunciado_atri5.setText(marcadorTipo.getAtributo5()+":"); respuesta_atri5.setText(marcador.getAtributo5());
                enunciado_atri6.setText(marcadorTipo.getAtributo6()+":"); respuesta_atri6.setText(marcador.getAtributo6());
                enunciado_atri7.setText(marcadorTipo.getAtributo7()+":"); respuesta_atri7.setText(marcador.getAtributo7());
                enunciado_atri8.setText(marcadorTipo.getAtributo8()+":"); respuesta_atri8.setText(marcador.getAtributo8());
                enunciado_atri9.setText(marcadorTipo.getAtributo9()+":"); respuesta_atri9.setText(marcador.getAtributo9());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                enunciado_atri3.setVisibility(View.VISIBLE); respuesta_atri3.setVisibility(View.VISIBLE);
                enunciado_atri4.setVisibility(View.VISIBLE); respuesta_atri4.setVisibility(View.VISIBLE);
                enunciado_atri5.setVisibility(View.VISIBLE); respuesta_atri5.setVisibility(View.VISIBLE);
                enunciado_atri6.setVisibility(View.VISIBLE); respuesta_atri6.setVisibility(View.VISIBLE);
                enunciado_atri7.setVisibility(View.VISIBLE); respuesta_atri7.setVisibility(View.VISIBLE);
                enunciado_atri8.setVisibility(View.VISIBLE); respuesta_atri8.setVisibility(View.VISIBLE);
                enunciado_atri9.setVisibility(View.VISIBLE); respuesta_atri9.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE); layout_atributo5.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE); layout_atributo6.setVisibility(View.VISIBLE);
                layout_atributo3.setVisibility(View.VISIBLE); layout_atributo7.setVisibility(View.VISIBLE);
                layout_atributo4.setVisibility(View.VISIBLE); layout_atributo8.setVisibility(View.VISIBLE);
                layout_atributo9.setVisibility(View.VISIBLE);
            } else if(marcadorTipo.getNum_atributos() == 10){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
                enunciado_atri3.setText(marcadorTipo.getAtributo3()+":"); respuesta_atri3.setText(marcador.getAtributo3());
                enunciado_atri4.setText(marcadorTipo.getAtributo4()+":"); respuesta_atri4.setText(marcador.getAtributo4());
                enunciado_atri5.setText(marcadorTipo.getAtributo5()+":"); respuesta_atri5.setText(marcador.getAtributo5());
                enunciado_atri6.setText(marcadorTipo.getAtributo6()+":"); respuesta_atri6.setText(marcador.getAtributo6());
                enunciado_atri7.setText(marcadorTipo.getAtributo7()+":"); respuesta_atri7.setText(marcador.getAtributo7());
                enunciado_atri8.setText(marcadorTipo.getAtributo8()+":"); respuesta_atri8.setText(marcador.getAtributo8());
                enunciado_atri9.setText(marcadorTipo.getAtributo9()+":"); respuesta_atri9.setText(marcador.getAtributo9());
                enunciado_atri10.setText(marcadorTipo.getAtributo10()+":"); respuesta_atri10.setText(marcador.getAtributo10());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                enunciado_atri3.setVisibility(View.VISIBLE); respuesta_atri3.setVisibility(View.VISIBLE);
                enunciado_atri4.setVisibility(View.VISIBLE); respuesta_atri4.setVisibility(View.VISIBLE);
                enunciado_atri5.setVisibility(View.VISIBLE); respuesta_atri5.setVisibility(View.VISIBLE);
                enunciado_atri6.setVisibility(View.VISIBLE); respuesta_atri6.setVisibility(View.VISIBLE);
                enunciado_atri7.setVisibility(View.VISIBLE); respuesta_atri7.setVisibility(View.VISIBLE);
                enunciado_atri8.setVisibility(View.VISIBLE); respuesta_atri8.setVisibility(View.VISIBLE);
                enunciado_atri9.setVisibility(View.VISIBLE); respuesta_atri9.setVisibility(View.VISIBLE);
                enunciado_atri10.setVisibility(View.VISIBLE); respuesta_atri10.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE); layout_atributo5.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE); layout_atributo6.setVisibility(View.VISIBLE);
                layout_atributo3.setVisibility(View.VISIBLE); layout_atributo7.setVisibility(View.VISIBLE);
                layout_atributo4.setVisibility(View.VISIBLE); layout_atributo8.setVisibility(View.VISIBLE);
                layout_atributo9.setVisibility(View.VISIBLE); layout_atributo10.setVisibility(View.VISIBLE);
            } else if(marcadorTipo.getNum_atributos() == 11){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
                enunciado_atri3.setText(marcadorTipo.getAtributo3()+":"); respuesta_atri3.setText(marcador.getAtributo3());
                enunciado_atri4.setText(marcadorTipo.getAtributo4()+":"); respuesta_atri4.setText(marcador.getAtributo4());
                enunciado_atri5.setText(marcadorTipo.getAtributo5()+":"); respuesta_atri5.setText(marcador.getAtributo5());
                enunciado_atri6.setText(marcadorTipo.getAtributo6()+":"); respuesta_atri6.setText(marcador.getAtributo6());
                enunciado_atri7.setText(marcadorTipo.getAtributo7()+":"); respuesta_atri7.setText(marcador.getAtributo7());
                enunciado_atri8.setText(marcadorTipo.getAtributo8()+":"); respuesta_atri8.setText(marcador.getAtributo8());
                enunciado_atri9.setText(marcadorTipo.getAtributo9()+":"); respuesta_atri9.setText(marcador.getAtributo9());
                enunciado_atri10.setText(marcadorTipo.getAtributo10()+":"); respuesta_atri10.setText(marcador.getAtributo10());
                enunciado_atri11.setText(marcadorTipo.getAtributo11()+":"); respuesta_atri11.setText(marcador.getAtributo11());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                enunciado_atri3.setVisibility(View.VISIBLE); respuesta_atri3.setVisibility(View.VISIBLE);
                enunciado_atri4.setVisibility(View.VISIBLE); respuesta_atri4.setVisibility(View.VISIBLE);
                enunciado_atri5.setVisibility(View.VISIBLE); respuesta_atri5.setVisibility(View.VISIBLE);
                enunciado_atri6.setVisibility(View.VISIBLE); respuesta_atri6.setVisibility(View.VISIBLE);
                enunciado_atri7.setVisibility(View.VISIBLE); respuesta_atri7.setVisibility(View.VISIBLE);
                enunciado_atri8.setVisibility(View.VISIBLE); respuesta_atri8.setVisibility(View.VISIBLE);
                enunciado_atri9.setVisibility(View.VISIBLE); respuesta_atri9.setVisibility(View.VISIBLE);
                enunciado_atri10.setVisibility(View.VISIBLE); respuesta_atri10.setVisibility(View.VISIBLE);
                enunciado_atri11.setVisibility(View.VISIBLE); respuesta_atri11.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE); layout_atributo5.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE); layout_atributo6.setVisibility(View.VISIBLE);
                layout_atributo3.setVisibility(View.VISIBLE); layout_atributo7.setVisibility(View.VISIBLE);
                layout_atributo4.setVisibility(View.VISIBLE); layout_atributo8.setVisibility(View.VISIBLE);
                layout_atributo9.setVisibility(View.VISIBLE); layout_atributo10.setVisibility(View.VISIBLE);
                layout_atributo11.setVisibility(View.VISIBLE);
            } else if(marcadorTipo.getNum_atributos() == 12){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
                enunciado_atri3.setText(marcadorTipo.getAtributo3()+":"); respuesta_atri3.setText(marcador.getAtributo3());
                enunciado_atri4.setText(marcadorTipo.getAtributo4()+":"); respuesta_atri4.setText(marcador.getAtributo4());
                enunciado_atri5.setText(marcadorTipo.getAtributo5()+":"); respuesta_atri5.setText(marcador.getAtributo5());
                enunciado_atri6.setText(marcadorTipo.getAtributo6()+":"); respuesta_atri6.setText(marcador.getAtributo6());
                enunciado_atri7.setText(marcadorTipo.getAtributo7()+":"); respuesta_atri7.setText(marcador.getAtributo7());
                enunciado_atri8.setText(marcadorTipo.getAtributo8()+":"); respuesta_atri8.setText(marcador.getAtributo8());
                enunciado_atri9.setText(marcadorTipo.getAtributo9()+":"); respuesta_atri9.setText(marcador.getAtributo9());
                enunciado_atri10.setText(marcadorTipo.getAtributo10()+":"); respuesta_atri10.setText(marcador.getAtributo10());
                enunciado_atri11.setText(marcadorTipo.getAtributo11()+":"); respuesta_atri11.setText(marcador.getAtributo11());
                enunciado_atri12.setText(marcadorTipo.getAtributo12()+":"); respuesta_atri12.setText(marcador.getAtributo12());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                enunciado_atri3.setVisibility(View.VISIBLE); respuesta_atri3.setVisibility(View.VISIBLE);
                enunciado_atri4.setVisibility(View.VISIBLE); respuesta_atri4.setVisibility(View.VISIBLE);
                enunciado_atri5.setVisibility(View.VISIBLE); respuesta_atri5.setVisibility(View.VISIBLE);
                enunciado_atri6.setVisibility(View.VISIBLE); respuesta_atri6.setVisibility(View.VISIBLE);
                enunciado_atri7.setVisibility(View.VISIBLE); respuesta_atri7.setVisibility(View.VISIBLE);
                enunciado_atri8.setVisibility(View.VISIBLE); respuesta_atri8.setVisibility(View.VISIBLE);
                enunciado_atri9.setVisibility(View.VISIBLE); respuesta_atri9.setVisibility(View.VISIBLE);
                enunciado_atri10.setVisibility(View.VISIBLE); respuesta_atri10.setVisibility(View.VISIBLE);
                enunciado_atri12.setVisibility(View.VISIBLE); respuesta_atri12.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE); layout_atributo5.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE); layout_atributo6.setVisibility(View.VISIBLE);
                layout_atributo3.setVisibility(View.VISIBLE); layout_atributo7.setVisibility(View.VISIBLE);
                layout_atributo4.setVisibility(View.VISIBLE); layout_atributo8.setVisibility(View.VISIBLE);
                layout_atributo9.setVisibility(View.VISIBLE); layout_atributo10.setVisibility(View.VISIBLE);
                layout_atributo11.setVisibility(View.VISIBLE); layout_atributo12.setVisibility(View.VISIBLE);
            } else if(marcadorTipo.getNum_atributos() == 13){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
                enunciado_atri3.setText(marcadorTipo.getAtributo3()+":"); respuesta_atri3.setText(marcador.getAtributo3());
                enunciado_atri4.setText(marcadorTipo.getAtributo4()+":"); respuesta_atri4.setText(marcador.getAtributo4());
                enunciado_atri5.setText(marcadorTipo.getAtributo5()+":"); respuesta_atri5.setText(marcador.getAtributo5());
                enunciado_atri6.setText(marcadorTipo.getAtributo6()+":"); respuesta_atri6.setText(marcador.getAtributo6());
                enunciado_atri7.setText(marcadorTipo.getAtributo7()+":"); respuesta_atri7.setText(marcador.getAtributo7());
                enunciado_atri8.setText(marcadorTipo.getAtributo8()+":"); respuesta_atri8.setText(marcador.getAtributo8());
                enunciado_atri9.setText(marcadorTipo.getAtributo9()+":"); respuesta_atri9.setText(marcador.getAtributo9());
                enunciado_atri10.setText(marcadorTipo.getAtributo10()+":"); respuesta_atri10.setText(marcador.getAtributo10());
                enunciado_atri11.setText(marcadorTipo.getAtributo11()+":"); respuesta_atri11.setText(marcador.getAtributo11());
                enunciado_atri12.setText(marcadorTipo.getAtributo12()+":"); respuesta_atri12.setText(marcador.getAtributo12());
                enunciado_atri13.setText(marcadorTipo.getAtributo13()+":"); respuesta_atri13.setText(marcador.getAtributo13());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                enunciado_atri3.setVisibility(View.VISIBLE); respuesta_atri3.setVisibility(View.VISIBLE);
                enunciado_atri4.setVisibility(View.VISIBLE); respuesta_atri4.setVisibility(View.VISIBLE);
                enunciado_atri5.setVisibility(View.VISIBLE); respuesta_atri5.setVisibility(View.VISIBLE);
                enunciado_atri6.setVisibility(View.VISIBLE); respuesta_atri6.setVisibility(View.VISIBLE);
                enunciado_atri7.setVisibility(View.VISIBLE); respuesta_atri7.setVisibility(View.VISIBLE);
                enunciado_atri8.setVisibility(View.VISIBLE); respuesta_atri8.setVisibility(View.VISIBLE);
                enunciado_atri9.setVisibility(View.VISIBLE); respuesta_atri9.setVisibility(View.VISIBLE);
                enunciado_atri10.setVisibility(View.VISIBLE); respuesta_atri10.setVisibility(View.VISIBLE);
                enunciado_atri12.setVisibility(View.VISIBLE); respuesta_atri12.setVisibility(View.VISIBLE);
                enunciado_atri13.setVisibility(View.VISIBLE); respuesta_atri13.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE); layout_atributo5.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE); layout_atributo6.setVisibility(View.VISIBLE);
                layout_atributo3.setVisibility(View.VISIBLE); layout_atributo7.setVisibility(View.VISIBLE);
                layout_atributo4.setVisibility(View.VISIBLE); layout_atributo8.setVisibility(View.VISIBLE);
                layout_atributo9.setVisibility(View.VISIBLE); layout_atributo10.setVisibility(View.VISIBLE);
                layout_atributo11.setVisibility(View.VISIBLE); layout_atributo12.setVisibility(View.VISIBLE);
                layout_atributo13.setVisibility(View.VISIBLE);
            } else if(marcadorTipo.getNum_atributos() == 14){
            enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
            enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
            enunciado_atri3.setText(marcadorTipo.getAtributo3()+":"); respuesta_atri3.setText(marcador.getAtributo3());
            enunciado_atri4.setText(marcadorTipo.getAtributo4()+":"); respuesta_atri4.setText(marcador.getAtributo4());
            enunciado_atri5.setText(marcadorTipo.getAtributo5()+":"); respuesta_atri5.setText(marcador.getAtributo5());
            enunciado_atri6.setText(marcadorTipo.getAtributo6()+":"); respuesta_atri6.setText(marcador.getAtributo6());
            enunciado_atri7.setText(marcadorTipo.getAtributo7()+":"); respuesta_atri7.setText(marcador.getAtributo7());
            enunciado_atri8.setText(marcadorTipo.getAtributo8()+":"); respuesta_atri8.setText(marcador.getAtributo8());
            enunciado_atri9.setText(marcadorTipo.getAtributo9()+":"); respuesta_atri9.setText(marcador.getAtributo9());
            enunciado_atri10.setText(marcadorTipo.getAtributo10()+":"); respuesta_atri10.setText(marcador.getAtributo10());
            enunciado_atri11.setText(marcadorTipo.getAtributo11()+":"); respuesta_atri11.setText(marcador.getAtributo11());
            enunciado_atri12.setText(marcadorTipo.getAtributo12()+":"); respuesta_atri12.setText(marcador.getAtributo12());
            enunciado_atri13.setText(marcadorTipo.getAtributo13()+":"); respuesta_atri13.setText(marcador.getAtributo13());
            enunciado_atri14.setText(marcadorTipo.getAtributo14()+":"); respuesta_atri14.setText(marcador.getAtributo14());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                enunciado_atri3.setVisibility(View.VISIBLE); respuesta_atri3.setVisibility(View.VISIBLE);
                enunciado_atri4.setVisibility(View.VISIBLE); respuesta_atri4.setVisibility(View.VISIBLE);
                enunciado_atri5.setVisibility(View.VISIBLE); respuesta_atri5.setVisibility(View.VISIBLE);
                enunciado_atri6.setVisibility(View.VISIBLE); respuesta_atri6.setVisibility(View.VISIBLE);
                enunciado_atri7.setVisibility(View.VISIBLE); respuesta_atri7.setVisibility(View.VISIBLE);
                enunciado_atri8.setVisibility(View.VISIBLE); respuesta_atri8.setVisibility(View.VISIBLE);
                enunciado_atri9.setVisibility(View.VISIBLE); respuesta_atri9.setVisibility(View.VISIBLE);
                enunciado_atri10.setVisibility(View.VISIBLE); respuesta_atri10.setVisibility(View.VISIBLE);
                enunciado_atri12.setVisibility(View.VISIBLE); respuesta_atri12.setVisibility(View.VISIBLE);
                enunciado_atri13.setVisibility(View.VISIBLE); respuesta_atri13.setVisibility(View.VISIBLE);
                enunciado_atri14.setVisibility(View.VISIBLE); respuesta_atri14.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE); layout_atributo5.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE); layout_atributo6.setVisibility(View.VISIBLE);
                layout_atributo3.setVisibility(View.VISIBLE); layout_atributo7.setVisibility(View.VISIBLE);
                layout_atributo4.setVisibility(View.VISIBLE); layout_atributo8.setVisibility(View.VISIBLE);
                layout_atributo9.setVisibility(View.VISIBLE); layout_atributo10.setVisibility(View.VISIBLE);
                layout_atributo11.setVisibility(View.VISIBLE); layout_atributo12.setVisibility(View.VISIBLE);
                layout_atributo13.setVisibility(View.VISIBLE); layout_atributo14.setVisibility(View.VISIBLE);
        } else if(marcadorTipo.getNum_atributos() == 15){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
                enunciado_atri3.setText(marcadorTipo.getAtributo3()+":"); respuesta_atri3.setText(marcador.getAtributo3());
                enunciado_atri4.setText(marcadorTipo.getAtributo4()+":"); respuesta_atri4.setText(marcador.getAtributo4());
                enunciado_atri5.setText(marcadorTipo.getAtributo5()+":"); respuesta_atri5.setText(marcador.getAtributo5());
                enunciado_atri6.setText(marcadorTipo.getAtributo6()+":"); respuesta_atri6.setText(marcador.getAtributo6());
                enunciado_atri7.setText(marcadorTipo.getAtributo7()+":"); respuesta_atri7.setText(marcador.getAtributo7());
                enunciado_atri8.setText(marcadorTipo.getAtributo8()+":"); respuesta_atri8.setText(marcador.getAtributo8());
                enunciado_atri9.setText(marcadorTipo.getAtributo9()+":"); respuesta_atri9.setText(marcador.getAtributo9());
                enunciado_atri10.setText(marcadorTipo.getAtributo10()+":"); respuesta_atri10.setText(marcador.getAtributo10());
                enunciado_atri11.setText(marcadorTipo.getAtributo11()+":"); respuesta_atri11.setText(marcador.getAtributo11());
                enunciado_atri12.setText(marcadorTipo.getAtributo12()+":"); respuesta_atri12.setText(marcador.getAtributo12());
                enunciado_atri13.setText(marcadorTipo.getAtributo13()+":"); respuesta_atri13.setText(marcador.getAtributo13());
                enunciado_atri14.setText(marcadorTipo.getAtributo14()+":"); respuesta_atri14.setText(marcador.getAtributo14());
                enunciado_atri15.setText(marcadorTipo.getAtributo15()+":"); respuesta_atri15.setText(marcador.getAtributo15());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                enunciado_atri3.setVisibility(View.VISIBLE); respuesta_atri3.setVisibility(View.VISIBLE);
                enunciado_atri4.setVisibility(View.VISIBLE); respuesta_atri4.setVisibility(View.VISIBLE);
                enunciado_atri5.setVisibility(View.VISIBLE); respuesta_atri5.setVisibility(View.VISIBLE);
                enunciado_atri6.setVisibility(View.VISIBLE); respuesta_atri6.setVisibility(View.VISIBLE);
                enunciado_atri7.setVisibility(View.VISIBLE); respuesta_atri7.setVisibility(View.VISIBLE);
                enunciado_atri8.setVisibility(View.VISIBLE); respuesta_atri8.setVisibility(View.VISIBLE);
                enunciado_atri9.setVisibility(View.VISIBLE); respuesta_atri9.setVisibility(View.VISIBLE);
                enunciado_atri10.setVisibility(View.VISIBLE); respuesta_atri10.setVisibility(View.VISIBLE);
                enunciado_atri12.setVisibility(View.VISIBLE); respuesta_atri12.setVisibility(View.VISIBLE);
                enunciado_atri13.setVisibility(View.VISIBLE); respuesta_atri13.setVisibility(View.VISIBLE);
                enunciado_atri14.setVisibility(View.VISIBLE); respuesta_atri14.setVisibility(View.VISIBLE);
                enunciado_atri15.setVisibility(View.VISIBLE); respuesta_atri15.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE); layout_atributo5.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE); layout_atributo6.setVisibility(View.VISIBLE);
                layout_atributo3.setVisibility(View.VISIBLE); layout_atributo7.setVisibility(View.VISIBLE);
                layout_atributo4.setVisibility(View.VISIBLE); layout_atributo8.setVisibility(View.VISIBLE);
                layout_atributo9.setVisibility(View.VISIBLE); layout_atributo10.setVisibility(View.VISIBLE);
                layout_atributo11.setVisibility(View.VISIBLE); layout_atributo12.setVisibility(View.VISIBLE);
                layout_atributo13.setVisibility(View.VISIBLE); layout_atributo14.setVisibility(View.VISIBLE);
                layout_atributo15.setVisibility(View.VISIBLE);
            } else if(marcadorTipo.getNum_atributos() == 16){
                enunciado_atri1.setText(marcadorTipo.getAtributo1()+":"); respuesta_atri1.setText(marcador.getAtributo1());
                enunciado_atri2.setText(marcadorTipo.getAtributo2()+":"); respuesta_atri2.setText(marcador.getAtributo2());
                enunciado_atri3.setText(marcadorTipo.getAtributo3()+":"); respuesta_atri3.setText(marcador.getAtributo3());
                enunciado_atri4.setText(marcadorTipo.getAtributo4()+":"); respuesta_atri4.setText(marcador.getAtributo4());
                enunciado_atri5.setText(marcadorTipo.getAtributo5()+":"); respuesta_atri5.setText(marcador.getAtributo5());
                enunciado_atri6.setText(marcadorTipo.getAtributo6()+":"); respuesta_atri6.setText(marcador.getAtributo6());
                enunciado_atri7.setText(marcadorTipo.getAtributo7()+":"); respuesta_atri7.setText(marcador.getAtributo7());
                enunciado_atri8.setText(marcadorTipo.getAtributo8()+":"); respuesta_atri8.setText(marcador.getAtributo8());
                enunciado_atri9.setText(marcadorTipo.getAtributo9()+":"); respuesta_atri9.setText(marcador.getAtributo9());
                enunciado_atri10.setText(marcadorTipo.getAtributo10()+":"); respuesta_atri10.setText(marcador.getAtributo10());
                enunciado_atri11.setText(marcadorTipo.getAtributo11()+":"); respuesta_atri11.setText(marcador.getAtributo11());
                enunciado_atri12.setText(marcadorTipo.getAtributo12()+":"); respuesta_atri12.setText(marcador.getAtributo12());
                enunciado_atri13.setText(marcadorTipo.getAtributo13()+":"); respuesta_atri13.setText(marcador.getAtributo13());
                enunciado_atri14.setText(marcadorTipo.getAtributo14()+":"); respuesta_atri14.setText(marcador.getAtributo14());
                enunciado_atri15.setText(marcadorTipo.getAtributo15()+":"); respuesta_atri15.setText(marcador.getAtributo15());
                enunciado_atri16.setText(marcadorTipo.getAtributo16()+":"); respuesta_atri16.setText(marcador.getAtributo16());
                enunciado_atri1.setVisibility(View.VISIBLE); respuesta_atri1.setVisibility(View.VISIBLE);
                enunciado_atri2.setVisibility(View.VISIBLE); respuesta_atri2.setVisibility(View.VISIBLE);
                enunciado_atri3.setVisibility(View.VISIBLE); respuesta_atri3.setVisibility(View.VISIBLE);
                enunciado_atri4.setVisibility(View.VISIBLE); respuesta_atri4.setVisibility(View.VISIBLE);
                enunciado_atri5.setVisibility(View.VISIBLE); respuesta_atri5.setVisibility(View.VISIBLE);
                enunciado_atri6.setVisibility(View.VISIBLE); respuesta_atri6.setVisibility(View.VISIBLE);
                enunciado_atri7.setVisibility(View.VISIBLE); respuesta_atri7.setVisibility(View.VISIBLE);
                enunciado_atri8.setVisibility(View.VISIBLE); respuesta_atri8.setVisibility(View.VISIBLE);
                enunciado_atri9.setVisibility(View.VISIBLE); respuesta_atri9.setVisibility(View.VISIBLE);
                enunciado_atri10.setVisibility(View.VISIBLE); respuesta_atri10.setVisibility(View.VISIBLE);
                enunciado_atri12.setVisibility(View.VISIBLE); respuesta_atri12.setVisibility(View.VISIBLE);
                enunciado_atri13.setVisibility(View.VISIBLE); respuesta_atri13.setVisibility(View.VISIBLE);
                enunciado_atri14.setVisibility(View.VISIBLE); respuesta_atri14.setVisibility(View.VISIBLE);
                enunciado_atri15.setVisibility(View.VISIBLE); respuesta_atri15.setVisibility(View.VISIBLE);
                enunciado_atri16.setVisibility(View.VISIBLE); respuesta_atri16.setVisibility(View.VISIBLE);
                layout_atributo1.setVisibility(View.VISIBLE); layout_atributo5.setVisibility(View.VISIBLE);
                layout_atributo2.setVisibility(View.VISIBLE); layout_atributo6.setVisibility(View.VISIBLE);
                layout_atributo3.setVisibility(View.VISIBLE); layout_atributo7.setVisibility(View.VISIBLE);
                layout_atributo4.setVisibility(View.VISIBLE); layout_atributo8.setVisibility(View.VISIBLE);
                layout_atributo9.setVisibility(View.VISIBLE); layout_atributo10.setVisibility(View.VISIBLE);
                layout_atributo11.setVisibility(View.VISIBLE); layout_atributo12.setVisibility(View.VISIBLE);
                layout_atributo13.setVisibility(View.VISIBLE); layout_atributo14.setVisibility(View.VISIBLE);
                layout_atributo15.setVisibility(View.VISIBLE); layout_atributo16.setVisibility(View.VISIBLE);
            }

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marcador_mostrado.setVisibility(View.GONE);
                }
            });
    }

    public void mostrarFoto(Marcador marcador){


        urlfoto = "https://citmapp.ibercivis.es/uploads/marcadores/"+String.valueOf(marcador.getId())+".jpg";
       // Bitmap foto = new DownloadFilesTask().execute(urlfoto);
        Picasso.with(this).load(urlfoto).into(marco_foto);

        marco_foto.setScaleType(ImageView.ScaleType.CENTER_CROP);


       // marco_foto.setRotation(90);

    }

    public void deleteMarkerRequest (final Marcador marcador_delete) {

        final LinearLayout cargar = findViewById(R.id.cargando);

        cargar.setVisibility(View.VISIBLE);





        // Url for the webservice
        String url = getString(R.string.base_url) + "/deleteMarcador.php";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println(response.toString());

                    JSONObject responseJSON = new JSONObject(response);

                    if ((int) responseJSON.get("result") == 1) {

                        int duration = Toast.LENGTH_SHORT;
                        Toast toast;
                        CharSequence text;

                        text = "El marcador ha sido eliminado";
                        toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();

                        cargar.setVisibility(View.GONE);

                        recreate();


                    } else {
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast;
                        CharSequence text;

                        text = "Algo ha ocurrido. Inténtalo más tarde.";
                        toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();

                        // Clean the text fields for new entries

                        cargar.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    cargar.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                int duration = Toast.LENGTH_SHORT;
                Toast toast;
                CharSequence text;
                text = "Error while login: " + error.getLocalizedMessage() + ".";
                toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
                cargar.setVisibility(View.GONE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> login_params = new HashMap<String, String>();

                SessionManager session = new SessionManager(getApplicationContext());
                login_params.put("idUser", String.valueOf(session.getIdUser()));
                login_params.put("token", String.valueOf(session.getToken()));
                login_params.put("id", String.valueOf(marcador_delete.getId()));


                return login_params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    }

