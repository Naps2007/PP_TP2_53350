package MODELO;

import java.time.LocalDate;

public class Inscripcion implements java.io.Serializable {

    private LocalDate fecha;
    private String estado;

    private Estudiante estudiante;

    public Inscripcion(String estado, Estudiante estudiante) {
        this.estado = estado;
        this.estudiante = estudiante;
        this.fecha = LocalDate.now();
    }

    public Inscripcion(Estudiante estudiante) {
        this("confirmada", estudiante);
    }

    public LocalDate getFecha(){
        return fecha;
    }
    public String getEstado(){
        return estado;
    }
    public Estudiante getEstudiante(){
        return estudiante;
    }
}
