package Biblioteca;

import java.util.Scanner;

public class LibraryUI {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        Library biblioteca = new Library(50);
        String opc;
        do {
            opc = mostrarMenuPrincipal(leer);
            switch (opc) {
                case "1":
                    mostrarMenuGestionLibros(biblioteca, leer);
                    break;
                case "2":
                    System.out.println(" Saliendo del programa...");
                    break;
                default:
                    System.out.println(" Opción no válida. Intente de nuevo.");
            }
        } while (opc != "2");
        leer.close();                   // <-- Cerrar el scanner para evitar fugas de memoria
    }

    //Método para mostrar el menu principal.
    public static String mostrarMenuPrincipal(Scanner leer){
        System.out.println("╔════════════════════════════╗");
        System.out.println("║       MENÚ PRINCIPAL       ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║ 1. Gestión de libros       ║");
        System.out.println("║ 2. Salir                   ║");
        System.out.println("╚════════════════════════════╝");
        return leer.nextLine();
    }

    //Metodo para mostrar el menu de gestion de libros
    public static void mostrarMenuGestionLibros(Library biblioteca, Scanner leer){
        String opc;
        do{
            System.out.println(" ╔═════════════════════════════════════╗");
            System.out.println(" ║        MENÚ GESTIÓN DE LIBROS       ║");
            System.out.println(" ╠═════════════════════════════════════╣");
            System.out.println(" ║ 1. Añadir Libro                     ║");
            System.out.println(" ║ 2. Eliminar Libro                   ║");
            System.out.println(" ║ 3. Prestar Libro                    ║");
            System.out.println(" ║ 4. Regresar Libro                   ║");
            System.out.println(" ║ 5. Mostrar Todos los Libros         ║");
            System.out.println(" ║ 6. Mostrar Solo Libros Disponibles  ║");
            System.out.println(" ║ 7. Buscar Libro por Título          ║");
            System.out.println(" ║ 8. Volver al Menú Principal         ║");
            System.out.println(" ║ Seleccione una opción:              ║");
            System.out.println(" ╚═════════════════════════════════════╝");
            opc = leer.nextLine();
            switch (opc) {
                case "1":
                    anadirLibroMenu(biblioteca, leer);
                    break;
                case "2":
                    eliminarLibroMenu(biblioteca, leer);
                    break;
                case "3":
                    prestarLibroMenu(biblioteca, leer);
                    break;
                case "4":
                    regresarLibroMenu(biblioteca, leer);
                    break;
                case "5":
                    mostrarTodosLibros(biblioteca);
                    break;
                case "6":
                    mostrarLibrosDisponibles(biblioteca);
                    break;
                case "7":
                    buscarLibroMenu(biblioteca, leer);
                    break;
                case "8":
                    System.out.println("↩️ Volviendo al Menú Principal...");
                break;
                default:
                System.out.println("⚠️ Opción no válida. Intente de nuevo.");
            }
        } while (opc != "8"); {
            
        }

    }

    // 1: Metodo para añadir un libro a partir del metodo anadirLibro de la clase Library
    public static void anadirLibroMenu(Library biblioteca, Scanner leer) {
        leer.nextLine(); // Limpiar el buffer
        System.out.println();
        System.out.print("Ingrese el título del libro: ");
        String titulo = leer.nextLine();
        System.out.println();
        System.out.print("Ingrese el autor del libro: ");
        String autor = leer.nextLine();
        System.out.println();
        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = leer.nextLine();

        Book libro = new Book(titulo, autor, isbn, true);

        if (biblioteca.anadirLibro(libro)) {
            System.out.println("=====================================================================");
            System.out.println("  📗 Libro añadido: " + libro);
            System.out.println("=====================================================================");
        } else {
            System.out.println("===========================================================");
            System.out.println("= 🚫 No se puede añadir el libro. Capacidad máxima alcanzada =");
            System.out.println("===========================================================");
        }
    }

    // 2: Método para eliminar un libro a partir del método eliminarLibro de la clase Library
    public static void eliminarLibroMenu(Library biblioteca, Scanner leer) {
        System.out.println();
        System.out.print("Ingrese el ISBN del libro a eliminar: ");
        String isbn = leer.nextLine();

        String tituloEliminado = biblioteca.eliminarLibro(isbn);

        if (tituloEliminado != null) {
            System.out.println("================================================================================================");
            System.out.println("Libro eliminado: " + tituloEliminado + " Total libros: " + biblioteca.getTodosLibros().size() + ")");
            System.out.println("================================================================================================");
        } else {
            System.out.println("========================================");
            System.out.println("Libro con ISBN " + isbn + " no encontrado.");
            System.out.println("========================================");
        }
    }

    // 3: Método para prestar un libro a partir del método prestarLibro de la clase Library
    public static void prestarLibroMenu(Library biblioteca, Scanner leer) {
        //Validar si hay libros registrados
        if (biblioteca.getTodosLibros().isEmpty()) {
            System.out.println("=================================================================");
            System.out.println(" 🚫 No hay ningún libro registrado en la biblioteca para prestar.");
            System.out.println("=================================================================");
            return;
        }
        System.out.println();
        System.out.println();
        System.out.print("Ingrese el ISBN del libro a prestar: ");
        String isbn = leer.nextLine();

        String resultado = biblioteca.prestarLibro(isbn);

        if (resultado == null) {
            System.out.println("============================================");
            System.out.println("Libro con ISBN " + isbn + " no fue encontrado.");
            System.out.println("============================================");
        } else if (resultado.equals("")) {
            System.out.println("===================================================================");
            System.out.println("El libro con ISBN " + isbn + " ya está prestado y no está disponible.");
            System.out.println("===================================================================");
        } else {
            System.out.println("============================");
            System.out.println("Libro prestado: " + resultado);
            System.out.println("============================");
        }
    }

    // 4: Método para regresar un libro a partir del método regresarLibro de la clase Library
    public static void regresarLibroMenu(Library biblioteca, Scanner leer) {
        // Validar si todos los libros están disponibles
        if (biblioteca.getLibrosDisponibles().size() == biblioteca.getTodosLibros().size()) {
        System.out.println("No hay libros prestados para regresar.");
        return;
        }
        System.out.println();
        System.out.print("Ingrese el ISBN del libro a regresar: ");
        String isbn = leer.nextLine();

        // Buscar el libro en todos los libros
        Book libroEncontrado = null;
        for (int i = 0; i < biblioteca.getTodosLibros().size(); i++) {
            Book libro = biblioteca.getTodosLibros().get(i);
            if (libro.getIsbn().equals(isbn)) {
                libroEncontrado = libro;
                break;
            }
        }

        // CEn caso de que el libro no exista
        if (libroEncontrado == null) {
            System.out.println("=================================================");
            System.out.println("El libro no está en el sistema o no se encuentra.");
            System.out.println("=================================================");
            return;
        }

        // Si el libro existe, verificar si está prestado y regresarlo
        if (!libroEncontrado.isDisponible()) {
            libroEncontrado.setDisponible(true);
            System.out.println("=================================================");
            System.out.println("Libro regresado: " + libroEncontrado.getTitulo());
            System.out.println("=================================================");
        } else {
            System.out.println("=============================");
            System.out.println("Ese libro no estaba prestado.");
            System.out.println("=============================");
        }
    }

    // 5. Método para mostrar todos los libros a partir del metodo getTodosLibros de la clase Library
    public static void mostrarTodosLibros(Library biblioteca) {
        System.out.println("================================");
        System.out.println("        Todos los Libros        ");
        System.out.println("================================");
        if (biblioteca.getTodosLibros().isEmpty()) {
            System.out.println("===============================");
            System.out.println("No hay libros en la biblioteca ");
            System.out.println("===============================");
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
        System.out.println("================================");
        System.out.println("        Libros Disponibles      ");
        System.out.println("================================");
        if (biblioteca.getLibrosDisponibles().isEmpty()) {
            System.out.println("===========================================");
            System.out.println("No hay libros disponibles en la biblioteca.");
            System.out.println("===========================================");
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
        System.out.print("Ingrese el título del libro a buscar: ");
        String titulo = leer.nextLine();
        Book libro = biblioteca.buscarTitulo(titulo);

        if (libro != null) {
            System.out.println("===========================================================================");                             // <-- Si el libro es diferente a nulo, es decir, que hay datos
            System.out.println("Libro encontrado: " + libro);
            System.out.println("===========================================================================");
        } else {
            System.out.println("==============================================");
            System.out.println("Libro con título '" + titulo + "' no encontrado.");
            System.out.println("==============================================");                 // <-- Si no, el libro es nulo, es decir, que no hay datos
        }
    }
}