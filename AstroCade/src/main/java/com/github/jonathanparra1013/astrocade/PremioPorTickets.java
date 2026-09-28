/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;

/**
 *
 * @author jonap
 */
public class PremioPorTickets extends Premios {

    private int costoTickets;

    public PremioPorTickets(String nombre, int stock, int costoTickets) {

        super(nombre, stock);

        if (costoTickets > 0) {
            this.costoTickets = costoTickets;
        } else {
            this.costoTickets = 1;
        }
    }

    public int getCostoTickets() {
        return costoTickets;
    }

    @Override
    public void mostrarPremio() {

        System.out.println("///////////PREMIO POR TICKETS///////////");
        System.out.println("Premio: " + getNombre());
        System.out.println("Costo: " + costoTickets + " tickets");
        System.out.println("Stock disponible: " + getStock());
    }

    public void reclamarPremio(Tarjeta tarjeta) {

        if (!hayStock()) {

            System.out.println("///////////PREMIO AGOTADO///////////");
            System.out.println("Premio: " + getNombre());
            System.out.println("Actualmente no hay stock.");

            return;
        }

        if (tarjeta.getTickets() >= costoTickets) {

            tarjeta.descontarTickets(costoTickets);

            reducirStock();

            System.out.println("///////////PREMIO RECLAMADO///////////");
            System.out.println("Premio: " + getNombre());
            System.out.println("Costo: " + costoTickets + " tickets");
            System.out.println("Tarjeta: " + tarjeta.getNumeroTarjeta());
            System.out.println("Tickets restantes: "
                    + tarjeta.getTickets());
            System.out.println("Stock restante: "
                    + getStock());

        } else {

            System.out.println("///////////NO SE PUEDE RECLAMAR///////////");
            System.out.println("Premio: " + getNombre());
            System.out.println("Costo: " + costoTickets + " tickets");
            System.out.println("Tickets disponibles: "
                    + tarjeta.getTickets());

            System.out.println(
                    "Te faltan " +
                    (costoTickets - tarjeta.getTickets()) +
                    " tickets."
            );
        }
    }

    @Override
    public String toString() {

        return "Premio: " + getNombre()
                + " | Costo: " + costoTickets
                + " tickets"
                + " | Stock: " + getStock();
    }
}
