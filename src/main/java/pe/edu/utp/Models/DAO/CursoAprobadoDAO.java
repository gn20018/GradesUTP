package pe.edu.utp.Models.DAO;


import pe.edu.utp.Models.DTO.CursoAprobadoDTO;
import pe.edu.utp.Models.DTO.CursoDTO;
import pe.edu.utp.utils.ErrorLog;
import pe.edu.utp.utils.TextUTP;
import java.util.ArrayList;

public class CursoAprobadoDAO {

    public static ArrayList<CursoAprobadoDTO> listarCursosAprobados(String rutaRegistros,
                                                           String codigoEstudiante,
                                                           CursoDTO[] cursos , ErrorLog errorLog){

        //Declarando un array de CursoAprobadoDTO
        ArrayList<CursoAprobadoDTO> listaCursosAprobados = new ArrayList<>();


        //Procesando la data del archivo csv
        try {

//          Extrayendo cada linea del csv en un Array de String
            String[] datos = TextUTP.readlinesAsArray(rutaRegistros, TextUTP.OS.WINDOWS );



            //Creando un string para albergar los atributos de un cursoAprobado
            String[] atributos;

            //Creando array de registro de notas del estudiante ingresado
            for (int i=0; i < datos.length-1; i++){
                //Extrayendo los atributos de cada línea y colocandolos en posiciones del array atributos
                atributos = datos[i+1].strip().split(";");

                if (atributos[0].equalsIgnoreCase(codigoEstudiante.strip().toLowerCase())){
                    for (CursoDTO curso : cursos) {
                        if (atributos[1].equalsIgnoreCase(curso.getCodigoCurso())) {
                            listaCursosAprobados.add(new CursoAprobadoDTO(curso.getCodigoCurso(),
                                    curso.getNombreCurso(), curso.getTurno(), curso.getDocente(), curso.getCreditos(),
                                    curso.getHorasSemanales(), curso.getSeccion(), Integer.parseInt(atributos[2])));
                        }
                    }
                }
            }

            //Manejando y propagando excepciones
        } catch (Exception e) {
            System.out.println(e.getMessage());
            try {
                //Capturando excepción y guardandola en un log de errores
                String  event = errorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
                //Imprimiendo error acontecido
                System.out.print(event+"-"+ CursoAprobadoDAO.class+"\n");
            }catch (Exception ex){
                System.out.print("Error al registrar logs - CURSO_APROBADO_DAO\n");
            }
        }
        return listaCursosAprobados;
    }
}
