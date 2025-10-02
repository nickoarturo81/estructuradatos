package Biblioteca;

import java.util.ArrayList;

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

    // Metodo para buscar un libro por su titulo
    public Book buscarTitulo(String titulo) {
        for (int i = 0; i < libros.size(); i++) {
            Book libro = libros.get(i);
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {                
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

    // Metodo para mostrar todos los libros de la biblioteca
    public ArrayList<Book> getTodosLibros() {
        return new ArrayList<>(libros);                                      
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
    public String prestarLibro(String isbn) {
        for (Book libro : libros) {                                          
            if (libro.getIsbn().equals(isbn)) {                                 
                if (libro.isDisponible()) {                                  
                    libro.setDisponible(false);                   
                    return libro.getTitulo();                                
                } else {
                return "";
                }
            }
        }
        return null;                                                         
    }

    // Metodo para regresar un libro por su ISBN
    public boolean regresarLibro(String isbn) {
        for (int i = 0; i < libros.size(); i++) {                            
            if (libros.get(i).getIsbn().equals(isbn)) {                      
                if (libros.get(i).isDisponible()) {                          
                    return false;                                            
                }
                libros.get(i).setDisponible(true);               
                return true;
            }
        }
        return false;                                                        
    }
}