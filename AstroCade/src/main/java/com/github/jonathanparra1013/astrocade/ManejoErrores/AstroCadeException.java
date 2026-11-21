package com.github.jonathanparra1013.astrocade.ManejoErrores;

public class AstroCadeException extends Exception {

    public AstroCadeException(String mensaje) {
        super(mensaje);
    }

    public AstroCadeException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
