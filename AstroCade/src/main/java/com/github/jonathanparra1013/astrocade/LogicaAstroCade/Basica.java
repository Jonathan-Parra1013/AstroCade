/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;

/**
 *
 * @author jonap
 */
// Tarjeta Basic (Sin descuento)
public class Basica extends Tarjeta {
    public Basica(String idTarjeta) {
        super(idTarjeta, 50000); // Límite de saldo
    }

    @Override
    public double calcularDescuento(double costoBase) {
        return costoBase; // 0% de descuento
    }
}