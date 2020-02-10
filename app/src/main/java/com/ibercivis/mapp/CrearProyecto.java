package com.ibercivis.mapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
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
import com.ibercivis.mapp.clases.Marcador;
import com.ibercivis.mapp.clases.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CrearProyecto extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    int atributo_added = 0, photo_added = 0, esPrivado = 0;
    TextView textomarcadores;
    Button addphoto, addtextatribute, addnumberatribute, createproject;
    LinearLayout phot, atri1, atri2, atri3, atri4, atri5, atri6, atri7, atri8, atri9, atri10, atri11, atri12, atri13, atri14, atri15, atri16;
    Integer isText1 = 0, isText2 = 0, isText3 = 0, isText4 = 0, isText5 = 0, isText6 = 0, isText7 = 0, isText8 = 0, isText9 = 0, isText10 = 0, isText11 = 0, isText12 = 0, isText13 = 0, isText14 = 0, isText15 = 0, isText16 = 0;
    ImageView cancelphoto, cancelatri1, cancelatri2, cancelatri3, cancelatri4, cancelatri5, cancelatri6, cancelatri7, cancelatri8, cancelatri9, cancelatri10, cancelatri11, cancelatri12, cancelatri13, cancelatri14, cancelatri15, cancelatri16;
    TextInputEditText atributo1, atributo2, atributo3, atributo4, atributo5, atributo6, atributo7, atributo8, atributo9, atributo10, atributo11, atributo12, atributo13, atributo14, atributo15, atributo16;
    TextInputLayout pass_privado, hintatributo1, hintatributo2, hintatributo3, hintatributo4, hintatributo5, hintatributo6, hintatributo7, hintatributo8, hintatributo9, hintatributo10, hintatributo11, hintatributo12, hintatributo13, hintatributo14, hintatributo15, hintatributo16;
    TextInputEditText titulo_proyecto, descripcion_proyecto, web_proyecto, pass_proyecto;
    Switch foto, switch_privado;

    String error_check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyecto);

        /*-----Hooks-----*/

        drawerLayout = findViewById(R.id.drawer_layout3);
        navigationView = findViewById(R.id.nav_view3);
        toolbar = findViewById(R.id.toolbar3);

        textomarcadores = findViewById(R.id.texto_marcadores);

        //Buttons
        foto = findViewById(R.id.switchfoto);
        foto.setChecked(false);
        switch_privado = findViewById(R.id.switchprivado);
        switch_privado.setChecked(false);
        addtextatribute = findViewById(R.id.add_textatribute);
        addnumberatribute = findViewById(R.id.add_numberatribute);
        createproject = findViewById(R.id.btn_crearproyecto);

        //EditText
        atributo1 = findViewById(R.id.edit_atributo1); atributo9 = findViewById(R.id.edit_atributo9);
        atributo2 = findViewById(R.id.edit_atributo2); atributo10 = findViewById(R.id.edit_atributo10);
        atributo3 = findViewById(R.id.edit_atributo3); atributo11 = findViewById(R.id.edit_atributo11);
        atributo4 = findViewById(R.id.edit_atributo4); atributo12 = findViewById(R.id.edit_atributo12);
        atributo5 = findViewById(R.id.edit_atributo5); atributo13 = findViewById(R.id.edit_atributo13);
        atributo6 = findViewById(R.id.edit_atributo6); atributo14 = findViewById(R.id.edit_atributo14);
        atributo7 = findViewById(R.id.edit_atributo7); atributo15 = findViewById(R.id.edit_atributo15);
        atributo8 = findViewById(R.id.edit_atributo8); atributo16 = findViewById(R.id.edit_atributo16);

        atributo1.setText(""); atributo2.setText(""); atributo3.setText(""); atributo4.setText("");
        atributo5.setText(""); atributo6.setText(""); atributo7.setText(""); atributo8.setText("");
        atributo9.setText(""); atributo10.setText(""); atributo11.setText(""); atributo12.setText("");
        atributo13.setText(""); atributo14.setText(""); atributo15.setText(""); atributo16.setText("");
      //  atributo1.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS); atributo2.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS); atributo3.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS); atributo4.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS);
      //  atributo5.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS); atributo6.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS); atributo7.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS); atributo8.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS);
      //  atributo9.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS); atributo10.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS); atributo11.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS); atributo12.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS);
      //  atributo13.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS); atributo14.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS); atributo15.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS); atributo16.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_FLAG_CAP_WORDS);

        //Para hint
        hintatributo1 = findViewById(R.id.hint1); hintatributo9 = findViewById(R.id.hint9);
        hintatributo2 = findViewById(R.id.hint2); hintatributo10 = findViewById(R.id.hint10);
        hintatributo3 = findViewById(R.id.hint3); hintatributo11 = findViewById(R.id.hint11);
        hintatributo4 = findViewById(R.id.hint4); hintatributo12 = findViewById(R.id.hint12);
        hintatributo5 = findViewById(R.id.hint5); hintatributo13 = findViewById(R.id.hint13);
        hintatributo6 = findViewById(R.id.hint6); hintatributo14 = findViewById(R.id.hint14);
        hintatributo7 = findViewById(R.id.hint7); hintatributo15 = findViewById(R.id.hint15);
        hintatributo8 = findViewById(R.id.hint8); hintatributo16 = findViewById(R.id.hint16);

        //LinearLayouts
        phot = findViewById(R.id.photo);
        atri1 = findViewById(R.id.atributo1); atri9 = findViewById(R.id.atributo9);
        atri2 = findViewById(R.id.atributo2); atri10 = findViewById(R.id.atributo10);
        atri3 = findViewById(R.id.atributo3); atri11 = findViewById(R.id.atributo11);
        atri4 = findViewById(R.id.atributo4); atri12 = findViewById(R.id.atributo12);
        atri5 = findViewById(R.id.atributo5); atri13 = findViewById(R.id.atributo13);
        atri6 = findViewById(R.id.atributo6); atri14 = findViewById(R.id.atributo14);
        atri7 = findViewById(R.id.atributo7); atri15 = findViewById(R.id.atributo15);
        atri8 = findViewById(R.id.atributo8); atri16 = findViewById(R.id.atributo16);
        pass_privado = findViewById(R.id.edit_privado);

        //ImageViews
        cancelatri1 = findViewById(R.id.cancel_atributo1); cancelatri9 = findViewById(R.id.cancel_atributo9);
        cancelatri2 = findViewById(R.id.cancel_atributo2); cancelatri10 = findViewById(R.id.cancel_atributo10);
        cancelatri3 = findViewById(R.id.cancel_atributo3); cancelatri11 = findViewById(R.id.cancel_atributo11);
        cancelatri4 = findViewById(R.id.cancel_atributo4); cancelatri12 = findViewById(R.id.cancel_atributo12);
        cancelatri5 = findViewById(R.id.cancel_atributo5); cancelatri13 = findViewById(R.id.cancel_atributo13);
        cancelatri6 = findViewById(R.id.cancel_atributo6); cancelatri14 = findViewById(R.id.cancel_atributo14);
        cancelatri7 = findViewById(R.id.cancel_atributo7); cancelatri15 = findViewById(R.id.cancel_atributo15);
        cancelatri8 = findViewById(R.id.cancel_atributo8); cancelatri16 = findViewById(R.id.cancel_atributo16);

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
        navigationView.setCheckedItem(R.id.nav_crear);

        /*----- Para los botones -----*/

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(foto.isChecked() == true) {

                    phot.setVisibility(View.VISIBLE);
                    photo_added = 1;

                } else {
                    phot.setVisibility(View.GONE);
                    photo_added = 0;
                }
            }
        });

        switch_privado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch_privado.isChecked() == true){
                    pass_privado.setVisibility(View.VISIBLE);
                    esPrivado = 1;
                } else {
                    pass_privado.setVisibility(View.GONE);
                    esPrivado = 0;
                }
            }
        });


        addtextatribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(atributo_added != 16) {

                    atributo_added = atributo_added + 1;

                    switch (atributo_added) {
                        case 1:
                            atri1.setVisibility(View.VISIBLE);
                            textomarcadores.setVisibility(View.GONE);
                            isText1 = 1;
                            atributo1.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo1.setHint("Atributo 1 (Respuesta texto)");
                            break;

                        case 2:
                            atri2.setVisibility(View.VISIBLE);
                            cancelatri2.setVisibility(View.VISIBLE);
                            cancelatri1.setVisibility(View.GONE);
                            isText2 = 1;
                            atributo2.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo2.setHint("Atributo 2 (Respuesta texto)");
                            break;
                        case 3:
                            atri3.setVisibility(View.VISIBLE);
                            cancelatri3.setVisibility(View.VISIBLE);
                            cancelatri2.setVisibility(View.GONE);
                            isText3 = 1;
                            atributo3.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo3.setHint("Atributo 3 (Respuesta texto)");
                            break;
                        case 4:
                            atri4.setVisibility(View.VISIBLE);
                            cancelatri4.setVisibility(View.VISIBLE);
                            cancelatri3.setVisibility(View.GONE);
                            isText4 = 1;
                            atributo4.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo4.setHint("Atributo 4 (Respuesta texto)");
                            break;
                        case 5:
                            atri5.setVisibility(View.VISIBLE);
                            cancelatri5.setVisibility(View.VISIBLE);
                            cancelatri4.setVisibility(View.GONE);
                            isText5 = 1;
                            atributo5.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo5.setHint("Atributo 5 (Respuesta texto)");
                            break;
                        case 6:
                            atri6.setVisibility(View.VISIBLE);
                            cancelatri6.setVisibility(View.VISIBLE);
                            cancelatri5.setVisibility(View.GONE);
                            isText6 = 1;
                            atributo6.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo6.setHint("Atributo 6 (Respuesta texto)");
                            break;
                        case 7:
                            atri7.setVisibility(View.VISIBLE);
                            cancelatri7.setVisibility(View.VISIBLE);
                            cancelatri6.setVisibility(View.GONE);
                            isText7 = 1;
                            atributo7.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo7.setHint("Atributo 7 (Respuesta texto)");
                            break;
                        case 8:
                            atri8.setVisibility(View.VISIBLE);
                            cancelatri8.setVisibility(View.VISIBLE);
                            cancelatri7.setVisibility(View.GONE);
                            isText8 = 1;
                            atributo8.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo8.setHint("Atributo 8 (Respuesta texto)");
                            break;
                        case 9:
                            atri9.setVisibility(View.VISIBLE);
                            cancelatri9.setVisibility(View.VISIBLE);
                            cancelatri8.setVisibility(View.GONE);
                            isText9 = 1;
                            atributo9.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo9.setHint("Atributo 9 (Respuesta texto)");
                            break;
                        case 10:
                            atri10.setVisibility(View.VISIBLE);
                            cancelatri10.setVisibility(View.VISIBLE);
                            cancelatri9.setVisibility(View.GONE);
                            isText10 = 1;
                            atributo10.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo10.setHint("Atributo 10 (Respuesta texto)");
                            break;
                        case 11:
                            atri11.setVisibility(View.VISIBLE);
                            cancelatri11.setVisibility(View.VISIBLE);
                            cancelatri10.setVisibility(View.GONE);
                            isText11 = 1;
                            atributo11.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo11.setHint("Atributo 11 (Respuesta texto)");
                            break;
                        case 12:
                            atri12.setVisibility(View.VISIBLE);
                            cancelatri12.setVisibility(View.VISIBLE);
                            cancelatri11.setVisibility(View.GONE);
                            isText12 = 1;
                            atributo12.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo12.setHint("Atributo 12 (Respuesta texto)");
                            break;
                        case 13:
                            atri13.setVisibility(View.VISIBLE);
                            cancelatri13.setVisibility(View.VISIBLE);
                            cancelatri12.setVisibility(View.GONE);
                            isText13 = 1;
                            atributo13.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo13.setHint("Atributo 13 (Respuesta texto)");
                            break;
                        case 14:
                            atri14.setVisibility(View.VISIBLE);
                            cancelatri14.setVisibility(View.VISIBLE);
                            cancelatri13.setVisibility(View.GONE);
                            isText14 = 1;
                            atributo14.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo14.setHint("Atributo 14 (Respuesta texto)");
                            break;
                        case 15:
                            atri15.setVisibility(View.VISIBLE);
                            cancelatri15.setVisibility(View.VISIBLE);
                            cancelatri14.setVisibility(View.GONE);
                            isText15 = 1;
                            atributo15.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo15.setHint("Atributo 15 (Respuesta texto)");
                            break;
                        case 16:
                            atri16.setVisibility(View.VISIBLE);
                            cancelatri16.setVisibility(View.VISIBLE);
                            cancelatri15.setVisibility(View.GONE);
                            isText16 = 1;
                            atributo16.setInputType(InputType.TYPE_CLASS_TEXT);
                            hintatributo16.setHint("Atributo 16 (Respuesta texto)");
                            break;
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Has alcanzado el límite de atributos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addnumberatribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(atributo_added != 16) {

                    atributo_added = atributo_added + 1;

                    switch (atributo_added) {
                        case 1:
                            atri1.setVisibility(View.VISIBLE);
                            textomarcadores.setVisibility(View.GONE);
                            isText1 = 0;
                            //atributo1.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo1.setHint("Atributo 1 (Respuesta número)");
                            break;

                        case 2:
                            atri2.setVisibility(View.VISIBLE);
                            cancelatri2.setVisibility(View.VISIBLE);
                            cancelatri1.setVisibility(View.GONE);
                            isText2 = 0;
                            //atributo2.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo2.setHint("Atributo 2 (Respuesta número)");
                            break;
                        case 3:
                            atri3.setVisibility(View.VISIBLE);
                            cancelatri3.setVisibility(View.VISIBLE);
                            cancelatri2.setVisibility(View.GONE);
                            isText3 = 0;
                            //atributo3.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo3.setHint("Atributo 3 (Respuesta número)");
                            break;
                        case 4:
                            atri4.setVisibility(View.VISIBLE);
                            cancelatri4.setVisibility(View.VISIBLE);
                            cancelatri3.setVisibility(View.GONE);
                            isText4 = 0;
                            //atributo4.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo4.setHint("Atributo 4 (Respuesta número)");
                            break;
                        case 5:
                            atri5.setVisibility(View.VISIBLE);
                            cancelatri5.setVisibility(View.VISIBLE);
                            cancelatri4.setVisibility(View.GONE);
                            isText5 = 0;
                            //atributo5.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo5.setHint("Atributo 5 (Respuesta número)");
                            break;
                        case 6:
                            atri6.setVisibility(View.VISIBLE);
                            cancelatri6.setVisibility(View.VISIBLE);
                            cancelatri5.setVisibility(View.GONE);
                            isText6 = 0;
                            //atributo6.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo6.setHint("Atributo 6 (Respuesta número)");
                            break;
                        case 7:
                            atri7.setVisibility(View.VISIBLE);
                            cancelatri7.setVisibility(View.VISIBLE);
                            cancelatri6.setVisibility(View.GONE);
                            isText7 = 0;
                            //atributo7.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo7.setHint("Atributo 7 (Respuesta número)");
                            break;
                        case 8:
                            atri8.setVisibility(View.VISIBLE);
                            cancelatri8.setVisibility(View.VISIBLE);
                            cancelatri7.setVisibility(View.GONE);
                            isText8 = 0;
                            //atributo8.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo8.setHint("Atributo 8 (Respuesta número)");
                            break;
                        case 9:
                            atri9.setVisibility(View.VISIBLE);
                            cancelatri9.setVisibility(View.VISIBLE);
                            cancelatri8.setVisibility(View.GONE);
                            isText9 = 0;
                            //atributo9.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo9.setHint("Atributo 9 (Respuesta número)");
                            break;
                        case 10:
                            atri10.setVisibility(View.VISIBLE);
                            cancelatri10.setVisibility(View.VISIBLE);
                            cancelatri9.setVisibility(View.GONE);
                            isText10 = 0;
                            //atributo10.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo10.setHint("Atributo 10 (Respuesta número)");
                            break;
                        case 11:
                            atri11.setVisibility(View.VISIBLE);
                            cancelatri11.setVisibility(View.VISIBLE);
                            cancelatri10.setVisibility(View.GONE);
                            isText11 = 0;
                            //atributo11.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo11.setHint("Atributo 11 (Respuesta número)");
                            break;
                        case 12:
                            atri12.setVisibility(View.VISIBLE);
                            cancelatri12.setVisibility(View.VISIBLE);
                            cancelatri11.setVisibility(View.GONE);
                            isText12 = 0;
                            //atributo12.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo12.setHint("Atributo 12 (Respuesta número)");
                            break;
                        case 13:
                            atri13.setVisibility(View.VISIBLE);
                            cancelatri13.setVisibility(View.VISIBLE);
                            cancelatri12.setVisibility(View.GONE);
                            isText13 = 0;
                            //atributo13.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo13.setHint("Atributo 13 (Respuesta número)");
                            break;
                        case 14:
                            atri14.setVisibility(View.VISIBLE);
                            cancelatri14.setVisibility(View.VISIBLE);
                            cancelatri13.setVisibility(View.GONE);
                            isText14 = 0;
                            //atributo14.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo14.setHint("Atributo 14 (Respuesta número)");
                            break;
                        case 15:
                            atri15.setVisibility(View.VISIBLE);
                            cancelatri15.setVisibility(View.VISIBLE);
                            cancelatri14.setVisibility(View.GONE);
                            isText15 = 0;
                            //atributo15.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo15.setHint("Atributo 15 (Respuesta número)");
                            break;
                        case 16:
                            atri16.setVisibility(View.VISIBLE);
                            cancelatri16.setVisibility(View.VISIBLE);
                            cancelatri15.setVisibility(View.GONE);
                            isText16 = 0;
                            //atributo16.setInputType(InputType.TYPE_CLASS_NUMBER);
                            hintatributo16.setHint("Atributo 16 (Respuesta número)");
                            break;
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Has alcanzado el límite de atributos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelatri1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri1.setVisibility(View.GONE);
                textomarcadores.setVisibility(View.VISIBLE);
                atributo_added = 0;
            }
        });

        cancelatri2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri2.setVisibility(View.GONE);
                cancelatri1.setVisibility(View.VISIBLE);
                atributo_added = 1;
            }
        });

        cancelatri3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri3.setVisibility(View.GONE);
                cancelatri2.setVisibility(View.VISIBLE);
                atributo_added = 2;
            }
        });

        cancelatri4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri4.setVisibility(View.GONE);
                cancelatri3.setVisibility(View.VISIBLE);
                atributo_added = 3;
            }
        });

        cancelatri5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri5.setVisibility(View.GONE);
                cancelatri4.setVisibility(View.VISIBLE);
                atributo_added = 4;
            }
        });

        cancelatri6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri6.setVisibility(View.GONE);
                cancelatri5.setVisibility(View.VISIBLE);
                atributo_added = 5;
            }
        });

        cancelatri7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri7.setVisibility(View.GONE);
                cancelatri6.setVisibility(View.VISIBLE);
                atributo_added = 6;
            }
        });

        cancelatri8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri8.setVisibility(View.GONE);
                cancelatri7.setVisibility(View.VISIBLE);
                atributo_added = 7;
            }
        });

        cancelatri9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri9.setVisibility(View.GONE);
                cancelatri8.setVisibility(View.VISIBLE);
                atributo_added = 8;
            }
        });

        cancelatri10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri10.setVisibility(View.GONE);
                cancelatri9.setVisibility(View.VISIBLE);
                atributo_added = 9;
            }
        });

        cancelatri11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri11.setVisibility(View.GONE);
                cancelatri10.setVisibility(View.VISIBLE);
                atributo_added = 10;
            }
        });

        cancelatri12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri12.setVisibility(View.GONE);
                cancelatri11.setVisibility(View.VISIBLE);
                atributo_added = 11;
            }
        });

        cancelatri13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri13.setVisibility(View.GONE);
                cancelatri12.setVisibility(View.VISIBLE);
                atributo_added = 12;
            }
        });

        cancelatri14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri14.setVisibility(View.GONE);
                cancelatri13.setVisibility(View.VISIBLE);
                atributo_added = 13;
            }
        });

        cancelatri15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri15.setVisibility(View.GONE);
                cancelatri14.setVisibility(View.VISIBLE);
                atributo_added = 14;
            }
        });

        cancelatri16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atri16.setVisibility(View.GONE);
                cancelatri15.setVisibility(View.VISIBLE);
                atributo_added = 15;
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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_projects:
                Intent intent2 = new Intent(getApplicationContext(), ListadoProyectos.class);
                startActivity(intent2);
                break;
            case R.id.nav_crear:

                break;
            case R.id.nav_logout:
                SessionManager session = new SessionManager(getApplicationContext());
                session.setClear();
                Intent intent4 = new Intent(getApplicationContext(), Login.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

    public void crearProyecto(int numat1, int foto, EditText at1, EditText at2, EditText at3, EditText at4, EditText at5, EditText at6, EditText at7, EditText at8, EditText at9, EditText at10, EditText at11, EditText at12, EditText at13, EditText at14, EditText at15, EditText at16){

        String atrib1 = at1.getText().toString(); String atrib9 = at1.getText().toString();
        String atrib2 = at2.getText().toString(); String atrib10 = at1.getText().toString();
        String atrib3 = at3.getText().toString(); String atrib11 = at1.getText().toString();
        String atrib4 = at4.getText().toString(); String atrib12 = at1.getText().toString();
        String atrib5 = at5.getText().toString(); String atrib13 = at1.getText().toString();
        String atrib6 = at6.getText().toString(); String atrib14 = at1.getText().toString();
        String atrib7 = at7.getText().toString(); String atrib15 = at1.getText().toString();
        String atrib8 = at8.getText().toString(); String atrib16 = at1.getText().toString();


        // Marcador marcadorpersonalizado = new Marcador(numat1, foto, atrib1, atrib2, atrib3, atrib4, atrib5, atrib6, atrib7, atrib8, atrib9, atrib10, atrib11, atrib12, atrib13, atrib14, atrib15, atrib16);


    }

    public void addprojectRequest (View view) {
        final LinearLayout cargar = findViewById(R.id.cargando);

        atributo1 = findViewById(R.id.edit_atributo1); atributo9 = findViewById(R.id.edit_atributo9);
        atributo2 = findViewById(R.id.edit_atributo2); atributo10 = findViewById(R.id.edit_atributo10);
        atributo3 = findViewById(R.id.edit_atributo3); atributo11 = findViewById(R.id.edit_atributo11);
        atributo4 = findViewById(R.id.edit_atributo4); atributo12 = findViewById(R.id.edit_atributo12);
        atributo5 = findViewById(R.id.edit_atributo5); atributo13 = findViewById(R.id.edit_atributo13);
        atributo6 = findViewById(R.id.edit_atributo6); atributo14 = findViewById(R.id.edit_atributo14);
        atributo7 = findViewById(R.id.edit_atributo7); atributo15 = findViewById(R.id.edit_atributo15);
        atributo8 = findViewById(R.id.edit_atributo8); atributo16 = findViewById(R.id.edit_atributo16);

        titulo_proyecto = findViewById(R.id.project_titulo);
        descripcion_proyecto = findViewById(R.id.project_description);
        web_proyecto = findViewById(R.id.project_web);
        pass_proyecto = findViewById(R.id.project_password);
        cargar.setVisibility(View.VISIBLE);

        if(atributo_added != 0) {
            if (checkInputLogin()) {
                String url = getString(R.string.base_url) + "/crearProyecto.php";

                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response.toString());

                            JSONObject responseJSON = new JSONObject(response);

                            if ((int) responseJSON.get("result") == 1) {

                                cargar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Proyecto añadido correctamente", Toast.LENGTH_SHORT).show();
                                openMain();


                            } else {
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast;
                                CharSequence text;

                                text = "Error subiendo proyecto: " + responseJSON.get("message") + ".";
                                toast = Toast.makeText(getApplicationContext(), text, duration);
                                toast.show();

                                // Clean the text fields for new entries
                                //login_username_textview.setText("");
                                //login_password_textview.setText("");
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
                        login_params.put("titulo", titulo_proyecto.getText().toString());
                        login_params.put("descripcion", descripcion_proyecto.getText().toString());
                        login_params.put("web", web_proyecto.getText().toString());
                        login_params.put("privado", String.valueOf(esPrivado));
                        login_params.put("password", pass_proyecto.getText().toString());
                        login_params.put("numAtributos", String.valueOf(atributo_added));
                        login_params.put("foto", String.valueOf(photo_added));
                        login_params.put("atributo1", atributo1.getText().toString());
                        login_params.put("atributo2", atributo2.getText().toString());
                        login_params.put("atributo3", atributo3.getText().toString());
                        login_params.put("atributo4", atributo4.getText().toString());
                        login_params.put("atributo5", atributo5.getText().toString());
                        login_params.put("atributo6", atributo6.getText().toString());
                        login_params.put("atributo7", atributo7.getText().toString());
                        login_params.put("atributo8", atributo8.getText().toString());
                        login_params.put("atributo9", atributo9.getText().toString());
                        login_params.put("atributo10", atributo10.getText().toString());
                        login_params.put("atributo11", atributo11.getText().toString());
                        login_params.put("atributo12", atributo12.getText().toString());
                        login_params.put("atributo13", atributo13.getText().toString());
                        login_params.put("atributo14", atributo14.getText().toString());
                        login_params.put("atributo15", atributo15.getText().toString());
                        login_params.put("atributo16", atributo16.getText().toString());
                        login_params.put("isText1", String.valueOf(isText1));
                        login_params.put("isText2", String.valueOf(isText2));
                        login_params.put("isText3", String.valueOf(isText3));
                        login_params.put("isText4", String.valueOf(isText4));
                        login_params.put("isText5", String.valueOf(isText5));
                        login_params.put("isText6", String.valueOf(isText6));
                        login_params.put("isText7", String.valueOf(isText7));
                        login_params.put("isText8", String.valueOf(isText8));
                        login_params.put("isText9", String.valueOf(isText9));
                        login_params.put("isText10", String.valueOf(isText10));
                        login_params.put("isText11", String.valueOf(isText11));
                        login_params.put("isText12", String.valueOf(isText12));
                        login_params.put("isText13", String.valueOf(isText13));
                        login_params.put("isText14", String.valueOf(isText14));
                        login_params.put("isText15", String.valueOf(isText15));
                        login_params.put("isText16", String.valueOf(isText16));

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
            } else {
                // Do nothing, error has been shown in a toast and views clean
                cargar.setVisibility(View.GONE);
            }
        } else {
            cargar.setVisibility(View.GONE);
            int duration = Toast.LENGTH_SHORT;
            Toast toast;
            CharSequence text;
            text = "Se debe añadir al menos un atributo al marcador del proyecto.";
            toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();

        }

    }

    /** Open MAIN Activity */
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public Boolean checkCorrectInput(){

        if(esPrivado == 1){
            if(pass_proyecto.getText().equals("")){
                return false; //el proyecto no lleva contraseña asociada.
            }
        }


        return true;
    }

    private void showError (CharSequence error) {
        int duration = Toast.LENGTH_LONG;
        Toast toast;

        toast = Toast.makeText(getApplicationContext(), error, duration);
        toast.show();
    }

    private boolean checkLength( String text, String fieldName, int min, int max ) {
        if ( text.length() > max || text.length() < min ) {
            error_check = error_check + "El parámetro" + fieldName + " debe tener entre " +
                    min + " y " + max + " caracteres.\n";
            return false;
        } else {
            return true;
        }
    }

    private boolean checkRegexp(String text, Pattern regexp, String errorMessage) {
        if (!regexp.matcher(text).matches()) {
            error_check = error_check + errorMessage + "\n";
            return false;
        } else {
            return true;
        }
    }

    private boolean checkInputLogin () {

        error_check = "";
        boolean valid = true;

        // valid is evaluated in the second part to force the check function being called always, so all the errors are displayed at the same time (&& conditional evaluation)
        valid = checkLength( titulo_proyecto.getText().toString(), "Titulo", 1, 100 ) && valid;
        valid = checkLength( descripcion_proyecto.getText().toString(), "Descripcion", 1, 250 ) && valid;
//"/^[a-z]([0-9a-z_ ])+$/i"
        // In the regular expression for the username and password we do not use {3,16} (for instance),
        // to control the length through the regex, since it is most accurate to indicate the length error
        // separately, so it is not considered the length in the regex (it has been taken into account previously
        if(esPrivado == 1){

            valid = checkLength( pass_proyecto.getText().toString(), "Password", 3, 16 ) && valid;

        }

        if(atributo_added == 1){
            valid = checkLength( atributo1.getText().toString(), "Atributo1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 2){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 3){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo3.getText().toString(), "Atributo 3", 1, 100 ) && valid;
            valid = checkRegexp( atributo3.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 4){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo3.getText().toString(), "Atributo 3", 1, 100 ) && valid;
            valid = checkRegexp( atributo3.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo4.getText().toString(), "Atributo 4", 1, 100 ) && valid;
            valid = checkRegexp( atributo4.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 5){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo3.getText().toString(), "Atributo 3", 1, 100 ) && valid;
            valid = checkRegexp( atributo3.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo4.getText().toString(), "Atributo 4", 1, 100 ) && valid;
            valid = checkRegexp( atributo4.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo5.getText().toString(), "Atributo 5", 1, 100 ) && valid;
            valid = checkRegexp( atributo5.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 6){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo3.getText().toString(), "Atributo 3", 1, 100 ) && valid;
            valid = checkRegexp( atributo3.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo4.getText().toString(), "Atributo 4", 1, 100 ) && valid;
            valid = checkRegexp( atributo4.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo5.getText().toString(), "Atributo 5", 1, 100 ) && valid;
            valid = checkRegexp( atributo5.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo6.getText().toString(), "Atributo 6", 1, 100 ) && valid;
            valid = checkRegexp( atributo6.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 7){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo3.getText().toString(), "Atributo 3", 1, 100 ) && valid;
            valid = checkRegexp( atributo3.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo4.getText().toString(), "Atributo 4", 1, 100 ) && valid;
            valid = checkRegexp( atributo4.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo5.getText().toString(), "Atributo 5", 1, 100 ) && valid;
            valid = checkRegexp( atributo5.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo6.getText().toString(), "Atributo 6", 1, 100 ) && valid;
            valid = checkRegexp( atributo6.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo7.getText().toString(), "Atributo 7", 1, 100 ) && valid;
            valid = checkRegexp( atributo7.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 8){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo3.getText().toString(), "Atributo 3", 1, 100 ) && valid;
            valid = checkRegexp( atributo3.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo4.getText().toString(), "Atributo 4", 1, 100 ) && valid;
            valid = checkRegexp( atributo4.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo5.getText().toString(), "Atributo 5", 1, 100 ) && valid;
            valid = checkRegexp( atributo5.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo6.getText().toString(), "Atributo 6", 1, 100 ) && valid;
            valid = checkRegexp( atributo6.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo7.getText().toString(), "Atributo 7", 1, 100 ) && valid;
            valid = checkRegexp( atributo7.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo8.getText().toString(), "Atributo 8", 1, 100 ) && valid;
            valid = checkRegexp( atributo8.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 9){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo3.getText().toString(), "Atributo 3", 1, 100 ) && valid;
            valid = checkRegexp( atributo3.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo4.getText().toString(), "Atributo 4", 1, 100 ) && valid;
            valid = checkRegexp( atributo4.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo5.getText().toString(), "Atributo 5", 1, 100 ) && valid;
            valid = checkRegexp( atributo5.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo6.getText().toString(), "Atributo 6", 1, 100 ) && valid;
            valid = checkRegexp( atributo6.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo7.getText().toString(), "Atributo 7", 1, 100 ) && valid;
            valid = checkRegexp( atributo7.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo8.getText().toString(), "Atributo 8", 1, 100 ) && valid;
            valid = checkRegexp( atributo8.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo9.getText().toString(), "Atributo 9", 1, 100 ) && valid;
            valid = checkRegexp( atributo9.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 10){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo3.getText().toString(), "Atributo 3", 1, 100 ) && valid;
            valid = checkRegexp( atributo3.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo4.getText().toString(), "Atributo 4", 1, 100 ) && valid;
            valid = checkRegexp( atributo4.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo5.getText().toString(), "Atributo 5", 1, 100 ) && valid;
            valid = checkRegexp( atributo5.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo6.getText().toString(), "Atributo 6", 1, 100 ) && valid;
            valid = checkRegexp( atributo6.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo7.getText().toString(), "Atributo 7", 1, 100 ) && valid;
            valid = checkRegexp( atributo7.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo8.getText().toString(), "Atributo 8", 1, 100 ) && valid;
            valid = checkRegexp( atributo8.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo9.getText().toString(), "Atributo 9", 1, 100 ) && valid;
            valid = checkRegexp( atributo9.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo10.getText().toString(), "Atributo 10", 1, 100 ) && valid;
            valid = checkRegexp( atributo10.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 11){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo3.getText().toString(), "Atributo 3", 1, 100 ) && valid;
            valid = checkRegexp( atributo3.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo4.getText().toString(), "Atributo 4", 1, 100 ) && valid;
            valid = checkRegexp( atributo4.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo5.getText().toString(), "Atributo 5", 1, 100 ) && valid;
            valid = checkRegexp( atributo5.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo6.getText().toString(), "Atributo 6", 1, 100 ) && valid;
            valid = checkRegexp( atributo6.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo7.getText().toString(), "Atributo 7", 1, 100 ) && valid;
            valid = checkRegexp( atributo7.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo8.getText().toString(), "Atributo 8", 1, 100 ) && valid;
            valid = checkRegexp( atributo8.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo9.getText().toString(), "Atributo 9", 1, 100 ) && valid;
            valid = checkRegexp( atributo9.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo10.getText().toString(), "Atributo 10", 1, 100 ) && valid;
            valid = checkRegexp( atributo10.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo11.getText().toString(), "Atributo 11", 1, 100 ) && valid;
            valid = checkRegexp( atributo11.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 12){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo3.getText().toString(), "Atributo 3", 1, 100 ) && valid;
            valid = checkRegexp( atributo3.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo4.getText().toString(), "Atributo 4", 1, 100 ) && valid;
            valid = checkRegexp( atributo4.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo5.getText().toString(), "Atributo 5", 1, 100 ) && valid;
            valid = checkRegexp( atributo5.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo6.getText().toString(), "Atributo 6", 1, 100 ) && valid;
            valid = checkRegexp( atributo6.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo7.getText().toString(), "Atributo 7", 1, 100 ) && valid;
            valid = checkRegexp( atributo7.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo8.getText().toString(), "Atributo 8", 1, 100 ) && valid;
            valid = checkRegexp( atributo8.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo9.getText().toString(), "Atributo 9", 1, 100 ) && valid;
            valid = checkRegexp( atributo9.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo10.getText().toString(), "Atributo 10", 1, 100 ) && valid;
            valid = checkRegexp( atributo10.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo11.getText().toString(), "Atributo 11", 1, 100 ) && valid;
            valid = checkRegexp( atributo11.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo12.getText().toString(), "Atributo 12", 1, 100 ) && valid;
            valid = checkRegexp( atributo12.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 13){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo3.getText().toString(), "Atributo 3", 1, 100 ) && valid;
            valid = checkRegexp( atributo3.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo4.getText().toString(), "Atributo 4", 1, 100 ) && valid;
            valid = checkRegexp( atributo4.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo5.getText().toString(), "Atributo 5", 1, 100 ) && valid;
            valid = checkRegexp( atributo5.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo6.getText().toString(), "Atributo 6", 1, 100 ) && valid;
            valid = checkRegexp( atributo6.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo7.getText().toString(), "Atributo 7", 1, 100 ) && valid;
            valid = checkRegexp( atributo7.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo8.getText().toString(), "Atributo 8", 1, 100 ) && valid;
            valid = checkRegexp( atributo8.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo9.getText().toString(), "Atributo 9", 1, 100 ) && valid;
            valid = checkRegexp( atributo9.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo10.getText().toString(), "Atributo 10", 1, 100 ) && valid;
            valid = checkRegexp( atributo10.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo11.getText().toString(), "Atributo 11", 1, 100 ) && valid;
            valid = checkRegexp( atributo11.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo12.getText().toString(), "Atributo 12", 1, 100 ) && valid;
            valid = checkRegexp( atributo12.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo13.getText().toString(), "Atributo 13", 1, 100 ) && valid;
            valid = checkRegexp( atributo13.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 14){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo3.getText().toString(), "Atributo 3", 1, 100 ) && valid;
            valid = checkRegexp( atributo3.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo4.getText().toString(), "Atributo 4", 1, 100 ) && valid;
            valid = checkRegexp( atributo4.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo5.getText().toString(), "Atributo 5", 1, 100 ) && valid;
            valid = checkRegexp( atributo5.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo6.getText().toString(), "Atributo 6", 1, 100 ) && valid;
            valid = checkRegexp( atributo6.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo7.getText().toString(), "Atributo 7", 1, 100 ) && valid;
            valid = checkRegexp( atributo7.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo8.getText().toString(), "Atributo 8", 1, 100 ) && valid;
            valid = checkRegexp( atributo8.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo9.getText().toString(), "Atributo 9", 1, 100 ) && valid;
            valid = checkRegexp( atributo9.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo10.getText().toString(), "Atributo 10", 1, 100 ) && valid;
            valid = checkRegexp( atributo10.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo11.getText().toString(), "Atributo 11", 1, 100 ) && valid;
            valid = checkRegexp( atributo11.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo12.getText().toString(), "Atributo 12", 1, 100 ) && valid;
            valid = checkRegexp( atributo12.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo13.getText().toString(), "Atributo 13", 1, 100 ) && valid;
            valid = checkRegexp( atributo13.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo14.getText().toString(), "Atributo 14", 1, 100 ) && valid;
            valid = checkRegexp( atributo14.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 15){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo3.getText().toString(), "Atributo 3", 1, 100 ) && valid;
            valid = checkRegexp( atributo3.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo4.getText().toString(), "Atributo 4", 1, 100 ) && valid;
            valid = checkRegexp( atributo4.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo5.getText().toString(), "Atributo 5", 1, 100 ) && valid;
            valid = checkRegexp( atributo5.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo6.getText().toString(), "Atributo 6", 1, 100 ) && valid;
            valid = checkRegexp( atributo6.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo7.getText().toString(), "Atributo 7", 1, 100 ) && valid;
            valid = checkRegexp( atributo7.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo8.getText().toString(), "Atributo 8", 1, 100 ) && valid;
            valid = checkRegexp( atributo8.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo9.getText().toString(), "Atributo 9", 1, 100 ) && valid;
            valid = checkRegexp( atributo9.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo10.getText().toString(), "Atributo 10", 1, 100 ) && valid;
            valid = checkRegexp( atributo10.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo11.getText().toString(), "Atributo 11", 1, 100 ) && valid;
            valid = checkRegexp( atributo11.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo12.getText().toString(), "Atributo 12", 1, 100 ) && valid;
            valid = checkRegexp( atributo12.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo13.getText().toString(), "Atributo 13", 1, 100 ) && valid;
            valid = checkRegexp( atributo13.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo14.getText().toString(), "Atributo 14", 1, 100 ) && valid;
            valid = checkRegexp( atributo14.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo15.getText().toString(), "Atributo 15", 1, 100 ) && valid;
            valid = checkRegexp( atributo15.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;

        } else if(atributo_added == 16){
            valid = checkLength( atributo1.getText().toString(), "Atributo 1", 1, 100 ) && valid;
            valid = checkRegexp( atributo1.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo2.getText().toString(), "Atributo 2", 1, 100 ) && valid;
            valid = checkRegexp( atributo2.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo3.getText().toString(), "Atributo 3", 1, 100 ) && valid;
            valid = checkRegexp( atributo3.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo4.getText().toString(), "Atributo 4", 1, 100 ) && valid;
            valid = checkRegexp( atributo4.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo5.getText().toString(), "Atributo 5", 1, 100 ) && valid;
            valid = checkRegexp( atributo5.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo6.getText().toString(), "Atributo 6", 1, 100 ) && valid;
            valid = checkRegexp( atributo6.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo7.getText().toString(), "Atributo 7", 1, 100 ) && valid;
            valid = checkRegexp( atributo7.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo8.getText().toString(), "Atributo 8", 1, 100 ) && valid;
            valid = checkRegexp( atributo8.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo9.getText().toString(), "Atributo 9", 1, 100 ) && valid;
            valid = checkRegexp( atributo9.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo10.getText().toString(), "Atributo 10", 1, 100 ) && valid;
            valid = checkRegexp( atributo10.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo11.getText().toString(), "Atributo 11", 1, 100 ) && valid;
            valid = checkRegexp( atributo11.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo12.getText().toString(), "Atributo 12", 1, 100 ) && valid;
            valid = checkRegexp( atributo12.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo13.getText().toString(), "Atributo 13", 1, 100 ) && valid;
            valid = checkRegexp( atributo13.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo14.getText().toString(), "Atributo 14", 1, 100 ) && valid;
            valid = checkRegexp( atributo14.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo15.getText().toString(), "Atributo 15", 1, 100 ) && valid;
            valid = checkRegexp( atributo15.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
            valid = checkLength( atributo16.getText().toString(), "Atributo 16", 1, 100 ) && valid;
            valid = checkRegexp( atributo16.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Los atributos deben estar completados adecuadamente." ) && valid;
        }




        if (!error_check.equals("")){
            showError(error_check);
        }

        return valid;
    }

}

