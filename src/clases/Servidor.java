package clases;

import gui.FrmServidor;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jheyson Gaona
 */
public class Servidor {

    static String mensajeCliente = "";
    static String mensajeFragmentado = "";
    static public int puerto = 4444;

    public static void main() throws IOException, Exception {
        // levantamos servicios http
        ServerHttp objServerHttp = new ServerHttp();
        Grafica objGrafica = new Grafica();
        // llamamos al frame del servidor
        FrmServidor objFrmServidor = new FrmServidor();
        objFrmServidor.setVisible(true);
        // grafica TCP Vegas
        objGrafica.plotGraph();
        try {
            // verificando IP del servidor
            String ipServer = InetAddress.getLocalHost().getHostAddress();
            // Levantamos el servidor en un puerto especifico
            DatagramSocket socket = new DatagramSocket(puerto);
            // Detallamos en textarea los detalles del servidor y estado
            FrmServidor.txthTerminal.append("============ Servidor TCP iniciado ============\n\n");
            FrmServidor.txthTerminal.append("Direccion IP del servidor: " + ipServer + "\n");
            FrmServidor.txthTerminal.append("Puerto de enlace del servidor: " + puerto + "\n\n");
            // El servidor siempre estara escuchando las peticiones del cliente
            while (true) {
                byte[] buffer = new byte[1000];
                DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length);
                socket.receive(datagrama);
                FrmServidor.txthTerminal.append(">> Petición de : " + datagrama.getAddress()
                        + " - desde el puerto: " + datagrama.getPort() + "\n");
                mensajeCliente = new String(buffer);

                //Une los datagramas y envia a buscar la url.
                ByteArrayInputStream input = new ByteArrayInputStream(datagrama.getData());
                for (int i = 0; i < datagrama.getLength(); i++) {
                    int dato = input.read();
                    mensajeFragmentado = mensajeFragmentado + (char) dato;
                }
//                System.out.println(mensajeFragmentado.length());
                // La solicitud del cliente debe ser: url = http://127.0.0.1:8080/index
                // en donde 27 equivale a la loguitud de la url
                if (mensajeFragmentado.length() == 27) {
                    FrmServidor.txthTerminal.append("\n" + ">> Url solicitada: " + mensajeFragmentado + "\n\n");
                    FrmServidor.txthTerminal.append("<< Preparando resultado... Iniciando TCP Vegas \n");

                    // Creando metodo de coneccion hacia la URL
                    URL objUrl = new URL(mensajeFragmentado);
                    URLConnection objURLConnection = objUrl.openConnection();
                    BufferedReader objBufferedReader = new BufferedReader(
                            new InputStreamReader(objURLConnection.getInputStream()));
                    String linea;
                    String respuesta = "";
                    while ((linea = objBufferedReader.readLine()) != null) {
                        respuesta += linea;
                    }
                    // variantes TCP Vegas
                    int cwnd;
                    int con = 0;
                    int lim_cong = 16;
                    int beta = 14;
                    int alpha = 12;
                    int y = 8;
                    String subString;
                    int segm = 1;
                    // Inicio de protocolo de algoritmo de TCP Vegas
                    for (cwnd = 0; con < respuesta.length();) {
                        FrmServidor.txthTerminal.append("-------------------------------------------------------------\n");
                        if ((con + cwnd) > respuesta.length()) {
                            subString = respuesta.substring(con, respuesta.length());
                            con = respuesta.length();

                        } else if (cwnd >= y && cwnd < alpha) {
                            FrmServidor.txthTerminal.append("ALPHA\n");
                            subString = respuesta.substring(con, (con + cwnd + 1));
                            cwnd += 1;
                            con += cwnd;
                            beta = ThreadLocalRandom.current().nextInt(beta, lim_cong);
                            alpha = ThreadLocalRandom.current().nextInt(9, (beta - 1));
                            FrmServidor.txthTerminal.append("alpha = " + alpha
                                    + "\tbeta = " + beta + "\n");

                        } else if (cwnd >= alpha && cwnd < beta) {
                            FrmServidor.txthTerminal.append("BETA\n");
                            subString = respuesta.substring(con, (con + cwnd));
                            con += cwnd;
                            beta = ThreadLocalRandom.current().nextInt((beta - 1), lim_cong);
                            alpha = ThreadLocalRandom.current().nextInt(9, (beta - 1));
                            FrmServidor.txthTerminal.append("alpha = " + alpha
                                    + "\tbeta = " + beta + "\n");

                        } else if (cwnd >= beta && cwnd < lim_cong) {
                            FrmServidor.txthTerminal.append("LIMITE\n");
                            subString = respuesta.substring(con, (con + cwnd - 1));
                            cwnd -= 1;
                            con += cwnd;
                            beta = ThreadLocalRandom.current().nextInt(beta, lim_cong);
                            alpha = ThreadLocalRandom.current().nextInt(9, (beta - 1));
                            FrmServidor.txthTerminal.append("alpha = " + alpha
                                    + "\tbeta = " + beta + "\n");

                        } else {
                            FrmServidor.txthTerminal.append("COMIENZO LENTO\n");
                            if (cwnd == 0) {
                                subString = respuesta.substring(con, 1);
                                cwnd += 1;
                                con += cwnd;
                            } else {
                                subString = respuesta.substring(con, (con + (cwnd * 2)));
                                cwnd = cwnd * 2;
                                con += cwnd;
                            }
                        }
                        FrmServidor.txthTerminal.append("segm: " + segm + "\tcwnd: " + cwnd + "\tY: " + y + "\n");
                        objGrafica.graficarTCP(segm, cwnd, y, alpha, beta, lim_cong);
                        segm++;
                        Thread.sleep(100);
                        byte[] buff = subString.getBytes();
                        DatagramPacket response = new DatagramPacket(buff, buff.length, datagrama.getAddress(), datagrama.getPort());
                        socket.send(response);
                    }
                    FrmServidor.txthTerminal.append("\n" + ">> Peticion finalizada\n");
                }
            }
        } catch (IOException | InterruptedException ex) {
            FrmServidor.txthTerminal.append(ex.getMessage() + "\n");
        }
    }
}
