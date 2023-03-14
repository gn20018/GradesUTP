package pe.edu.utp.Validators;

import pe.edu.utp.LanzadorApp;
import pe.edu.utp.Models.DTO.EstudianteDTO;

public class ValidadorLogin {

    public static boolean esUsuarioValido(String usuario) throws Exception {
      boolean resultado=false;
      int vueltas = 0;

      while (vueltas<=2 && vueltas>=0){
          for (int i=0; i<LanzadorApp.estudiantes.length;i++) {
              if (LanzadorApp.estudiantes[i].getCodigoEstudiante().equalsIgnoreCase(usuario)) {
                  resultado = true;
              }
          }

          vueltas++;

          if (resultado==false){
              switch (vueltas){
                  case 1 -> LanzadorApp.generarDatos();
                  case 2 -> {
                      String msg = "Código de Estudiante Erróneo";
                      throw new Exception(msg);
                  }
                  default -> {
                      String msg = "Validación de Usuario Errónea";
                      throw new Exception(msg);
                  }
              }

          }
      }

        return resultado;
    }

    public static boolean esContrasenaValida(String contrasena, String codigoEstudiante) throws Exception{
        boolean resultado=false;
        int vueltas = 0;

        while (vueltas<=2 && vueltas>=0){
            for (int i=0; i<LanzadorApp.estudiantes.length;i++) {
                if (LanzadorApp.estudiantes[i].getCodigoEstudiante().equalsIgnoreCase(codigoEstudiante)) {
                    if (LanzadorApp.estudiantes[i].getContrasena().equalsIgnoreCase(contrasena)){
                        resultado = true;
                    }
                }
            }

            vueltas++;

            if (resultado==false){
                switch (vueltas){
                    case 1 -> LanzadorApp.generarDatos();
                    case 2 -> {
                        String msg = "Contraseña Errónea";
                        throw new Exception(msg);
                    }
                    default -> {
                        String msg = "Validación de Contraseña Errónea";
                        throw new Exception(msg);
                    }
                }

            }
        }

        return resultado;

    }



}
