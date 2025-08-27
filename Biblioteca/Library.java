package Biblioteca;

public class Library {
    private Book[] libros;
    private int contarLibro;
    
    public Library(int capacidad) {
        this.libros = new Book[capacidad];
        this.contarLibro = 0;
        System.out.println("Biblioteca creada con capacidad para " + capacidad);
    }

    public Book buscarTitulo(String titulo){
        System.out.println("Buscando por: " + titulo);

        for (int i = 0; i < contarLibro; i++) {
            if (libros[i].getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println("Libro encontrado: " + libros[i]);
                return libros[i];
            }
        }
        System.out.println("Libro no encontrado");
        return null;
    }

    public boolean añadirLibro(Book libro) {
    if (contarLibro >= libros.length) {
        System.out.println("Biblioteca llena, no se puede añadir más libros.");
        return false;
    }

        if (libro == null) {
        System.out.println("No se puede añadir un libro nulo.");
        return false;
    }

        for (int i = 0; i < contarLibro; i++) {
        if (libros[i].getIsbn().equals(libro.getIsbn())) {
            System.out.println("El libro con ISBN " + libro.getIsbn() + " ya existe.");
            return false;
            }
        }

        this.libros[contarLibro] = libro;
        contarLibro++;
        System.out.println("Libro añadido: " + libro.getTitulo() + " (Total libros: " + contarLibro + ")");
        return true;
    }

    public boolean eliminarLibro(String isbn) {
        int position = -1;
        for (int i =0; i < contarLibro; i++) {
            if (libros[i].getIsbn().equals(isbn)) {
                position = i;
                break;
            }
        }

        if (position == -1) {
            System.out.println("Libro con ISBN " + isbn + " no encontrado.");
            return false;
        }

        String tituloEliminadoString = libros[position].getTitulo();

        for (int i = position; i < contarLibro - 1; i++) {
            libros[i] = libros[i + 1];
        }

        contarLibro--;
        libros[contarLibro] = null;

        System.out.println("Libro eliminado: " + tituloEliminadoString + " (Total libros: " + contarLibro + ")");
        return true;


    }

    public void mostrarTodosLibros() {
        System.out.println("\n== Biblioteca (" + contarLibro + "/" + libros.length + " libros) ==");

        if (contarLibro == 0) {
            System.out.println("No hay libros en la biblioteca.");
            return;
        } else {
            for (int i = 0; i < contarLibro; i++) {
                System.out.println((i + 1 ) + ". " + libros[i]);
            }
        }
        System.out.println("====================================\n");
    }

    public void mostrarLibrosDisponibles() {
        System.out.println("\n== Libros Disponibles ==");
        int contadorDisponibles = 0;

        for (int i = 0; i < contarLibro; i++) {
            if (libros[i].isDisponible()) {
                System.out.println("- " + libros[i]);
                contadorDisponibles++;
            }
        }

        if (contadorDisponibles == 0) {
            System.out.println("No hay libros disponibles en este momento.");
            return;
        } else {
            System.out.println("Total de libros disponibles: " + contadorDisponibles);
        }
        System.out.println("========================\n");

        
    }

    public boolean prestarLibro(String isbn) {
        for (int i = 0; i < contarLibro; i++) {
            if (libros[i].getIsbn().equals(isbn)) {
                if (libros[i].isDisponible()) {
                    libros[i].setDisponible(false);
                    System.out.println("Has prestado el libro: " + libros[i].getTitulo());
                    return true;
                }
                libros[i].setDisponible(false);
                System.out.println("Libro prestado: " + libros[i].getTitulo());
                return true;
            }
        }
        System.out.println("Libro con ISBN " + isbn + " no encontrado.");
        return false;
    }

    public boolean regresarLibro (String isbn){
        for (int i = 0; i < contarLibro; i++) {
            if (libros[i].getIsbn().equals(isbn)) {
                if (libros[i].isDisponible()) {
                    System.out.println(" El libro no fue prestado");
                    return true;
                }
                libros[i].setDisponible(true);
                System.out.println("Has regresado el libro: " + libros[i].getTitulo());
                return true;
            }
        }

        System.out.println("Libro con ISBN " + isbn + " no encontrado.");
        return false;
    }

    public boolean isVacio() {
        return contarLibro == 0;
    }

    public boolean isLleno() {
        return contarLibro >= libros.length;
    }

    public int getContarLibro() {
        return contarLibro;
    }  

    public int getCapacidad() {
        return libros.length;
    }
}
