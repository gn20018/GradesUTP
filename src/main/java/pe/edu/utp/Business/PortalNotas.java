package pe.edu.utp.Business;

import pe.edu.utp.Models.DTO.CursoAprobadoDTO;
import pe.edu.utp.Models.DTO.CursoDTO;
import pe.edu.utp.Models.DTO.EstudianteDTO;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.util.*;

public class PortalNotas {

    private TreeMap<EstudianteDTO, ArrayList<CursoAprobadoDTO>> listadoEstudianteCursosAprobados = new TreeMap<>();


    public void listCursosAprobados(EstudianteDTO estudiante, ArrayList<CursoAprobadoDTO> cursosAprobados){
        listadoEstudianteCursosAprobados.put(estudiante,cursosAprobados);
    }

    public TreeMap<EstudianteDTO, ArrayList<CursoAprobadoDTO>> getListadoEstudiantes(){
        return listadoEstudianteCursosAprobados;
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
        String filename = "src\\main\\resources\\templates\\portalNotas.html";
        String html = TextUTP.read(filename);

        // Cargar plantilla para las filas de tarjetas
        String filename_items = "src/main/resources/templates/filas.html";
        String fila_html = TextUTP.read(filename_items);

        // Cargar plantilla para las tarjetas de cursos
        String filename_tarjetas = "src/main/resources/templates/tarjetaCurso.html";
        String html_item = TextUTP.read(filename_tarjetas);

        //Recorrer la lista de cursos aprobados
        StringBuilder items_html = new StringBuilder();
        String filaTarjetas = "";
        int tarjetas=0;
        String filas;

          CursoAprobadoDTO[] cursoAprobados = new CursoAprobadoDTO[0];
          cursoAprobados = listadoEstudianteCursosAprobados.get(estudianteDTO).toArray(cursoAprobados);


            for (int i = 0; i < cursoAprobados.length; i++) {


                if (tarjetas%3==0&&tarjetas>0){
                    //Agregando filas de tarjetas a html
                    filas =fila_html.replace("${txtTarjetaCurso}",filaTarjetas);
                    items_html.append(filas);
                    filaTarjetas="";
                }

                String item = html_item.replace("${txtNombreCurso}", cursoAprobados[i].getNombreCurso())
                    .replace("${txtTurno}", cursoAprobados[i].getTurno())
                        .replace("${Seccion}", cursoAprobados[i].getSeccion())
                        .replace("${PromedioFinal}", cursoAprobados[i].getPromedioFinal())
                        .replace("${txtDocente}", cursoAprobados[i].getDocente())
                    .replace("${txtCreditos}", Integer.toString(cursoAprobados[i].getCreditos()))
                    .replace("${txtHorasSemanales}", Integer.toString((cursoAprobados[i].getHorasSemanales())));

                filaTarjetas+=(item);
                tarjetas++;

                 if ( tarjetas == cursoAprobados.length){
                    filas = fila_html.replace("${txtTarjetaCurso}",filaTarjetas);
                    items_html.append(filas);
                }
            }


        //Reemplazando datos del estudiante
        String reporteHTML = html.replace("${txtCodigo}",estudianteDTO.getCodigoEstudiante())
                .replace("${txtNombres}",estudianteDTO.getPrimerNombre() + " " + estudianteDTO.getSegundoNombre())
                .replace("${txtApellidos}",estudianteDTO.getApellidoPaterno()+" "+estudianteDTO.getApellidoMaterno())
                .replace("${txtCarrera}",estudianteDTO.getCarrera())
                .replace("${txtPlanDeEstudios}",estudianteDTO.getPlanDeEstudios())
                .replace("${txtCreditosAprobados}",Integer.toString(getCreditosAprobados(estudianteDTO)))
                .replace("${listaCursos}",items_html);

        return reporteHTML;
    }

}
