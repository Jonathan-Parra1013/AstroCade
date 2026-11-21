package com.github.jonathanparra1013.astrocade.Repositorios;

import com.github.jonathanparra1013.astrocade.BaseDatos.PersistenciaException;
import com.github.jonathanparra1013.astrocade.LogicaAstroCade.*;
import java.util.List;


public interface UsuarioRepositorio {

    Administrador autenticarAdministrador(String password) throws PersistenciaException;
}
