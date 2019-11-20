package com.example.peliculasencartelera;

import android.graphics.Bitmap;
import android.media.Image;

import java.net.URL;

public class Pelicula {
    String titulo;
    String descripcion;
    String url;
    Bitmap imagen;

    public Pelicula() {
    }

    public Pelicula(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;

        this.imagen = imagen;
    }
    public Pelicula(String titulo, String descripcion, String url, Bitmap imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUrl() {
        return url;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
