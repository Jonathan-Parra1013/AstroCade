package com.github.jonathanparra1013.astrocade;

public class DescuentoDiamond implements EstrategiaDescuento {
    @Override
    public double calcular(double costoBase) {
        return costoBase * 0.75;
    }
}
