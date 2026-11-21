package com.github.jonathanparra1013.astrocade.BaseDatos;

import com.github.jonathanparra1013.astrocade.LogicaAstroCade.Administrador;
import com.github.jonathanparra1013.astrocade.Repositorios.UsuarioRepositorio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Acceso a datos de los usuarios que no son clientes (administradores). 
public class UsuarioDAO implements UsuarioRepositorio {

   
    @Override
    public Administrador autenticarAdministrador(String password) throws PersistenciaException {
        String sql = "SELECT id, nombre, email FROM usuarios "
                + "WHERE rol = 'ADMINISTRADOR' AND password_hash = SHA2(?, 256) LIMIT 1";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Administrador(rs.getString("id"), rs.getString("nombre"),
                            rs.getString("email"), password);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo verificar al administrador. "
                    + "Revise que MySQL (XAMPP) esté encendido.", e);
        }
    }
}
