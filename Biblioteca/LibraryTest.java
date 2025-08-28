package Biblioteca;

import java.util.Scanner;

public class LibraryTest {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int opc;
        do {
            opc = mostrarMenuPrincipal(leer);
            switch (opc) {
                case 1:
                    mostrarMenuGestionLibros(leer);
                    break;
                case 2:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opc != 2);
    }

    //Metodo para mostrar el menu principal
    public static int mostrarMenuPrincipal(Scanner leer){
        int opc;
        System.out.println("----- Menú Principal -----");
        System.out.println("1. Gestion de Libros");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opción: ");
        opc = leer.nextInt();
        return opc;
    }

    //Metodo para mostrar el menu de gestion de libros
    public static int mostrarMenuGestionLibros(Scanner leer){
        int opc;
        System.out.println("----- Gestión de Libros -----");
        System.out.println("1. Añadir Libro");
        System.out.println("2. Listar Libros");
        System.out.println("3. Buscar Libro por Título");
        System.out.println("4. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
        opc = leer.nextInt();
        return opc;
    }
}
