package com.ibercivis.mapp.clases;

public class proyectos {

    String titulo;
    String descripcion;
    int color;
    int aportaciones;
    int likes;
    int legusta;
    int idProyecto;
    int privado;
    String password;

    public proyectos(int idProyecto, String titulo, String descripcion, int color, int aportaciones, int likes, int legusta, int privado, String password) {
        this.idProyecto = idProyecto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.color = color;
        this.aportaciones = aportaciones;
        this.likes = likes;
        this.legusta = legusta;
        this.privado = privado;
        this.password = password;
    }

    public int getLegusta() {
        return legusta;
    }

    public int getPrivado() {
        return privado;
    }

    public void setPrivado(int privado) {
        this.privado = privado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int isLegusta() {
        return legusta;
    }

    public void setLegusta(int legusta) {
        this.legusta = legusta;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }
}
