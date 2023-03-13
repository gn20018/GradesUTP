package pe.edu.utp.Models.DAO;

import pe.edu.utp.Models.DTO.CursoAprobadoDTO;
import pe.edu.utp.Models.DTO.CursoDTO;
import pe.edu.utp.Models.DTO.EstudianteDTO;
import pe.edu.utp.utils.ErrorLog;
import pe.edu.utp.utils.TextUTP;

import java.util.ArrayList;

public class CursoAprobadoDAO {

    public static ArrayList<CursoAprobadoDTO> listarCursosAprobados(String path_file,
                                                           String codigoEstudiante,
                                                           CursoDTO[] cursos , ErrorLog errorLog){

        //Declarando un array de CursoAprobadoDTO
        ArrayList<CursoAprobadoDTO> listaCursosAprobados = new ArrayList<>();


        //Procesando la data del archivo csv
        try {
            //Extrayendo cada linea del csv en un Array de String
            String[] datos = TextUTP.readlinesAsArray(path_file, TextUTP.OS.WINDOWS );


            //Creando un string para albergar los atributos de un cursoAprobado
            String[] atributos = new String[3];

            //Creando array de registro de notas del estudiante ingresado
            for (int i=0; i < datos.length-1; i++){
                //Extrayendo los atributos de cada línea y colocandolos en posiciones del array atributos
                atributos = datos[i+1].strip().split(";");

                if (atributos[0].equalsIgnoreCase(codigoEstudiante.strip().toLowerCase())){
                    for (int j=0; j < cursos.length;j++) {
                        if (atributos[1].equalsIgnoreCase(cursos[j].getCodigoCurso())){
                            listaCursosAprobados.add(new CursoAprobadoDTO(cursos[j].getCodigoCurso(),
                                    cursos[j].getNombreCurso(),cursos[j].getTurno(),cursos[j].getDocente(),cursos[j].getCreditos(),
                                    cursos[j].getHorasSemanales(),cursos[j].getSeccion(),Integer.parseInt(atributos[2])));
                        }
                    }
                }
            }

            //Manejando y propagando excepciones
        } catch (Exception e) {
            try {
                //Capturando excepción y guardandola en un log de errores
                String  event = errorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
                //Imprimiendo error acontecido
                System.out.print(event+"-"+ CursoAprobadoDAO.class.toString()+"\n");
            }catch (Exception ex){
                System.out.print("Error al registrar logs");
            }
        }
        return listaCursosAprobados;
    }
}
