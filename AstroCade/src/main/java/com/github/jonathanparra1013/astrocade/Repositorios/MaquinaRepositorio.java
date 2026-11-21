package com.github.jonathanparra1013.astrocade.Repositorios;

import com.github.jonathanparra1013.astrocade.Maquinas.Maquina;
import com.github.jonathanparra1013.astrocade.Tarjetas.Tarjeta;
import com.github.jonathanparra1013.astrocade.BaseDatos.PersistenciaException;
import com.github.jonathanparra1013.astrocade.LogicaAstroCade.*;
import java.util.List;

public interface MaquinaRepositorio {

    List<Maquina> listar() throws PersistenciaException;

    void actualizarEstado(Maquina maquina) throws PersistenciaException;

    void guardarPartida(Maquina maquina, Tarjeta tarjeta, int saldoAntes, int ticketsAntes)
            throws PersistenciaException;
}
