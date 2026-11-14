
package Modelo;

import java.io.Serializable;

public class Partido implements Serializable  {
    private static final long serialVersionUID = 1L;
    private String etapa ;
    private Equipo equipo1;
    private Equipo equipo2;
    private Equipo ganador; 

    public Partido(String etapa, Equipo equipo1, Equipo equipo2) {
        this.etapa = etapa;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    public void DefinirGanador(Equipo ganador) {
        this.ganador = ganador;
    }

    public String getEtapa() {
        return etapa;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public Equipo getGanador() {
        return ganador;
    }

    @Override
    public String toString() {
        return "Partido{" + "etapa=" + etapa + ", equipo1=" + equipo1 + ", equipo2=" + equipo2 + ", ganador=" + ganador + '}';
    }
    
           
            
}
