package com.github.jonathanparra1013.astrocade.ManejoErrores;

// Se lanza cuando el usuario escribe un dato que no cumple las reglas (vacío, negativo, formato) :D
public class DatoInvalidoException extends AstroCadeException {

    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
