package pe.edu.utp.utils;

import pe.edu.utp.utils.TextUTP;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLog {

    public enum Level {INFO , WARN, ERROR};

    private String filename;
    public  String getFilename(){
        return filename;
    }

    public ErrorLog(String filename ) throws Exception {
        File f = new File(filename);
        //Es un directorio ??

        if (f.isDirectory()){
            throw new Exception("El archivo log no puede ser un directorio");
        }
        this.filename = filename;
    }

  public  String  log(String msg, Level level) throws IOException {
      // FORMATO: FECHA HORA - LEVEL - MENSAJE
      LocalDateTime ldt = LocalDateTime.now();
      DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
      String  tiempo = ldt.format(fmt);

      //Aplicando el formato para el evento
      //                        Tiempo - level - mensaje
      String evento_fmt = "%s - %s - %s\r\n";
      String  evento = String.format(evento_fmt,tiempo,level,msg);

      //Guardamos el log
      TextUTP.append(evento,filename);

      return evento;
  }
}
