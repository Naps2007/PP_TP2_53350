package app;

import MODELO.*;
import actividades.*;
import excepciones.*;
import certificacion.*;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE EVENTOS UNIVERSITARIOS ===");

        // Creacamos evento y su sala
        EventoUniversitario evento = new EventoUniversitario("E100", "Jornadas de Inteligencia Artificial", 2000.0, false);
        Sala sala = new Sala("Auditorio Central", 50);
        evento.asignarSala(sala);

        // Estudiantes
        Estudiante e1 = new Estudiante("53350", "Tomas Felman");
        Estudiante e2 = new Estudiante("44503", "Rando Cold");
        Estudiante e3 = new Estudiante("60001", "Ana Martinez");

        // Actividades
        Charla charla = new Charla(101, "IA del Futuro", 1, "Dr. Turing");
        Taller taller = new Taller(102, "Taller Intensivo de Java", 20, true);
        Curso curso = new Curso(103, "Curso de Data Science", 15, 2);

        evento.getActividades().add(charla);
        evento.getActividades().add(taller);
        evento.getActividades().add(curso);

        // Ve si hay cupos
        System.out.println("\n--- Procesando Inscripciones ---");
        try {
            System.out.println("Inscribiendo a " + e1.getNombre() + " en Charla...");
            charla.inscribir(e1);

            System.out.println("Inscribiendo a " + e2.getNombre() + " en Taller y Curso...");
            taller.inscribir(e2);
            curso.inscribir(e2);
            curso.inscribir(e3);

            // Intento que supera el cupo para demostrar la captura de la excepción
            System.out.println("Intentando inscribir a " + e2.getNombre() + " en Charla (sin cupo)...");
            charla.inscribir(e2);

        } catch (CupoExcedidoException e) {
            System.out.println("[ALERTA DE SISTEMA] " + e.getMessage());
        } finally {
            System.out.println("[SISTEMA] Validación de cupos de inscripción finalizada.");
        }

        // Guardado y lectura del evento desde archivo
        System.out.println("\n--- Persistencia de Datos ---");
        if (evento.persistirEvento()) {
            System.out.println("Estado del evento guardado correctamente en archivo .dat.");
        }

        EventoUniversitario eventoRecuperado = EventoUniversitario.recuperarEvento("E100");
        if (eventoRecuperado != null) {
            System.out.println("Evento verificado y recuperado desde disco: " + eventoRecuperado.getTitulo());
        }

        // Emisión de Certificados para actividades elegibles
        System.out.println("\n--- Emitiendo Certificados ---");
        for (Actividad act : evento.getActividades()) {
            if (act instanceof Certificable) {
                Certificable certificable = (Certificable) act;
                for (Inscripcion ins : act.getInscripciones()) {
                    System.out.println(" * " + certificable.generarCertificado(ins.getEstudiante()));
                }
            }
        }

        // Filtrado de actividades con Generics y cálculo de costos de materiales
        System.out.println("\n--- Resumen Técnico y Costos ---");
        List<Charla> charlas = evento.filtrarActividadesPorTipo(Charla.class);
        List<Taller> talleres = evento.filtrarActividadesPorTipo(Taller.class);
        List<Curso> cursos = evento.filtrarActividadesPorTipo(Curso.class);

        System.out.println("Charlas registradas: " + charlas.size());
        System.out.println("Talleres registrados: " + talleres.size());
        System.out.println("Cursos registrados: " + cursos.size());

        double costoTotalMateriales = evento.calcularCostoMateriales(evento.getActividades());
        System.out.println("Costo total de materiales: $" + costoTotalMateriales);

        System.out.println("\n=== EJECUCIÓN FINALIZADA EXITOSAMENTE ===");
    }
}
