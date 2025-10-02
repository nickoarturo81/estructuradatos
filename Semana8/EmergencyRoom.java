package Semana8;

import java.util.Scanner;

public class EmergencyRoom {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        SistemaTriage sistemaTriage = new SistemaTriage();
        String opc;

        do {
            opc = mostrarMenuSalaEmergencia(leer);
            switch (opc) {
                case "1":
                    registrarPaciente(leer, sistemaTriage);
                    break;
                case "2":
                    llamarPaciente(sistemaTriage);
                    break;
                case "3":
                    sistemaTriage.mostrarEstado();
                    break;
                case "4":
                    removerPaciente(leer, sistemaTriage);
                    break;
                case "5":
                    estimarEspera(leer, sistemaTriage);
                    break;
                case "6":
                    System.out.println("👋 Saliendo del sistema...");
                    break;
                default:
                    System.out.println("❌ Opción no válida, intente de nuevo.");
            }
        } while (!opc.equals("6"));

        leer.close();
    }

    // --- Menú
    public static String mostrarMenuSalaEmergencia(Scanner leer) {
        System.out.println("\n=== Menú de la Sala de Emergencias ===");
        System.out.println("1. Registrar paciente");
        System.out.println("2. Llamar siguiente paciente");
        System.out.println("3. Ver pacientes en espera");
        System.out.println("4. Remover paciente por nombre");
        System.out.println("5. Estimar tiempo de espera por paciente");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
        return leer.nextLine();
    }

    // --- 1. Registrar paciente
    public static void registrarPaciente(Scanner leer, SistemaTriage sistemaTriage) {
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = leer.nextLine();

        System.out.print("Ingrese los síntomas: ");
        String sintomas = leer.nextLine();

        System.out.print("Ingrese la prioridad (1 - Crítica, 2 - Urgente, 3 - Estándar): ");
        int prioridad;
        try {
            prioridad = Integer.parseInt(leer.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Prioridad inválida.");
            return;
        }

        if (prioridad < 1 || prioridad > 3) {
            System.out.println("❌ Prioridad no válida. Intente de nuevo.");
            return;
        }

        Paciente nuevoPaciente = new Paciente(nombre, prioridad, sintomas);
        int posicion = sistemaTriage.registrarPaciente(nuevoPaciente);

        if (posicion != -1) {
            System.out.println("✅ Paciente registrado exitosamente en la posición " + posicion + " de la cola.");
        } else {
            System.out.println("❌ No se pudo registrar al paciente (capacidad llena).");
        }
    }

    // --- 2. Llamar siguiente paciente
    public static void llamarPaciente(SistemaTriage sistemaTriage) {
        Paciente siguiente = sistemaTriage.llamarSiguientePaciente();
        if (siguiente == null) {
            System.out.println("⚠️ No hay pacientes en espera.");
        } else {
            System.out.println("📢 Atendiendo a: " + siguiente.getNombre() + 
                               " | Prioridad: " + siguiente.getPrioridad() + 
                               " | Síntomas: " + siguiente.getSintomas());
        }
    }

    // --- 4. Remover paciente
    public static void removerPaciente(Scanner leer, SistemaTriage sistemaTriage) {
        System.out.print("Ingrese el nombre del paciente a remover: ");
        String nombre = leer.nextLine();
        boolean eliminado = sistemaTriage.removerPaciente(nombre);
        if (eliminado) {
            System.out.println("✅ Paciente eliminado de la lista de espera.");
        } else {
            System.out.println("❌ No se encontró un paciente con ese nombre.");
        }
    }

    // --- 5. Estimar tiempo de espera
    public static void estimarEspera(Scanner leer, SistemaTriage sistemaTriage) {
        System.out.print("Ingrese el nombre del paciente para estimar el tiempo: ");
        String nombre = leer.nextLine();
        long minutos = sistemaTriage.estimarTiempoEsperaPorNombre(nombre);

        if (minutos == -1) {
            System.out.println("❌ Paciente no encontrado en las colas.");
        } else {
            System.out.println("⏳ Tiempo estimado de espera para " + nombre + ": " + minutos + " minutos.");
        }
    }
}
