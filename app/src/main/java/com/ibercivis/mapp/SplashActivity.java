package com.ibercivis.mapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibercivis.mapp.clases.SessionManager;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    // Variables
    Animation topAnim, bottomAnim;
    ImageView logo;
    TextView titulo, slogan;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_splash);

        /* AQUÍ SE HACEN LAS PETICIONES DE PERMISOS QUE VA A NECESITAR LA APP: UBICACIÓN, ACCESO A ESCRITURA PARA LOS TILES DE LOS MAPAS, ACCESO A LA CÁMARA*/

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

        //Animations

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks

        logo = findViewById(R.id.logosplash);
        titulo = findViewById(R.id.titulosplash);
        slogan = findViewById(R.id.slogansplash);

        logo.setAnimation(topAnim);
        titulo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        // AQUÍ SE VERIFICA SI EL USUARIO YA SE HABÍA LOGEADO ANTERIORMENTE, SI ES ASÍ, LE LLEVA DIRECTAMENTE A LA HOME.

        verificarLog();


    }

    public void verificarLog(){

        SessionManager session = new SessionManager(getApplicationContext());
        if(session.isLoggedIn()==true){
            esperarYMain(SPLASH_SCREEN); // SI YA ESTABA LOGEADO SE LE LLEVA A LA HOME.
        } else if (session.isLoggedIn()==false){
            esperarYLogin(SPLASH_SCREEN); // SI NO ESTABA LOGEADO SE LE LLEVA A LA PANTALLA DE LOGIN.
        }

    }

    public void esperarYLogin(int milisegundos) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, Login.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(logo,"logo_image");
                pairs[1] = new Pair<View,String>(titulo,"logo_text");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this, pairs);
                startActivity(intent, options.toBundle());
                finish();
            }
        }, SPLASH_SCREEN);
    }

    public void esperarYMain(int milisegundos) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        }, milisegundos);
    }


}
