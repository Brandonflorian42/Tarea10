package umg.edu;


import umg.edu.Conexión.clsTabla;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc2 = new Scanner(System.in);
        System.out.println("\t\t\t REGISTRO DE DATOS.");
        System.out.println("\t\t\t ------------------");

        int opcion;
        do {
            System.out.println("Seleccione lo que desea hacer: ");
            System.out.println("1. Ingresar datos.");
            System.out.println("2. Mostrar datos.");
            System.out.println("3. Eliminar datos.");
            System.out.println("4. Modificar datos.");
            System.out.println("5. Salir.");
            opcion = sc2.nextInt();


            clsTabla tabla = new clsTabla();


            switch (opcion) {

                case 1:

                    System.out.println("\t\t\t Ingreso de datos.");
                    Scanner sc = new Scanner(System.in);
                    System.out.println("\nIngrese el nombre y el apellido: ");
                    tabla.setNombreyapellido(sc.nextLine());
                    System.out.println("Ingrese la fecha: ");
                    tabla.setFecha(sc.nextLine());
                    System.out.println("Ingrese el sueldo base: ");
                    tabla.setSueldobase(sc.nextDouble());

// Consumir el carácter de nueva línea restante
                    sc.nextLine();

                    System.out.println("Ingrese el sexo: ");
                    tabla.setSexo(sc.nextLine());
                    System.out.println("Ingrese la longitud y la latitud: ");
                    tabla.setLongitudylatitud(sc.nextLine());
                    System.out.println("Ingrese los comentarios: ");
                    tabla.setComentarios(sc.nextLine());
                    tabla.insertarDatos();

                    break;

                case 2:
                    System.out.println("\t\t\t Mostrar datos.");
                    tabla.mostrarTabla();
                    break;

                case 3:
                    System.out.println("\t\t\t Eliminar datos.");
                    tabla.borrarDatos();
                    break;

                case 4:
                    System.out.println("\t\t\t Modificar datos.");
                    tabla.modificarDatos();
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    System.exit(0);

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 5);
    }
}