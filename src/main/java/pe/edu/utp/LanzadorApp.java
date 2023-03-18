package pe.edu.utp;

import org.apache.commons.cli.*;
import pe.edu.utp.Business.PortalNotas;
import pe.edu.utp.Models.DAO.CursoDAO;
import pe.edu.utp.Models.DAO.EstudianteDAO;
import pe.edu.utp.Models.DTO.CursoDTO;
import pe.edu.utp.Models.DTO.EstudianteDTO;
import pe.edu.utp.Servlets.LoginServlet;
import pe.edu.utp.utils.ErrorLog;
import pe.edu.utp.utils.JettyUTP;


public class LanzadorApp {

    private static Options options;
    private static CommandLine cmd;
    public static PortalNotas portal;

    public static ErrorLog errorLog;
    public static String directorioRaiz;
    private static String archivoEstudiantes;
    private static String archivoCursos;
    private static String archivoRegistros;
    private static String archivoErrorLog;

    public static final String separador = System.getProperty("file.separator");
   public static EstudianteDTO[] estudiantes;
   public static CursoDTO[] cursos;
    public static String getArchivoRegistros() {
        return archivoRegistros;
    }


    public static void main(String[] args) throws Exception {

        try {
            //Generando Opciones
            crearOpciones();

            //Pedir Ruta de Carpeta de Recursos
            directorioRaiz = obtenerDirectorioRecursos(args);

            try {
                //Generando rutas de archivos necesarios
                archivoEstudiantes = String.format("%s%scsv%sregistroEstudiantes.csv",directorioRaiz,separador,separador);
                archivoCursos = String.format("%s%scsv%sregistroCursos.csv",directorioRaiz,separador,separador);
                archivoRegistros = String.format("%s%scsv%sregistroNotas.csv",directorioRaiz,separador,separador);
                archivoErrorLog = String.format("%s%serror.log",directorioRaiz,separador);

                //Generar ErrorLog
                generarErrorLog();

                //Instanciando clase Portal de Notas
                portal = new PortalNotas();

                //Generar lista de datos
                generarDatos();

                //Iniciando Servidor Web
                String path = directorioRaiz.concat(separador.concat("web"));

                int puerto = obtenerPuerto(args);

                try{
                    System.out.println("Servidor iniciado en el puerto: "+puerto);
                    JettyUTP  webserver = new JettyUTP(obtenerPuerto(args),path);
                    webserver.addServlet(LoginServlet.class, "/portalNotas");
                    webserver.start();
                }catch (Exception e) {
                    System.out.println(e);
                }




            }catch (Exception e){
                try {
                    //Capturando excepción y guardandola en un log de errores
                    String  event = LanzadorApp.errorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
                    System.out.println(event);
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                    System.out.print("Error al registrar logs");
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }


    private static void crearOpciones() throws Exception {
        //Generando Opciones de CMD
        options = new Options();
        options.addOption("h", "help", false, "Muestra ayuda");
        options.addOption("r", "resources", true, "Ingresa el Directorio de Recursos");
        options.addOption("p", "port", true, "Especifica el puerto para ejecutar.");
    }

    private static String obtenerDirectorioRecursos(String[] args) throws Exception {

        String directorioRecursos = null;

        try {
            cmd = new DefaultParser().parse(options, args);
            if (cmd.hasOption("r")) {
                directorioRecursos = cmd.getOptionValue("r");
            }
        } catch (Exception e) {
            String msg = "Ruta de Directorio de Recursos No ingresada";
            throw new Exception(msg);
        }finally {
            return directorioRecursos;
        }

    }

    private static int obtenerPuerto(String[] args)throws Exception{
        int puerto = 8080;

        try {
            cmd = new DefaultParser().parse(options, args);
            if (cmd.hasOption("p")) {
                puerto = Integer.parseInt(cmd.getOptionValue("p"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            return puerto;
        }

    }


    private static void generarErrorLog() {
        try {
            errorLog = new ErrorLog(archivoErrorLog);
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
        estudiantes = EstudianteDAO.listarEstudiantes(archivoEstudiantes,errorLog);
    }

    private static void generarCursos() {
        //Generando lista de cursos
        cursos = CursoDAO.listarCursos(archivoCursos, errorLog);
    }
}