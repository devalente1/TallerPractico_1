package Ejercicio_6;
import java.util.Random;

public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    private int golesEquipo1;
    private int golesEquipo2;

    public Partido(Equipo equipo1, Equipo equipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    public void simularPartido() {
        Random random = new Random();
        // Esto genera goles al azar entre 0 y 5 para cada equipo
        golesEquipo1 = random.nextInt(6); // 0 a 5
        golesEquipo2 = random.nextInt(6); // 0 a 5
    }

    public Equipo getGanador() {
        if (golesEquipo1 > golesEquipo2) {
            return equipo1;
        } else if (golesEquipo2 > golesEquipo1) {
            return equipo2;
        } else {
            // Empate: decide al azar (simulando penales)
            Random random = new Random();
            return random.nextBoolean() ? equipo1 : equipo2;
        }
    }

    public String getResultado() {
        String resultado = equipo1.getNombre() + " " + golesEquipo1 + " - " +
                golesEquipo2 + " " + equipo2.getNombre();
        if (golesEquipo1 == golesEquipo2) {
            resultado += " (" + getGanador().getNombre() + " gana por penales)";
        }
        return resultado;
    }
}
