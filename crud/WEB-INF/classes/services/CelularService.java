package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.connectionDB;
import interfaces.Celulares;
import modelo.Celular;

public class CelularService implements Celulares {
    private final connectionDB db = new connectionDB();

    @Override
    public String añadirCelular(Celular celular) {
        String query = "INSERT INTO celular (imei, nombre, marca, anoLanzamiento) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = db.getConnection().prepareStatement(query);
            stmt.setLong(1, celular.getImei());
            stmt.setString(2, celular.getNombre());
            stmt.setString(3, celular.getMarca());
            stmt.setInt(4, celular.getAnoLanzamiento());
            stmt.executeUpdate();
            return "Celular añadido correctamente";
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) { 
                return "El celular ya se encuentra en la base de datos";
            }
            return "Error al añadir celular: " + e.getMessage();
        } finally {
            try {
                if (stmt != null) stmt.close();
                db.desconectar();;
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Celular> verCelulares() {
        List<Celular> celulares = new ArrayList<>();
        String query = "SELECT * FROM celular";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = db.getConnection().prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Celular celular = new Celular();
                celular.setImei(rs.getLong("imei"));
                celular.setNombre(rs.getString("nombre"));
                celular.setMarca(rs.getString("marca"));
                celular.setAnoLanzamiento(rs.getInt("anoLanzamiento"));
                celulares.add(celular);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.desconectar();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return celulares;
    }

    @Override
    public boolean findByImei(Long imei) {
        String query = "SELECT imei FROM celular WHERE imei = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = db.getConnection().prepareStatement(query);
            stmt.setLong(1, imei);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.desconectar();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    @Override
    public String modificarCelular(Celular nuevoCelular) {
        String query = "UPDATE celular SET nombre = ?, marca = ?, anoLanzamiento = ? WHERE imei = ?";
        PreparedStatement stmt = null;
        try {
            
            stmt = db.getConnection().prepareStatement(query);
            stmt.setString(1, nuevoCelular.getNombre());
            stmt.setString(2, nuevoCelular.getMarca());
            stmt.setInt(3, nuevoCelular.getAnoLanzamiento());
            stmt.setLong(4, nuevoCelular.getImei());
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                return "Celular modificado correctamente";
            } else {
                return "No se pudo modificar el celular";
            }
        } catch (SQLException e) {
            return "Error al modificar el celular: " + e.getMessage();
        } finally {
            try {
                if (stmt != null) stmt.close();
                db.getConnection();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    @Override
    public String eliminarCelular(Long imei) {
        String query = "DELETE FROM celular WHERE imei = ?";
        PreparedStatement stmt = null;
        try {
            stmt = db.getConnection().prepareStatement(query);
            stmt.setLong(1, imei);
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                return "Celular eliminado correctamente";
            } else {
                return "No se encontró un celular con el IMEI proporcionado";
            }
        } catch (SQLException e) {
            return "Error al eliminar el celular: " + e.getMessage();
        } finally {
            try {
                if (stmt != null) stmt.close();
                db.desconectar();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
    @Override
    public Celular findCelularByImei(Long imei){
        String query = "SELECT nombre,marca,anoLanzamiento FROM celular where imei = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Celular celular = new Celular();
        try {
            stmt = db.getConnection().prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                celular.setNombre(rs.getString("nombre"));
                celular.setMarca(rs.getString("marca"));
                celular.setAnoLanzamiento(rs.getInt("anoLanzamiento"));
                stmt.setLong(1, imei);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.desconectar();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return celular;
    }
}
