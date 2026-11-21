package com.github.jonathanparra1013.astrocade.BaseDatos;

import com.github.jonathanparra1013.astrocade.Maquina;
import com.github.jonathanparra1013.astrocade.MaquinaCarreras;
import com.github.jonathanparra1013.astrocade.MaquinaDisparos;
import com.github.jonathanparra1013.astrocade.MaquinaGarra;
import com.github.jonathanparra1013.astrocade.MaquinaRuleta;
import com.github.jonathanparra1013.astrocade.PremioPorMaquina;
import com.github.jonathanparra1013.astrocade.LogicaAstroCade.Tarjeta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Acceso a datos de las máquinas, sus premios y el historial de partidas
public class MaquinaDAO {

    // Carga las máquinas (con el tipo de clase que corresponde) y los premios de Garra y Ruleta
    public List<Maquina> listar() throws PersistenciaException {
        String sql = "SELECT id, nombre, tipo, precio_partida, estado FROM maquinas ORDER BY id";
        List<Maquina> maquinas = new ArrayList<>();
        try {
            Connection con = Conexion.getConexion();
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    double precio = rs.getDouble("precio_partida");
                    Maquina maquina;
                    switch (rs.getString("tipo")) {
                        case "CARRERAS":
                            maquina = new MaquinaCarreras(nombre, precio);
                            break;
                        case "DISPAROS":
                            maquina = new MaquinaDisparos(nombre, precio);
                            break;
                        case "GARRA":
                            maquina = new MaquinaGarra(nombre, precio, cargarPremios(con, rs.getInt("id")));
                            break;
                        case "RULETA":
                            maquina = new MaquinaRuleta(nombre, precio, cargarPremios(con, rs.getInt("id")));
                            break;
                        default:
                            continue; // tipo que la aplicación no conoce
                    }
                    maquina.setEstado(rs.getString("estado"));
                    maquinas.add(maquina);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudieron cargar las máquinas. "
                    + "Revise que MySQL (XAMPP) esté encendido.", e);
        }
        return maquinas;
    }

    private PremioPorMaquina[] cargarPremios(Connection con, int idMaquina) throws SQLException {
        String sql = "SELECT nombre, stock FROM premios WHERE tipo = 'MAQUINA' AND id_maquina = ? ORDER BY id";
        List<PremioPorMaquina> premios = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMaquina);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    premios.add(new PremioPorMaquina(rs.getString("nombre"), rs.getInt("stock")));
                }
            }
        }
        return premios.toArray(new PremioPorMaquina[0]);
    }

    /** Guarda el estado actual de la máquina ("Activa" o "Mantenimiento"). */
    public void actualizarEstado(Maquina maquina) throws PersistenciaException {
        String sql = "UPDATE maquinas SET estado = ? WHERE nombre = ?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, maquina.getEstado());
            ps.setString(2, maquina.getNombre());
            if (ps.executeUpdate() == 0) {
                throw new PersistenciaException("La máquina \"" + maquina.getNombre() + "\" no existe en la base de datos.");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo guardar el estado de la máquina: " + e.getMessage(), e);
        }
    }

    
    public void guardarPartida(Maquina maquina, Tarjeta tarjeta, int saldoAntes, int ticketsAntes)
            throws PersistenciaException {
        String sqlPartida = "INSERT INTO partidas (id_tarjeta, id_maquina, costo_cobrado, tickets_ganados) "
                + "VALUES (?, (SELECT id FROM maquinas WHERE nombre = ?), ?, ?)";
        String sqlStock = "UPDATE premios SET stock = ? WHERE nombre = ? AND tipo = 'MAQUINA' "
                + "AND id_maquina = (SELECT id FROM maquinas WHERE nombre = ?)";
        Connection con = null;
        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false);

            ClienteDAO.actualizarTarjeta(con, tarjeta);

            int costo = saldoAntes - tarjeta.getSaldoJugadas();
            if (costo > 0) { // si no se cobró nada, la partida no se jugó (ej. saldo insuficiente)
                try (PreparedStatement ps = con.prepareStatement(sqlPartida)) {
                    ps.setString(1, tarjeta.getNumeroTarjeta());
                    ps.setString(2, maquina.getNombre());
                    ps.setInt(3, costo);
                    ps.setInt(4, Math.max(0, tarjeta.getSaldoTickets() - ticketsAntes));
                    ps.executeUpdate();
                }
            }

            for (PremioPorMaquina premio : premiosDe(maquina)) {
                try (PreparedStatement ps = con.prepareStatement(sqlStock)) {
                    ps.setInt(1, premio.getStock());
                    ps.setString(2, premio.getNombre());
                    ps.setString(3, maquina.getNombre());
                    ps.executeUpdate();
                }
            }
            con.commit();
        } catch (SQLException e) {
            Conexion.deshacer(con);
            throw new PersistenciaException("No se pudo guardar la partida: " + e.getMessage(), e);
        } finally {
            Conexion.restaurarAutoCommit(con);
        }
    }

    private PremioPorMaquina[] premiosDe(Maquina maquina) {
        if (maquina instanceof MaquinaGarra) {
            return ((MaquinaGarra) maquina).getPremios();
        }
        if (maquina instanceof MaquinaRuleta) {
            return ((MaquinaRuleta) maquina).getPremios();
        }
        return new PremioPorMaquina[0]; // Carreras y Disparos no entregan premios
    }
}
