package pe.edu.utp.Models.DAO;

import pe.edu.utp.Models.DTO.CursoDTO;
import pe.edu.utp.utils.ErrorLog;
import pe.edu.utp.utils.TextUTP;

public class CursoDAO {

    public static CursoDTO[] listarCursos(String path_file, ErrorLog errorLog){
        //Declarando un array de cursosDTO
        CursoDTO[] listaCursos = new CursoDTO[0];

        //Procesando la data del archivo csv
        try {
            //Extrayendo cada linea del csv en un Array de String
            String[] datos = TextUTP.readlinesAsArray(path_file, TextUTP.OS.WINDOWS);

            //Inicializando el array de cursos con la longitud exacta de cursos
            listaCursos = new CursoDTO[datos.length-1];

            //Creando un string para albergar los atributos de un curso
            String[] atributos = new String[7];

            //Declarando un CursoDTO de paso
            CursoDTO cursoDTO;

            //Creando instancias de clase CursoDTO que se guardan en un array
            for (int i=0; i < listaCursos.length; i++){
                //Extrayendo los atributos de cada línea y colocandolos en posiciones del array atributos
                atributos = datos[i+1].strip().split(";");

                //Copiando el CursoDTO de paso en una posición del array listaCursos
                listaCursos[i]= new CursoDTO(atributos[0],atributos[1],atributos[2],
                        atributos[3],Integer.parseInt(atributos[4]),Integer.parseInt(atributos[5]),
                        Integer.parseInt(atributos[6]));
            }

            //Manejando y propagando excepciones
        } catch (Exception e) {
            try {
                //Capturando excepción y guardandola en un log de errores
                String  event = errorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
                //Imprimiendo error acontecido
                System.out.print(event+"-"+ CursoDAO.class.toString());
            }catch (Exception ex){
                System.out.print("Error al registrar logs");
            }
        }
        return listaCursos;
    }

}
