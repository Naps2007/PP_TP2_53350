package actividades;

import certificacion.Certificable;
import MODELO.Estudiante;
public class Taller extends Actividad implements Certificable {
    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
            super(id, titulo, cupoMaximo);
            this.requiereNotebook = requiereNotebook;
    }

    @Override
        public double calcularCostoMateriales() {
            return requiereNotebook ? 5000 : 2000;
    }

    @Override
        public String getTipo() {
            return "actividades.Taller";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "Certificado emitido por " + ENTIDAD_EMISORA + " a " + estudiante.getNombre() + " por el Taller " + getTitulo();
    }

}

