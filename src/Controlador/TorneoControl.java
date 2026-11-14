package Controlador;

import Controlador.Excepcion.NumeroEquiposInvalidoException;
import Controlador.Excepcion.SorteoInvalidoException;
import Modelo.Equipo;
import Modelo.Partido;
import Modelo.Sorteo;
import Modelo.Torneo;
import java.io.*;
import java.util.List;

public class TorneoControl implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Torneo torneoActual;
    public void inicializarTorneo(String nombreTorneo, List<Equipo> equipos) throws NumeroEquiposInvalidoException {
        int n = equipos.size();
        //Validación de potencia de 2 (ej: 16, 8, 4, 2)
        if (n < 2 || (n & (n - 1)) != 0) {
            throw new NumeroEquiposInvalidoException("El número de equipos (" + n + ") no es}"
                    + " valido para una eliminación directa. Debe ser una potencia de 2 (16, 8, 4, 2).");
        }
        
        this.torneoActual = new Torneo(nombreTorneo, equipos);
    }

    public List<Partido> sortearEtapaTorneo(String etapa) throws SorteoInvalidoException, NumeroEquiposInvalidoException {
    if (torneoActual == null) {
        throw new SorteoInvalidoException("No hay un torneo activo para sortear.");
    }
    
    // Ejecuta el sorteo recursivo y aleatorio que ya programaste en la clase Torneo
    torneoActual.iniciarSorteoAleatorio();
    
    return torneoActual.getPartidosOctavos();
}

    public void guardarTorneo(String rutaArchivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(this.torneoActual);
        }
    }

    public void cargarTorneo(String rutaArchivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            this.torneoActual = (Torneo) ois.readObject();
        }
    }

    public Torneo getTorneoActual() {
        return torneoActual;
    }
}
