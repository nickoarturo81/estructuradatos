package Semana12;

public class test {
    private int contarPalabrasFrases(String cadena){
        String texto = "Hola mundo esto es una prueba";
        String[] palabras = cadena.split("\\s+");
        int contarPalabrasFrases = palabras.length;
        System.out.println("Cantidad Número de palabras/frases: " + contarPalabrasFrases);
        return contarPalabrasFrases;        
    }
    
}
