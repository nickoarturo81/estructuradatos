package Biblioteca;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class Users {
        
    private ArrayList<User> users;

    // Constructor
    public Users() {
    this.users = new ArrayList<>();
    }

    // Método para obtener todos los usuarios como ArrayList
    public ArrayList<User> getTodosUsuarios() {
    return this.users;
    }

    // Añadir Usuario
    public void anadirUsuario(User user) {
        users.add(user);
    }

    // Eliminar Usuario (versión tradicional con for)
    public boolean eliminarUsuario(String idUsuario) {
    for (int i = 0; i < users.size(); i++) { // Recorre toda la lista de usuarios
        User user = users.get(i);            // Obtiene el usuario en la posición actual
        if (user.getIdUsuario().equalsIgnoreCase(idUsuario)) { // Compara el ID
            users.remove(i);                        // Elimina el usuario por su posición
            return true;                            // Retorna true si se eliminó
        }
    }
        return false; // Si no se encontró el usuario, retorna false
    }

    // Historial de Préstamos
    public LinkedList<Prestamo> historialDePrestamos(String idUsuario) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getIdUsuario().equals(idUsuario)) {
                return user.getHistorialPrestamos();
            }
        }
        return null; // Usuario no encontrado
    }

    // Mostrar Usuarios con Iterator
    public void mostrarUsuarios() {
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            User user = it.next();
            System.out.println(user);
        }
    }
    
}
