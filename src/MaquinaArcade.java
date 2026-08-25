public class MaquinaArcade {

    private String nombre;
    private double precioPartida;

    public MaquinaArcade(String nombre, double precioPartida) {
        this.nombre = nombre;
        this.precioPartida = precioPartida;
    }

    public void iniciarPartida() {
        if (saldo >= precioPartida) {
            saldo -= precioPartida;
            System.out.println("Partida iniciada en " + nombre);
        } else {
            System.out.println("No hay suficiente crédito.");
        }
    }

    public void mostrarInformacionMaquina() {
        System.out.println("Máquina: " + nombre);
        System.out.println("Precio por partida: $" + precioPartida);
    }
}
