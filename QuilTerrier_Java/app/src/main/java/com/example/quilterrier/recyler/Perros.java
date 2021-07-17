package com.example.quilterrier.recyler;

public class Perros {
    private String nombre;
    private String tipo; // Adopción o Perdido
    //private String descripcion;
    private String ubicacion;
    private Boolean tieneChip;


    public Perros(String nombre, String tipo, String ubicacion, Boolean tieneChip) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.tieneChip = tieneChip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Boolean getTieneChip() {
        return tieneChip;
    }

    public void setTieneChip(Boolean tieneChip) {
        this.tieneChip = tieneChip;
    }
}
