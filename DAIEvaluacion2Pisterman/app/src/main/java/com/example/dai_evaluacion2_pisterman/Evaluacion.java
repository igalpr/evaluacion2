package com.example.dai_evaluacion2_pisterman;

public class Evaluacion {
    private String Titulo;
    private String URlImagen;

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getURlImagen() {
        return URlImagen;
    }

    public void setURlImagen(String URlImagen) {
        this.URlImagen = URlImagen;
    }

    public Evaluacion(String titulo, String URlImagen) {
        Titulo = titulo;
        this.URlImagen = URlImagen;
    }
}
