package pe.edu.utp;

import pe.edu.utp.Business.PortalNotas;
import pe.edu.utp.Servlets.LoginServlet;
import pe.edu.utp.utils.JettyUTP;

public class LanzadorApp {

    public static PortalNotas portal = new PortalNotas();

    public static void main(String[] args) throws Exception {

        String path = "src/main/resources/web";
        JettyUTP webserver = new JettyUTP(8080,path);
        webserver.addServlet(LoginServlet.class, "/portalNotas");
        webserver.start();

    }
}