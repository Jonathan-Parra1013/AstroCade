public class MaquinaArcade {

    private String nombre;
    private double precioPartida;
    private int tickets;

    public MaquinaArcade(String nombre, double precioPartida) {
        this.nombre = nombre;
        this.precioPartida = precioPartida;
        this.tickets = 0;
    }

    public void jugar() {
        if (saldo >= precioPartida) {
            saldo -= precioPartida;
            System.out.println("Partida iniciada en " + nombre);
            
            int puntuacion = (int) (Math.random() * 10000);
            System.out.println("PARTIDA TERMINADA");
            System.out.println("Puntuación obtenida: " + puntuacion);
            
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

            tickets += ticketsGanados;

            System.out.println("Tickets ganados: " + ticketsGanados);
            System.out.println("Tickets acumulados: " + tickets);
        } else {
            System.out.println("No hay suficiente crédito.");
        }
    }

    public void mostrarInformacionMaquina() {
        System.out.println("Máquina: " + nombre);
        System.out.println("Precio por partida: $" + precioPartida);
    }
}
