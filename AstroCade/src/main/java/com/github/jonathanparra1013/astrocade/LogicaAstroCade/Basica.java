/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade.LogicaAstroCade;

/**
 *
 * @author jonap
 */
// Tarjeta Basic (Sin descuento)
public class Basica extends Tarjeta {
    public Basica(String idTarjeta) {
        super(idTarjeta, 50000, new DescuentoBasico());
    }

    @Override
    public String getTipo() {
        return "Básica";
    }
}
