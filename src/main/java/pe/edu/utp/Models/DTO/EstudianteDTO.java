package pe.edu.utp.Models.DTO;

public class EstudianteDTO {

    // region [Attributes]
    String codigoEstudiante;
    String contrasena;
    String primerNombre;
    String segundoNombre;
    String apellidoPaterno;
    String apellidoMaterno;
    String carrera;
    String planDeEstudios;
    // endregion

    // region [Getters & Setters]

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getPlanDeEstudios() {
        return planDeEstudios;
    }

    public void setPlanDeEstudios(String planDeEstudios) {
        this.planDeEstudios = planDeEstudios;
    }

    // endregion

    // region [Contructors]

    public EstudianteDTO(String codigoEstudiante, String contrasena,
                         String primerNombre, String segundoNombre,
                         String apellidoPaterno, String apellidoMaterno, String carrera,
                         String planDeEstudios) {
        this.codigoEstudiante = codigoEstudiante;
        this.contrasena = contrasena;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.carrera = carrera;
        this.planDeEstudios = planDeEstudios;
    }

    // endregion
}
