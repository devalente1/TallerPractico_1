package Ejercicio_6;
import javax.swing.JOptionPane;

public class MiniTorneoFutbol {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,
                "¡Bienvenido al Mini Torneo de Fútbol (4 equipos)!",
                "Inicio", JOptionPane.INFORMATION_MESSAGE);

        Torneo torneo = new Torneo();
        torneo.simularTorneo();
    }
}