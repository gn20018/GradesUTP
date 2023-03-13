package pe.edu.utp.Models.DTO;

public class CursoAprobadoDTO {

    // region [Attributes]
    String codigoCurso;
    String nombreCurso;
    String turno;
    String docente;
    int creditos;
    int horasSemanales;
    int seccion;
    int promedioFinal;
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

    public String getSeccion() {
        return String.valueOf(seccion);
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }

    public String  getPromedioFinal() {
        return String.valueOf(promedioFinal);
    }

    public void setPromedioFinal(int promedioFinal) {
        this.promedioFinal = promedioFinal;
    }

    // endregion

    // region [Contructors]

    public CursoAprobadoDTO(String codigoCurso, String nombreCurso,
                            String turno, String docente, int creditos, int horasSemanales,
                            int seccion, int promedioFinal) {
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        this.turno = turno;
        this.docente = docente;
        this.creditos = creditos;
        this.horasSemanales = horasSemanales;
        this.seccion = seccion;
        this.promedioFinal = promedioFinal;
    }

    // endregion


    @Override
    public String toString() {
        return "CursoAprobadoDTO{" +
                "codigoCurso='" + codigoCurso + '\'' +
                ", nombreCurso='" + nombreCurso + '\'' +
                ", turno='" + turno + '\'' +
                ", docente='" + docente + '\'' +
                ", creditos=" + creditos +
                ", horasSemanales=" + horasSemanales +
                ", seccion=" + seccion +
                ", promedioFinal=" + promedioFinal +
                '}';
    }
}
