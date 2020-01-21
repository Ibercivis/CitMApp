package com.ibercivis.mapp.clases;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.ibercivis.mapp.ListadoProyectos;
import com.ibercivis.mapp.Mapa;
import com.ibercivis.mapp.R;

import java.util.List;

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
            Intent intent = new Intent(btn.getContext(), Mapa.class);
            startActivity(btn.getContext(), intent, null);
        }
        if (v.getId() == animation.getId()){
            if(proyecto.legusta == false) {
                proyecto.likes = (proyecto.likes + 1);
                likes.setText(Integer.toString(proyecto.likes));

                animation.playAnimation();
                proyecto.setLegusta(true);
            }
            else if(proyecto.legusta == true){
                proyecto.likes = (proyecto.likes - 1);
                likes.setText(Integer.toString(proyecto.likes));

                animation.setProgress(0.2f);
                animation.pauseAnimation();

                proyecto.setLegusta(false);
            }
        }

    }
}
