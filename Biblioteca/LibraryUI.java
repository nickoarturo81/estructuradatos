package Biblioteca;

import java.util.Scanner;
import java.util.ArrayList;
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
                    mostrarMenuGestionUsuarios(users, biblioteca, leer);
                    break;
                case "3":
                    mostrarMenuSistemaPrestamos(biblioteca, users, leer);
                break;
                case "4":
                    System.out.println(" Deshacer (no implementado aún.)");
                break;
                case "5":
                    System.out.println("👋 Saliendo del sistema. ¡Hasta luego!");
                break;
                default:
                    System.out.println(" ❌ Opción no válida. Intente de nuevo.");
            }
        } while (!opc.equals("5"));
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

    // 5. Metodo para buscar un libro a partir del metodo buscarTitulo de la clase Library
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
    } // -> Cambiar que se busque por ISBN en vez de título!!!!!

    //Metodo para mostrar el menu de gestion de usuarios
    public static void mostrarMenuGestionUsuarios(Users users, Library biblioteca, Scanner leer) {
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
                    historialDePrestamosMenu(users);
                break;
                case "4":
                    users.mostrarUsuarios();
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
            System.out.println("");
    }

    // 1. Metodo para añadir un usuario a partir del metodo anadirUsuario de la clase Users
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

    // 2. Metodo para eliminar un usuario a partir del metodo eliminarUsuario de la clase Users
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
    public static void mostrarMenuSistemaPrestamos(Library biblioteca, Users users, Scanner leer){
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
                    prestarLibroMenu(biblioteca, users, leer);
                break;
                case "2":
                    regresarLibroMenu(biblioteca, users, leer);
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
        } while (!opc.equals("4"));
    }

    // 1. Método para prestar un libro a un usuario específico
    public static void prestarLibroMenu(Library biblioteca, Users users, Scanner leer) {

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
        for (User u : users.getTodosUsuarios()) {
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
        String tituloPrestado = biblioteca.prestarLibro(isbn);

        if (tituloPrestado == null) {
            System.out.println();
            System.out.println("===================================================");
            System.out.println(" 🔎❌ Libro con ISBN " + isbn + " no fue encontrado.");
            System.out.println("===================================================");
            return;
        } else if (tituloPrestado.equals("")) {
            System.out.println();
            System.out.println("========================================================================");
            System.out.println(" ⚠️ El libro con ISBN " + isbn + " ya está prestado y no está disponible.  ");
            System.out.println("========================================================================");
            return;
        }

        // Pedir la fecha del préstamo
        System.out.println();
        System.out.print("Ingrese la fecha del préstamo (formato DD/MM/AAAA): ");
        String fechaPrestamo = leer.nextLine();

        // Crear el préstamo y asignarlo al usuario
        Prestamo nuevoPrestamo = new Prestamo(idUsuario, usuario.getNombre(), tituloPrestado, fechaPrestamo, null, "Prestado");

        // Guardar el préstamo en el historial individual del usuario
        usuario.getHistorialPrestamos().add(nuevoPrestamo);

        System.out.println();
        System.out.println("====================================");
        System.out.println(" ✅ Libro prestado correctamente");
        System.out.println(" Usuario: " + usuario.getNombre() + " " + usuario.getApellido());
        System.out.println(" Libro: " + tituloPrestado);
        System.out.println(" Fecha de préstamo: " + fechaPrestamo);
        System.out.println("====================================");
    }

    // 2: Método para regresar un libro a partir del método regresarLibro de la clase Library
    public static void regresarLibroMenu(Library biblioteca, Users users, Scanner leer) {
        // Validar si todos los libros están disponibles
        if (biblioteca.getLibrosDisponibles().size() == biblioteca.getTodosLibros().size()) {
            System.out.println(" 📚❌ No hay libros prestados para regresar.");
            return;
        }

        System.out.println();
        System.out.print("Ingrese el ISBN del libro a regresar: ");
        String isbn = leer.nextLine();

        //Buscar el libro por ISBN en la lista de Library
        Book libroEncontrado = null;
        for (int i = 0; i < biblioteca.getTodosLibros().size(); i++) {
            Book b = biblioteca.getTodosLibros().get(i);
            if (b.getIsbn().equals(isbn)) {
                libroEncontrado = b;
                break;
            }
        }

        if (libroEncontrado == null) {
            System.out.println();
            System.out.println("=============================================");
            System.out.println(" ❌ No se encontró ningún libro con ese ISBN.");
            System.out.println("=============================================");
            return;
        }

        String tituloLibro = libroEncontrado.getTitulo();

        //Buscar en los historiales individuales de todos los usuarios el préstamo activo de ese título
        Prestamo prestamoEncontrado = null;
        User usuarioConPrestamo = null;

        ArrayList<User> listaUsuarios = users.getTodosUsuarios();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            User u = listaUsuarios.get(i);
            LinkedList<Prestamo> historial = u.getHistorialPrestamos();
            for (int j = 0; j < historial.size(); j++) {
                Prestamo p = historial.get(j);
                // Compara título (asegúrate de que Prestamo almacene título) y que esté marcado como "Prestado"
                if (p.getTituloLibro() != null &&
                    p.getTituloLibro().equalsIgnoreCase(tituloLibro) &&
                    p.getEstado() != null &&
                    p.getEstado().equalsIgnoreCase("Prestado")) {
                    prestamoEncontrado = p;
                    usuarioConPrestamo = u;
                    break;
                }
            }
            if (prestamoEncontrado != null) break;
        }

        if (prestamoEncontrado == null) {
            System.out.println();
            System.out.println("=======================================================================================");
            System.out.println(" ❌ No se encontró ningún préstamo activo de ese libro en los historiales de usuarios.");
            System.out.println("=======================================================================================");
            return;
        }

        // 3) Pedir la fecha de devolución
        System.out.println();
        System.out.print("Ingrese la fecha de devolución (formato DD/MM/AAAA): ");
        String fechaDevolucion = leer.nextLine();

        // 4) Actualizar el objeto Prestamo (fecha y estado)
        prestamoEncontrado.setFechaDevolucion(fechaDevolucion);
        prestamoEncontrado.setEstado("Devuelto");

        // 5) Actualizar en Library el estado del libro a disponible
        boolean resultado = biblioteca.regresarLibro(isbn);

        if (resultado) {
            System.out.println();
            System.out.println("=================================================");
            System.out.println(" ✅ Libro regresado correctamente");
            System.out.println(" ISBN: " + isbn);
            System.out.println(" Usuario que lo tenía: " + usuarioConPrestamo.getNombre() + " " + usuarioConPrestamo.getApellido());
            System.out.println(" Fecha de entrega: " + fechaDevolucion);
            System.out.println("=================================================");
        } else {
            System.out.println();
            System.out.println("=================================================");
            System.out.println(" ❌ El libro no existe o ya estaba disponible (error al actualizar Library).");
            System.out.println("=================================================");
        }
    }




    /*
     * para el deshacer, cada proceso que se realice se debe almacenar en una "transaccion" y se almacenara en la pila.
     */
}