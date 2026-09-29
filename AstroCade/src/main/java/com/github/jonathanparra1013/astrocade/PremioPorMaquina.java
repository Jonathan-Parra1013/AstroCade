/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;

/**
 *
 * @author jonap
 */
public class PremioPorMaquina extends Premios implements Entregable {

    public PremioPorMaquina(String nombre, int stock) {
        super(nombre, stock);
    }

    @Override
    public void mostrarPremio() {

        System.out.println("///////////PREMIO DE MAQUINA///////////");
        System.out.println("Premio: " + getNombre());
        System.out.println("Stock disponible: " + getStock());
    }

    public String entregarPremio(){

        if (reducirStock()) {

            System.out.println("///////////PREMIO GANADO///////////");
            System.out.println("Premio: " + getNombre());
            System.out.println("¡Felicidades!");
            System.out.println("Stock restante: " + getStock());

        } else {

            System.out.println("///////////PREMIO AGOTADO///////////");
            System.out.println("Premio: " + getNombre());
        }
        return null;
    }
}