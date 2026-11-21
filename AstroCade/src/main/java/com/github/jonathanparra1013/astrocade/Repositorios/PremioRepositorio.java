package com.github.jonathanparra1013.astrocade.Repositorios;

import com.github.jonathanparra1013.astrocade.Premios.PremioPorTickets;
import com.github.jonathanparra1013.astrocade.Tarjetas.Tarjeta;
import com.github.jonathanparra1013.astrocade.Usuarios.Cliente;
import com.github.jonathanparra1013.astrocade.BaseDatos.PersistenciaException;
import com.github.jonathanparra1013.astrocade.LogicaAstroCade.*;
import java.util.List;


public interface PremioRepositorio {

    List<PremioPorTickets> listarTienda() throws PersistenciaException;

    void redimir(Cliente cliente, Tarjeta tarjeta, PremioPorTickets premio) throws PersistenciaException;
}
