package Biblioteca;

import java.util.LinkedList;

public class User {
    //Atributos
    private String idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private LinkedList<Prestamo> historialPrestamos; // 👈 historial individual

    // Constructor
    public User(String idUsuario, String nombre, String apellido, String email, String telefono) {
    this.idUsuario = idUsuario;
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.telefono = telefono;
    this.historialPrestamos = new LinkedList<>();
}

    //Esto nos permite crear un usuario con todos sus atributos

    //Getters
    public String getIdUsuario() {
        return idUsuario;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefono() {
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
    
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    //Los setters permiten modificar los atributos de un usuario

    @Override
    public String toString() {
        return String.format(
            "[ID: %s | Nombre: %s | Apellido: %s | Email: %s | Teléfono: %s]", idUsuario, nombre, apellido, email, telefono);
    }
    //Permitira mostrar la informacion del usuario de forma legible.
}