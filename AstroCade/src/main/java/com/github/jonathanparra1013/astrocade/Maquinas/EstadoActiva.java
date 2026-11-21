package com.github.jonathanparra1013.astrocade.Maquinas;

import com.github.jonathanparra1013.astrocade.Maquinas.EstadoMaquina;

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
