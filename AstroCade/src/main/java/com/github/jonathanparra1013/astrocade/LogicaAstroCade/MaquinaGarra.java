/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;

/**
 *
 * @author jonap
 */
public class MaquinaGarra extends Maquina {

    private PremioPorMaquina[] premios;

    public MaquinaGarra(String nombre, double precioPartida,
                        PremioPorMaquina[] premios) {

        super(nombre, precioPartida);
        this.premios = premios;
    }

    @Override
    public void jugar(Tarjeta tarjeta) {

        // Verificar y descontar el saldo
        if (!tarjeta.descontarSaldo(getPrecioPartida())) {

            System.out.println("//////////////////////////////");
            System.out.println("        MAQUINA GARRA");
            System.out.println("//////////////////////////////");
            System.out.println("Saldo insuficiente para jugar.");

            return;
        }

        System.out.println("//////////////////////////////");
        System.out.println("          MAQUINA GARRA");
        System.out.println("//////////////////////////////");

        System.out.println("Maquina: " + getNombre());
        System.out.println("Precio: $" + getPrecioPartida());

        // Buscar un premio disponible
        PremioPorMaquina premio = buscarPremioDisponible();

        if (premio == null) {

            System.out.println("No hay premios disponibles.");
            System.out.println("La maquina no puede entregar premios.");

            return;
        }

        System.out.println("La garra esta bajando...");
        System.out.println("Intentando atrapar: "+ premio.getNombre());

        int fuerzaGarra = (int) (Math.random() * 100) + 1;

        System.out.println("Fuerza de la garra: "+ fuerzaGarra);

        if (fuerzaGarra >= 70) {

            System.out.println("¡LA GARRA ATRAPO EL PREMIO!");

            premio.entregarPremio();

        } else {

            System.out.println("La garra solto el premio.");
            System.out.println("No ganaste esta vez.");
        }

        System.out.println("Saldo restante: $"+ tarjeta.getSaldo());
    }

    private PremioPorMaquina buscarPremioDisponible() {

        for (PremioPorMaquina premio : premios) {

            if (premio.hayStock()) {
                return premio;
            }
        }

        return null;
    }
}
