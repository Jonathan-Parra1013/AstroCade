/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;

/**
 *
 * @author jonap
 */
public abstract class Premios {

    private String nombre;
    private int stock;

    public Premios(String nombre, int stock) {

        this.nombre = nombre;

        if (stock >= 0) {
            this.stock = stock;
        } else {
            this.stock = 0;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public void aumentarStock(int cantidad) {

        if (cantidad > 0) {
            stock += cantidad;
        }
    }

    public boolean reducirStock() {

        if (stock > 0) {
            stock--;
            return true;
        }

        return false;
    }

    public boolean hayStock() {
        return stock > 0;
    }

    public abstract void mostrarPremio();

    @Override
    public String toString() {
        return "Premio: " + nombre +
               " | Stock: " + stock;
    }
}

