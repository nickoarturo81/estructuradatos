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
        leer.close();                   // <-- Cerrar el scanner para evitar fugas de memoria
    }

    //Método para mostrar el menu principal.
    public static int mostrarMenuPrincipal(Scanner leer){
        System.out.println("----- Menú Principal -----");
        System.out.println("1. Gestion de Libros");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opción: ");
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
                    mostrarTodosLibros(biblioteca);
                    break;
                case 6:
                    mostrarLibrosDisponibles(biblioteca);
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

    // 1: Metodo para añadir un libro a partir del metodo anadirLibro de la clase Library
    public static void anadirLibroMenu(Library biblioteca, Scanner leer) {
        leer.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el título del libro: ");
        String titulo = leer.nextLine();
        System.out.print("Ingrese el autor del libro: ");
        String autor = leer.nextLine();
        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = leer.nextLine();

        Book libro = new Book(titulo, autor, isbn, true);

        if (biblioteca.anadirLibro(libro)) {
            System.out.println("Libro añadido: " + libro);
        } else {
            System.out.println("No se puede añadir el libro. Capacidad máxima alcanzada.");
        }
    }

    // 2: Método para eliminar un libro a partir del método eliminarLibro de la clase Library
    public static void eliminarLibroMenu(Library biblioteca, Scanner leer) {
        leer.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el ISBN del libro a eliminar: ");
        String isbn = leer.nextLine();

        String tituloEliminado = biblioteca.eliminarLibro(isbn);

        if (tituloEliminado != null) {
            System.out.println("Libro eliminado: " + tituloEliminado + " Total libros: " + biblioteca.getTodosLibros().size() + ")");
        } else {
            System.out.println("Libro con ISBN " + isbn + " no encontrado.");
        }
    }

    // 3: Método para prestar un libro a partir del método prestarLibro de la clase Library
    public static void prestarLibroMenu(Library biblioteca, Scanner leer) {
        leer.nextLine(); // Limpiar el buffer

        //Validar si hay libros registrados
        if (biblioteca.getTodosLibros().isEmpty()) {
            System.out.println("No hay ningún libro registrado en la biblioteca para prestar.");
            return;
        }

        System.out.print("Ingrese el ISBN del libro a prestar: ");
        String isbn = leer.nextLine();

        String resultado = biblioteca.prestarLibro(isbn);

        if (resultado == null) {
            System.out.println("Libro con ISBN " + isbn + " no fue encontrado.");
        } else if (resultado.equals("")) {
            System.out.println("El libro con ISBN " + isbn + " ya está prestado y no está disponible.");
        } else {
            System.out.println("Libro prestado: " + resultado);
        }
    }

    // 4: Metodo para regresar un libro a partir del metodo regresarLibro de la clase Library
    public static void regresarLibroMenu(Library biblioteca, Scanner leer) {
        leer.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el ISBN del libro a regresar: ");
        String isbn = leer.nextLine();
        biblioteca.regresarLibro(isbn);
        
    }

    // 5. Método para mostrar todos los libros a partir del metodo getTodosLibros de la clase Library
    public static void mostrarTodosLibros(Library biblioteca) {
        System.out.println("===== Todos los Libros ====");
        if (biblioteca.getTodosLibros().isEmpty()) {
            System.out.println("No hay libros en la biblioteca.");
        } else {
            for (int i = 0; i < biblioteca.getTodosLibros().size(); i++) {
                Book libro = biblioteca.getTodosLibros().get(i);
                System.out.println((i + 1) + ". " + libro);
            }
        }
        System.out.println("============================");
    }

    // 6. Método para mostrar solo los libros disponibles a partir del metodo getLibrosDisponibles de la clase Library
    public static void mostrarLibrosDisponibles(Library biblioteca) {
        System.out.println("===== Libros Disponibles ====");
        if (biblioteca.getLibrosDisponibles().isEmpty()) {
            System.out.println("No hay libros disponibles en la biblioteca.");
        } else {
            for (int i = 0; i < biblioteca.getLibrosDisponibles().size(); i++) {
                Book libro = biblioteca.getLibrosDisponibles().get(i);
                System.out.println((i + 1) + ". " + libro);
            }
        }
        System.out.println("============================");
    }  

    // 7. Metodo para buscar un libro a partir del metodo buscarTitulo de la clase Library
    public static void buscarLibroMenu(Library biblioteca, Scanner leer) {
        leer.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el título del libro a buscar: ");
        String titulo = leer.nextLine();
        Book libro = biblioteca.buscarTitulo(titulo);

        if (libro != null) {                                                                        // <-- Si el libro es diferente a nulo, es decir, que hay datos
            System.out.println("Libro encontrado: " + libro);                                       
        } else {
            System.out.println("Libro con título '" + titulo + "' no encontrado.");                 // <-- Si no, el libro es nulo, es decir, que no hay datos
        }
    }
}