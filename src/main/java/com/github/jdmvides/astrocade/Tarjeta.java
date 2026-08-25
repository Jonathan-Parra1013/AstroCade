/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jdmvides.astrocade;

/**
 * Representa la tarjeta física/digital dentro de AstroCade.
 * Maneja de forma independiente el saldo en dinero y los tickets ganados.
 */
import java.util.UUID; // se impporta el generador de identificadores únicos

public class Tarjeta {

    private String numeroTarjeta;
    private double saldo;
    private int tickets;

    // El propio sistema lo crea de forma privada y segura
    public Tarjeta(double saldoInicial) {
        // Genera un ID único con prefijo del parque (ej: ASTRO-8F3A)
        this.numeroTarjeta = "ASTRO-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        // Validar saldo inicial
        if (saldoInicial >= 0) {
            this.saldo = saldoInicial;
        } else {
            System.out.println(" ALERTA: Saldo inicial inválido. Se asigno $0.0 por defecto.");
            this.saldo = 0.0;
        }

        this.tickets = 0;
    }

    // NINGÚN usuario 
    // pueda alterar el número de la tarjeta una vez creada (Seguridad)
    public String getNumeroTarjeta() {
        return this.numeroTarjeta;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public int getTickets() {
        return this.tickets;
    }

    public void recargar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            System.out.println(" Recarga exitosa de $" + monto + " a la tarjeta [" + this.numeroTarjeta + 
                               "]. Nuevo saldo: $" + this.saldo);
        } else {
            System.out.println(" ERROR: El monto a recargar debe ser mayor a cero.");
        }
    }

    public boolean descontarSaldo(double monto) {
        if (monto <= 0) return false;

        if (this.saldo >= monto) {
            this.saldo -= monto;
            return true;
        } else {
            System.out.println(" SALDO INSUFICIENTE en la tarjeta [" + this.numeroTarjeta + 
                               "] (Saldo: $" + this.saldo + ", Requerido: $" + monto + ")");
            return false;
        }
    }

    public void agregarTickets(int cantidad) {
        if (cantidad > 0) {
            this.tickets += cantidad;
            System.out.println(" Se acredito(aron) " + cantidad + " ticket(s) a la tarjeta [" + this.numeroTarjeta + "].");
        }
    }

    public boolean descontarTickets(int cantidad) {
        if (cantidad <= 0) return false;

        if (this.tickets >= cantidad) {
            this.tickets -= cantidad;
            return true;
        } else {
            System.out.println(" TICKETS INSUFICIENTES en tarjeta [" + this.numeroTarjeta + "]");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Tarjeta ID: " + this.numeroTarjeta + " | Saldo: $" + this.saldo + " | Tickets: " + this.tickets;
    }
}