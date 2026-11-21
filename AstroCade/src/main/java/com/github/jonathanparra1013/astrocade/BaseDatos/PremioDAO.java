package com.github.jonathanparra1013.astrocade.BaseDatos;

import com.github.jonathanparra1013.astrocade.LogicaAstroCade.Cliente;
import com.github.jonathanparra1013.astrocade.LogicaAstroCade.PremioPorTickets;
import com.github.jonathanparra1013.astrocade.LogicaAstroCade.Tarjeta;
import com.github.jonathanparra1013.astrocade.Repositorios.PremioRepositorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Acceso a datos de los premios de la tienda (los que se pagan con tickets). 
public class PremioDAO implements PremioRepositorio {

    @Override
    public List<PremioPorTickets> listarTienda() throws PersistenciaException {
        String sql = "SELECT nombre, stock, costo_tickets FROM premios WHERE tipo = 'TICKETS' ORDER BY id";
        List<PremioPorTickets> premios = new ArrayList<>();
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                premios.add(new PremioPorTickets(rs.getString("nombre"), rs.getInt("stock"),
                        rs.getInt("costo_tickets")));
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudieron cargar los premios de la tienda. "
                    + "Revise que MySQL (XAMPP) esté encendido.", e);
        }
        return premios;
    }

   
    @Override
    public void redimir(Cliente cliente, Tarjeta tarjeta, PremioPorTickets premio) throws PersistenciaException {
        String sqlStock = "UPDATE premios SET stock = ? WHERE nombre = ? AND tipo = 'TICKETS'";
        String sqlRedencion = "INSERT INTO premios_redimidos (id_cliente, id_premio, tickets_gastados) "
                + "VALUES (?, (SELECT id FROM premios WHERE nombre = ? AND tipo = 'TICKETS'), ?)";
        Connection con = null;
        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false);

            ClienteDAO.actualizarTarjeta(con, tarjeta);
            try (PreparedStatement ps = con.prepareStatement(sqlStock)) {
                ps.setInt(1, premio.getStock());
                ps.setString(2, premio.getNombre());
                ps.executeUpdate();
            }
            try (PreparedStatement ps = con.prepareStatement(sqlRedencion)) {
                ps.setString(1, cliente.getId());
                ps.setString(2, premio.getNombre());
                ps.setInt(3, premio.getCostoTickets());
                ps.executeUpdate();
            }
            con.commit();
        } catch (SQLException e) {
            Conexion.deshacer(con);
            throw new PersistenciaException("No se pudo registrar la redención: " + e.getMessage(), e);
        } finally {
            Conexion.restaurarAutoCommit(con);
        }
    }
}
