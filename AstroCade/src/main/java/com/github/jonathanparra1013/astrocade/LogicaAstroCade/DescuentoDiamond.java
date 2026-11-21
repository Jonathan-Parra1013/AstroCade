package com.github.jonathanparra1013.astrocade.LogicaAstroCade;

public class DescuentoDiamond implements EstrategiaDescuento {
    @Override
    public double calcular(double costoBase) {
        return costoBase * 0.75;
    }
}
