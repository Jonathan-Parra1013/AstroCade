/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jonathan.astrocade;

/**
 *
 * @author salas
 */
public class Premios {

    private String nombre;
    private int costoTickets;

    public Premios(String nombre, int costoTickets) {
        this.nombre = nombre;
        this.costoTickets = costoTickets;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCostoTickets() {
        return costoTickets;
    }

    public void mostrarPremio() {
        System.out.println("Premio: " + nombre);
        System.out.println("Costo: " + costoTickets + " tickets");
    }
    
     public void reclamarPremio(Tarjeta tarjeta) {

        if (tarjeta.getTickets() >= costoTickets) {

            // Descontar los tickets de la tarjeta
            tarjeta.descontarTickets(costoTickets);

            System.out.println("PREMIO RECLAMADO");
            System.out.println("Premio: " + nombre);
            System.out.println("Costo: " + costoTickets + " tickets");
            System.out.println("Tarjeta: " + tarjeta.getNumeroTarjeta());
            System.out.println("Tickets restantes: " + tarjeta.getTickets());

        } else {

            System.out.println("\n===== NO SE PUEDE RECLAMAR =====");
            System.out.println("Premio: " + nombre);
            System.out.println("Costo: " + costoTickets + " tickets");
            System.out.println("Tickets disponibles: " + tarjeta.getTickets());
            System.out.println(
                "Te faltan " +
                (costoTickets - tarjeta.getTickets()) +
                " tickets."
            );
        }
    }
    @Override
    public String toString() {
        return "Premio: " + nombre +
               " | Costo: " + costoTickets + " tickets";
    }
}