
package Controlador.Excepcion;

public class NumeroEquiposInvalidoException extends Exception  {
    private static final long serialVersionUID = 1L;

    // Constructor que recibe el mensaje descriptivo
    public NumeroEquiposInvalidoException(String mensaje) {
        super(mensaje);
    }
}
