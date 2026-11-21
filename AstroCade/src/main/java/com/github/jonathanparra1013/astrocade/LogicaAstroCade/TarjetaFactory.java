package com.github.jonathanparra1013.astrocade;

public class TarjetaFactory {
    public enum TipoTarjeta {
        BASICA,
        GOLD,
        DIAMOND
    }

    private TarjetaFactory() {
    }

    public static Tarjeta crear(TipoTarjeta tipo, String idTarjeta) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de tarjeta es obligatorio.");
        }

        switch (tipo) {
            case BASICA:
                return new Basica(idTarjeta);
            case GOLD:
                return new Gold(idTarjeta);
            case DIAMOND:
                return new Diamond(idTarjeta);
            default:
                throw new IllegalArgumentException("Tipo de tarjeta no soportado: " + tipo);
        }
    }
}
