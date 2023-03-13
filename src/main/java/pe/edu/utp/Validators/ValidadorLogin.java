package pe.edu.utp.Validators;

import pe.edu.utp.Models.DTO.EstudianteDTO;

public class ValidadorLogin {

    public static boolean esUsuarioValido(String usuario, EstudianteDTO[] estudianteDTO){
      boolean resultado=false;

      for (int i=0; i<estudianteDTO.length;i++) {
          if (estudianteDTO[i].getCodigoEstudiante().equalsIgnoreCase(usuario)) {
              resultado = true;
          }
      }

      return resultado;
    }

    public static boolean esContrasenaValida(String contrasena, EstudianteDTO[] estudianteDTO){
        boolean resultado=false;

        for (int i=0; i<estudianteDTO.length;i++) {
            if (estudianteDTO[i].getContrasena().equalsIgnoreCase(contrasena)) {
                resultado = true;
            }
        }

        return resultado;
    }
}
