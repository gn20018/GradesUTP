package pe.edu.utp.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.LanzadorApp;
import pe.edu.utp.Models.DAO.CursoAprobadoDAO;
import pe.edu.utp.Models.DAO.CursoDAO;
import pe.edu.utp.Models.DAO.EstudianteDAO;
import pe.edu.utp.Models.DTO.CursoAprobadoDTO;
import pe.edu.utp.Models.DTO.CursoDTO;
import pe.edu.utp.Models.DTO.EstudianteDTO;
import pe.edu.utp.Validators.ValidadorLogin;
import pe.edu.utp.utils.ErrorLog;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.util.ArrayList;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Capturar credenciales
        String codigoEstudiante = (req.getParameter("txtCodigoEstudiante"));
        String contrasena = req.getParameter("txtContrasena");

        ErrorLog errorLog = null;
        try {
            errorLog = new ErrorLog("src/main/resources/error.log");
        } catch (Exception e) {
            System.out.print("Error al crear el log");
        }


        try {

            //Generando lista de alumnos
            String listaAlumnos = "src/main/resources/csv/registroEstudiantes.csv";
            EstudianteDTO[] estudiantes = EstudianteDAO.listarEstudiantes(listaAlumnos, errorLog);

            //Validando usuario ingresado
            ValidadorLogin.esUsuarioValido(codigoEstudiante,estudiantes);
            ValidadorLogin.esContrasenaValida(contrasena,estudiantes,codigoEstudiante);

            //Generando lista de cursos
            String listaCursos= "src/main/resources/csv/registroCursos.csv";
            CursoDTO[] cursos = CursoDAO.listarCursos(listaCursos,errorLog);


            //Extrayendo estudiante ingresado
            EstudianteDTO estudianteDTO=new EstudianteDTO();
            for (EstudianteDTO estudiante : estudiantes) {
                if (estudiante.getCodigoEstudiante().equalsIgnoreCase(codigoEstudiante)) {
                    estudianteDTO = estudiante;
                }
            }

            //Extrayendo cursos aprobados del estudiante ingresado
            String registrosNotas= "src/main/resources/csv/registrosNotas.csv";
            ArrayList<CursoAprobadoDTO> cursosAprobados = CursoAprobadoDAO.listarCursosAprobados(registrosNotas,estudianteDTO.getCodigoEstudiante(),cursos,errorLog);


            // Listando Cursos Aprobados del estudiante
            LanzadorApp.portal.listCursosAprobados(estudianteDTO,cursosAprobados);

            //Imprimiendo portal de notas del estudiante
            String html = LanzadorApp.portal.getHTMLReport(estudianteDTO);
            resp.setCharacterEncoding("utf-8");
            resp.getWriter().println(html);


        } catch (Exception e) {
            try {
                //Capturando excepción y guardandola en un log de errores
                String  event = errorLog.log(e.getMessage(), ErrorLog.Level.ERROR);

                //Devolviendo Página de credenciales erróneas
                String html = TextUTP.read("src/main/resources/web/credencialesErroneas.html");
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().println(html);
            }catch (Exception ex){
                System.out.print("Error al registrar logs");
            }
        }

    }
}