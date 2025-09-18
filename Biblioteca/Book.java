package Biblioteca;

public class Book {
    //Atributos
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponible;
    //Al ser privados no se pueden acceder directamente desde fuera de la clase sino mediante getters y setters

    // Constructor
    public Book(String titulo, String autor, String isbn, boolean disponible) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponible = disponible;
    }
    //Esto nos permite crear un libro con todos sus atributos

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isDisponible() {
        return disponible;
    }
    //Los getters permiten consultar los atributos de un libro

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    //Los setters permiten modificar los atributos de un libro

    @Override
    public String toString() {
        return String.format("Libro [Título: %s | Autor: %s | ISBN: %s | Disponible: %s]", titulo, autor, isbn, disponible ? "Sí" : "No");
    }
    //Permitira mostrar la informacion del libro de forma legible.
}