package umg.edu.Conexión;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class clsConexión {
    Connection conn = null;

    String user = "root";
    String password = "brandon123";
    String url = "jdbc:mysql://localhost:3306/tarea";

    public Connection establecerconexion() {
        try {
            // Establecer la conexión
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida");

            // Ejecutar una consulta
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tabla");

            // Procesar y mostrar los resultados
            while (resultSet.next()) {
                System.out.println(resultSet.getString("ID") + " | " + resultSet.getString("Nombre_y_Apellido") + " | " + resultSet.getString("Fecha_de_Registro") + " | " + resultSet.getString("Sueldo_base") + " | " + resultSet.getString("Sexo") + " | " + resultSet.getString("Edad") + " | " + resultSet.getString("Longitud_latitud") + " | " + resultSet.getString("Comentarios"));
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        } finally {
            // Cerrar la conexión y los recursos
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return conn;
    }
}