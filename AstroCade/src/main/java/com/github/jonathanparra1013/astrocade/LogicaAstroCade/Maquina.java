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
    private EstadoMaquina estado;

    public Maquina(String nombre, double precioPartida) {
        this.nombre = nombre;
        this.precioPartida = precioPartida;
        this.estado = new EstadoActiva();
    }

    public final void jugar(Tarjeta tarjeta) {
        if (tarjeta == null) {
            System.out.println("Tarjeta invalida.");
            return;
        }
        if (!estado.puedeJugar()) {
            System.out.println("La maquina " + nombre
                    + " no puede jugar. Estado: " + estado.getNombre());
            return;
        }
        jugarPartida(tarjeta);
    }

    protected abstract void jugarPartida(Tarjeta tarjeta);

    public String getNombre() {
        return nombre;
    }

    public double getPrecioPartida() {
        return precioPartida;
    }

    // Nuevos métodos para el Administrador
    public String getEstado() {
        return estado.getNombre();
    }

    public void setEstado(String estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado de la máquina es obligatorio.");
        }
        switch (estado.trim().toLowerCase(java.util.Locale.ROOT)) {
            case "activa":
                setEstado(new EstadoActiva());
                break;
            case "mantenimiento":
                setEstado(new EstadoMantenimiento());
                break;
            case "fuera de servicio":
            case "fueradeservicio":
                setEstado(new EstadoFueraDeServicio());
                break;
            default:
                throw new IllegalArgumentException("Estado de máquina no reconocido: " + estado);
        }
    }

    public void setEstado(EstadoMaquina estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado de la máquina es obligatorio.");
        }
        this.estado = estado;
    }

    public void ponerEnMantenimiento() {
        setEstado(new EstadoMantenimiento());
    }

    public void ponerFueraDeServicio() {
        setEstado(new EstadoFueraDeServicio());
    }

    public void activar() {
        setEstado(new EstadoActiva());
    }

    public double calcularCostoPartida(Tarjeta tarjeta) {
        if (tarjeta == null) {
            throw new IllegalArgumentException("La tarjeta es obligatoria.");
        }
        return tarjeta.calcularDescuento(precioPartida);
    }

    protected boolean cobrarPartida(Tarjeta tarjeta) {
        return tarjeta.descontarSaldo(calcularCostoPartida(tarjeta));
    }

    public void mostrarInformacionMaquina() {
        System.out.println("Maquina: " + nombre + " | Estado: " + estado.getNombre());
        System.out.println("Precio por partida: $" + precioPartida);
    }
}