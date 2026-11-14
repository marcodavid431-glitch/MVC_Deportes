/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Excepcion;

/**
 *
 * @author PERSONAL
 */
public class SorteoInvalidoException extends Exception {
    private static final long serialVersionUID = 1L;

    // Constructor que recibe el mensaje descriptivo
    public SorteoInvalidoException(String mensaje) {
        super(mensaje);
    }
    
}
