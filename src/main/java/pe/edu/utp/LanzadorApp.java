package pe.edu.utp;

import pe.edu.utp.Business.PortalNotas;
import pe.edu.utp.Models.DAO.CursoDAO;
import pe.edu.utp.Models.DAO.EstudianteDAO;
import pe.edu.utp.Models.DTO.CursoDTO;
import pe.edu.utp.Models.DTO.EstudianteDTO;
import pe.edu.utp.Servlets.LoginServlet;
import pe.edu.utp.utils.ErrorLog;
import pe.edu.utp.utils.JettyUTP;

public class LanzadorApp {

    public static PortalNotas portal = new PortalNotas();

    public static ErrorLog errorLog;
    private static String listaEstudiantes_ruta = "src/main/resources/csv/registroEstudiantes.csv";
    private static String listaCursos_ruta= "src/main/resources/csv/registroCursos.csv";


   public static EstudianteDTO[] estudiantes ;
   public static CursoDTO[] cursos;


    public static void main(String[] args) throws Exception {

        //Generar ErrorLog
        generarErrorLog();

        //Generar lista de datos
        generarDatos();

        //Iniciando Servidor Web
        String path = "src/main/resources/web";
        JettyUTP webserver = new JettyUTP(8080,path);
        webserver.addServlet(LoginServlet.class, "/portalNotas");
        webserver.start();

    }

    private static void generarErrorLog() {
        try {
            errorLog = new ErrorLog("src/main/resources/error.log");
        }catch (Exception e){
            System.out.print("Error al crear el log");
        }
    }

    public static void generarDatos(){
        generarEstudiantes();
        generarCursos();
    }
    private static void generarEstudiantes() {
        //Generando lista de estudiantes
        estudiantes = EstudianteDAO.listarEstudiantes(listaEstudiantes_ruta, errorLog);
    }

    private static void generarCursos() {
        //Generando lista de cursos
        cursos = CursoDAO.listarCursos(listaCursos_ruta,errorLog);
    }
}