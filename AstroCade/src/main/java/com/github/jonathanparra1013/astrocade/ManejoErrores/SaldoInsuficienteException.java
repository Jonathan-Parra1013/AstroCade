package com.github.jonathanparra1013.astrocade.ManejoErrores;

// Se lanza cuando la tarjeta no tiene saldo de jugadas o tickets suficientes
public class SaldoInsuficienteException extends AstroCadeException {

    public SaldoInsuficienteException(String recurso, double disponible, double requerido) {
        super("Saldo insuficiente de " + recurso + ": tienes " + (long) disponible
                + " y necesitas " + (long) Math.ceil(requerido) + ".");
    }
}
