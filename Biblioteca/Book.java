package Biblioteca;

public class Book {
    private String titulo;
    private String autor;
    private String isbn;  // unique identifier
    private boolean disponible;
    
    // Constructor
    public Book(String title, String author, String isbn, boolean available) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponible = disponible;
    }
    
    // Add getters and setters
    // Add a toString() method to display book info
}
