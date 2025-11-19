package Biblioteca;

public class Transaction {
    public enum Tipo {
        AÑADIR_LIBRO,
        ELIMINAR_LIBRO,
        AÑADIR_USUARIO,
        ELIMINAR_USUARIO,
        PRESTAR_LIBRO,
        REGRESAR_LIBRO
    }

    private Tipo tipo;      // Almacena la acción realizada
    private Object data;    // Almacena el tipo de dato

    public Transaction(Tipo tipo, Object data) {
        this.tipo = tipo;
        this.data = data;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Object getData() {
        return data;
    }
    public void undo(Library biblioteca, Users users) {
        switch (tipo) {
            //Añade el libro eliminado anteriormente
            case AÑADIR_LIBRO:
                
                if (data instanceof Book) {
                    Book libro = (Book) data;
                    biblioteca.eliminarLibro(libro.getIsbn());
                }
            break;
            //Elimina el libro añadido anteriormente
            case ELIMINAR_LIBRO:
                
                if (data instanceof Book) {
                    Book libro = (Book) data;
                    biblioteca.anadirLibro(libro);
                }
            break;
            //Añade el usuario eliminado anteriormente
            case AÑADIR_USUARIO:
                
                if (data instanceof User) {
                    User usuario = (User) data;
                    users.eliminarUsuario(usuario.getIdUsuario());
                }
            break;
            //Elimina el usuario añadido anteriormente
            case ELIMINAR_USUARIO:
                
                if (data instanceof User) {
                    User usuario = (User) data;
                    users.anadirUsuario(usuario);
                }
            break;
            //Regresa el libro prestado anteriormente
            case PRESTAR_LIBRO:
                
                if (data instanceof Prestamo) {
                    Prestamo prestamo = (Prestamo) data;
                    biblioteca.regresarLibro(prestamo.getIsbnLibro());
                }
            break;
            //Presta el libro regresado anteriormente
            case REGRESAR_LIBRO:
                
                if (data instanceof Prestamo) {
                    Prestamo prestamo = (Prestamo) data;
                    biblioteca.prestarLibro(prestamo.getIsbnLibro(), prestamo.getNombreUsuario());
                }
            break;

            default:
                System.out.println("⚠️ Tipo de proceso desconocido.");
        }
    }
}
