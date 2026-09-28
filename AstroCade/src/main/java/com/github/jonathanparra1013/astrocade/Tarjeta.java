/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;

/**
 *
 * @author jonap
 */
public abstract class Tarjeta {
    protected String idTarjeta;
    protected int saldoJugadas;
    protected int saldoTickets;
    protected int limiteSaldo;

    public Tarjeta(String idTarjeta, int limiteSaldo) {
        this.idTarjeta = idTarjeta;
        this.limiteSaldo = limiteSaldo;
        this.saldoJugadas = 0;
        this.saldoTickets = 0;
    }

    public abstract double calcularDescuento(double costoBase);

    public void recargarSaldo(int cantidad) {
        if ((this.saldoJugadas + cantidad) <= limiteSaldo) {
            this.saldoJugadas += cantidad;
        } else {
            System.out.println("Error: Supera el límite de saldo de este tipo de tarjeta.");
        }
    }

    // Renombrado para que coincida con las máquinas
    public boolean descontarSaldo(double costo) {
        if (saldoJugadas >= costo) {
            saldoJugadas -= costo;
            return true;
        }
        return false;
    }

    // Renombrado para coincidir con las máquinas
    public void agregarTickets(int tickets) {
        this.saldoTickets += tickets;
    }

    // Nuevo método necesario para reclamar premios
    public void descontarTickets(int cantidad) {
        if (this.saldoTickets >= cantidad) {
            this.saldoTickets -= cantidad;
        }
    }

    // Getters adaptados a lo que piden las demás clases
    public int getSaldoJugadas() { return saldoJugadas; }
    public int getSaldoTickets() { return saldoTickets; }
    public int getSaldo() { return saldoJugadas; } // Usado en las máquinas
    public int getTickets() { return saldoTickets; } // Usado en las máquinas
    public String getNumeroTarjeta() { return idTarjeta; } // Usado en PremioPorTickets
}

