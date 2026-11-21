package com.github.jonathanparra1013.astrocade.Maquinas;

import com.github.jonathanparra1013.astrocade.Premios.PremioPorMaquina;
import com.github.jonathanparra1013.astrocade.Maquinas.MaquinaDisparos;
import com.github.jonathanparra1013.astrocade.Maquinas.MaquinaCarreras;
import com.github.jonathanparra1013.astrocade.Maquinas.Maquina;

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
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de máquina es obligatorio.");
        }

        switch (tipo) {
            case CARRERAS:
                return new MaquinaCarreras(nombre, precioPartida);
            case DISPAROS:
                return new MaquinaDisparos(nombre, precioPartida);
            case GARRA:
                return new MaquinaGarra(nombre, precioPartida,
                        premios == null ? new PremioPorMaquina[0] : premios);
            case RULETA:
                return new MaquinaRuleta(nombre, precioPartida,
                        premios == null ? new PremioPorMaquina[0] : premios);
            default:
                throw new IllegalArgumentException("Tipo de máquina no soportado: " + tipo);
        }
    }
}
