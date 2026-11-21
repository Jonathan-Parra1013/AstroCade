package com.github.jonathanparra1013.astrocade;

public class EstadoMantenimiento implements EstadoMaquina {
    @Override
    public String getNombre() {
        return "Mantenimiento";
    }

    @Override
    public boolean puedeJugar() {
        return false;
    }
}
