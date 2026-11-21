package com.github.jonathanparra1013.astrocade.Repositorios;

import com.github.jonathanparra1013.astrocade.Tarjetas.Tarjeta;
import com.github.jonathanparra1013.astrocade.Usuarios.GestorClientes;
import com.github.jonathanparra1013.astrocade.Usuarios.Cliente;
import com.github.jonathanparra1013.astrocade.BaseDatos.PersistenciaException;
import com.github.jonathanparra1013.astrocade.LogicaAstroCade.*;
import java.util.List;


public interface ClienteRepositorio {

    List<GestorClientes.ClienteConTarjeta> listar() throws PersistenciaException;

    void insertar(Cliente cliente, Tarjeta tarjeta) throws PersistenciaException;

    void actualizarTarjeta(Tarjeta tarjeta) throws PersistenciaException;
}
