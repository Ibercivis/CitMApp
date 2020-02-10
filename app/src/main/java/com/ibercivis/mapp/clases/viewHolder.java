package com.ibercivis.mapp.clases;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ibercivis.mapp.ListadoProyectos;
import com.ibercivis.mapp.Mapa;
import com.ibercivis.mapp.ProyectoPrivado;
import com.ibercivis.mapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.provider.Settings.System.getString;
import static androidx.core.content.ContextCompat.startActivity;

public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView titulo;
    TextView descripcion;
    TextView aportaciones;
    CardView card;
    Button btn;
    LottieAnimationView animation;
    TextView likes;
    List<proyectos> ListaProyectos;

    public viewHolder(@NonNull View itemView, List<proyectos> datos) {
        super(itemView);

        titulo = itemView.findViewById(R.id.titulo);
        descripcion = itemView.findViewById(R.id.descripcion);
        aportaciones = itemView.findViewById(R.id.numaportaciones);
        btn = itemView.findViewById(R.id.btn);
        animation = itemView.findViewById(R.id.animation_view);
        likes = itemView.findViewById(R.id.numeroLikes);
        ListaProyectos = datos;
        card = itemView.findViewById(R.id.carta);

        btn.setOnClickListener(this);
        animation.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        proyectos proyecto = ListaProyectos.get(position);


        if (v.getId() == btn.getId()) {
            if(proyecto.privado == 0){


            int id = proyecto.getIdProyecto();
            Intent intent = new Intent(btn.getContext(), Mapa.class);
            intent.putExtra("idProyecto", id);
            startActivity(btn.getContext(), intent, null);

            }
            if(proyecto.privado == 1){

            int id = proyecto.getIdProyecto();
            String pass = proyecto.getPassword();
            Intent intent = new Intent(btn.getContext(), ProyectoPrivado.class);
            intent.putExtra("password_proyecto", pass);
            intent.putExtra("idProyecto", id);
            startActivity(btn.getContext(), intent, null);

            }
        }
        if (v.getId() == animation.getId()){
            if(proyecto.isLegusta() == 0) {
                votarProyecto(proyecto, v);
                proyecto.likes = (proyecto.likes + 1);
                likes.setText(Integer.toString(proyecto.likes));

                animation.playAnimation();
                proyecto.setLegusta(1);
            }
            else if(proyecto.isLegusta() == 1){
                votarProyecto(proyecto, v);
                proyecto.likes = (proyecto.likes - 1);
                likes.setText(Integer.toString(proyecto.likes));

                animation.setProgress(0.2f);
                animation.pauseAnimation();
                proyecto.setLegusta(0);
            }
        }

    }

    public void votarProyecto (final proyectos proyecto, final View v) {




        // Input data ok, so go with the request

        // Url for the webservice
        String url = "https://citmapp.ibercivis.es/votar.php";

        RequestQueue queue = Volley.newRequestQueue(v.getContext());
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println(response.toString());

                    JSONObject responseJSON = new JSONObject(response);

                    if ((int) responseJSON.get("result") == 1) {

                        System.out.println("Votación correcta");


                    } else {

                        System.out.println("Votación incorrecta. Algo ha salido mal.");

                        // Clean the text fields for new entries


                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                int duration = Toast.LENGTH_SHORT;
                Toast toast;
                CharSequence text;
                text = "Algo ha fallado, vuelva a intentarlo.";
                toast = Toast.makeText(v.getContext(), text, duration);
                toast.show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> login_params = new HashMap<String, String>();
                SessionManager session = new SessionManager(v.getContext());
                login_params.put("idUser", String.valueOf(session.getIdUser()));
                login_params.put("token", session.getToken());
                login_params.put("idProyecto", String.valueOf(proyecto.getIdProyecto()));


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


