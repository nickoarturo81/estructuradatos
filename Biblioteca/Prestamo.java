package Biblioteca;

public class Prestamo {
    private String idUsuario;
    private String nombreUsuario;
    private String tituloLibro;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private String estado; // "Prestado" o "Devuelto"

    public Prestamo(String idUsuario, String nombreUsuario, String tituloLibro, String fechaPrestamo, String fechaDevolucion, String estado) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.tituloLibro = tituloLibro;
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

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    // Setters (solo si los necesitas para actualizar estado o fecha)
    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format(
            " Libro: %s | Usuario: %s | Préstamo: %s | Devolución: %s | Estado: %s", tituloLibro, nombreUsuario, 
            (fechaPrestamo != null ? fechaPrestamo : "-"), 
            (fechaDevolucion != null ? fechaDevolucion : "-"), 
            estado
        );
    }
}
