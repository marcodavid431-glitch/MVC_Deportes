package Vista;

import Controlador.TorneoControl;
import Controlador.Excepcion.NumeroEquiposInvalidoException;
import Controlador.Excepcion.SorteoInvalidoException;
import Modelo.Equipo;
import Modelo.Partido;
import Modelo.Torneo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsolaVista {
    private TorneoControl controlador;
    private Scanner scanner;

    public ConsolaVista() {
        this.controlador = new TorneoControl();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = 0;
        do {
            System.out.println("\n===== TORNEO DE FUTBOL - ELIMINACION DIRECTA =====");
            System.out.println("1. Inicializar Torneo (16 equipos)");
            System.out.println("2. Sortear Octavos de Final");
            System.out.println("3. Guardar Torneo (Serializacion)");
            System.out.println("4. Cargar Torneo (Deserializacion)");
            System.out.println("5. Imprimir datos ");
            System.out.println("6. Salir");
            System.out.print("Elija una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        inicializarEquiposMenu();
                        break;
                    case 2:
                        ejecutarSorteoMenu("Octavos de Final");
                        break;
                    case 3:
                        guardarTorneoMenu();
                        break;
                    case 4:
                        cargarTorneoMenu();
                        break;
                    case 5:
                        verContenidoBinarioMenu();
                        break;
                    case 6:
                        System.out.println("Saliendo de la aplicacio. ¡Exitos con el proyecto!");
                        break;
                    default:
                        System.out.println("Opcion no valida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Por favor, ingrese un numero válido.");
            } catch (Exception e) {
                System.out.println("[Error inesperado]: " + e.getMessage());
            }

        } while (opcion != 6);
    }

    private void inicializarEquiposMenu() {
        List<Equipo> listaEquipos = new ArrayList<>();
        // Creamos automáticamente 16 equipos 
        for (int i = 1; i <= 16; i++) {
            listaEquipos.add(new Equipo(i, "Equipo " + i));
        }

        try {
            controlador.inicializarTorneo("Liga Profesional UPS", listaEquipos);
            System.out.println("Torneo inicializado con exito con 16 equipos");
        } catch (NumeroEquiposInvalidoException e) {
            System.out.println("[Excepcion Controlada] " + e.getMessage());
        }
    }

    private void ejecutarSorteoMenu(String etapa) {
        try {
            List<Partido> partidos = controlador.sortearEtapaTorneo(etapa);
            System.out.println("\n--- SORTEO EXITOSO: " + etapa + " ---");
            for (Partido p : partidos) {
                System.out.println(p);
            }
        } catch (SorteoInvalidoException e) {
            System.out.println("Excepcion de Sorteo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void guardarTorneoMenu() {
        try {
            controlador.guardarTorneo("torneo.ser");
            System.out.println("Torneo guardado correctamente en 'torneo.ser'!");
        } catch (IOException e) {
            System.out.println("[Error de E/S al guardar]: " + e.getMessage());
        }
    }

    private void cargarTorneoMenu() {
        try {
            controlador.cargarTorneo("torneo.ser");
            System.out.println("Torneo cargado correctamente desde el archivo binario");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar archivo: " + e.getMessage());
        }
    }
    private void verContenidoBinarioMenu() {
        try {
            // Utiliza el método estático que ya tienes en Torneo para leer el archivo
            Torneo torneoCargado = Torneo.cargarTorneo("torneo.ser");
            
            System.out.println("       CONTENIDO DEL ARCHIVO BINARIO (torneo.ser)");
            System.out.println("Nombre del Torneo: " + torneoCargado.getNombreTorneo());
            
            System.out.println("\n--- Equipos Registrados ---");
            if (torneoCargado.getEquipos() != null && !torneoCargado.getEquipos().isEmpty()) {
                for (Equipo eq : torneoCargado.getEquipos()) {
                    System.out.println(" - " + eq);
                }
            } else {
                System.out.println(" (No hay equipos registrados)");
            }

            System.out.println("\n--- Partidos Sorteados (Octavos de Final) ---");
            if (torneoCargado.getPartidosOctavos() != null && !torneoCargado.getPartidosOctavos().isEmpty()) {
                for (Partido par : torneoCargado.getPartidosOctavos()) {
                    System.out.println(" * " + par);
                }
            } else {
                System.out.println(" (Aun no se han sorteado partidos)");
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" No se pudo leer el archivo binario." + e.getMessage());
        }}
}