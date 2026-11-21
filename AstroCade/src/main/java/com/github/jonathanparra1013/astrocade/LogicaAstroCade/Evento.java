/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade.LogicaAstroCade;

/**
 *
 * @author jonap
 */
public class Evento {
    private String nombre;
    private String tipo; // "BlackFriday", "Cumpleanos", "Normal"

    public Evento(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    // Métodos Getter
    public String getNombre() { 
        return nombre; 
    }
    
    public String getTipo() { 
        return tipo; 
    }

    /** Multiplicador del precio de las partidas según el evento (1.0 = precio normal). */
    public double getFactorPrecio() {
        if (tipo == null) {
            return 1.0;
        }
        switch (tipo) {
            case "BlackFriday":
                return 0.5;   // 50% de descuento
            case "Cumpleanos":
                return 0.8;   // 20% de descuento
            default:
                return 1.0;
        }
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
