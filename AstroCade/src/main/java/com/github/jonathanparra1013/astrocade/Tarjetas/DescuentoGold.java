package com.github.jonathanparra1013.astrocade.Tarjetas;

import com.github.jonathanparra1013.astrocade.Tarjetas.EstrategiaDescuento;

public class DescuentoGold implements EstrategiaDescuento {
    @Override
    public double calcular(double costoBase) {
        return costoBase * 0.90;
    }
}
