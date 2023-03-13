package pe.edu.utp.Models.DAO;

import pe.edu.utp.Models.DTO.EstudianteDTO;
import pe.edu.utp.utils.ErrorLog;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.util.Arrays;

public class EstudianteDAO {

    public static EstudianteDTO[] listarEstudiantes(String path_file, ErrorLog errorLog){
        //Declarando un array de EstudiantesDTO
        EstudianteDTO[] listaEstudiantes = new EstudianteDTO[0];

        //Procesando la data del archivo csv
        try {
            //Extrayendo cada linea del csv en un Array de String
            String[] datos = TextUTP.readlinesAsArray(path_file, TextUTP.OS.WINDOWS);

            //Inicializando el array de Estudiantes con la longitud exacta de estudiantes
            listaEstudiantes = new EstudianteDTO[datos.length-1];

            //Creando un string para albergar los atributos de un estudiante
            String[] atributos = new String[8];

            //Creando instancias de clase EstudianteDTO que se guardan en un array
            for (int i=0; i < listaEstudiantes.length; i++){
                //Extrayendo los atributos de cada línea y colocandolos en posiciones del array atributos
                atributos = datos[i+1].strip().split(";");

                //Copiando el estudianteDTO de paso en una posición del array listaEstudiantes
                listaEstudiantes[i]= new EstudianteDTO(atributos[0],atributos[1],atributos[2],
                        atributos[3],atributos[4],atributos[5],atributos[6],atributos[7]);

            }

            //Manejando y propagando excepciones
        } catch (Exception e) {
            try {
                //Capturando excepción y guardandola en un log de errores
                String  event = errorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
                //Imprimiendo error acontecido
                System.out.print(event+"-"+ EstudianteDAO.class.toString());
            }catch (Exception ex){
                System.out.print("Error al registrar logs");
            }
        }
        return listaEstudiantes;
    }



}
