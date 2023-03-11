package pe.edu.utp.Models.DAO;

import pe.edu.utp.Models.DTO.EstudianteDTO;
import pe.edu.utp.utils.ErrorLog;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;

public class EstudianteDAO {

    public static EstudianteDTO[] listarEstudiantes(String path_file, ErrorLog errorLog){
        EstudianteDTO[] listaEstudiantes = new EstudianteDTO[0];

        try {
            String[] datos = TextUTP.readlinesAsArray(path_file, TextUTP.OS.WINDOWS);

            listaEstudiantes = new EstudianteDTO[datos.length-1];

            EstudianteDTO estudianteDTO;
            for (int i=0; i < listaEstudiantes.length; i++){

            }

        } catch (Exception e) {
            try {
                String  event = errorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
                System.out.print(event);
            }catch (Exception ex){
                System.out.print("Error al registrar logs");
            }
        }
        return listaEstudiantes;
    }
}
