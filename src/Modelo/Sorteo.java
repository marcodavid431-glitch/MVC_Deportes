package Modelo;

import Controlador.Excepcion.SorteoInvalidoException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sorteo implements Serializable {
    private static final long serialVersionUID = 1L;

    public List<Partido> sortearEtapa(List<Equipo> equiposOriginales, String etapa) throws SorteoInvalidoException {
        // Creamos una copia de la lista para trabajar de forma segura en la recursividad
        List<Equipo> listaTrabajo = new ArrayList<>(equiposOriginales);
        List<Partido> partidosGenerados = new ArrayList<>();

        // Llamamos al método recursivo privado
        ejecutarSorteoRecursivo(listaTrabajo, etapa, partidosGenerados);

        return partidosGenerados;
    }

    private void ejecutarSorteoRecursivo(List<Equipo> listaDisponibles, String etapa, List<Partido> acumulador) throws SorteoInvalidoException {
        //  Si la lista está vacía, la recursividad ha terminado con éxito.
        if (listaDisponibles.isEmpty()) {
            return;
        }

        // (Lanza SorteoInvalidoException si hay fallo lógico)
        if (listaDisponibles.size() % 2 != 0) {
            throw new SorteoInvalidoException("Error crítico en el sorteo de " + etapa + ": Quedó un número impar de equipos (" + listaDisponibles.size() + ").");
        }

        // seleccion aliatoria   
        Equipo equipo1 = listaDisponibles.get(0);
        
        // Seleccionamos un oponente aleatorio 
        int indiceAleatorio = 1 + (int) (Math.random() * (listaDisponibles.size() - 1));
        Equipo equipo2 = listaDisponibles.get(indiceAleatorio);

        // Creamos el partido cumpliendo 
        acumulador.add(new Partido(etapa, equipo1, equipo2));

        // elimanomos equipos emparejados 
        if (indiceAleatorio > 0) {
            listaDisponibles.remove(indiceAleatorio);
        }
        listaDisponibles.remove(0);

        // 5. LLAMADA RECURSIVA para el siguiente par de equipos
        ejecutarSorteoRecursivo(listaDisponibles, etapa, acumulador);
    }
}