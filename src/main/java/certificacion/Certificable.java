package certificacion;

import MODELO.Estudiante;

public interface Certificable {
    String ENTIDAD_EMISORA = "UTN FRM";
    String generarCertificado(Estudiante estudiante);
}
