package Biblioteca;

public class Prestamo {
    private String idUsuario;
    private String tituloLibro;
    private String fechaPrestamo;
    private String fechaDevolucion;

    public Prestamo(String idUsuario, String tituloLibro, String fechaPrestamo, String fechaDevolucion) {
        this.idUsuario = idUsuario;
        this.tituloLibro = tituloLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getIdUsuario() {
        return idUsuario;
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

    @Override
    public String toString() {
        return String.format(
            "Préstamo [Usuario ID: %s | Libro: %s | Fecha Préstamo: %s | Fecha Devolución: %s]", idUsuario, tituloLibro, fechaPrestamo, fechaDevolucion
        );
    }
}
