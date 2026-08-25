package com.github.jdmvides.astrocade;

public class MaquinaArcade {

    private String nombre;
    private double precioPartida;
    private int tickets;

    public MaquinaArcade(String nombre, double precioPartida) {
        this.nombre = nombre;
        this.precioPartida = precioPartida;
        this.tickets = 0;
    }

    public void jugar(Tarjeta tarjeta) {
        if (tarjeta.descontarSaldo(precioPartida)) {
            System.out.println("Partida iniciada en " + nombre);
            System.out.println("Tarjeta: " + tarjeta.getNumeroTarjeta());
            System.out.println("Se descontaron $" + precioPartida);
            
            int puntuacion = (int) (Math.random() * 10000);
            System.out.println("PARTIDA TERMINADA");
            System.out.println("Puntuacion obtenida: " + puntuacion);
            
            int ticketsGanados;

            if (puntuacion < 1000) {
                ticketsGanados = 1;
            } else if (puntuacion < 2500) {
                ticketsGanados = 5;
            } else if (puntuacion < 5000) {
                ticketsGanados = 10;
            } else if (puntuacion < 7500) {
                ticketsGanados = 20;
            } else {
                ticketsGanados = 50;
            }

            tarjeta.agregarTickets(ticketsGanados);

            System.out.println("Tickets ganados: " + ticketsGanados);
            System.out.println("Tickets disponibles: " + tarjeta.getTickets());
            System.out.println("Saldo restante: $" + tarjeta.getSaldo());
        } else {
            System.out.println("No se puede iniciar la partida.");
            System.out.println("Saldo insuficiente en la tarjeta.");
        }
    }

    
    public void mostrarInformacionMaquina() {
        System.out.println("Maquina: " + nombre);
        System.out.println("Precio por partida: $" + precioPartida);
    }
}
