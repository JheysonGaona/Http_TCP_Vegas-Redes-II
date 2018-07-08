package clases;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * @author Jheyson Gaona
 */
public class ServerHttp {

    private final int puerto = 8080;

    public ServerHttp() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(puerto), 0);
        server.createContext("/index", new RootHandler());
        server.setExecutor(null);
        server.start();
    }

    static class RootHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) {
//            try {
            String html = "";
            try {
                // Apertura del fichero y creacion de BufferedReader para poder
                // hacer una lectura comoda (disponer del metodo readLine()).
                File archivo = new File("/home/jheyson/NetBeansProjects/Http_Tcp_Vegas_Redes_II/src/html/index.txt");
                FileReader objFileReader = new FileReader(archivo);
                BufferedReader objBufferedReader = new BufferedReader(objFileReader);

                // Lectura del fichero
                String linea;
                while ((linea = objBufferedReader.readLine()) != null) {
                    html = html + linea + "\n";
                }
            } catch (IOException ioex) {
                System.err.println("IOException error: " + ioex.getMessage());
            }
            // grabamos toda la lectura del txt que contiene el html
            try {
                byte[] bs = html.getBytes("UTF-8");
                he.sendResponseHeaders(200, bs.length);
                OutputStream os = he.getResponseBody();
                os.write(bs);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
