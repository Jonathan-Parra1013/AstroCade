package com.github.jonathanparra1013.astrocade.Tarjetas;


public abstract class Tarjeta {

    private final String idTarjeta;
    private int saldoJugadas;
    private int saldoTickets;
    private final int limiteSaldo;
    private final EstrategiaDescuento estrategiaDescuento;

    protected Tarjeta(String idTarjeta, int limiteSaldo, EstrategiaDescuento estrategiaDescuento) {
        if (idTarjeta == null || idTarjeta.isBlank()) {
            throw new IllegalArgumentException("El id de la tarjeta es obligatorio.");
        }
        if (limiteSaldo <= 0) {
            throw new IllegalArgumentException("El límite de saldo debe ser positivo.");
        }
        if (estrategiaDescuento == null) {
            throw new IllegalArgumentException("La estrategia de descuento es obligatoria.");
        }
        this.idTarjeta = idTarjeta.trim();
        this.limiteSaldo = limiteSaldo;
        this.estrategiaDescuento = estrategiaDescuento;
    }

    // Nombre legible del tipo de tarjeta (cada subclase lo sobrescribe). */
    public abstract String getTipo();

    public double calcularDescuento(double costoBase) {
        return estrategiaDescuento.calcular(costoBase);
    }

    public boolean puedeRecargar(int cantidad) {
        return cantidad > 0 && (saldoJugadas + cantidad) <= limiteSaldo;
    }

    public void recargarSaldo(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La recarga no puede ser negativa.");
        }
        if (saldoJugadas + cantidad > limiteSaldo) {
            throw new IllegalArgumentException("Supera el límite de saldo de este tipo de tarjeta.");
        }
        this.saldoJugadas += cantidad;
    }

    public boolean descontarSaldo(double costo) {
        if (costo >= 0 && saldoJugadas >= costo) {
            saldoJugadas -= (int) Math.ceil(costo);
            return true;
        }
        return false;
    }

    public void agregarTickets(int tickets) {
        if (tickets < 0) {
            throw new IllegalArgumentException("Los tickets a agregar no pueden ser negativos.");
        }
        this.saldoTickets += tickets;
    }

    public void descontarTickets(int cantidad) {
        if (cantidad < 0 || saldoTickets < cantidad) {
            throw new IllegalArgumentException("Tickets insuficientes.");
        }
        this.saldoTickets -= cantidad;
    }

    public int getSaldoJugadas() { return saldoJugadas; }
    public int getSaldoTickets() { return saldoTickets; }
    public int getSaldo() { return saldoJugadas; }
    public int getTickets() { return saldoTickets; }
    public int getLimiteSaldo() { return limiteSaldo; }
    public String getNumeroTarjeta() { return idTarjeta; }

    public void setSaldoTickets(int nuevoSaldoTickets) {
        if (nuevoSaldoTickets < 0) {
            throw new IllegalArgumentException("El saldo de tickets no puede ser negativo.");
        }
        this.saldoTickets = nuevoSaldoTickets;
    }
}
