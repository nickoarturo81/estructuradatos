package Biblioteca;

import java.util.Scanner;

public class LibraryUI {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        Library biblioteca = new Library(100);
        int opc;
        do {
            opc = mostrarMenuPrincipal(leer);
            switch (opc) {
                case 1:
                    mostrarMenuGestionLibros(biblioteca, leer);
                    break;
                case 2:
                    System.out.println(" Saliendo del programa...");
                    break;
                default:
                    System.out.println(" Opción no válida. Intente de nuevo.");
            }
        } while (opc != 2);
    }

    //Método para mostrar el menu principal.
    public static int mostrarMenuPrincipal(Scanner leer){
        //int opc;
        System.out.println("----- Menú Principal -----");
        System.out.println("1. Gestion de Libros");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opción: ");
        //opc = leer.nextInt();
        //return opc;
        return leer.nextInt();
    }

    //Metodo para mostrar el menu de gestion de libros
    public static void mostrarMenuGestionLibros(Library biblioteca, Scanner leer){
        int opc;
        do{
            System.out.println("----- Menú de Gestión de Libros -----");
            System.out.println("1. Añadir Libro");
            System.out.println("2. Eliminar Libro");
            System.out.println("3. Prestar Libro");
            System.out.println("4. Regresar Libro");
            System.out.println("5. Mostrar Todos los Libros");
            System.out.println("6. Mostrar Solo Libros Disponibles");
            System.out.println("7. Buscar Libro por Título");
            System.out.println("8. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opc = leer.nextInt();

            switch (opc) {
                case 1:
                    anadirLibroMenu(biblioteca, leer);
                    break;
                case 2:
                    eliminarLibroMenu(biblioteca, leer);
                    break;
                case 3:
                    prestarLibroMenu(biblioteca, leer);
                    break;
                case 4:
                    regresarLibroMenu(biblioteca, leer);
                    break;
                case 5:
                    biblioteca.mostrarTodosLibros();
                    break;
                case 6:
                    biblioteca.mostrarLibrosDisponibles();
                    break;
                case 7:
                    buscarLibroMenu(biblioteca, leer);
                    break;
                case 8:
                    System.out.println("Volviendo al Menú Principal...");
                break;
                default:
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opc != 8); {
            
        }

    }

    //Metodo para añadir un libro a partir del metodo anadirLibro de la clase Library
    public static void anadirLibroMenu(Library biblioteca, Scanner leer) {
        leer.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el título del libro: ");
        String titulo = leer.nextLine();
        System.out.print("Ingrese el autor del libro: ");
        String autor = leer.nextLine();
        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = leer.nextLine();

        Book libro = new Book(titulo, autor, isbn, true);
        biblioteca.anadirLibro(libro);
    }

    //Metodo para eliminar un libro a partir del metodo eliminarLibro de la clase Library
    public static void eliminarLibroMenu(Library biblioteca, Scanner leer) {
        leer.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el ISBN del libro a eliminar: ");
        String isbn = leer.nextLine();
        biblioteca.eliminarLibro(isbn);
    }

    //Metodo para prestar un libro a partir del metodo prestarLibro de la clase Library
    public static void prestarLibroMenu(Library biblioteca, Scanner leer) {
        leer.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el ISBN del libro a prestar: ");
        String isbn = leer.nextLine();
        biblioteca.prestarLibro(isbn);

    }

    //Metodo para regresar un libro a partir del metodo regresarLibro de la clase Library
    public static void regresarLibroMenu(Library biblioteca, Scanner leer) {
        leer.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el ISBN del libro a regresar: ");
        String isbn = leer.nextLine();
        biblioteca.regresarLibro(isbn);
    }

    //Metodo para buscar un libro a partir del metodo buscarTitulo de la clase Library
    public static void buscarLibroMenu(Library biblioteca, Scanner leer) {
        leer.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el título del libro a buscar: ");
        String titulo = leer.nextLine();
        Book libro = biblioteca.buscarTitulo(titulo);
    }
}