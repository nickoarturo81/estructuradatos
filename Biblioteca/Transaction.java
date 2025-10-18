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

    private Tipo tipo;
    private Object data;

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
            case AÑADIR_LIBRO:
                
                if (data instanceof Book) {
                    Book libro = (Book) data;
                    biblioteca.eliminarLibro(libro.getIsbn());
                }
                break;

            case ELIMINAR_LIBRO:
                
                if (data instanceof Book) {
                    Book libro = (Book) data;
                    biblioteca.anadirLibro(libro);
                }
                break;

            case AÑADIR_USUARIO:
                
                if (data instanceof User) {
                    User usuario = (User) data;
                    users.eliminarUsuario(usuario.getIdUsuario());
                }
                break;

            case ELIMINAR_USUARIO:
                
                if (data instanceof User) {
                    User usuario = (User) data;
                    users.anadirUsuario(usuario);
                }
                break;

            case PRESTAR_LIBRO:
                
                if (data instanceof Prestamo) {
                    Prestamo prestamo = (Prestamo) data;
                    biblioteca.regresarLibro(prestamo.getIsbnLibro());
                }
                break;

            case REGRESAR_LIBRO:
                
                if (data instanceof Prestamo) {
                    Prestamo prestamo = (Prestamo) data;
                    biblioteca.prestarLibro(prestamo.getIsbnLibro(), prestamo.getNombreUsuario());
                }
                break;

            default:
                System.out.println("⚠️ Tipo de transacción desconocido.");
        }
    }
}
