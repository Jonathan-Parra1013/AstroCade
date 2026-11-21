package com.github.jonathanparra1013.astrocade.Tarjetas;

import com.github.jonathanparra1013.astrocade.Tarjetas.EstrategiaDescuento;

public class DescuentoBasico implements EstrategiaDescuento {
    @Override
    public double calcular(double costoBase) {
        return costoBase;
    }
}
