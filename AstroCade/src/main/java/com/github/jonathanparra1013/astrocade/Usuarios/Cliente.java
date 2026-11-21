package com.github.jonathanparra1013.astrocade.Usuarios;

import com.github.jonathanparra1013.astrocade.Tarjetas.Tarjeta;
import com.github.jonathanparra1013.astrocade.Usuarios.Usuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

// Cliente del arcade: tiene fecha de nacimiento (para el beneficio de cumpleaños) y premios redimidos. */
public class Cliente extends Usuario {

    private final Date fechaNacimiento;
    private final List<String> premiosRedimidos = new ArrayList<>();

    public Cliente(String id, String nombre, String email, Date fechaNacimiento) {
        super(id, nombre, email);
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String getRol() {
        return "Cliente";
    }

    public boolean esSuCumpleanosHoy() {
        if (fechaNacimiento == null) {
            return false;
        }
        Calendar calNac = Calendar.getInstance();
        calNac.setTime(fechaNacimiento);
        Calendar calHoy = Calendar.getInstance();
        return calNac.get(Calendar.MONTH) == calHoy.get(Calendar.MONTH)
                && calNac.get(Calendar.DAY_OF_MONTH) == calHoy.get(Calendar.DAY_OF_MONTH);
    }

    public void verPuntajes(Tarjeta tarjeta) {
        System.out.println("Saldo de jugadas: " + tarjeta.getSaldoJugadas()
                + " Tickets: " + tarjeta.getSaldoTickets());
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void agregarPremio(String premio) {
        if (premio != null && !premio.isBlank()) {
            this.premiosRedimidos.add(premio);
        }
    }

    public String getPremiosTexto() {
        if (premiosRedimidos.isEmpty()) {
            return "Ninguno";
        }
        return String.join(", ", premiosRedimidos);
    }
}
