package Biblioteca;

import java.util.Scanner;
import java.util.LinkedList;

public class LibraryUI {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        Library biblioteca = new Library(50);
        Users users = new Users();
        String opc;
        do {
            opc = mostrarMenuPrincipal(leer);
            switch (opc) {
                case "1":
                    mostrarMenuGestionLibros(biblioteca, leer);
                    break;
                case "2":
                    mostrarMenuGestionUsuarios(users, leer);
                    break;
                case "3":
                    mostrarMenuSistemaPrestamos(biblioteca, leer);
                break;
                case "5":
                    System.out.println(" Saliendo del programa...");
                    break;
                default:
                    System.out.println(" ❌ Opción no válida. Intente de nuevo.");
            }
        } while (!opc.equals("2"));
        leer.close();                                                                                   // Cerrar el scanner para liberar memoria
    }

    //Método para mostrar el menu principal.
    public static String mostrarMenuPrincipal(Scanner leer){
        System.out.println("╔════════════════════════════╗");
        System.out.println("║       MENÚ PRINCIPAL       ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║ 1. Gestión de Libros       ║");
        System.out.println("║ 2. Gestión de Usuarios     ║");
        System.out.println("║ 3. Sistema de Prestamos    ║");
        System.out.println("║ 4. Deshacer                ║");
        System.out.println("║ 5. Salir                   ║");
        System.out.println("╚════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
        return leer.nextLine();
    }

    //Metodo para mostrar el menu de gestion de libros
    public static void mostrarMenuGestionLibros(Library biblioteca, Scanner leer){
        String opc;
        do{
            System.out.println();
            System.out.println(" ╔═════════════════════════════════════╗");
            System.out.println(" ║        MENÚ GESTIÓN DE LIBROS       ║");
            System.out.println(" ╠═════════════════════════════════════╣");
            System.out.println(" ║ 1. Añadir Libro                     ║");
            System.out.println(" ║ 2. Eliminar Libro                   ║");
            System.out.println(" ║ 3. Mostrar Libros                   ║");
            System.out.println(" ║ 4. Mostrar Libros Disponibles       ║");
            System.out.println(" ║ 5. Buscar Libro por Título          ║");
            System.out.println(" ║ 6. Volver al Menú Principal         ║");
            System.out.println(" ╚═════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            
            opc = leer.nextLine();
            switch (opc) {
                case "1":
                    anadirLibroMenu(biblioteca, leer);
                break;
                case "2":
                    eliminarLibroMenu(biblioteca, leer);
                break;
                case "3":
                    mostrarTodosLibros(biblioteca);
                break;
                case "4":
                    mostrarLibrosDisponibles(biblioteca);
                break;
                case "5":
                    buscarLibroMenu(biblioteca, leer);
                break;
                case "6":
                    System.out.println("↩️ Volviendo al Menú Principal...");
                break;
                default:
                System.out.println("❌ Opción no válida. Intente de nuevo.");
            }
        } while (!opc.equals("6")); {
            System.out.println();
        }


    }

    // 1: Metodo para añadir un libro a partir del metodo anadirLibro de la clase Library
    public static void anadirLibroMenu(Library biblioteca, Scanner leer) {
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
            System.out.println();
            System.out.println("===================================================================================================================");
            System.out.println("  ✅ Libro añadido: " + libro);
            System.out.println("===================================================================================================================");
        } else {
            System.out.println();
            System.out.println("==============================================================");
            System.out.println("= 🚨 No se puede añadir el libro. Capacidad máxima alcanzada  ");
            System.out.println("==============================================================");
        }
    }

    // 2: Método para eliminar un libro a partir del método eliminarLibro de la clase Library
    public static void eliminarLibroMenu(Library biblioteca, Scanner leer) {
        System.out.println();
        System.out.print("Ingrese el ISBN del libro a eliminar: ");
        String isbn = leer.nextLine();

        String tituloEliminado = biblioteca.eliminarLibro(isbn);

        if (tituloEliminado != null) {
            System.out.println();
            System.out.println("================================================================================================");
            System.out.println(" 🗑️ Libro eliminado: " + tituloEliminado + " Total libros: " + biblioteca.getTodosLibros().size() + ")");
            System.out.println("================================================================================================");
        } else {
            System.out.println();
            System.out.println("===============================================");
            System.out.println(" 🔎❌ Libro con ISBN " + isbn + " no encontrado ");
            System.out.println("===============================================");
        }
    }

    // 5. Método para mostrar todos los libros a partir del metodo getTodosLibros de la clase Library
    public static void mostrarTodosLibros(Library biblioteca) {
        System.out.println();
        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║       Todos los Libros        ║");
        System.out.println("╚═══════════════════════════════╝");
        if (biblioteca.getTodosLibros().isEmpty()) {
            System.out.println();
            System.out.println("=====================================");
            System.out.println(" 📚❌ No hay libros en la biblioteca ");
            System.out.println("=====================================");
        } else {
            for (int i = 0; i < biblioteca.getTodosLibros().size(); i++) {
                Book libro = biblioteca.getTodosLibros().get(i);
                System.out.println((i + 1) + ". " + libro);
            }
        }
    }

    // 6. Método para mostrar solo los libros disponibles a partir del metodo getLibrosDisponibles de la clase Library
    public static void mostrarLibrosDisponibles(Library biblioteca) {
        System.out.println();
        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║       Libros Disponibles      ║");
        System.out.println("╚═══════════════════════════════╝");
        if (biblioteca.getLibrosDisponibles().isEmpty()) {
            System.out.println();
            System.out.println("==================================================");
            System.out.println(" 📚❌ No hay libros disponibles en la biblioteca.");
            System.out.println("==================================================");
        } else {
            for (int i = 0; i < biblioteca.getLibrosDisponibles().size(); i++) {
                Book libro = biblioteca.getLibrosDisponibles().get(i);
                System.out.println((i + 1) + ". " + libro);
            }
        }
    }  

    // 7. Metodo para buscar un libro a partir del metodo buscarTitulo de la clase Library
    public static void buscarLibroMenu(Library biblioteca, Scanner leer) {
        System.out.print("Ingrese el título del libro a buscar: ");
        String titulo = leer.nextLine();
        Book libro = biblioteca.buscarTitulo(titulo);

        if (libro != null) {
            System.out.println();
            System.out.println("================================================================================================");                             // <-- Si el libro es diferente a nulo, es decir, que hay datos
            System.out.println(" ✅ Libro encontrado: " + libro);
            System.out.println("================================================================================================");
        } else {
            System.out.println();
            System.out.println("=============================================================");
            System.out.println(" 🔎❌ Libro con título '" + titulo + "' no encontrado.");
            System.out.println("=============================================================");                 // <-- Si no, el libro es nulo, es decir, que no hay datos
        }
    }

    //Metodo para mostrar el menu de gestion de usuarios
    public static void mostrarMenuGestionUsuarios(Users users, Scanner leer) {
        String opc;
        do{
            System.out.println();
            System.out.println(" ╔═════════════════════════════════════╗");
            System.out.println(" ║       MENÚ GESTIÓN DE USUARIOS      ║");
            System.out.println(" ╠═════════════════════════════════════╣");
            System.out.println(" ║ 1. Añadir Usuario                   ║");
            System.out.println(" ║ 2. Eliminar Usuario                 ║");
            System.out.println(" ║ 3. Historial de Prestamos           ║");
            System.out.println(" ║ 4. Mostrar Usuarios                 ║");
            System.out.println(" ║ 5. Buscar Usuario                   ║");
            System.out.println(" ║ 6. Reportes Básicos                 ║");
            System.out.println(" ║ 7. Volver al Menú Principal         ║");
            System.out.println(" ╚═════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            opc = leer.nextLine();
            switch (opc) {
                case "1":
                    anadirUsuarioMenu(users, leer);
                break;
                case "2":
                    eliminarUsuarioMenu(users, leer);
                break;
                case "3":
                    historialDePrestamosMenu(users, leer);
                break;
                case "4":
                    users.mostrarUsuarios();
                break;
                case "5":
                    System.out.println("Funcionalidad de Buscar Usuario no implementada aún.");
                break;
                case "6":
                    System.out.println("Funcionalidad de Reportes Básicos no implementada aún.");
                break;
                case "7":
                    System.out.println("↩️ Volviendo al Menú Principal...");
                break;
                default:
                System.out.println("❌ Opción no válida. Intente de nuevo.");
            }
        } while (!opc.equals("7")); {
            System.out.println("");
        }
    }

    //Metodo para añadir un usuario a partir del metodo anadirUsuario de la clase Users
    public static void anadirUsuarioMenu(Users users, Scanner leer) {
        System.out.println("╔═══════════════════════════╗");
        System.out.println("║       Añadir Usuario      ║");
        System.out.println("╚═══════════════════════════╝");
        System.out.print("Ingrese el ID del usuario: ");
        String idUsuario = leer.nextLine();
        System.out.println();
        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = leer.nextLine();
        System.out.println();
        System.out.print("Ingrese el apellido del usuario: ");
        String apellido = leer.nextLine();
        System.out.println();
        System.out.print("Ingrese el email del usuario: ");
        String email = leer.nextLine();
        System.out.println();
        System.out.print("Ingrese el teléfono del usuario: ");
        String telefono = leer.nextLine();
        System.out.println();

        // Crear el objeto User
        User nuevoUsuario = new User(idUsuario, nombre, apellido, email, telefono);

        //Usamos el método anadirUsuario() de la clase Users
        users.anadirUsuario(nuevoUsuario);
        System.out.println();
        System.out.println("===================================================================================================================");
        System.out.println("  ✅ Usuario añadido: " + nuevoUsuario);
        System.out.println("===================================================================================================================");
    }

    //Metodo para eliminar un usuario a partir del metodo eliminarUsuario de la clase Users
    public static void eliminarUsuarioMenu(Users users, Scanner leer) {
        System.out.println();
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        String idUsuario = leer.nextLine();
        boolean eliminado = users.eliminarUsuario(idUsuario);
        if (eliminado) {
            System.out.println();
            System.out.println("==============================================================");
            System.out.println(" ✅ Usuario con ID " + idUsuario + " eliminado con éxito.");
            System.out.println("==============================================================");
        } else {
            System.out.println();
            System.out.println("==============================================================");
            System.out.println(" ❌ Usuario con ID " + idUsuario + " no encontrado.");
            System.out.println("==============================================================");
        }
    }

    // Metodo para el historial de prestamos de un usuario a partir del metodo historialDePrestamos de la clase Users
    public static void historialDePrestamosMenu(Users users, Scanner leer) {
        System.out.println();
        System.out.print("Ingrese el ID del usuario para ver su historial de préstamos: ");
        String idUsuario = leer.nextLine();
        LinkedList<Prestamo> historial = users.historialDePrestamos(idUsuario);
        if (historial != null && !historial.isEmpty()) {
            System.out.println();
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║     Historial de Préstamos    ║");
            System.out.println("╚═══════════════════════════════╝");
            for (int i = 0; i < historial.size(); i++) {
                Prestamo prestamo = historial.get(i);
                System.out.println((i + 1) + ". " + prestamo);
            }
        } else {
            System.out.println();
            System.out.println("==============================================================");
            System.out.println(" 📚❌ No hay historial de préstamos para el usuario con ID " + idUsuario);
            System.out.println("==============================================================");
        }
    }


    //Metodo para mostrar el menú de sistema de prestamos
    public static void mostrarMenuSistemaPrestamos(Library biblioteca, Scanner leer){
        String opc;
        do{
            System.out.println();
            System.out.println(" ╔═════════════════════════════════════╗");
            System.out.println(" ║        MENÚ SISTEMA DE PRÉSTAMOS    ║");
            System.out.println(" ╠═════════════════════════════════════╣");
            System.out.println(" ║ 1. Prestar Libro                    ║");
            System.out.println(" ║ 2. Regresar Libro                   ║");
            System.out.println(" ║ 3. Ver Cola de Espera               ║");
            System.out.println(" ║ 4. Volver al Menú Principal         ║");
            System.out.println(" ╚═════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            opc = leer.nextLine();
            switch (opc) {
                case "1":
                    prestarLibroMenu(biblioteca, leer);
                break;
                case "2":
                    regresarLibroMenu(biblioteca, leer);
                break;
                case "3":
                    System.out.println("Funcionalidad de Ver Cola de Espera no implementada aún.");
                break;
                case "4":
                    System.out.println("↩️ Volviendo al Menú Principal...");
                break;
                default:
                System.out.println("❌ Opción no válida. Intente de nuevo.");
            }
        } while (!opc.equals("4")); {
            System.out.println("");
        }
    }

    // 1: Método para prestar un libro a partir del método prestarLibro de la clase Library
    public static void prestarLibroMenu(Library biblioteca, Scanner leer) {
        //Validar si hay libros registrados
        if (biblioteca.getTodosLibros().isEmpty()) {
            System.out.println();
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
            System.out.println();
            System.out.println("===================================================");
            System.out.println(" 🔎❌ Libro con ISBN " + isbn + " no fue encontrado.");
            System.out.println("===================================================");
        } else if (resultado.equals("")) {
            System.out.println();
            System.out.println("===================================================================");
            System.out.println(" ⚠️ El libro con ISBN " + isbn + " ya está prestado y no está disponible.");
            System.out.println("===================================================================");
        } else {
            System.out.println();
            System.out.println("====================================");
            System.out.println(" ✅ Libro prestado: " + resultado);
            System.out.println("====================================");
        }
    }

    // 2: Método para regresar un libro a partir del método regresarLibro de la clase Library
    public static void regresarLibroMenu(Library biblioteca, Scanner leer) {
    // Validar si todos los libros están disponibles
        if (biblioteca.getLibrosDisponibles().size() == biblioteca.getTodosLibros().size()) {
            System.out.println(" 📚❌ No hay libros prestados para regresar.");
            return;
        }

        System.out.println();
        System.out.print("Ingrese el ISBN del libro a regresar: ");
        String isbn = leer.nextLine();

        // Usar directamente el método de Library
        boolean resultado = biblioteca.regresarLibro(isbn);

        if (resultado) {
            System.out.println();
            System.out.println("=================================================");
            System.out.println(" ✅ Libro con ISBN " + isbn + " regresado con éxito.");
            System.out.println("=================================================");
        } else {
            System.out.println();
            System.out.println("=================================================");
            System.out.println(" ❌ El libro no existe o ya estaba disponible.");
            System.out.println("=================================================");
        }
    }

    /*
     * para el deshacer, cada proceso que se realice se debe almacenar en una "transaccion" y se almacenara en la pila.
     */
}