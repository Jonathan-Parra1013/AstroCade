package com.github.jonathanparra1013.astrocade.BaseDatos;

import com.github.jonathanparra1013.astrocade.LogicaAstroCade.Evento;
import com.github.jonathanparra1013.astrocade.Repositorios.EventoRepositorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Acceso a datos de los eventos globales (tabla eventos). */
public class EventoDAO implements EventoRepositorio {

    @Override
    public Evento obtenerActivo() throws PersistenciaException {
        String sql = "SELECT nombre, tipo FROM eventos WHERE activo = 1 ORDER BY id LIMIT 1";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Evento(rs.getString("nombre"), rs.getString("tipo"));
            }
            return new Evento("Evento Normal", "Normal"); // ningún evento marcado: se usa el normal
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo consultar el evento activo. "
                    + "Revise que MySQL esté encendido.", e);
        }
    }

    @Override
    public List<Evento> listar() throws PersistenciaException {
        String sql = "SELECT nombre, tipo FROM eventos ORDER BY id";
        List<Evento> eventos = new ArrayList<>();
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                eventos.add(new Evento(rs.getString("nombre"), rs.getString("tipo")));
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudieron cargar los eventos. "
                    + "Revise que MySQL esté encendido.", e);
        }
        return eventos;
    }

    @Override
    public void activar(String tipoEvento) throws PersistenciaException {
        String sqlApagar = "UPDATE eventos SET activo = 0";
        String sqlActivar = "UPDATE eventos SET activo = 1 WHERE tipo = ?";
        Connection con = null;
        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(sqlApagar)) {
                ps.executeUpdate();
            }
            try (PreparedStatement ps = con.prepareStatement(sqlActivar)) {
                ps.setString(1, tipoEvento);
                if (ps.executeUpdate() == 0) {
                    Conexion.deshacer(con);
                    throw new PersistenciaException("El evento \"" + tipoEvento + "\" no existe.");
                }
            }
            con.commit();
        } catch (SQLException e) {
            Conexion.deshacer(con);
            throw new PersistenciaException("No se pudo cambiar el evento: " + e.getMessage(), e);
        } finally {
            Conexion.restaurarAutoCommit(con);
        }
    }
}
