/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;

/**
 *
 * @author nicolas
 */
public abstract class Maquina {

    private String nombre;
    private double precioPartida;

    public Maquina(String nombre, double precioPartida) {
        this.nombre = nombre;
        this.precioPartida = precioPartida;
    }

    public abstract void jugar(Tarjeta tarjeta);

    public String getNombre() {
        return nombre;
    }

    public double getPrecioPartida() {
        return precioPartida;
    }

    public void mostrarInformacionMaquina() {
        System.out.println("Maquina: " + nombre);
        System.out.println("Precio por partida: $" + precioPartida);
    }
}
