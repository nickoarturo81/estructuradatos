package Semana12;

public class Contador {
    public SalidaContador contar(String cadena){}

    private int contarPalabrasFrases(String cadena){
        int contarPalabrasFrases = 0;
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            
    }

    private int contarCaracteres(String cadena){

    }

    private int contarVocales(String cadena){
        int contarVocales = 0;
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A') {
                contarVocales++;
            }
        }
        System.out.println("Cantidad Número de vocales: " + contarVocales);
    }

    private String identificarPalabraMasLarga(String cadena){

    }
}
