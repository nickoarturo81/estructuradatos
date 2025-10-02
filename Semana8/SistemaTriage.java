package Semana8;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public class SistemaTriage {
    
    private Queue<Paciente> critico;
    private Queue<Paciente> urgente;
    private Queue<Paciente> estandar;

    public SistemaTriage() {
        this.critico = new ArrayDeque<>();
        this.urgente = new ArrayDeque<>();
        this.estandar = new ArrayDeque<>();
        
    }

    // Metodo para registrar paciente
    public int registrarPaciente(Paciente paciente) {
        if (paciente == null) return -1;
        if (getTotalPatients() >= capacidadTotal) return -1;

        paciente.setHoraLlegada(LocalDateTime.now());
        Queue<Paciente> q = queuePorPrioridad(paciente.getPrioridad());
        q.add(paciente);
        return q.size(); // posición en su cola (1-based)
    }

    //Metodo para llamar proximo paciente
    public Paciente llamarSiguientePaciente() {
        Paciente next = criticalQueue.poll();          //<- Revisa la priodad 1
        if (next != null) return next;
        next = urgentQueue.poll();                     //<- Revisa la priodad 2
        if (next != null) return next;
        return standardQueue.poll();                   //<- Revisa la priodad 3
    }

    // --- 3. GET_WAITING_TIME
    public long estimarTiempoEsperaPorNombre(String nombrePaciente) {
        SearchResult res = buscarPaciente(nombrePaciente);
        if (!res.found) return -1;

        int prioridad = res.queuePriority;
        int posicionEnCola = res.position; 
        int pacientesAdelanteMismaCola = posicionEnCola - 1;

        int pacientesPrioridadSuperior = contarPrioritariosSuperiores(prioridad);

        int totalAhead = pacientesPrioridadSuperior + pacientesAdelanteMismaCola;
        return (long) totalAhead * avgConsultationMinutes;
    }

    // --- 4. DISPLAY_STATUS
    public void mostrarEstado() {
        System.out.println("Estado del Sistema Triage:");
        System.out.println("Prioridad 1 (CRÍTICA): " + criticalQueue.size() +
                           (criticalQueue.peek() != null ? " | Siguiente: " + criticalQueue.peek().getNombre() : " | Vacía"));
        System.out.println("Prioridad 2 (URGENTE): " + urgentQueue.size() +
                           (urgentQueue.peek() != null ? " | Siguiente: " + urgentQueue.peek().getNombre() : " | Vacía"));
        System.out.println("Prioridad 3 (ESTÁNDAR): " + standardQueue.size() +
                           (standardQueue.peek() != null ? " | Siguiente: " + standardQueue.peek().getNombre() : " | Vacía"));
        System.out.println("Total pacientes en espera: " + getTotalPatients());
    }

    // --- 5. REMOVE_PATIENT
    public boolean removerPaciente(String nombrePaciente) {
        if (removerEnCola(criticalQueue, nombrePaciente)) return true;
        if (removerEnCola(urgentQueue, nombrePaciente)) return true;
        return removerEnCola(standardQueue, nombrePaciente);
    }

    // --- Helpers privados
    private Queue<Paciente> queuePorPrioridad(int prioridad) {
        switch (prioridad) {
            case 1: return criticalQueue;
            case 2: return urgentQueue;
            default: return standardQueue;
        }
    }

    private boolean removerEnCola(Queue<Paciente> cola, String nombre) {
        Iterator<Paciente> it = cola.iterator();
        while (it.hasNext()) {
            Paciente p = it.next();
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    private int contarPrioritariosSuperiores(int prioridad) {
        if (prioridad <= 1) return 0;
        if (prioridad == 2) return criticalQueue.size();
        return criticalQueue.size() + urgentQueue.size();
    }

    private int getTotalPatients() {
        return criticalQueue.size() + urgentQueue.size() + standardQueue.size();
    }

    private SearchResult buscarPaciente(String nombre) {
        int pos = 1;
        for (Paciente p : criticalQueue) {
            if (p.getNombre().equalsIgnoreCase(nombre)) return new SearchResult(true, 1, pos);
            pos++;
        }
        pos = 1;
        for (Paciente p : urgentQueue) {
            if (p.getNombre().equalsIgnoreCase(nombre)) return new SearchResult(true, 2, pos);
            pos++;
        }
        pos = 1;
        for (Paciente p : standardQueue) {
            if (p.getNombre().equalsIgnoreCase(nombre)) return new SearchResult(true, 3, pos);
            pos++;
        }
        return new SearchResult(false, -1, -1);
    }

    // Clase interna
    private static class SearchResult {
        boolean found;
        int queuePriority;
        int position;
        SearchResult(boolean f, int q, int p) {
            this.found = f;
            this.queuePriority = q;
            this.position = p;
        }
    }
}






