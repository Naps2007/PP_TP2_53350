package MODELO;

import actividades.Charla;
import actividades.Taller;
import actividades.Actividad;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements java.io.Serializable {
    private final String Id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos;
    private Sala sala;
    private List<Actividad> actividades = new ArrayList<>();


    public EventoUniversitario(String Id, String nombre,  double costo, boolean esGratuito) {
        this.Id = Id;
        titulo = nombre;
        this.gratuito = esGratuito;
        this.costoBase = gratuito ? 0 : costo;
         cantidadEventos++;
    }

    public EventoUniversitario(EventoUniversitario otroEvento) {
        this.Id = otroEvento.Id + "-COPIA";
        this.titulo = otroEvento.titulo;
        this.costoBase = otroEvento.costoBase;
        this.gratuito = otroEvento.gratuito;
        cantidadEventos++;
    }


    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0;
        }

        double costoTotalActividades = 0;
        for (Actividad act : actividades) {
            costoTotalActividades += act.calcularCostoMateriales();
        }

        return (costoBase + costoTotalActividades) * 1.21;
    }

    public void asignarSala(Sala sala){
        this.sala=sala;

    }
    public void crearActividad(int id, String titulo, int cupo, String tipo, boolean requiereNotebook, String orador) {
        if (tipo.equalsIgnoreCase("actividades.Charla")) {
            this.actividades.add(new Charla(id, titulo, cupo, orador));
        } else if (tipo.equalsIgnoreCase("actividades.Taller")) {
            this.actividades.add(new Taller(id, titulo, cupo, requiereNotebook));
        }
    }

    public List<Actividad> getActividades() {
        return actividades;
    }
    public void mostrarDatos(){

        System.out.println("nombre del evento:"+ titulo);
        System.out.println("costo estimado:"+ calcularCostoEstimado());
        System.out.println("¿Evento gratuito?:"+ gratuito);
        System.out.println("Evento:"+ Id);
        System.out.println("cantidad de eventos:"+ cantidadEventos);
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public boolean persistirEvento() {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream("evento_" + this.Id + ".dat"))) {
            oos.writeObject(this);
            return true;
        } catch (java.io.IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
            return false;
        }
    }

    public static EventoUniversitario recuperarEvento(String id) {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream("evento_" + id + ".dat"))) {
            return (EventoUniversitario) ois.readObject();
        } catch (java.io.IOException | ClassNotFoundException e) {
            System.err.println("Error al recuperar: " + e.getMessage());
            return null;
        }
    }

    public <T extends Actividad> java.util.List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        java.util.List<T> resultado = new java.util.ArrayList<>();
        for (Actividad act : this.actividades) {
            if (tipo.isInstance(act)) {
                resultado.add(tipo.cast(act));
            }
        }
        return resultado;
    }

    public double calcularCostoMateriales(java.util.List<? extends Actividad> listaActividades) {
        double total = 0;
        for (Actividad act : listaActividades) {
            total += act.calcularCostoMateriales();
        }
        return total;
    }


}
