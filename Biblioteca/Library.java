package Biblioteca;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> libros;
    private int capacidadMaxima;
    
    //Constructor que inicializa la biblioteca con una capacidad determinada
    public Library(int capacidad) {
        this.libros = new ArrayList<>();
        this.capacidadMaxima = capacidad;
        System.out.println("Biblioteca creada con capacidad para " + capacidad);
    }

    // Metodo para buscar un libro por su titulo
    public Book buscarTitulo(String titulo) {
        System.out.println("Buscando por: " + titulo);

        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTitulo().equalsIgnoreCase(titulo)) {
            System.out.println("Libro encontrado: " + libros.get(i));
            return libros.get(i);
         }
        }
        System.out.println("Libro no encontrado");
        return null;
    }

    //Metodo para añadir un libro a la biblioteca
    public void anadirLibro(Book libro) {
        if (libros.size() < capacidadMaxima) {
            libros.add(libro);
            System.out.println("Libro añadido exitosamente: ");

        } else {
            System.out.println("No se puede añadir más libros, capacidad máxima alcanzada.");
        }

        if (libro == null) {
        System.out.println("No se puede añadir un libro nulo.");
        }
    }

    // Metodo para eliminar un libro de la biblioteca
    public boolean eliminarLibro(String isbn) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getIsbn().equals(isbn)) {
                String tituloEliminado = libros.get(i).getTitulo();
                libros.remove(i); // elimina el libro en esa posición
                System.out.println("Libro eliminado: " + tituloEliminado + " (Total libros: " + libros.size() + ")");
                return true;
            }
        }
        System.out.println("Libro con ISBN:  " + isbn + " NO encontrado.");
        return false;
    }

    // Metodo para mostrar todos los libros de la biblioteca
    public void mostrarTodosLibros() {
    System.out.println("\n== Biblioteca (" + libros.size() + " libros) ==");

        if (libros.isEmpty()) {
            System.out.println("No hay libros en la biblioteca.");
        } else {
            for (int i = 0; i < libros.size(); i++) {
                System.out.println((i + 1) + ". " + libros.get(i));
            }
        }
        System.out.println("====================================\n");
    }

    // Metodo para mostrar solo los libros disponibles
    public void mostrarLibrosDisponibles() {
    System.out.println("\n== Libros Disponibles ==");
    int contadorDisponibles = 0;

        for (int i = 0; i < libros.size(); i++) {   // usamos libros.size() en vez de contarLibro
        if (libros.get(i).isDisponible()) {     // accedemos con get(i)
            System.out.println("- " + libros.get(i));
            contadorDisponibles++;
            }
        }

        if (contadorDisponibles == 0) {
            System.out.println("No hay libros disponibles en este momento.");
        } else {
            System.out.println("Total de libros disponibles: " + contadorDisponibles);
        }

        System.out.println("========================\n");
    }

    // Metodo para prestar un libro por su ISBN
    public boolean prestarLibro(String isbn) {
        for (int i = 0; i < libros.size(); i++) {   // recorremos con size()
            if (libros.get(i).getIsbn().equals(isbn)) {   // accedemos con get(i)
            
                if (libros.get(i).isDisponible()) {
                    libros.get(i).setDisponible(false);
                    System.out.println("Has prestado el libro: " + libros.get(i).getTitulo());
                    return true;
                } else {
                    System.out.println("El libro ya está prestado: " + libros.get(i).getTitulo());
                    return false;
                }
            }
        }
        System.out.println("Libro con ISBN " + isbn + " no encontrado.");
        return false;
    }

    // Metodo para regresar un libro por su ISBN
    public boolean regresarLibro(String isbn) {
        for (int i = 0; i < libros.size(); i++) {  // recorremos con size()
            if (libros.get(i).getIsbn().equals(isbn)) {
            
                if (libros.get(i).isDisponible()) {
                    System.out.println("El libro '" + libros.get(i).getTitulo() + "' no fue prestado.");
                    return false;  // aquí debería ser false, porque no se devolvió nada
                }

                libros.get(i).setDisponible(true);
                System.out.println("Has regresado el libro: " + libros.get(i).getTitulo());
                return true;
            }
        }

        System.out.println("Libro con ISBN " + isbn + " no encontrado.");
        return false;
    }
}
