package pe.edu.utp.Validators;

import pe.edu.utp.LanzadorApp;

public class ValidadorLogin {

    public static boolean esUsuarioValido(String usuario) throws Exception {

        //Variable de retorno
        boolean resultado=false;
        //Contador de verificaciones hechas
        int vueltas = 0;

        //Bucle ejecutado mientras las verificaciones no sobrepasen las 2
      while (vueltas<=2 && vueltas>=0){

          //Verificación de Código de Estudiante ingresado con los Códigos de estudiante almacenados
          for (int i=0; i<LanzadorApp.estudiantes.length;i++) {
              if (LanzadorApp.estudiantes[i].getCodigoEstudiante().equalsIgnoreCase(usuario)) {
                  resultado = true;
                  break;
              }
          }

          //Se agregó una unidad a la cantidad de verificaciones hechas
          vueltas++;

          //Si no se encontró al estudiante, se generan nuevamente los datos para realizar una segunda verificación
          //En caso contrario, se genera una excepción
          if (!resultado){
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
        //Variable de retorno
        boolean resultado=false;
        //Contador de verificaciones hechas
        int vueltas = 0;

        //Bucle ejecutado mientras las verificaciones no sobrepasen las 2
        while (vueltas<=2 && vueltas>=0){

            //Verificación de Código de Estudiante ingresado con los Códigos de estudiante almacenados
            for (int i=0; i<LanzadorApp.estudiantes.length;i++) {
                if (LanzadorApp.estudiantes[i].getCodigoEstudiante().equalsIgnoreCase(codigoEstudiante)) {
                    if (LanzadorApp.estudiantes[i].getContrasena().equals(contrasena)){
                        resultado = true;
                        break;
                    }
                }
            }

            //Se agregó una unidad a la cantidad de verificaciones hechas

            vueltas++;

            //Si no se encontró al estudiante, se generan nuevamente los datos para realizar una segunda verificación
            //En caso contrario, se genera una excepción
            if (!resultado){
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
