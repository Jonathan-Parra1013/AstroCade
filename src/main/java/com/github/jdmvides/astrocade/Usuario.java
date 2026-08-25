/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jdmvides.astrocade;

/**
 * Representa al jugador o cliente en AstroCade.
 * Puede poseer o no una Tarjeta (permitiendo prestársela o transferírsela a otros usuarios).
 */
public class Usuario {

    // Atributos de instancia
    private String nombre;
    private String cedula;
    private Tarjeta tarjeta; // Puede ser null si el usuario no tiene tarjeta en ese momento

    // Constructor 1: Usuario que se crea sin tarjeta inicialmente
    public Usuario(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.tarjeta = null; // Inicia sin tarjeta asignada
    }

    // Constructor 2 (Sobrecarga): Usuario que se crea comprando/recibiendo una tarjeta de una vez
    public Usuario(String nombre, String cedula, Tarjeta tarjeta) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.tarjeta = tarjeta;
    }

    // =======================================================
    // MÉTODOS DE COMPORTAMIENTO (Lógica de Portabilidad)
    // =======================================================

    /**
     * Permite al usuario prestar su tarjeta a otro amigo (Usuario).
     * La referencia del objeto Tarjeta pasa al amigo y el usuario actual se queda sin tarjeta (null).
     */
    public void prestarTarjetaA(Usuario amigo) {
        if (amigo == null) {
            System.out.println(" ERROR: El usuario destino no existe.");
            return;
        }

        if (this.tarjeta != null) {
            System.out.println(this.nombre + " le prestó la tarjeta #" + this.tarjeta.getNumeroTarjeta() + 
                               " a " + amigo.getNombre() + ".");
            
            // Se le asigna el objeto Tarjeta al amigo
            amigo.setTarjeta(this.tarjeta);
            
            // El usuario actual libera la tarjeta
            this.tarjeta = null;
        } else {
            System.out.println(this.nombre + " no tiene ninguna tarjeta para prestar.");
        }
    }

    /**
     * Muestra en pantalla la tarjeta actual y sus datos de saldo y tickets.
     */
    public void mostrarInfo() {
        System.out.println("\n--- PERFIL DE USUARIO ---");
        System.out.println("Cliente: " + this.nombre + " (C.C: " + this.cedula + ")");
        if (this.tarjeta != null) {
            System.out.println("Estado de Tarjeta en Posesión: " + this.tarjeta.toString());
        } else {
            System.out.println("Estado de Tarjeta:  No tiene tarjeta asignada actualmente.");
        }
    }

    // =======================================================
    // GETTERS Y SETTERS
    // =======================================================

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        }
    }

    public String getCedula() {
        return this.cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Tarjeta getTarjeta() {
        return this.tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }
}