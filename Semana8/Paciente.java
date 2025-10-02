package Semana8;

import java.time.LocalDateTime;
public class Paciente {

    //Atributos
    private String nombre;
    private LocalDateTime horaLlegada;
    private int prioridad;
    private String sintomas;

    //Constructor
    public Paciente(String nombre, int prioridad, String sintomas) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.sintomas = sintomas;
        this.horaLlegada = null;
    }

    public void setHoraLlegada(LocalDateTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public LocalDateTime getHoraLlegada() {
        return horaLlegada;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getSintomas() {
        return sintomas;
    }

    public String toString() {
        return String.format("Paciente [Nombre: %s | Hora de Llegada: %s | Prioridad: %d | Síntomas: %s]", nombre, horaLlegada != null ? horaLlegada.toString() : "No registrada", prioridad, sintomas);
    }
}
