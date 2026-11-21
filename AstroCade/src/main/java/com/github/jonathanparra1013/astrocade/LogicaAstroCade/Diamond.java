/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade.LogicaAstroCade;

/**
 *
 * @author jonap
 */
// Tarjeta Diamond (25% de descuento)
public class Diamond extends Tarjeta {
    public Diamond(String idTarjeta) {
        super(idTarjeta, 1000000, new DescuentoDiamond());
    }

    @Override
    public String getTipo() {
        return "Diamond";
    }
}
