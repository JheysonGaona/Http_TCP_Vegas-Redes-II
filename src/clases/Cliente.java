package clases;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

/**
 * @author Jheyson Gaona
 */
public class Cliente {

    static String solicitudUrl;
    static String ip;
    static int puerto;

    public Cliente(String solicitudUrl, String ip, int puerto) {
        Cliente.solicitudUrl = solicitudUrl;
        Cliente.ip = ip;
        Cliente.puerto = puerto;
    }

    public static void main() throws SocketException, IOException {
        
        // creando el datagrama socketClient
        DatagramSocket socketClient = new DatagramSocket();
        // variantes TCP VEGAS
        int cwnd;
        int lim_cong = 14;
        int y = 8;
        int alpha = 10;
        int beta = 11;
        int con = 0;
        String subString;
        boolean segmentosEnviados = false;

        try {
            InetAddress receiverHost = InetAddress.getByName(ip);
            // algoritmo TCP Vegas
            for (cwnd = 0; con < solicitudUrl.length();) {
                if (con + cwnd > solicitudUrl.length()) {
                    subString = solicitudUrl.substring(con, solicitudUrl.length());
                    con = solicitudUrl.length();
                    // ALPHA
                } else if (cwnd >= y && cwnd < alpha) {
                    subString = solicitudUrl.substring(con, con + cwnd + 1);
                    cwnd += 1;
                    con += cwnd;
                    beta = ThreadLocalRandom.current().nextInt(beta, lim_cong);
                    alpha = ThreadLocalRandom.current().nextInt(9, (beta - 1));
                    // BETA
                } else if (cwnd >= alpha && cwnd < beta) {
                    subString = solicitudUrl.substring(con, (con + cwnd));
                    con += cwnd;
                    beta = ThreadLocalRandom.current().nextInt((beta - 1), lim_cong);
                    alpha = ThreadLocalRandom.current().nextInt(9, (beta - 1));
                     // LIMITE
                } else if (cwnd >= beta && cwnd < lim_cong) {
                    subString = solicitudUrl.substring(con, (con + (cwnd - 1)));
                    cwnd -= 1;
                    con += cwnd;
                    beta = ThreadLocalRandom.current().nextInt(beta, lim_cong);
                    alpha = ThreadLocalRandom.current().nextInt(9, (beta - 1));
                    // COMIENZO LENTO
                } else {
                    if (cwnd == 0) {
                        subString = solicitudUrl.substring(con, 1);
                        cwnd += 1;
                        con += cwnd;
                    } else {
                        subString = solicitudUrl.substring(con, con + (cwnd * 2));
                        cwnd = cwnd * 2;
                        con += cwnd;
                    }
                }
                Thread.sleep(400);
                byte[] buffer = subString.getBytes();
                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, receiverHost, puerto);
                socketClient.send(datagram);
                segmentosEnviados = true;
            }
        } catch (NumberFormatException | InterruptedException | IOException ex) {
            System.err.println(ex.getMessage());
        }

        // Si la Solicitud
        if (segmentosEnviados) {
            String mensajeFragmentado = "";

            while (true) {
                byte[] buffer = new byte[1000];
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
                socketClient.receive(respuesta);

                // Une los datagramas.
                ByteArrayInputStream bin = new ByteArrayInputStream(respuesta.getData());
                for (int i = 0; i < respuesta.getLength(); i++) {
                    int dato = bin.read();
                    mensajeFragmentado = mensajeFragmentado + (char) dato;
                }
                // imprimir el mensaje fragmentado para saber en que cantidad quemarlo
                System.out.println("URL a quemar de longuitud: " + mensajeFragmentado.length());
                if (mensajeFragmentado.length() == 2728) {
                    String url = solicitudUrl;
                    // Reconocer sistema operativo para abrir un navegador
                    String osName = System.getProperty("os.name");
                    try {
                        // Si el sistema es Windows
                        if (osName.startsWith("Windows")) {
                            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
                            // Si el sistema es Linux
                        } else if (osName.startsWith("Linux")) {
                            Runtime.getRuntime().exec("x-www-browser " + url);
                        } // Si el sistema es Mac OS
                        else if (osName.startsWith("Mac OS")) {
                            Runtime.getRuntime().exec("open " + url);
                            // Cualquier otro sistema operativo
                        } else {
                            JOptionPane.showInputDialog("Copie la URL y abrala en su navegador", url);
                        }
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error no se puede abrir el navegador"
                                + "Error" + e.getLocalizedMessage());
                    }
                    // Una vez finalizado cieera el frame de cliente y abre el navegador con la solicitud
                    System.exit(0);
                }
            }
        }
    }
}
