package com.github.jonathanparra1013.astrocade;

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
