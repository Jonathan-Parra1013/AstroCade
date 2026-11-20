/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;

/**
 *
 * @author jonap
 */
public class MaquinaDisparos extends Maquina {

    public MaquinaDisparos(String nombre, double precioPartida) {
        super(nombre, precioPartida);
    }

    @Override
    public void jugar(Tarjeta tarjeta) {

        if (tarjeta.descontarSaldo(getPrecioPartida())) {

            System.out.println("//////////////////////////////");
            System.out.println("       JUEGO DE DISPAROS");
            System.out.println("//////////////////////////////");
            System.out.println("Maquina: " + getNombre());

            int puntuacion = (int) (Math.random() * 10000);

            System.out.println("Puntuacion: " + puntuacion);

            int tickets;

            if (puntuacion >= 8000) {
                tickets = 50;
            } else if (puntuacion >= 5000) {
                tickets = 25;
            } else if (puntuacion >= 2000) {
                tickets = 10;
            } else {
                tickets = 2;
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
