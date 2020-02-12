package com.ibercivis.mapp.clases;

import android.graphics.Bitmap;

public class proyectos {

    String titulo;
    String descripcion;
    int idUser;
    int color;
    int aportaciones;
    int likes;
    int legusta;
    int idProyecto;
    int privado;
    String password;
    int hasLogo;
    String logo;
    String web;

    public proyectos(int idProyecto, int idUser, String titulo, String descripcion, String web, int color, int aportaciones, int likes, int legusta, int privado, String password, int hasLogo) {
        this.idProyecto = idProyecto;
        this.titulo = titulo;
        this.idUser = idUser;
        this.descripcion = descripcion;
        this.color = color;
        this.aportaciones = aportaciones;
        this.likes = likes;
        this.legusta = legusta;
        this.privado = privado;
        this.password = password;
        this.hasLogo = hasLogo;
        this.web = web;
    }

    public proyectos(int idProyecto, int idUser, String titulo, String descripcion, String web, int color, int aportaciones, int likes, int legusta, int privado, String password, int hasLogo, String logo) {
        this.idProyecto = idProyecto;
        this.idUser = idUser;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.color = color;
        this.aportaciones = aportaciones;
        this.likes = likes;
        this.legusta = legusta;
        this.privado = privado;
        this.password = password;
        this.hasLogo = hasLogo;
        this.logo = logo;
        this.web = web;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getHasLogo() {
        return hasLogo;
    }

    public void setHasLogo(int hasLogo) {
        this.hasLogo = hasLogo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
