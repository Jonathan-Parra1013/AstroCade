/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;

/**
 *
 * @author jonap
 */
public class Administrador extends Usuario {
    private String password;

    public Administrador(String id, String nombre, String email, String password) {
        super(id, nombre, email);
        this.password = password;
    }

    public boolean validarPassword(String passIngresada) {
        return this.password.equals(passIngresada);
    }
    
    public void ponerMaquinaEnMantenimiento(Maquina maquina) {
        maquina.setEstado("Mantenimiento");
        System.out.println("Maquina " + maquina.getNombre() + " puesta en mantenimiento.");
    }

    public void recargarTarjeta(Tarjeta tarjeta, int cantidad) {
        tarjeta.recargarSaldo(cantidad);
        System.out.println("Tarjeta recargada con exitos. Nuevo saldo: " + tarjeta.getSaldoJugadas());
    }
    
    public void cambiarEstadoMaquina(Maquina maquina, String nuevoEstado) {
        maquina.setEstado(nuevoEstado);
        System.out.println("La maquina " + maquina.getNombre() + " ahora esta: " + nuevoEstado);
    }

    public Object getNombre() {
        return super.nombre;
    }

    public Object getEmail() {
       return super.email;
    }
}