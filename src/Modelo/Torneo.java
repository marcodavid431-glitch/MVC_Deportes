
package Modelo;
import Controlador.Excepcion.NumeroEquiposInvalidoException;
import Controlador.Excepcion.SorteoInvalidoException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Torneo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombreTorneo; 
    
    private List<Equipo> equipos;
    private  List<Partido> partidosOctavos ;
    private  List<Partido> partidosCuaertos ;
    private  List<Partido> partidosSemifinales; 
    private Partido partidoFinal; 

    public Torneo(String nombreTorneo, List<Equipo> equipos) {
        this.nombreTorneo = nombreTorneo;
        this.equipos = equipos != null ? equipos : new ArrayList<>();
       // this.equipos = new ArrayList <>();
        this.partidosOctavos = new ArrayList<>();
        this.partidosCuaertos = new ArrayList<>();
        this.partidosSemifinales = new ArrayList<>();
    }
    public void registrarEquipo(Equipo equipo) throws NumeroEquiposInvalidoException {
        if (equipos.size() >= 16) {
            throw new NumeroEquiposInvalidoException("No se pueden registrar mas de 16 equipos para el torneo.");
        }
        equipos.add(equipo);
    }
    public void sortearOctavosRecursivo(List<Equipo> listaDisponibles, int indice) throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        if (equipos.size() != 16) {
            throw new NumeroEquiposInvalidoException("Se necesitan exactamente 16 equipos para sortear los octavos.");
        }

        // Condición base de la recursividad: 
        if (indice >= listaDisponibles.size()) {
            return;
        }

        try {
            if (indice + 1 < listaDisponibles.size()) {
                Equipo eq1 = listaDisponibles.get(indice);
                Equipo eq2 = listaDisponibles.get(indice + 1);
                
                // Creamos el partido y lo guardamos
                Partido partido = new Partido("Octavos de Final", eq1, eq2);
                partidosOctavos.add(partido);
                
                // LLAMADA RECURSIVA avanzando de 2 en 2
                sortearOctavosRecursivo(listaDisponibles, indice + 2);
            }
        } catch (Exception e) {
            throw new SorteoInvalidoException("Error en el sorteo recursivo: " + e.getMessage());
        }
    }

    // Metodo auxiliar para mezclar aleatoriamente antes de la recursividad
    public void iniciarSorteoAleatorio() throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        List<Equipo> copiaMezclada = new ArrayList<>(this.equipos);
        Collections.shuffle(copiaMezclada);
        this.partidosOctavos.clear();
        sortearOctavosRecursivo(copiaMezclada, 0);
    }

    // 3. SERIALIZACION (Guardar en archivo binario .ser)
    public void guardarTorneo(String rutaArchivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(this);
        }
    }

    // 4. DESERIALIZACION (Cargar desde archivo binario .ser)
    public static Torneo cargarTorneo(String rutaArchivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (Torneo) ois.readObject();
        }
    }
    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public List<Partido> getPartidosOctavos() {
        return partidosOctavos;
    }

    public void setPartidosOctavos(List<Partido> partidosOctavos) {
        this.partidosOctavos = partidosOctavos;
    }

    public List<Partido> getPartidosCuaertos() {
        return partidosCuaertos;
    }

    public void setPartidosCuaertos(List<Partido> partidosCuaertos) {
        this.partidosCuaertos = partidosCuaertos;
    }

    public List<Partido> getPartidosSemifinales() {
        return partidosSemifinales;
    }

    public void setPartidosSemifinales(List<Partido> partidosSemifinales) {
        this.partidosSemifinales = partidosSemifinales;
    }

    public Partido getPartidoFinal() {
        return partidoFinal;
    }

    public void setPartidoFinal(Partido partidoFinal) {
        this.partidoFinal = partidoFinal;
    }
    
}
