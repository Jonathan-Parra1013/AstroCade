package com.github.jonathanparra1013.astrocade.LogicaAstroCade;

public class EstadoActiva implements EstadoMaquina {
    @Override
    public String getNombre() {
        return "Activa";
    }

    @Override
    public boolean puedeJugar() {
        return true;
    }
}
