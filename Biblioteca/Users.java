package Biblioteca;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class Users {

    private ArrayList<User> users;
    private LinkedList<Prestamo> historialGeneral;

    // Constructor
    public Users() {
        this.users = new ArrayList<>();
        this.historialGeneral = new LinkedList<>(); 
    }


    // Método para obtener todos los usuarios
    public ArrayList<User> getTodosUsuarios() {
        return this.users;
    }

    // Añadir Usuario
    public void anadirUsuario(User user) {
        users.add(user);
    }

    // Eliminar Usuario (versión tradicional con for)
    public boolean eliminarUsuario(String idUsuario) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getIdUsuario().equalsIgnoreCase(idUsuario)) {
                users.remove(i);
                return true;
            }
        }
        return false; // Si no se encontró el usuario, retorna false
    }

    // Historial General de Préstamos
    public LinkedList<Prestamo> getHistorialGeneral() {
        return historialGeneral;
    }

    // Registrar Préstamo en Historial General
    public void registrarPrestamo(Prestamo prestamo) {
        historialGeneral.add(prestamo);
    }

    // Mostrar Usuarios con Iterator
    public void mostrarUsuarios() {
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            User user = it.next();
            System.out.println(user);
        }
    }

    // Buscar un usuario por su ID o por nombre
    public User buscarUsuario(String criterio) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            // Permite buscar por ID o por nombre (ignorando mayúsculas/minúsculas)
            if (user.getIdUsuario().equalsIgnoreCase(criterio) || user.getNombre().equalsIgnoreCase(criterio)) {
                return user;
            }
        }
        return null; // No se encontró el usuario
    }
}