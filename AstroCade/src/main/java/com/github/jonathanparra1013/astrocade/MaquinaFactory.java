package com.github.jonathanparra1013.astrocade;

public class MaquinaFactory {
    public enum TipoMaquina {
        CARRERAS,
        DISPAROS,
        GARRA,
        RULETA
    }

    private MaquinaFactory() {
    }

    public static Maquina crear(TipoMaquina tipo, String nombre,
                                double precioPartida,
                                PremioPorMaquina[] premios) {
        switch (tipo) {
            case CARRERAS:
                return new MaquinaCarreras(nombre, precioPartida);
            case DISPAROS:
                return new MaquinaDisparos(nombre, precioPartida);
            case GARRA:
                return new MaquinaGarra(nombre, precioPartida, premios);
            case RULETA:
                return new MaquinaRuleta(nombre, precioPartida, premios);
            default:
                throw new IllegalArgumentException("Tipo de maquina no soportado: " + tipo);
        }
    }
}
