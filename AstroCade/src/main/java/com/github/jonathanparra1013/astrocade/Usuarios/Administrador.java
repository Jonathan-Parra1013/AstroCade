package com.github.jonathanparra1013.astrocade.Usuarios;

//Administrador del arcade. Su contraseña se valida contra el hash guardado en la base de datos.

import com.github.jonathanparra1013.astrocade.Maquinas.Maquina;
import com.github.jonathanparra1013.astrocade.Tarjetas.Tarjeta;
import com.github.jonathanparra1013.astrocade.Usuarios.Usuario;

public class Administrador extends Usuario {

    private final String password;

    public Administrador(String id, String nombre, String email, String password) {
        super(id, nombre, email);
        this.password = password;
    }

    @Override
    public String getRol() {
        return "Administrador";
    }

    public boolean validarPassword(String passIngresada) {
        return this.password != null && this.password.equals(passIngresada);
    }

    public void ponerMaquinaEnMantenimiento(Maquina maquina) {
        maquina.ponerEnMantenimiento();
        System.out.println("Maquina " + maquina.getNombre() + " puesta en mantenimiento.");
    }

    public void recargarTarjeta(Tarjeta tarjeta, int cantidad) {
        tarjeta.recargarSaldo(cantidad);
        System.out.println("Tarjeta recargada con exito. Nuevo saldo: " + tarjeta.getSaldoJugadas());
    }

    public void cambiarEstadoMaquina(Maquina maquina, String nuevoEstado) {
        maquina.setEstado(nuevoEstado);
        System.out.println("La maquina " + maquina.getNombre() + " ahora esta: " + nuevoEstado);
    }
}
