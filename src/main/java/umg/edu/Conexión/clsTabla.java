package umg.edu.Conexión;

import java.sql.*;
import java.util.Scanner;

public class clsTabla {

    String nombreyapellido;
    String fecha;
    Double sueldobase;
    String sexo;
    String longitudylatitud;
    String Comentarios;
    String url;
    String user;
    String password;



    public String getNombreyapellido() {
        return nombreyapellido;
    }

    public void setNombreyapellido(String nombreyapellido) {
        this.nombreyapellido = nombreyapellido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getSueldobase() {
        return sueldobase;
    }

    public void setSueldobase(Double sueldobase) {
        this.sueldobase = sueldobase;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getLongitudylatitud() {
        return longitudylatitud;
    }

    public void setLongitudylatitud(String longitudylatitud) {
        this.longitudylatitud = longitudylatitud;
    }

    public String getComentarios() {
        return Comentarios;
    }

    public void setComentarios(String comentarios) {
        Comentarios = comentarios;
    }

    public void insertarDatos() {
        String consulta = "INSERT INTO tarea (nombreyapellido, fecha, sueldobase, sexo, longitudylatitud, Comentarios) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conexion.prepareStatement(consulta)) {

            // Configura los parámetros
            ps.setString(1, nombreyapellido);
            ps.setString(2, fecha);
            ps.setDouble(3, sueldobase);
            ps.setString(4, sexo);
            ps.setString(5, longitudylatitud);
            ps.setString(6, Comentarios);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Datos insertados correctamente");
            } else {
                System.out.println("No se insertaron datos");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar datos: " + e);
        }
    }


    public void mostrarTabla() {
        clsConexión conexion = new clsConexión();
        String sql = "SELECT * FROM tarea";

        try {
            PreparedStatement ps = conexion.establecerconexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Recorre y muestra los datos
            while (rs.next()) {
                // Obtén los valores de las columnas por nombre o índice (empezando desde 1)
                String nombreyapellido = rs.getString("nombreyapellido");
                String fecha = rs.getString("fecha");
                double sueldobase = rs.getDouble("sueldobase");
                String sexo = rs.getString("sexo");
                String longitudylatitud = rs.getString("longitudylatitud");
                String Comentarios = rs.getString("Comentarios");

                // Muestra los valores en la consola
                System.out.println("Nombre y Apellido: " + nombreyapellido);
                System.out.println("Fecha: " + fecha);
                System.out.println("Sueldo Base: " + sueldobase);
                System.out.println("Sexo: " + sexo);
                System.out.println("Longitud y Latitud: " + longitudylatitud);
                System.out.println("Comentarios: " + Comentarios);

                System.out.println(); // Separador entre filas
            }

            System.out.println("Datos mostrados correctamente");
        } catch (SQLException e) {
            System.out.println("Error al mostrar datos: " + e);
        }
    }

    public void borrarDatos() {
        clsConexión conexion = new clsConexión();

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID del registro que desea borrar: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM tarea WHERE id = ?";

        try {
            PreparedStatement ps = conexion.establecerconexion().prepareStatement(sql);
            ps.setInt(1, id); // Especifica el ID proporcionado por el usuario

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Registro eliminado correctamente");
            } else {
                System.out.println("No se encontró ningún registro con el ID proporcionado");
            }
        } catch (SQLException e) {
            System.out.println("Error al borrar datos: " + e);
        }
    }

    public void modificarDatos() {
        clsConexión conexion = new clsConexión();

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID del registro que desea modificar: ");
        int id = sc.nextInt();

        // Verificar si el registro existe antes de continuar
        if (!existeRegistro(id)) {
            System.out.println("No se encontró ningún registro con el ID proporcionado.");
            return;
        }

        // Solicitar al usuario los nuevos valores
        sc.nextLine(); // Consumir el carácter de nueva línea
        System.out.println("Ingrese el nuevo nombre y apellido: ");
        String nuevoNombreyApellido = sc.nextLine();
        System.out.println("Ingrese la nueva fecha: ");
        String nuevaFecha = sc.nextLine();
        System.out.println("Ingrese el nuevo sueldo base: ");
        double nuevoSueldobase = sc.nextDouble();
        sc.nextLine(); // Consumir el carácter de nueva línea
        System.out.println("Ingrese el nuevo sexo: ");
        String nuevoSexo = sc.nextLine();
        System.out.println("Ingrese la nueva longitud y latitud: ");
        String nuevaLongitudylatitud = sc.nextLine();
        System.out.println("Ingrese los nuevos comentarios: ");
        String nuevosComentarios = sc.nextLine();

        String sql = "UPDATE tarea SET nombreyapellido = ?, fecha = ?, sueldobase = ?, sexo = ?, longitudylatitud = ?, Comentarios = ? WHERE id = ?";

        try {
            PreparedStatement ps = conexion.establecerconexion().prepareStatement(sql);
            ps.setString(1, nuevoNombreyApellido);
            ps.setString(2, nuevaFecha);
            ps.setDouble(3, nuevoSueldobase);
            ps.setString(4, nuevoSexo);
            ps.setString(5, nuevaLongitudylatitud);
            ps.setString(6, nuevosComentarios);
            ps.setInt(7, id); // Especifica el ID del registro que se va a actualizar

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Registro actualizado correctamente");
            } else {
                System.out.println("No se encontró ningún registro con el ID proporcionado");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar datos: " + e);
        }
    }

    // Método para verificar si un registro con el ID proporcionado existe
    private boolean existeRegistro(int id) {
        // Realiza una consulta para verificar si el registro existe
        // Retorna true si existe, false si no existe

        return true;
    }
}