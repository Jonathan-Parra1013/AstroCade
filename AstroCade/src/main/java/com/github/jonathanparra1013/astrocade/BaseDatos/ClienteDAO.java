package com.github.jonathanparra1013.astrocade.BaseDatos;

import com.github.jonathanparra1013.astrocade.LogicaAstroCade.Basica;
import com.github.jonathanparra1013.astrocade.LogicaAstroCade.Cliente;
import com.github.jonathanparra1013.astrocade.LogicaAstroCade.Diamond;
import com.github.jonathanparra1013.astrocade.GestorClientes;
import com.github.jonathanparra1013.astrocade.LogicaAstroCade.Gold;
import com.github.jonathanparra1013.astrocade.LogicaAstroCade.Tarjeta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

// Acceso a datos de los clientes y sus tarjetas (tablas usuarios, tarjetas y premios_redimidos). */
public class ClienteDAO {

    // Carga todos los clientes con su tarjeta y los premios que ya redimieron. */
    public List<GestorClientes.ClienteConTarjeta> listar() throws PersistenciaException {
        String sqlClientes = "SELECT u.id, u.nombre, u.email, u.fecha_nacimiento, "
                + "t.id_tarjeta, t.tipo, t.saldo_jugadas, t.saldo_tickets "
                + "FROM usuarios u JOIN tarjetas t ON t.id_cliente = u.id "
                + "WHERE u.rol = 'CLIENTE' ORDER BY u.id";
        String sqlPremios = "SELECT r.id_cliente, p.nombre "
                + "FROM premios_redimidos r JOIN premios p ON p.id = r.id_premio ORDER BY r.id";

        List<GestorClientes.ClienteConTarjeta> lista = new ArrayList<>();
        Map<String, Cliente> clientesPorId = new HashMap<>();
        try {
            Connection con = Conexion.getConexion();
            try (PreparedStatement ps = con.prepareStatement(sqlClientes);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente(rs.getString("id"), rs.getString("nombre"),
                            rs.getString("email"), rs.getDate("fecha_nacimiento"));
                    Tarjeta tarjeta = crearTarjeta(rs.getString("tipo"), rs.getString("id_tarjeta"));
                    tarjeta.recargarSaldo(rs.getInt("saldo_jugadas"));
                    tarjeta.agregarTickets(rs.getInt("saldo_tickets"));
                    lista.add(new GestorClientes.ClienteConTarjeta(cliente, tarjeta));
                    clientesPorId.put(cliente.getId(), cliente);
                }
            }
            try (PreparedStatement ps = con.prepareStatement(sqlPremios);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = clientesPorId.get(rs.getString(1));
                    if (cliente != null) {
                        cliente.agregarPremio(rs.getString(2));
                    }
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudieron cargar los clientes. "
                    + "Revise que MySQL (XAMPP) esté encendido.", e);
        }
        return lista;
    }

    //Guarda un cliente nuevo junto con su tarjeta (las dos inserciones van en una sola transacción)
    public void insertar(Cliente cliente, Tarjeta tarjeta) throws PersistenciaException {
        String sqlUsuario = "INSERT INTO usuarios (id, nombre, email, rol, fecha_nacimiento) "
                + "VALUES (?, ?, ?, 'CLIENTE', ?)";
        String sqlTarjeta = "INSERT INTO tarjetas (id_tarjeta, tipo, saldo_jugadas, saldo_tickets, id_cliente) "
                + "VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sqlUsuario)) {
                ps.setString(1, cliente.getId());
                ps.setString(2, String.valueOf(cliente.getNombre()));
                ps.setString(3, String.valueOf(cliente.getEmail()));
                java.util.Date nacimiento = cliente.getFechaNacimiento();
                ps.setDate(4, nacimiento == null ? null : new java.sql.Date(nacimiento.getTime()));
                ps.executeUpdate();
            }
            try (PreparedStatement ps = con.prepareStatement(sqlTarjeta)) {
                ps.setString(1, tarjeta.getNumeroTarjeta());
                ps.setString(2, tipoDe(tarjeta));
                ps.setInt(3, tarjeta.getSaldoJugadas());
                ps.setInt(4, tarjeta.getSaldoTickets());
                ps.setString(5, cliente.getId());
                ps.executeUpdate();
            }
            con.commit();
        } catch (SQLIntegrityConstraintViolationException e) {
            Conexion.deshacer(con);
            throw new PersistenciaException("Ya existe un usuario o una tarjeta con ese id o ese correo.", e);
        } catch (SQLException e) {
            Conexion.deshacer(con);
            throw new PersistenciaException("No se pudo registrar el cliente: " + e.getMessage(), e);
        } finally {
            Conexion.restaurarAutoCommit(con);
        }
    }

    // Guarda en la base el saldo de jugadas y los tickets actuales de la tarjeta
    public void actualizarTarjeta(Tarjeta tarjeta) throws PersistenciaException {
        try {
            actualizarTarjeta(Conexion.getConexion(), tarjeta);
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo guardar el saldo de la tarjeta: " + e.getMessage(), e);
        }
    }

    // Versión que usan los otros DAO para incluir la tarjeta dentro de su propia transacción. 
    static void actualizarTarjeta(Connection con, Tarjeta tarjeta) throws SQLException {
        String sql = "UPDATE tarjetas SET saldo_jugadas = ?, saldo_tickets = ? WHERE id_tarjeta = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, tarjeta.getSaldoJugadas());
            ps.setInt(2, tarjeta.getSaldoTickets());
            ps.setString(3, tarjeta.getNumeroTarjeta());
            ps.executeUpdate();
        }
    }

    //Convierte la clase de la tarjeta en el texto que se guarda en la columna "tipo"
    private static String tipoDe(Tarjeta tarjeta) {
        // Basica -> BASICA, Gold -> GOLD, Diamond -> DIAMOND (mismos valores del ENUM de la base)
        return tarjeta.getClass().getSimpleName().toUpperCase(Locale.ROOT);
    }

    // Crea el objeto del tipo de tarjeta que corresponde al texto guardado en la base
    private static Tarjeta crearTarjeta(String tipo, String idTarjeta) throws SQLException {
        switch (tipo) {
            case "BASICA":
                return new Basica(idTarjeta);
            case "GOLD":
                return new Gold(idTarjeta);
            case "DIAMOND":
                return new Diamond(idTarjeta);
            default:
                throw new SQLException("Tipo de tarjeta desconocido: " + tipo);
        }
    }
}
