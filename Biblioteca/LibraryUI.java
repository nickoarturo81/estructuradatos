package Biblioteca;

import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LibraryUI {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        Library biblioteca = new Library(50);
        Users users = new Users();
        Stack<Transaction> pilaTransacciones = new Stack<>();

        String opc;
        do {
            opc = mostrarMenuPrincipal(leer);
            switch (opc) {
                case "1":
                    mostrarMenuGestionLibros(biblioteca, leer, pilaTransacciones);
                    break;
                case "2":
                    mostrarMenuGestionUsuarios(users, biblioteca, leer, pilaTransacciones);
                    break;
                case "3":
                    mostrarMenuSistemaPrestamos(biblioteca, users, leer, pilaTransacciones);
                break;
                case "4":
                    if (!pilaTransacciones.isEmpty()) {
                        Transaction ultimaAccion = pilaTransacciones.pop();
                        ultimaAccion.undo(biblioteca, users); // Deshacer la última acción
                        System.out.println("✅ Última acción deshecha correctamente.");
                    } else {
                        System.out.println("⚠️ No hay acciones para deshacer.");
                    }
                    break;
                case "5":
                    System.out.println("👋 Saliendo del sistema. ¡Hasta luego!");
                break;
                default:
                    System.out.println(" ❌ Opción no válida. Intente de nuevo.");
            }
        } while (!opc.equals("5"));
        leer.close();// Cerrar el scanner para liberar memoria
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
    public static void mostrarMenuGestionLibros(Library biblioteca, Scanner leer, Stack<Transaction> pilaTransacciones){
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
            System.out.println(" ║ 5. Buscar Libro por ISBN            ║");
            System.out.println(" ║ 6. Volver al Menú Principal         ║");
            System.out.println(" ╚═════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            
            opc = leer.nextLine();
            switch (opc) {
                case "1":
                    anadirLibroMenu(biblioteca, leer, pilaTransacciones);
                break;
                case "2":
                    eliminarLibroMenu(biblioteca, leer, pilaTransacciones);
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
        } while (!opc.equals("6"));
        System.out.println();
    }

    // 1: Metodo para añadir un libro a partir del metodo anadirLibro de la clase Library
    public static void anadirLibroMenu(Library biblioteca, Scanner leer, Stack<Transaction> pilaTransacciones) {
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

            //Proceso Pila
            Transaction transaccion = new Transaction(Transaction.Tipo.AÑADIR_LIBRO, libro);
            pilaTransacciones.push(transaccion);

        } else {
            System.out.println();
            System.out.println("==============================================================");
            System.out.println("= 🚨 No se puede añadir el libro. Capacidad máxima alcanzada  ");
            System.out.println("==============================================================");
        }
    }

    // 2: Método para eliminar un libro a partir del método eliminarLibro de la clase Library
    public static void eliminarLibroMenu(Library biblioteca, Scanner leer, Stack<Transaction> pilaTransacciones) {
        System.out.println();
        System.out.print("Ingrese el ISBN del libro a eliminar: ");
        String isbn = leer.nextLine();

        String tituloEliminado = biblioteca.eliminarLibro(isbn);

        if (tituloEliminado != null) {
            System.out.println();
            System.out.println("================================================================================================");
            System.out.println(" 🗑️ Libro eliminado: " + tituloEliminado + " Total libros: " + biblioteca.getTodosLibros().size() + ")");
            System.out.println("================================================================================================");

            Book libroEliminado = new Book(tituloEliminado, "", isbn, true);
            Transaction transaccion = new Transaction(Transaction.Tipo.ELIMINAR_LIBRO, libroEliminado);
            pilaTransacciones.push(transaccion);

        } else {
            System.out.println();
            System.out.println("===============================================");
            System.out.println(" 🔎❌ Libro con ISBN " + isbn + " no encontrado ");
            System.out.println("===============================================");
        }
    }

    // 3. Método para mostrar todos los libros a partir del metodo getTodosLibros de la clase Library
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

    // 4. Método para mostrar solo los libros disponibles a partir del metodo getLibrosDisponibles de la clase Library
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

    // 5. Método para buscar un libro por su ISBN
    public static void buscarLibroMenu(Library biblioteca, Scanner leer) {
        System.out.println();
        System.out.print("Ingrese el ISBN del libro a buscar: ");
        String isbn = leer.nextLine();

        // Buscar el libro en la biblioteca por ISBN
        Book libro = biblioteca.buscarLibroPorISBN(isbn);

        if (libro != null) {
            System.out.println();
            System.out.println("================================================================================================");
            System.out.println(" ✅ Libro encontrado:");
            System.out.println(" Título: " + libro.getTitulo());
            System.out.println(" Autor: " + libro.getAutor());
            System.out.println(" ISBN: " + libro.getIsbn());
            System.out.println(" Estado: " + (libro.isDisponible() ? "Disponible" : "Prestado"));
            System.out.println("================================================================================================");
        } else {
            System.out.println();
            System.out.println("=============================================================");
            System.out.println(" 🔎❌ Libro con ISBN '" + isbn + "' no encontrado.");
            System.out.println("=============================================================");
        }
    }


    //Metodo para mostrar el menu de gestion de usuarios
    public static void mostrarMenuGestionUsuarios(Users users, Library biblioteca, Scanner leer, Stack<Transaction> pilaTransacciones){ {
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
                    anadirUsuarioMenu(users, leer, pilaTransacciones);
                break;
                case "2":
                    eliminarUsuarioMenu(users, leer, pilaTransacciones);
                break;
                case "3":
                    historialDePrestamosMenu(users);
                break;
                case "4":
                    mostrarUsuariosMenu(users);
                break;
                case "5":
                    buscarUsuarioMenu(users, leer);
                break;
                case "6":
                    reportesBasicosMenu(users, biblioteca);
                break;
                case "7":
                    System.out.println("↩️ Volviendo al Menú Principal...");
                break;
                default:
                System.out.println("❌ Opción no válida. Intente de nuevo.");
            }
        } while (!opc.equals("7")); 
        System.out.println();
        }
    }

    // 1. Metodo para añadir un usuario a partir del metodo anadirUsuario de la clase Users
    public static void anadirUsuarioMenu(Users users, Scanner leer, Stack<Transaction> pilaTransacciones) {
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

        //Proceso Pila
        Transaction transaccion = new Transaction(Transaction.Tipo.AÑADIR_USUARIO, nuevoUsuario);
        pilaTransacciones.push(transaccion); // ← Guardamos en la pila

        //Usamos el método anadirUsuario() de la clase Users
        users.anadirUsuario(nuevoUsuario);
        System.out.println();
        System.out.println("===================================================================================================================");
        System.out.println("  ✅ Usuario añadido: " + nuevoUsuario);
        System.out.println("===================================================================================================================");
    }

    // 2. Metodo para eliminar un usuario a partir del metodo eliminarUsuario de la clase Users
    public static void eliminarUsuarioMenu(Users users, Scanner leer, Stack<Transaction> pilaTransacciones) {
        System.out.println();
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        String idUsuario = leer.nextLine();
        User usuarioAEliminar = users.buscarUsuario(idUsuario);
        if (usuarioAEliminar != null) {

            //Proceso Pila - Guardar el usuario eliminado
            Transaction transaccion = new Transaction(Transaction.Tipo.ELIMINAR_USUARIO, usuarioAEliminar);
            pilaTransacciones.push(transaccion);

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

    // 3. Método para mostrar el historial a partir del metodo getHistorialGeneral de la clase Users
    public static void historialDePrestamosMenu(Users users) {
    System.out.println();
    LinkedList<Prestamo> historial = users.getHistorialGeneral();

        if (historial != null && !historial.isEmpty()) {
        System.out.println();
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║           HISTORIAL GENERAL DE PRÉSTAMOS          ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");

            for (int i = 0; i < historial.size(); i++) {
                Prestamo p = historial.get(i);
                System.out.println("---------------------------------------------------------------");
                System.out.println("Registro #" + (i + 1));
                System.out.println("ID Usuario: " + p.getIdUsuario());
                System.out.println("Título del Libro: " + p.getTituloLibro());
                System.out.println("ISBN del Libro: " + p.getIsbnLibro());
                System.out.println("Fecha de Préstamo: " + p.getFechaPrestamo());
                System.out.println("Fecha de Devolución: " +
                (p.getFechaDevolucion() != null ? p.getFechaDevolucion() : "Pendiente"));
                System.out.println("Estado: " + p.getEstado());
            }

        } else {
            System.out.println();
            System.out.println("==============================================================");
            System.out.println(" 📚❌ No hay préstamos registrados en el historial general.");
            System.out.println("==============================================================");
        }
    }

    // 4. Metodo para mostrar todos los usuarios a partir del metodo mostrarUsuarios de la clase Users
    public static void mostrarUsuariosMenu(Users users) {
        System.out.println();
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║       LISTADO DE USUARIOS REGISTRADOS     ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.println();

        // Obtener todos los usuarios desde la clase Users
        ArrayList<User> listaUsuarios = users.getTodosUsuarios();

        // Validar si hay usuarios
        if (listaUsuarios.isEmpty()) {
            System.out.println("⚠️ No hay usuarios registrados actualmente.");
            return;
        }

        // Encabezado de tabla
        System.out.println("-------------------------------------------------------------");
        System.out.println("N° | ID Usuario | Nombre | Apellido | Email | Teléfono");
        System.out.println("-------------------------------------------------------------");

        // Recorrer la lista con un for tradicional
        for (int i = 0; i < listaUsuarios.size(); i++) {
            User user = listaUsuarios.get(i);
            System.out.println((i + 1) + ". " + user.getIdUsuario() + " | " + user.getNombre() + " | " + user.getApellido() + " | " + user.getEmail() + " | " 
            + user.getTelefono());
        }
    }

    // 5. Metodo para buscar un usuario a partir del metodo buscarUsuario de la clase Users
    public static void buscarUsuarioMenu(Users users, Scanner leer) {
        System.out.println();
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║           BUSCAR USUARIO          ║");
        System.out.println("╚═══════════════════════════════════╝");
        System.out.println();
        System.out.print("Ingrese el ID o nombre del usuario a buscar: ");
        String criterio = leer.nextLine();

        User usuarioEncontrado = users.buscarUsuario(criterio);

        if (usuarioEncontrado != null) {
            System.out.println();
            System.out.println("====================================");
            System.out.println("✅ Usuario encontrado:");
            System.out.println(usuarioEncontrado.toString());
            System.out.println("====================================");
        } else {
            System.out.println();
            System.out.println("====================================");
            System.out.println("❌ No se encontró ningún usuario con el criterio: " + criterio);
            System.out.println("====================================");
        }
    }

    // 6. Metodo para mostrar reportes basicos a partir de metodos de las clases Users y Library
    public static void reportesBasicosMenu(Users users, Library biblioteca) {
        System.out.println();
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║           REPORTES BÁSICOS            ║");
        System.out.println("╚═══════════════════════════════════════╝");

        // 📘 Total de usuarios
        int totalUsuarios = users.getTodosUsuarios().size();
        System.out.println("║ 👥 Total de usuarios registrados: " + totalUsuarios);

        // 📚 Total de libros
        int totalLibros = biblioteca.getTodosLibros().size();
        int disponibles = biblioteca.getLibrosDisponibles().size();
        int prestados = totalLibros - disponibles;
        System.out.println("📚 Total de libros registrados: " + totalLibros);
        System.out.println("✅ Disponibles: " + disponibles);
        System.out.println("❌ Prestados: " + prestados);

        // 🧾 Total de préstamos (del historial general)
        int totalPrestamos = users.getHistorialGeneral().size();
        System.out.println("🧾 Total de préstamos realizados: " + totalPrestamos);

    }

    //Metodo para mostrar el menú de sistema de prestamos
    public static void mostrarMenuSistemaPrestamos(Library biblioteca, Users users, Scanner leer, Stack<Transaction> pilaTransacciones) {
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
                    prestarLibroMenu(biblioteca, users, leer, pilaTransacciones);
                break;
                case "2":
                    regresarLibroMenu(biblioteca, users, leer, pilaTransacciones);
                break;
                case "3":
                    mostrarColaDeEspera(biblioteca, leer);
                break;
                case "4":
                    System.out.println("↩️ Volviendo al Menú Principal...");
                break;
                default:
                System.out.println("❌ Opción no válida. Intente de nuevo.");
            }
        } while (!opc.equals("4"));
        System.out.println();
    }

    // 1. Método para prestar un libro a un usuario específico
    public static void prestarLibroMenu(Library biblioteca, Users users, Scanner leer, Stack<Transaction> pilaTransacciones) {

        // Validar si hay libros registrados
        if (biblioteca.getTodosLibros().isEmpty()) {
            System.out.println();
            System.out.println("=================================================================");
            System.out.println(" 🚫 No hay ningún libro registrado en la biblioteca para prestar.");
            System.out.println("=================================================================");
            return;
        }

        // Validar si hay usuarios registrados
        if (users.getTodosUsuarios().isEmpty()) {
            System.out.println();
            System.out.println("=================================================================");
            System.out.println(" 🚫 No hay usuarios registrados para asignar el préstamo.");
            System.out.println("=================================================================");
            return;
        }

        // Inicio del proceso de préstamo
        System.out.println();
        System.out.print("Ingrese el ID del usuario que realizará el préstamo: ");
        String idUsuario = leer.nextLine();

        // Buscar el usuario
        User usuario = null;
        for (int i = 0; i < users.getTodosUsuarios().size(); i++) {
            User u = users.getTodosUsuarios().get(i);
            if (u.getIdUsuario().equals(idUsuario)) {
                usuario = u;
                break;
            }
        }

        // Validar si el usuario existe
        if (usuario == null) {
            System.out.println();
            System.out.println("=====================================================");
            System.out.println(" ❌ No se encontró ningún usuario con el ID ingresado.");
            System.out.println("=====================================================");
            return;
        }

        System.out.println();
        System.out.print("Ingrese el ISBN del libro a prestar: ");
        String isbn = leer.nextLine();

        // Verificar si el libro existe y está disponible
        String tituloPrestado = biblioteca.prestarLibro(isbn, usuario.getNombre());

        if (tituloPrestado == null) {
            System.out.println();
            System.out.println("===================================================");
            System.out.println(" 🔎❌ Libro con ISBN " + isbn + " no fue encontrado.");
            System.out.println("===================================================");
            return;
        } else if (tituloPrestado.equals("")) {
            System.out.println();
            System.out.println("===============================================================================");
            System.out.println("    El libro con ISBN " + isbn + " ya está prestado y no está disponible");
            System.out.println("  Registrar el usuario a la cola de espera.");
            System.out.println("===============================================================================");
            return;
        }

        // Pedir la fecha del préstamo
        System.out.println();
        System.out.print("Ingrese la fecha del préstamo (formato DD/MM/AAAA): ");
        String fechaPrestamo = leer.nextLine();

        // Crear el préstamo y asignarlo al usuario
        Prestamo nuevoPrestamo = new Prestamo(usuario.getIdUsuario(),usuario.getNombre(),tituloPrestado,isbn,fechaPrestamo,null, "Prestado");

        // Guardar el préstamo en el historial individual del usuario
        usuario.getHistorialPrestamos().add(nuevoPrestamo);

        Transaction transaccion = new Transaction(Transaction.Tipo.PRESTAR_LIBRO, nuevoPrestamo);
        pilaTransacciones.push(transaccion);

        System.out.println();
        System.out.println("====================================");
        System.out.println(" ✅ Libro prestado correctamente");
        System.out.println(" Usuario: " + usuario.getNombre() + " " + usuario.getApellido());
        System.out.println(" Libro: " + tituloPrestado);
        System.out.println(" Fecha de préstamo: " + fechaPrestamo);
        System.out.println("====================================");
    }

    // 2: Método para regresar un libro a partir del método regresarLibro de la clase Library
    public static void regresarLibroMenu(Library biblioteca, Users users, Scanner leer, Stack<Transaction> pilaTransacciones) {
    System.out.println();

    //Validar si hay libros registrados y si hay prestados
    if (biblioteca.getLibrosDisponibles().isEmpty() ||
    biblioteca.getLibrosDisponibles().size() == biblioteca.getTodosLibros().size()) {
        System.out.println(" 📚❌ No hay libros prestados para regresar.");
        return;
    }

    System.out.print("Ingrese el ISBN del libro a regresar: ");
    String isbn = leer.nextLine().trim(); // Eliminar espacios en blanco

    //Buscar el libro por ISBN en Library
    Book libroEncontrado = biblioteca.buscarLibroPorISBN(isbn);
    if (libroEncontrado == null) {
        System.out.println();
        System.out.println("=============================================");
        System.out.println(" ❌ No se encontró ningún libro con ese ISBN.");
        System.out.println("=============================================");
        return;
    }

    //Buscar el préstamo activo asociado a ese ISBN en los historiales de usuarios
    Prestamo prestamoEncontrado = null;
    User usuarioConPrestamo = null;

    //Obetener una lista de todos los usuarios
    ArrayList<User> listaUsuarios = users.getTodosUsuarios();
    //Recorrer todos los usuarios
    for (int i = 0; i < listaUsuarios.size(); i++) {
        User u = listaUsuarios.get(i);
        //Obtener el historial de prestamos del usuario
        LinkedList<Prestamo> historial = u.getHistorialPrestamos();
        if (historial == null || historial.isEmpty()) continue;
        //Recorrer el historial del usuario
        for (int j = 0; j < historial.size(); j++) {
            Prestamo p = historial.get(j);
            //Verificar si el préstamo coincide con el ISBN y está activo
            if (p.getIsbnLibro() != null &&
                p.getIsbnLibro().equalsIgnoreCase(isbn) &&
                p.getEstado() != null &&
                p.getEstado().equalsIgnoreCase("Prestado")) {
                //Préstamo encontrado
                prestamoEncontrado = p;
                usuarioConPrestamo = u;
                break;
            }
        }
        if (prestamoEncontrado != null) break;
    }

    if (prestamoEncontrado == null) {
        System.out.println();
        System.out.println("=====================================================================================");
        System.out.println(" ❌ No se encontró ningún préstamo activo de ese libro en los historiales de usuarios.");
        System.out.println("=====================================================================================");
        return;
    }

    // Pedir la fecha de devolución
    System.out.println();
    System.out.print("Ingrese la fecha de devolución (formato DD/MM/AAAA): ");
    String fechaDevolucion = leer.nextLine().trim();
    // Guardar la transacción en la pila antes de modificar el préstamo
    Transaction transaccion = new Transaction(Transaction.Tipo.REGRESAR_LIBRO, prestamoEncontrado);
    pilaTransacciones.push(transaccion);

    // Actualizar el objeto Prestamo (fecha y estado)
    prestamoEncontrado.setFechaDevolucion(fechaDevolucion);
    prestamoEncontrado.setEstado("Devuelto");

    // Llamar a Library.regresarLibro -> devuelve un String con el resultado lógico
    String resultado = biblioteca.regresarLibro(isbn);

    System.out.println();
    System.out.println("=================================================");
    if (resultado == null) {
        // Por si alguna versión devuelve null inesperadamente
        System.out.println(" ❌ Ocurrió un error al procesar la devolución.");

    } else if (resultado.equals("NO_ENCONTRADO")) { // Libro no existe
        System.out.println(" ❌ El libro no existe en la biblioteca.");

    } else if (resultado.equals("YA_DISPONIBLE")) {
        System.out.println(" ⚠️ El libro ya estaba disponible.");

    } else if (resultado.equals("DISPONIBLE")) {
        // Libro devuelto y sin cola de espera
        System.out.println(" ✅ Libro regresado correctamente");
        System.out.println(" ISBN: " + isbn);
        System.out.println(" Usuario que lo tenía: " + usuarioConPrestamo.getNombre() + " " + usuarioConPrestamo.getApellido());
        System.out.println(" Fecha de devolución: " + fechaDevolucion);
        
    } else if (resultado.startsWith("ASIGNADO_A_")) {
        // Library indica que reasignó el libro al siguiente usuario en cola
        String idSiguiente = resultado.substring("ASIGNADO_A_".length());

        System.out.println(" ✅ Libro regresado correctamente y asignado al siguiente de la cola.");
        System.out.println(" ISBN: " + isbn);
        System.out.println(" Usuario que lo tenía: " + usuarioConPrestamo.getNombre() + " " + usuarioConPrestamo.getApellido());
        System.out.println(" Fecha de devolución: " + fechaDevolucion);
        System.out.println("-------------------------------------------------");
        System.out.println(" 📢 Se asignó el libro automáticamente al usuario en espera: " + idSiguiente);

        // Buscar el usuario siguiente en Users para registrar el nuevo Prestamo en su historial
        User usuarioSiguiente = null;
        ArrayList<User> usuarios = users.getTodosUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getIdUsuario().equalsIgnoreCase(idSiguiente)) {
                usuarioSiguiente = usuarios.get(i);
                break;
            }
        }

        //Si encontramos al usuario siguiente, creamos y registramos el nuevo Prestamo en su historial
        if (usuarioSiguiente != null) {
            // Usamos la fecha actual como fecha de préstamo automática
            String fechaPrestamoHoy = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Prestamo nuevoPrestamo = new Prestamo(
                usuarioSiguiente.getIdUsuario(),
                usuarioSiguiente.getNombre(),
                libroEncontrado.getTitulo(),
                libroEncontrado.getIsbn(),
                fechaPrestamoHoy,
                null,
                "Prestado"
            );

            usuarioSiguiente.getHistorialPrestamos().add(nuevoPrestamo);
            System.out.println(" 📌 Se registró el préstamo en el historial de: " + usuarioSiguiente.getIdUsuario() +
                    " (" + usuarioSiguiente.getNombre() + " " + usuarioSiguiente.getApellido() + ") Fecha: " + fechaPrestamoHoy);
        } else {
            System.out.println(" ⚠️ Aviso: No se encontró el usuario " + idSiguiente + " en el registro de usuarios. No se pudo añadir el préstamo a su historial.");
        }
    } else {
        // Cualquier otro código inesperado
        System.out.println(" ⚠️ Resultado inesperado desde Library: " + resultado);
    }
    System.out.println("=================================================");
    }

    // 3: Método para mostrar la cola de espera de un libro específico
    public static void mostrarColaDeEspera(Library biblioteca, Scanner leer) {
    System.out.println();
    System.out.print("Ingrese el ISBN del libro para ver su cola de espera: ");
    String isbn = leer.nextLine().trim();

    // Buscar el libro en la biblioteca
    Book libro = biblioteca.buscarLibroPorISBN(isbn);

    // Validar si el libro existe
    if (libro == null) {
        System.out.println();
        System.out.println("=================================================");
        System.out.println(" ❌ No se encontró ningún libro con ese ISBN.");
        System.out.println("=================================================");
        return;
    }

    System.out.println();
    System.out.println("=================================================");
    System.out.println(" 📚 Libro: " + libro.getTitulo());
    System.out.println(" ISBN: " + libro.getIsbn());
    System.out.println("-------------------------------------------------");

    // Obtener la cola directamente desde el método público del libro
    Queue<String> cola = libro.getColaEspera();

    // Validar si la cola de espera está vacía
    if (cola == null || cola.isEmpty()) {
        System.out.println(" 🟢 No hay usuarios en la cola de espera.");
    } else {
        System.out.println(" 👥 Usuarios en cola de espera (orden FIFO):");

        // Convertir la cola a arreglo para recorrer con for tradicional
        Object[] usuarios = cola.toArray();

        for (int i = 0; i < usuarios.length; i++) {
            String usuario = (String) usuarios[i];
            System.out.println("  " + (i + 1) + ". " + usuario);
        }
    }

    System.out.println("=================================================");
    }
    
}