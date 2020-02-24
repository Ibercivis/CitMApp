package com.ibercivis.mapp.clases;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibercivis.mapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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
    public void onBindViewHolder(@NonNull final viewHolder holder, int position) {
        holder.titulo.setText(ListaObjeto.get(position).getTitulo());
        holder.descripcion.setText(ListaObjeto.get(position).getDescripcion());
        holder.aportaciones.setText(Integer.toString(ListaObjeto.get(position).getAportaciones()));
        holder.likes.setText(Integer.toString(ListaObjeto.get(position).getLikes()));

        if (ListaObjeto.get(position).getWeb() != ""){

            holder.web.setText(ListaObjeto.get(position).getWeb());
            holder.web.setMovementMethod(LinkMovementMethod.getInstance());

        } else {
            holder.web.setVisibility(View.GONE);
        }


        if(ListaObjeto.get(position).hasLogo == 1){

            String urlfoto = ListaObjeto.get(position).logo;
            // Bitmap foto = new DownloadFilesTask().execute(urlfoto);
            Picasso.with(holder.animation.getContext()).load(urlfoto).into(holder.logo);
        } else {
            holder.logo.setVisibility(View.INVISIBLE);
        }


        if(ListaObjeto.get(position).legusta == 1) {
            holder.animation.setProgress(1);
        } else {
            holder.animation.setProgress(0.2f);
        }

    }

    @Override
    public int getItemCount() {
        return ListaObjeto.size();
    }

    public void setFilter(ArrayList<proyectos> listaFiltrada){

        this.ListaObjeto = new ArrayList<>();
        this.ListaObjeto.addAll(listaFiltrada);
        notifyDataSetChanged();

    }

}
