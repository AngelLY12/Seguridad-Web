package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1/celulares";
    private static final String DB_USER = "angel";
    private static final String DB_PASSWORD = "123";
    private Connection con;

    public void conectar() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Conexion establecida");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error con el driver o la conexión: " + e.getMessage());
        } 
    }
    
    public void desconectar() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Desconexion de la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error al desconectar: " + e.getMessage());
        }
    }
    
    public Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                conectar();
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la conexión: " + e.getMessage());
        }
        return con;
    }
}
