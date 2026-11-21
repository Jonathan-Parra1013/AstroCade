package com.github.jonathanparra1013.astrocade.Maquinas;

import com.github.jonathanparra1013.astrocade.Maquinas.EstadoMaquina;

public class EstadoFueraDeServicio implements EstadoMaquina {
    @Override
    public String getNombre() {
        return "Fuera de servicio";
    }

    @Override
    public boolean puedeJugar() {
        return false;
    }
}
