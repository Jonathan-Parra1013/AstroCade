package com.github.jonathanparra1013.astrocade.Repositorios;

import com.github.jonathanparra1013.astrocade.BaseDatos.PersistenciaException;
import com.github.jonathanparra1013.astrocade.LogicaAstroCade.*;
import java.util.List;

public interface EventoRepositorio {

    Evento obtenerActivo() throws PersistenciaException;

    List<Evento> listar() throws PersistenciaException;

    void activar(String tipoEvento) throws PersistenciaException;
}
