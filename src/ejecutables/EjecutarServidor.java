package ejecutables;

import clases.Servidor;

/**
 * @author Jheyson Gaona
 */
public class EjecutarServidor {

    public static void main(String[] args) throws Exception {
        
        // El arranque de la aplicacion empieza desde una clase main
        // Pues nuestro frame FrmServidor solo nos sirve para visualizar
        // mediante variables estatic
        Servidor.main();        
    }
}
