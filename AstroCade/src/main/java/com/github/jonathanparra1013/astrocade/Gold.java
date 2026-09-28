/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jonathanparra1013.astrocade;

/**
 *
 * @author jonap
 */
// Tarjeta Gold (10% de descuento)
class Gold extends Tarjeta {
    public Gold(String idTarjeta) {
        super(idTarjeta, 200000);
    }

    @Override
    public double calcularDescuento(double costoBase) {
        return costoBase * 0.90; // 10% de descuento
    }
}