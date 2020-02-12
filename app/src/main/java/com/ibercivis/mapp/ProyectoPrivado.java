package com.ibercivis.mapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import static androidx.core.content.ContextCompat.startActivity;

public class ProyectoPrivado extends AppCompatActivity {

    String passwordProyecto;
    Button button_aceptar;
    TextInputEditText edit_contraseña;
    int idProyecto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyecto_privado);


        passwordProyecto = getIntent().getStringExtra("password_proyecto");
        idProyecto = getIntent().getIntExtra("idProyecto", 1);

        button_aceptar = findViewById(R.id.btn_aceptarpassword);
        edit_contraseña = findViewById(R.id.password_edittext_privado);

        button_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String pass_escrita = String.valueOf(edit_contraseña.getText());

                if(pass_escrita.equals(passwordProyecto)){
                    Intent intent = new Intent(ProyectoPrivado.this, Mapa.class);
                    intent.putExtra("idProyecto", idProyecto);
                    startActivity(intent, null);
                    finish();
                } else {

                    edit_contraseña.setText("");
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast;
                    CharSequence text;


                    text = "Contraseña incorrecta";
                    toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();

                }
            }
        });


    }
}
