package com.ibercivis.mapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    ImageView logo;
    TextView logotext, desc;
    TextInputLayout usertext, passtext;
    Button btnentrar, btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        /*-----Hooks-----*/

        btnentrar = findViewById(R.id.entrar_btn);
        btnsignup = findViewById(R.id.signup_btn);
        logo = findViewById(R.id.logologin);
        logotext = findViewById(R.id.bienvenidotxt);
        desc = findViewById(R.id.accedetxt);
        usertext = findViewById(R.id.username_edit);
        passtext = findViewById(R.id.password_edit);


        /*-----Button Functions-----*/

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_sign = new Intent(Login.this, SignUp.class);

                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View,String>(logo, "logo_image");
                pairs[1] = new Pair<View,String>(logotext, "logo_text");
                pairs[2] = new Pair<View,String>(desc, "logo_desc");
                pairs[3] = new Pair<View,String>(usertext, "username_tran");
                pairs[4] = new Pair<View,String>(passtext, "password_tran");
                pairs[5] = new Pair<View,String>(btnentrar, "btn1_tran");
                pairs[6] = new Pair<View,String>(btnsignup, "btn2_tran");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(intent_sign, options.toBundle());
            }
        });

        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_main = new Intent(Login.this, MainActivity.class);
                startActivity(intent_main);
                finish();
            }
        });

    }
}
