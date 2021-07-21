package com.example.quilterrier.recyler

class Perros {
    var nombre: String
    var tipo // Adopción o Perdido
            : String? = null

    //private String descripcion;
    var ubicacion: String? = null
    var tieneChip: Boolean? = null

    constructor(nombre: String) {
        this.nombre = nombre
    }

    constructor(nombre: String, tipo: String?, ubicacion: String?, tieneChip: Boolean?) {
        this.nombre = nombre
        this.tipo = tipo
        this.ubicacion = ubicacion
        this.tieneChip = tieneChip
    }
}