package com.github.jonathanparra1013.astrocade.ManejoErrores;

// Se lanza cuando un premio de la tienda ya no tiene unidades disponibles
public class StockAgotadoException extends AstroCadeException {

    public StockAgotadoException(String premio) {
        super("El premio \"" + premio + "\" está agotado.");
    }
}
