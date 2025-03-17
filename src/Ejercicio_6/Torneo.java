package Ejercicio_6;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Torneo {
    private ArrayList<Equipo> equipos;
    private ArrayList<Partido> semifinales;
    private Partido finalPartido;

    public Torneo() {
        equipos = new ArrayList<>();
        equipos.add(new Equipo("Real Madrid"));
        equipos.add(new Equipo("Barcelona"));
        equipos.add(new Equipo("Juventus"));
        equipos.add(new Equipo("PSG"));

        semifinales = new ArrayList<>();
    }

    public void simularTorneo() {
        semifinales.add(new Partido(equipos.get(0), equipos.get(1))); // Real Madrid vs Barcelona
        semifinales.add(new Partido(equipos.get(2), equipos.get(3))); // Juventus vs PSG

        // Simula semifinales
        StringBuilder resultadosSemifinales = new StringBuilder("Resultados de las semifinales:\n");
        ArrayList<Equipo> finalistas = new ArrayList<>();
        for (Partido partido : semifinales) {
            partido.simularPartido();
            resultadosSemifinales.append(partido.getResultado()).append("\n");
            finalistas.add(partido.getGanador());
        }
        JOptionPane.showMessageDialog(null, resultadosSemifinales.toString(),
                "Semifinales", JOptionPane.INFORMATION_MESSAGE);

        // Crear y simula una final
        finalPartido = new Partido(finalistas.get(0), finalistas.get(1));
        finalPartido.simularPartido();
        Equipo campeon = finalPartido.getGanador();

        String resultadoFinal = "Final:\n" + finalPartido.getResultado() + "\n\n" +
                "¡El campeón es: " + campeon.getNombre() + "!";
        JOptionPane.showMessageDialog(null, resultadoFinal,
                "Final y Campeón", JOptionPane.INFORMATION_MESSAGE);
    }
}