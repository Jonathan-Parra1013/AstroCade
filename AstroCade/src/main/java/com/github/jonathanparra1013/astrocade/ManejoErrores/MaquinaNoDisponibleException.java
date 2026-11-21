package com.github.jonathanparra1013.astrocade.ManejoErrores;

// Se lanza cuando se intenta jugar en una máquina en mantenimiento o fuera de servicio
public class MaquinaNoDisponibleException extends AstroCadeException {

    public MaquinaNoDisponibleException(String maquina, String estado) {
        super("La máquina \"" + maquina + "\" no está disponible. Estado: " + estado + ".");
    }
}
