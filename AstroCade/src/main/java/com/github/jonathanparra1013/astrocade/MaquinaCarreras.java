/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;

/**
 *
 * @author jonap
 */
public class MaquinaCarreras extends Maquina {

    public MaquinaCarreras(String nombre, double precioPartida) {
        super(nombre, precioPartida);
    }

    @Override
    public void jugar(Tarjeta tarjeta) {

        if (tarjeta.descontarSaldo(getPrecioPartida())) {

            System.out.println("//////////////////////////////");
            System.out.println("      JUEGO DE CARRERAS");
            System.out.println("//////////////////////////////");
            System.out.println("Maquina: " + getNombre());

            int posicion = (int) (Math.random() * 3) + 1;

            System.out.println("Carrera terminada.");
            System.out.println("Llegaste en la posicion: " + posicion);

            int tickets;

            if (posicion == 1) {
                tickets = 50;
            } else if (posicion == 2) {
                tickets = 20;
            } else {
                tickets = 5;
            }

            tarjeta.agregarTickets(tickets);

            System.out.println("Tickets ganados: " + tickets);
            System.out.println("Tickets disponibles: " + tarjeta.getTickets());
            System.out.println("Saldo restante: $" + tarjeta.getSaldo());

        } else {
            System.out.println("Saldo insuficiente para jugar.");
        }
    }
}
