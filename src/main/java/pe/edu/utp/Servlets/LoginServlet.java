package pe.edu.utp.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.LanzadorApp;
import pe.edu.utp.Models.DAO.CursoAprobadoDAO;
import pe.edu.utp.Models.DTO.CursoAprobadoDTO;
import pe.edu.utp.Models.DTO.EstudianteDTO;
import pe.edu.utp.Validators.ValidadorLogin;
import pe.edu.utp.utils.ErrorLog;
import pe.edu.utp.utils.TextUTP;
import java.io.IOException;
import java.util.ArrayList;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
                    break;
                }
            }

            //Extrayendo cursos aprobados del estudiante ingresado
            ArrayList<CursoAprobadoDTO> cursosAprobados = CursoAprobadoDAO.listarCursosAprobados(LanzadorApp.getArchivoRegistros(),
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
                String html = TextUTP.read(String.format("%s%sweb%scredencialesErroneas.html",LanzadorApp.directorioRaiz
                        ,LanzadorApp.separador, LanzadorApp.separador));
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().println(html);
            }catch (Exception ex){
                System.out.print("Error al registrar logs - "+getClass());
            }
        }

    }
}