package com.github.jonathanparra1013.astrocade;

import java.util.Date;

public class AstroCadeFacade {
    public void recargarTarjeta(Administrador administrador,
                                Tarjeta tarjeta,
                                int cantidad) {
        if (administrador == null || tarjeta == null) {
            throw new IllegalArgumentException("Administrador y tarjeta son obligatorios.");
        }
        administrador.recargarTarjeta(tarjeta, cantidad);
    }

    public GestorClientes.ClienteConTarjeta registrarCliente(
            String id, String nombre, String correo,
            int saldoInicial, TarjetaFactory.TipoTarjeta tipoTarjeta) {
        Cliente cliente = new Cliente(id, nombre, correo, new Date());
        Tarjeta tarjeta = TarjetaFactory.crear(tipoTarjeta, id);
        tarjeta.recargarSaldo(saldoInicial);
        GestorClientes.agregarCliente(cliente, tarjeta);
        return new GestorClientes.ClienteConTarjeta(cliente, tarjeta);
    }

    public boolean redimirPremio(Cliente cliente, Tarjeta tarjeta,
                                 String nombrePremio, int costoTickets) {
        if (tarjeta == null || nombrePremio == null || costoTickets <= 0) {
            throw new IllegalArgumentException(
                    "Tarjeta, premio y costo positivo son obligatorios.");
        }
        if (tarjeta.getSaldoTickets() < costoTickets) {
            return false;
        }

        tarjeta.descontarTickets(costoTickets);
        if (cliente != null) {
            cliente.agregarPremio(nombrePremio);
        }
        return true;
    }

    public void ponerMaquinaEnMantenimiento(Administrador administrador,
                                            Maquina maquina) {
        if (administrador == null || maquina == null) {
            throw new IllegalArgumentException("Administrador y máquina son obligatorios.");
        }
        administrador.ponerMaquinaEnMantenimiento(maquina);
    }

    public void cambiarEstadoMaquina(Administrador administrador,
                                     Maquina maquina,
                                     String estado) {
        if (administrador == null || maquina == null) {
            throw new IllegalArgumentException("Administrador y máquina son obligatorios.");
        }
        administrador.cambiarEstadoMaquina(maquina, estado);
    }

    public void jugar(Maquina maquina, Tarjeta tarjeta) {
        if (maquina == null || tarjeta == null) {
            throw new IllegalArgumentException("Máquina y tarjeta son obligatorias.");
        }
        maquina.jugar(tarjeta);
    }
}
