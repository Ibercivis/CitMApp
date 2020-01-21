package com.ibercivis.mapp.clases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibercivis.mapp.R;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<viewHolder> {

    List<proyectos> ListaObjeto;

    public Adaptador(List<proyectos> listaObjeto) {
        ListaObjeto = listaObjeto;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.proyectos_card, parent, false);
        return new viewHolder(vista, ListaObjeto);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.titulo.setText(ListaObjeto.get(position).getTitulo());
        holder.descripcion.setText(ListaObjeto.get(position).getDescripcion());
        holder.aportaciones.setText(Integer.toString(ListaObjeto.get(position).getAportaciones()));
        holder.likes.setText(Integer.toString(ListaObjeto.get(position).getLikes()));


        if(ListaObjeto.get(position).isLegusta() == true) {
            holder.animation.setProgress(1);
        }



    }

    @Override
    public int getItemCount() {
        return ListaObjeto.size();
    }
}
