package com.github.jonathanparra1013.astrocade.BaseDatos;

import com.github.jonathanparra1013.astrocade.ManejoErrores.AstroCadeException;


public class PersistenciaException extends AstroCadeException {

    public PersistenciaException(String mensaje) {
        super(mensaje);
    }

    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
