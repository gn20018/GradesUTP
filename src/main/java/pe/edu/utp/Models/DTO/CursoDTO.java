package pe.edu.utp.Models.DTO;

public class CursoDTO {

    // region [Attributes]
    String codigoCurso;
    String nombreCurso;
    String turno;
    String docente;
    int creditos;
    int horasSemanales;
    int seccion;
    // endregion

    // region [Getters & Setters]

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public int getSeccion() {
        return seccion;
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }

    // endregion

    // region [Contructors]

    public CursoDTO(String codigoCurso, String nombreCurso, String turno,
                    String docente, int creditos, int horasSemanales, int seccion) {
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        this.turno = turno;
        this.docente = docente;
        this.creditos = creditos;
        this.horasSemanales = horasSemanales;
        this.seccion = seccion;
    }

    // endregion
}
