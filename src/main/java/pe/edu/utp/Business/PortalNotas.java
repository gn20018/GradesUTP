package pe.edu.utp.Business;

import pe.edu.utp.LanzadorApp;
import pe.edu.utp.Models.DTO.CursoAprobadoDTO;
import pe.edu.utp.Models.DTO.EstudianteDTO;
import pe.edu.utp.utils.TextUTP;
import java.io.IOException;
import java.util.*;

public class PortalNotas {

    private HashMap<EstudianteDTO, ArrayList<CursoAprobadoDTO>> listadoEstudianteCursosAprobados = new HashMap<>();

    private static final String rutaPlantilaPortal = (String.format("%s%stemplates%sportalNotas.html",
            LanzadorApp.directorioRaiz, LanzadorApp.separador,LanzadorApp.separador));
    private static final String rutaPlantillaFila = (String.format("%s%stemplates%sfilas.html",
            LanzadorApp.directorioRaiz, LanzadorApp.separador,LanzadorApp.separador));
    private static final String rutaplantillaTarjetaCursoHtml= (String.format("%s%stemplates%starjetaCurso.html",
            LanzadorApp.directorioRaiz, LanzadorApp.separador,LanzadorApp.separador));


    public void listCursosAprobados(EstudianteDTO estudiante, ArrayList<CursoAprobadoDTO> cursosAprobados){
        listadoEstudianteCursosAprobados.put(estudiante,cursosAprobados);
    }

    //Función para obtener créditos aprobados totales a partir de la suma de creditos de cada curso aprobado
    public int getCreditosAprobados(EstudianteDTO e){
        int creditosAprobados=0;
        CursoAprobadoDTO[] cursos = new CursoAprobadoDTO[0];
        cursos = listadoEstudianteCursosAprobados.get(e).toArray(cursos);
        for (CursoAprobadoDTO c:cursos) {
            creditosAprobados += c.getCreditos();
        }
        return creditosAprobados;
    }

    public String getHTMLReport(EstudianteDTO estudianteDTO) throws IOException {

        // Cargar plantilla principal del portal
            String plantillaPortalHtml = TextUTP.read(rutaPlantilaPortal);

            // Cargar plantilla para las filas de tarjetas
            String plantillaFilasHtml = TextUTP.read(rutaPlantillaFila);

            // Cargar plantilla para las tarjetas de cursos
            String plantillaTarjetaCursoHtml = TextUTP.read(rutaplantillaTarjetaCursoHtml);

        //Recorrer la lista de cursos aprobados

        StringBuilder tarejtasCursosTotales = new StringBuilder();

        //String que albergará 1 sola fila completa con sus 3 tarjetas de cursos
        String filaTarjetas = "";

        //Contador de Tarjetas de cursos instanciadas
        int tarjetas=0;

        //String que albergará todas las filas con el total de tarjetas de cursos
        String filasTotales;

          CursoAprobadoDTO[] cursoAprobados = new CursoAprobadoDTO[0];
          cursoAprobados = listadoEstudianteCursosAprobados.get(estudianteDTO).toArray(cursoAprobados);


        for (CursoAprobadoDTO cursoAprobado : cursoAprobados) {

            //En caso de se instancien 3 tarjetas, se guarda dicha fila en un String y se empieza otra fila nueva
            if (tarjetas % 3 == 0 && tarjetas > 0) {
                //Agregando filas de tarjetas a html
                filasTotales = plantillaFilasHtml.replace("${txtTarjetaCurso}", filaTarjetas);
                tarejtasCursosTotales.append(filasTotales);
                filaTarjetas = "";
            }

            //Generando instancia de Tarjeta de Curso
            String item = plantillaTarjetaCursoHtml.replace("${txtNombreCurso}", cursoAprobado.getNombreCurso())
                    .replace("${txtTurno}", cursoAprobado.getTurno())
                    .replace("${Seccion}", cursoAprobado.getSeccion())
                    .replace("${PromedioFinal}", cursoAprobado.getPromedioFinal())
                    .replace("${txtDocente}", cursoAprobado.getDocente())
                    .replace("${txtCreditos}", Integer.toString(cursoAprobado.getCreditos()))
                    .replace("${txtHorasSemanales}", Integer.toString((cursoAprobado.getHorasSemanales())));

            //Añandiendo Tarjeta a una fila
            filaTarjetas += (item);

            //Aumentando contador de filas en 1 unidad
            tarjetas++;

            //En caso de llegar al último curso y la fila aún no está completa, llenar la fila con los cursos disponibles
            if (tarjetas == cursoAprobados.length) {
                filasTotales = plantillaFilasHtml.replace("${txtTarjetaCurso}", filaTarjetas);
                tarejtasCursosTotales.append(filasTotales);
            }
        }


        //Reemplazando datos del estudiante
        String reporteHTML = plantillaPortalHtml.replace("${txtCodigo}",estudianteDTO.getCodigoEstudiante())
                .replace("${txtNombres}",estudianteDTO.getPrimerNombre() + " " + estudianteDTO.getSegundoNombre())
                .replace("${txtApellidos}",estudianteDTO.getApellidoPaterno()+" "+estudianteDTO.getApellidoMaterno())
                .replace("${txtCarrera}",estudianteDTO.getCarrera())
                .replace("${txtPlanDeEstudios}",estudianteDTO.getPlanDeEstudios())
                .replace("${txtCreditosAprobados}",Integer.toString(getCreditosAprobados(estudianteDTO)))
                .replace("${listaCursos}",tarejtasCursosTotales);

        return reporteHTML;
    }

}
