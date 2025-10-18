package Biblioteca;

public class Prestamo {
    private String idUsuario;
    private String nombreUsuario;
    private String tituloLibro;
    private String isbnLibro; 
    private String fechaPrestamo;
    private String fechaDevolucion;
    private String estado; // "Prestado" o "Devuelto"

    public Prestamo(String idUsuario, String nombreUsuario, String tituloLibro, String isbnLibro, String fechaPrestamo, String fechaDevolucion, String estado) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.tituloLibro = tituloLibro;
        this.isbnLibro = isbnLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    // Getters
    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public String getIsbnLibro() {
        return isbnLibro;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    // Setters
    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format(
            " Libro: %s (ISBN: %s) | Usuario: %s | Préstamo: %s | Devolución: %s | Estado: %s",
            tituloLibro, isbnLibro, nombreUsuario,
            (fechaPrestamo != null ? fechaPrestamo : "-"),
            (fechaDevolucion != null ? fechaDevolucion : "-"),
            estado
        );
    }

}