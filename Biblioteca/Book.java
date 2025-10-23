package Biblioteca;

import java.util.LinkedList;
import java.util.Queue;

public class Book {
    
    //Atributos
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponible;
    private Queue<String> colaEspera;

    // Constructor
    public Book(String titulo, String autor, String isbn, boolean disponible) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponible = disponible;
        this.colaEspera = new LinkedList<>();
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

    public Queue<String> getColaEspera() {
        return colaEspera;
    }

    public void agregarACola(String nombreUsuario) {
        colaEspera.add(nombreUsuario);
    }

    public String siguienteEnCola() {
        return colaEspera.poll(); // devuelve y elimina el primero
    }

    public boolean hayUsuariosEnEspera() {
        return !colaEspera.isEmpty();
    }

    // Setters
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
  
   @Override
    public String toString() {
        return String.format(
            "Libro [Título: %s | Autor: %s | ISBN: %s | Disponible: %s | En cola: %d]",
            titulo, autor, isbn, disponible ? "Sí" : "No", colaEspera.size()
        );
    }
}