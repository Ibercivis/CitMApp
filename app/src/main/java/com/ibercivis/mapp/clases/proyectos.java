package com.ibercivis.mapp.clases;

public class proyectos {

    String titulo;
    String descripcion;
    int color;
    int aportaciones;
    int likes;
    boolean legusta;

    public proyectos(String titulo, String descripcion, int color, int aportaciones, int likes, boolean legusta) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.color = color;
        this.aportaciones = aportaciones;
        this.likes = likes;
        this.legusta = legusta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getAportaciones() {
        return aportaciones;
    }

    public void setAportaciones(int aportaciones) {
        this.aportaciones = aportaciones;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int aportaciones) {
        this.likes = likes;
    }

    public boolean isLegusta() {
        return legusta;
    }

    public void setLegusta(boolean legusta) {
        this.legusta = legusta;
    }

}
