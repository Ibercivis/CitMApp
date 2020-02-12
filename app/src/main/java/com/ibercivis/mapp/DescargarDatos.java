package com.ibercivis.mapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.ibercivis.mapp.clases.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DescargarDatos extends AppCompatActivity {

    int idProyecto;
    TextInputEditText edit_email;
    Button descargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descargar_datos);

        idProyecto = getIntent().getIntExtra("idProyecto", 1);

        edit_email = findViewById(R.id.edit_email_download);

        descargar = findViewById(R.id.btn_aceptar_download);

        descargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadRequest();
            }
        });




    }

    public void downloadRequest () {

        final LinearLayout cargar = findViewById(R.id.cargando);

        cargar.setVisibility(View.VISIBLE);

        if(edit_email.getText().toString().equals("")) {

            // Do nothing, error has been shown in a toast and views clean
            cargar.setVisibility(View.GONE);

            int duration = Toast.LENGTH_SHORT;
            Toast toast;
            CharSequence text;

            text = "Debes introducir una dirección de email válida.";
            toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();

        }
        else {


            // Url for the webservice
            String url = getString(R.string.base_url) + "/sendmarcadores.php";

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

                            text = "Se ha enviado un email a: " + edit_email.getText().toString();
                            toast = Toast.makeText(getApplicationContext(), text, duration);
                            toast.show();

                            cargar.setVisibility(View.GONE);

                            Intent intent = new Intent(getApplicationContext(), ListadoProyectos.class);
                            startActivity(intent);
                            finish();


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
                    login_params.put("token", String.valueOf(session.getToken()));
                    login_params.put("idProyecto", String.valueOf(idProyecto));
                    login_params.put("email", edit_email.getText().toString());

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

}
