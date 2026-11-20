/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorClientes {
    private static List<ClienteConTarjeta> listaGlobal = new ArrayList<>();


    static {
        Cliente c1 = new Cliente("CLI-01", "Juanito Perez", "juanito@email.com", new Date());
        Tarjeta t1 = new Diamond("TARJ-001");
        t1.recargarSaldo(100000);
        listaGlobal.add(new ClienteConTarjeta(c1, t1));
    }

    public static void agregarCliente(Cliente cliente, Tarjeta tarjeta) {
        listaGlobal.add(new ClienteConTarjeta(cliente, tarjeta));
    }

    public static List<ClienteConTarjeta> getClientes() {
        return listaGlobal;
    }

    public static class ClienteConTarjeta {
        private Cliente cliente;
        private Tarjeta tarjeta;

        public ClienteConTarjeta(Cliente cliente, Tarjeta tarjeta) {
            this.cliente = cliente;
            this.tarjeta = tarjeta;
        }

        public Cliente getCliente() { return cliente; }
        public Tarjeta getTarjeta() { return tarjeta; }

        @Override
        public String toString() {
            return (String) cliente.getNombre();
        }
    }
}