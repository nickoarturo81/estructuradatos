package Biblioteca;

import java.util.ArrayList;
import java.util.Queue;

public class Library {
    private ArrayList<Book> libros;                                           
    private int capacidadMaxima;                                              
    
    //Constructor que inicializa la biblioteca con una capacidad determinada
    public Library(int capacidad) {
        this.libros = new ArrayList<>();
        this.capacidadMaxima = capacidad;
    }

    //Metodo para añadir un libro a la biblioteca
    public boolean anadirLibro(Book libro) {
        if (libro == null) return false;                                       
        if (libros.size() < capacidadMaxima) {                              
            libros.add(libro);                                              
            return true;                                                   
        } 
        return false;                                                       
    }

    // Método para buscar un libro por su ISBN
    public Book buscarLibroPorISBN(String isbn) {
        for (int i = 0; i < libros.size(); i++) {
            Book libro = libros.get(i);
            if (libro.getIsbn().equalsIgnoreCase(isbn)) {
                return libro;
            }
        }
        return null;
    }

    // Metodo para eliminar un libro de la biblioteca
    public String eliminarLibro(String isbn) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getIsbn().equals(isbn)) {                     
                String tituloEliminado = libros.get(i).getTitulo();         
                libros.remove(i);                                           
                return tituloEliminado;                                     
            }
        }
        return null;                                                                     
    }

    //Metodo para restaurar un libro a la biblioteca
    public boolean restaurarLibro(Book libro) {
        if (libro == null) return false;
        if (libros.size() < capacidadMaxima) {
            libros.add(libro);
            return true;
        }
        return false;
    }

    //Metodo para mostrar todos los libros de la biblioteca
    public ArrayList<Book> getTodosLibros() {
        return new ArrayList<>(libros);                                      
    }

    //Metodo para mostrar solo los libros disponibles
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

    //Metodo para prestar un libro por su ISBN
    public String prestarLibro(String isbn, String idUsuario) {
    for (int i = 0; i < libros.size(); i++) {
        Book libro = libros.get(i);

        if (libro.getIsbn().equalsIgnoreCase(isbn)) {
            if (libro.isDisponible()) {
                libro.setDisponible(false);
                return "PRESTADO";
            } else {
                // Validar que el usuario no esté ya en la cola
                if (!libro.getColaEspera().contains(idUsuario)) {
                    libro.agregarACola(idUsuario);
                    return "AGREGADO_A_COLA";
                } else {
                    return "YA_EN_COLA";
                }
            }
        }
    }
    return "NO_ENCONTRADO";
    }

    //Metodo para deshacer el prestamo de un libro por su ISBN
    public boolean deshacerPrestamo(String isbn) {
        Book libro = buscarLibroPorISBN(isbn);
        if (libro != null) {
            libro.setDisponible(true);
            return true;
        }
        return false;
    }


    //Metodo para regresar un libro por su ISBN
    public String regresarLibro(String isbn) {
    for (int i = 0; i < libros.size(); i++) {
        Book libro = libros.get(i);

        if (libro.getIsbn().equalsIgnoreCase(isbn)) {
            if (libro.isDisponible()) {
                return "YA_DISPONIBLE";
            }

            libro.setDisponible(true);

            if (libro.hayUsuariosEnEspera()) {
                String siguienteUsuario = libro.siguienteEnCola();
                libro.setDisponible(false); // se vuelve a marcar como prestado
                return "ASIGNADO_A_" + siguienteUsuario;
            }

            return "DISPONIBLE";
        }
    }
    return "NO_ENCONTRADO";
    }

    //Metodo para obtener la cola de espera de un libro por su ISBN
    public Queue<String> obtenerColaDeEspera(String isbn) {
    for (int i = 0; i < libros.size(); i++) {
        Book libro = libros.get(i);

        if (libro.getIsbn().equalsIgnoreCase(isbn)) {
            return libro.getColaEspera();
        }
    }
    return null;
    }
}