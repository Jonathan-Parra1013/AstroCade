package com.github.jonathanparra1013.astrocade.ManejoErrores;

// Se lanza cuando una recarga supera el límite de saldo del tipo de tarjeta
public class LimiteSaldoException extends AstroCadeException {

    public LimiteSaldoException(int saldoActual, int recarga, int limite) {
        super("La recarga de $" + recarga + " supera el límite de la tarjeta ($" + limite
                + "). Saldo actual: $" + saldoActual + ".");
    }
}
