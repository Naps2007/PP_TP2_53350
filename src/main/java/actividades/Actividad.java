package actividades;

import MODELO.Estudiante;
import MODELO.Inscripcion;
import excepciones.CupoExcedidoException;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements java.io.Serializable {
    private int id;
    private String titulo;
    private int cupoMaximo;
    public static final int CUPO_MINIMO=5;

    private List<Inscripcion> inscripciones;

    public Actividad(int id, String titulo, int cupoMaximo){
        this.id=id;
        this.titulo=titulo;
        this.cupoMaximo=cupoMaximo;
        this.inscripciones=new ArrayList<>();

    }

    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException {
        if (this.inscripciones.size() >= this.cupoMaximo) {
            throw new CupoExcedidoException("Cupo agotado para la actividad: " + this.titulo);
        }
        Inscripcion nuevaInscripcion = new Inscripcion(estudiante);
        this.inscripciones.add(nuevaInscripcion);
        return nuevaInscripcion;
    }


    public void mostrarInscripciones() {
        System.out.println("Inscriptos en la actividad: " + this.titulo);
        for (Inscripcion insc : inscripciones) {
            System.out.println(" - " + insc.getEstudiante().getNombre() + " | Legajo: " + insc.getEstudiante().getLegajo() + " | Fecha: " + insc.getFecha());
        }
    }

    public final void mostrarIdentificacion(){
            System.out.println("actividades.Actividad: " + titulo + " | Tipo: " + getTipo());
        }


    public abstract double calcularCostoMateriales();
    public abstract String getTipo();

    public String getTitulo() {
        return this.titulo;
    }

    public List<Inscripcion> getInscripciones() {
        return this.inscripciones;
    }

}
