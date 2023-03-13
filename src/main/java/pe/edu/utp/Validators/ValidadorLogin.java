package pe.edu.utp.Validators;

import pe.edu.utp.Models.DTO.EstudianteDTO;

public class ValidadorLogin {

    public static boolean esUsuarioValido(String usuario, EstudianteDTO[] estudianteDTO) throws Exception {
      boolean resultado=false;

      for (int i=0; i<estudianteDTO.length;i++) {
          if (estudianteDTO[i].getCodigoEstudiante().equalsIgnoreCase(usuario)) {
              resultado = true;
          }
      }

        if (resultado==false){
            String msg = "Código de Estudiante Erróneo";
            throw new Exception(msg);
        }

        return resultado;
    }

    public static boolean esContrasenaValida(String contrasena, EstudianteDTO[] estudiantesDTO, String codigoEstudiante) throws Exception{
        boolean resultado=false;

        for (int i=0; i<estudiantesDTO.length;i++) {
            if (estudiantesDTO[i].getCodigoEstudiante().equalsIgnoreCase(codigoEstudiante)) {
                if (estudiantesDTO[i].getContrasena().equalsIgnoreCase(contrasena)){
                    resultado = true;
                }
            }
        }

        if (resultado==false){
            String msg = "Contraseña Errónea";
            throw new Exception(msg);
        }

        return resultado;
    }



}
