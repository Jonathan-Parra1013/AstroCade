package com.github.jonathanparra1013.astrocade.LogicaAstroCade;

public class DescuentoBasico implements EstrategiaDescuento {
    @Override
    public double calcular(double costoBase) {
        return costoBase;
    }
}
