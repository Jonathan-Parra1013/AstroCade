public class Premios {

    private String nombre;
    private int costoTickets;

    // Constructor
    public Premio(String nombre, int costoTickets) {
        this.nombre = nombre;
        this.costoTickets = costoTickets;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getCostoTickets() {
        return costoTickets;
    }

    // Mostrar información del premio
    public void mostrarPremio() {
        System.out.println("Premio: " + nombre);
        System.out.println("Costo: " + costoTickets + " tickets");
    }
}
