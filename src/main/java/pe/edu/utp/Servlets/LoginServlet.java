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

        try {

            //Validando usuario ingresado
            ValidadorLogin.esUsuarioValido(codigoEstudiante);
            ValidadorLogin.esContrasenaValida(contrasena,codigoEstudiante);


            //Extrayendo estudiante ingresado
            EstudianteDTO estudianteDTO=new EstudianteDTO();
            for (EstudianteDTO estudiante : LanzadorApp.estudiantes) {
                if (estudiante.getCodigoEstudiante().equalsIgnoreCase(codigoEstudiante)) {
                    estudianteDTO = estudiante;
                }
            }

            //Extrayendo cursos aprobados del estudiante ingresado
            String registrosNotas= "src/main/resources/csv/registrosNotas.csv";
            ArrayList<CursoAprobadoDTO> cursosAprobados = CursoAprobadoDAO.listarCursosAprobados(registrosNotas,
                    estudianteDTO.getCodigoEstudiante(),LanzadorApp.cursos,LanzadorApp.errorLog);


            // Listando Cursos Aprobados del estudiante
            LanzadorApp.portal.listCursosAprobados(estudianteDTO,cursosAprobados);

            //Imprimiendo portal de notas del estudiante
            String html = LanzadorApp.portal.getHTMLReport(estudianteDTO);
            resp.setCharacterEncoding("utf-8");
            resp.getWriter().println(html);


        } catch (Exception e) {
            try {
                //Capturando excepción y guardandola en un log de errores
                String  event = LanzadorApp.errorLog.log(e.getMessage(), ErrorLog.Level.ERROR);

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