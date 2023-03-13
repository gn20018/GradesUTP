package pe.edu.utp.Business;

import pe.edu.utp.Models.DTO.CursoAprobadoDTO;
import pe.edu.utp.Models.DTO.CursoDTO;
import pe.edu.utp.Models.DTO.EstudianteDTO;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class PortalNotas {

    private TreeMap<EstudianteDTO, ArrayList<CursoAprobadoDTO>> listadoEstudianteCursosAprobados = new TreeMap<>();


    public void listCursosAprobados(EstudianteDTO estudiante, ArrayList<CursoAprobadoDTO> cursosAprobados){
        listadoEstudianteCursosAprobados.put(estudiante,cursosAprobados);
    }

    public TreeMap<EstudianteDTO, ArrayList<CursoAprobadoDTO>> getListadoEstudiantes(){
        return listadoEstudianteCursosAprobados;
    }

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
        // Cargar plantilla principal
        String filename = "src\\main\\resources\\templates\\portalNotas.html";
        String html = TextUTP.read(filename);

//        // Cargar plantilla para los cursos aprobados
//        String filename_items = "src\\main\\resources\\templates\\listado_cursos.html";
//        String html_item = TextUTP.read(filename_items);

        //Reemplazando datos del estudiante
        String reporteHTML = html.replace("${txtCodigo}",estudianteDTO.getCodigoEstudiante())
                .replace("${txtNombres}",estudianteDTO.getPrimerNombre() + " " + estudianteDTO.getSegundoNombre())
                .replace("${txtApellidos}",estudianteDTO.getApellidoPaterno()+" "+estudianteDTO.getApellidoMaterno())
                .replace("${txtCarrera}",estudianteDTO.getCarrera())
                .replace("${txtPlanDeEstudios}",estudianteDTO.getPlanDeEstudios())
                .replace("${txtCreditosAprobados}",Integer.toString(getCreditosAprobados(estudianteDTO)));

//        // Recorrer la lista
//        StringBuilder items_html = new StringBuilder();
//
//        for(Iterator it = listadoEstudianteCursosAprobados.keySet().iterator(); it.hasNext();){
//            EstudianteDTO estudiante1 = (EstudianteDTO) it.next();
//            CursoAprobadoDTO[] cursoAprobados = (CursoAprobadoDTO[]) listadoEstudianteCursosAprobados.get(estudiante1).toArray();
//
//            for (int i = 0; i < cursoAprobados.length; i++) {
//                String item = html_item.replace("${nombreCurso}", cursoAprobados[i].getNombreCurso())
//                        .replace("${turno}", cursoAprobados[i].getTurno())
//                        .replace("${docente}", cursoAprobados[i].getDocente())
//                        .replace("${creditos}", Integer.toString(cursoAprobados[i].getCreditos()))
//                        .replace("${horasSemanales}", Integer.toString((cursoAprobados[i].getHorasSemanales()))
//                        .replace("${seccion}", Integer.toString((cursoAprobados[i].getSeccion()))
//                        .replace("${promedioFinal}", Integer.toString((cursoAprobados[i].getPromedioFinal())))));
//                items_html.append(item);
//            }
//
//        }


        return reporteHTML;
    }

}
