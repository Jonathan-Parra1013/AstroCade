package com.github.jonathanparra1013.astrocade;

public class DescuentoGold implements EstrategiaDescuento {
    @Override
    public double calcular(double costoBase) {
        return costoBase * 0.90;
    }
}
