package Biblioteca;

import java.util.LinkedList;

public class User {
    //Atributos
    private String nombre;
    private String apellido;
    private String idUsuario;
    private String email;
    private int telefono;
    private LinkedList<Prestamo> historialPrestamos; // 👈 historial individual

    // Constructor
    public User(String nombre, String apellido, String idUsuario, String email, int telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.idUsuario = idUsuario;
        this.email = email;
        this.telefono = telefono;
        this.historialPrestamos = new LinkedList<>();
    }
    //Esto nos permite crear un usuario con todos sus atributos

    //Getters
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    public String getEmail() {
        return email;
    }
    public int getTelefono() {
        return telefono;
    }

    public LinkedList<Prestamo> getHistorialPrestamos() {
        return historialPrestamos;
    }
    //Los getters permiten consultar los atributos de un usuario

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    //Los setters permiten modificar los atributos de un usuario

    @Override
    public String toString() {
        return String.format("Usuario [Nombre: %s | Apellido: %s | ID: %s | Email: %s | Teléfono: %d]", nombre, apellido, idUsuario, email, telefono);
    }
    //Permitira mostrar la informacion del usuario de forma legible.
}