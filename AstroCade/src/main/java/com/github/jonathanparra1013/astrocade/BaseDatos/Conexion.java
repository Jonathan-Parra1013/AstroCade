package com.github.jonathanparra1013.astrocade.BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/astrocade_db?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Por defecto en XAMPP está vacía

    private static Connection conexion = null;

    private Conexion() {
    }

    // Devuelve la conexión única; la crea si aún no existe o si se cerró. 
    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conexion;
    }

    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    // Deshace una transacción a medias (usado por los DAO cuando algo falla)
    static void deshacer(Connection con) {
        try {
            if (con != null) {
                con.rollback();
            }
        } catch (SQLException e) {
            System.err.println("No se pudo deshacer la transacción: " + e.getMessage());
        }
    }

    // Vuelve al modo normal (cada sentencia se guarda sola) al terminar una transacción. 
    static void restaurarAutoCommit(Connection con) {
        try {
            if (con != null) {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.err.println("No se pudo restaurar el autocommit: " + e.getMessage());
        }
    }
}
