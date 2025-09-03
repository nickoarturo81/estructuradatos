package Biblioteca;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> libros;
    private int capacidadMaxima;
    
    //Constructor que inicializa la biblioteca con una capacidad determinada
    public Library(int capacidad) {
        this.libros = new ArrayList<>();
        this.capacidadMaxima = capacidad;
        //System.out.println("Biblioteca creada con capacidad para " + capacidad);
    }

    // Metodo para buscar un libro por su titulo
    public Book buscarTitulo(String titulo) {
        //System.out.println("Buscando por: " + titulo);
        for (int i = 0; i < libros.size(); i++) {
            Book libro = libros.get(i);
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
            //System.out.println("Libro encontrado: " + libros.get(i));
            return libro;
         }
        }
        //System.out.println("Libro no encontrado");
        return null;
    }

    //Metodo para añadir un libro a la biblioteca
    public boolean anadirLibro(Book libro) {
        if (libro == null) return false;                                // No se puede añadir un libro nulo   
        if (libros.size() < capacidadMaxima) {                          // Si el tamaño de la cantidad de los libros es menor a la capacidad máxima
            libros.add(libro);                                          // Se añade un libro
            return true;                                                // Retorna true si se añadió correctamente
            //System.out.println("Libro añadido exitosamente: ");
        } 
        return false;                                                   // De lo contrario, retorna false
        /*else {
            System.out.println("No se puede añadir más libros, capacidad máxima alcanzada.");
        }

        if (libro == null) {
        System.out.println("No se puede añadir un libro nulo.");
        */
    }

    // Metodo para eliminar un libro de la biblioteca
    public boolean eliminarLibro(String isbn) {
        for (int i = 0; i < libros.size(); i++) {
            Book libro = libros.get(i);
            if (libro.getIsbn().equals(isbn)) {
                libros.remove(i); // elimina el libro en esa posición
                //System.out.println("Libro eliminado: " + tituloEliminado + " (Total libros: " + libros.size() + ")");
                return true;
            }
        }
        //System.out.println("Libro con ISBN:  " + isbn + " NO encontrado.");
        return false;
    }

    // Metodo para mostrar todos los libros de la biblioteca
    public ArrayList<Book> getTodosLibros() {
        return new ArrayList<>(libros);                                                     // Retorna una copia de la lista de libros
    }

    // Metodo para mostrar solo los libros disponibles
    public ArrayList<Book> getLibrosDisponibles() { 
        ArrayList<Book> disponibles = new ArrayList<>();
        for (int i = 0; i < libros.size(); i++) {
            Book libro = libros.get(i);
            if (libro.isDisponible()) {
                disponibles.add(libro);
            }
        }
        return disponibles;
    }

    // Metodo para prestar un libro por su ISBN
    public boolean prestarLibro(String isbn) {
        for (int i = 0; i < libros.size(); i++) {  
            Book libro = libros.get(i); 
                if (libro.getIsbn().equals(isbn)) {
                    if (libro.isDisponible()) {
                        libro.setDisponible(false);
                        //System.out.println("El libro '" + libro.getTitulo() + "' no fue prestado.");
                        return true;
                    }
                    //System.out.println("Has prestado el libro: " + libros.get(i).getTitulo());
                    return false;
                }
        }
        //System.out.println("Libro con ISBN " + isbn + " no encontrado.");
        return false;
    }

    // Metodo para regresar un libro por su ISBN
    public boolean regresarLibro(String isbn) {
        for (int i = 0; i < libros.size(); i++) {  // recorremos con size()
            if (libros.get(i).getIsbn().equals(isbn)) {
            
                if (libros.get(i).isDisponible()) {
                    //System.out.println("El libro '" + libros.get(i).getTitulo() + "' no fue prestado.");
                    return false;  // aquí debería ser false, porque no se devolvió nada
                }

                libros.get(i).setDisponible(true);
                //System.out.println("Has regresado el libro: " + libros.get(i).getTitulo());
                return true;
            }
        }

        //System.out.println("Libro con ISBN " + isbn + " no encontrado.");
        return false;
    }
}
