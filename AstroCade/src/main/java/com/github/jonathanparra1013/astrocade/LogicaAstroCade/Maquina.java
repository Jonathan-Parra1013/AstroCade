/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;

/**
 *
 * @author jonap
 */

public abstract class Maquina {

    private String nombre;
    private double precioPartida;
    private String estado; // Nuevo atributo

    public Maquina(String nombre, double precioPartida) {
        this.nombre = nombre;
        this.precioPartida = precioPartida;
        this.estado = "Activa"; // Estado por defecto
    }

    public abstract void jugar(Tarjeta tarjeta);

    public String getNombre() {
        return nombre;
    }

    public double getPrecioPartida() {
        return precioPartida;
    }

    // Nuevos métodos para el Administrador
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void mostrarInformacionMaquina() {
        System.out.println("Maquina: " + nombre + " | Estado: " + estado);
        System.out.println("Precio por partida: $" + precioPartida);
    }
}