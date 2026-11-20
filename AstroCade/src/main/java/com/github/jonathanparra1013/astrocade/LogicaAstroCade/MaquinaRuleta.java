/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;

/**
 *
 * @author jonap
 */
public class MaquinaRuleta extends Maquina {

    private PremioPorMaquina[] premios;

    public MaquinaRuleta(String nombre, double precioPartida,
                         PremioPorMaquina[] premios) {

        super(nombre, precioPartida);
        this.premios = premios;
    }

    @Override
    public void jugar(Tarjeta tarjeta) {

        // Verificar y descontar saldo
        if (!tarjeta.descontarSaldo(getPrecioPartida())) {

            System.out.println("//////////////////////////////");
            System.out.println("        MAQUINA RULETA");
            System.out.println("//////////////////////////////");
            System.out.println("Saldo insuficiente para jugar.");

            return;
        }

        System.out.println("//////////////////////////////");
        System.out.println("        MAQUINA RULETA");
        System.out.println("//////////////////////////////");

        System.out.println("Maquina: " + getNombre());
        System.out.println("Precio: $" + getPrecioPartida());

        PremioPorMaquina premio = buscarPremioDisponible();

        if (premio == null) {

            System.out.println("No hay premios disponibles.");
            System.out.println("La maquina no puede entregar premios.");

            return;
        }

        System.out.println("La ruleta esta girando...");

        int numero = (int) (Math.random() * 100) + 1;

        System.out.println("Numero obtenido: " + numero);

        if (numero >= 80) {

            System.out.println("¡GANASTE!");

            premio.entregarPremio();

        } else {

            System.out.println("No ganaste ningun premio.");
            System.out.println("¡Intentalo nuevamente!");
        }

        System.out.println("\nSaldo restante: $"+ tarjeta.getSaldo());
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
