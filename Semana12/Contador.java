package Semana12;

public class Contador {
    public SalidaContador contar(String cadena){}

    private int contarPalabrasFrases(String cadena){
        String[] palabras = cadena.split("\\s+");
        int contarPalabrasFrases = palabras.length;
        return contarPalabrasFrases;    
    }

    private int contarCaracteres(String cadena){
        String quitarEspacios = cadena.replace(" ", "");
        int contarCaracteres = quitarEspacios.length();
        return contarCaracteres;
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
