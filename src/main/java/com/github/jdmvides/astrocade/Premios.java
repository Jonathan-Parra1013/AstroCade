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
    
    public void reclamarPremio(MaquinaArcade maquina) {

   if (maquina.getTickets() >= costoTickets) {

            maquina.retirarTickets(costoTickets);

            System.out.println("PREMIO RECLAMADO");
            System.out.println("Premio: " + nombre);
            System.out.println("Costo: " + costoTickets + " tickets");
            System.out.println("Tickets restantes: " + maquina.getTickets());

        } else {

            System.out.println("No puedes reclamar este premio.");
            System.out.println("Premio: " + nombre);
            System.out.println("Necesitas: " + costoTickets + " tickets");
            System.out.println("Tienes: " + maquina.getTickets() + " tickets");
        }
    }
}
