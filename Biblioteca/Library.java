package Biblioteca;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> libros;                                           // Se crea una lista llamada libros
    private int capacidadMaxima;                                              // Capacidad máxima de la biblioteca
    
    //Constructor que inicializa la biblioteca con una capacidad determinada
    public Library(int capacidad) {
        this.libros = new ArrayList<>();
        this.capacidadMaxima = capacidad;
    }

    // Metodo para buscar un libro por su titulo
    public Book buscarTitulo(String titulo) {
        for (int i = 0; i < libros.size(); i++) {
            Book libro = libros.get(i);
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {                // "equalsIgnoreCase" Compara el título del libro ignorando mayúsculas y minúsculas
            return libro;
         }
        }
        return null;
    }

    //Metodo para añadir un libro a la biblioteca
    public boolean anadirLibro(Book libro) {
        if (libro == null) return false;                                    // No se puede añadir un libro nulo   
        if (libros.size() < capacidadMaxima) {                              // Si el tamaño de la cantidad de los libros es menor a la capacidad máxima
            libros.add(libro);                                              // Se añade un libro
            return true;                                                    // Retorna true si se añadió correctamente
        } 
        return false;                                                       // De lo contrario, retorna false
    }

    // Metodo para eliminar un libro de la biblioteca
    public String eliminarLibro(String isbn) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getIsbn().equals(isbn)) {                     // Obtiene el libro por su ISBN
                String tituloEliminado = libros.get(i).getTitulo();         // Guarda el título del libro que se va a eliminar en una variable llamado tituloEliminado
                libros.remove(i);                                           // Elimina el libro de la lista
                return tituloEliminado;                                     // Retorna el título del libro eliminado
            }
        }
        return null;                                                                     
    }

    // Metodo para mostrar todos los libros de la biblioteca
    public ArrayList<Book> getTodosLibros() {
        return new ArrayList<>(libros);                                      // Retorna una copia de la lista de libros
    }

    // Metodo para mostrar solo los libros disponibles
    public ArrayList<Book> getLibrosDisponibles() {                             
        ArrayList<Book> disponibles = new ArrayList<>();                     // Crea una nueva lista para almacenar los libros disponibles
        for (int i = 0; i < libros.size(); i++) {
            Book libro = libros.get(i);                                      // Crea una variable tipo Book para almacenar el libro actual
            if (libro.isDisponible()) {                                      // Si el libro está disponible
                disponibles.add(libro);                                      // Añade el libro a la lista de disponibles
            }
        }
        return disponibles;                                                  // Retorna la lista de libros disponibles
    }

    // Metodo para prestar un libro por su ISBN
    public String prestarLibro(String isbn) {
        for (Book libro : libros) {                                          // Recorre la lista de libros
            if (libro.getIsbn().equals(isbn)) {                              // Si el isbn del libro es igual al isbn proporcionado          
                if (libro.isDisponible()) {                                  // si el libro está disponible
                    libro.setDisponible(false);                   // Cambia el estado del libro a no disponible
                    return libro.getTitulo();                                // Retorna el título del libro prestado
                } else {
                return "";
                }
            }
        }
        return null;                                                         // Retorna null si no se encuentra el libro o no está disponible
    }

    // Metodo para regresar un libro por su ISBN
    public boolean regresarLibro(String isbn) {
        for (int i = 0; i < libros.size(); i++) {                            // Recorre la lista de libros
            if (libros.get(i).getIsbn().equals(isbn)) {                      // Si el isbn del libro es igual al isbn proporcionado
                if (libros.get(i).isDisponible()) {                          // Si el libro ya está disponible
                    return false;                                            // Retorna false, ya que no se puede regresar un libro que ya está disponible
                }
                libros.get(i).setDisponible(true);                // Cambia el estado del libro a disponible
                return true;
            }
        }
        return false;                                                        // Retorna false si no se encuentra el libro
    }
}