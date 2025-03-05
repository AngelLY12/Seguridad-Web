package connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connectionDB {
    private Connection con;
    private Properties properties = new Properties();
    private String DB_DRIVER,DB_URL,DB_USER,DB_PASSWORD;

    public connectionDB() {
        File file = new File("C:\\Users\\lopey\\Downloads\\apache-tomcat-10.1.31-windows-x64\\apache-tomcat-10.1.31\\webapps\\crud\\database.properties");
        if (file.exists()) {
            try (FileInputStream in = new FileInputStream(file)) {
                properties.load(in);
                DB_DRIVER=properties.getProperty("db.driver");
                DB_URL=properties.getProperty("db.url");
                DB_USER=properties.getProperty("db.user");
                DB_PASSWORD=properties.getProperty("db.password");
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al cargar el archivo de propiedades");
            }
        } else {
            System.err.println("El archivo no existe: " + file.getAbsolutePath());
            throw new RuntimeException("El archivo de propiedades no se encuentra");
        }
    }
    


    public void conectar() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName(DB_DRIVER);
                
                con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
                System.out.println("Conexión establecida");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Ocurrió un error con el driver o la conexión: " + e.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Desconexión de la base de datos");
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

