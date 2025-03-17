package Ejercicio_5;
import javax.swing.JOptionPane;

public class Juego {
    private Jugador usuario;
    private Jugador computadora;

    public Juego() {
        usuario = new Jugador("Tú");
        computadora = new Jugador("Computadora");
    }

    public void jugar() {
        boolean jugando = true;

        while (jugando) {
            JOptionPane.showMessageDialog(null,
                    "Bienvenido a Piedra, Papel o Tijera!",
                    "Juego", JOptionPane.INFORMATION_MESSAGE);

            usuario.elegirJugadaUsuario();
            if (usuario.getJugada() == null) { // Si cancela
                JOptionPane.showMessageDialog(null, "Juego terminado.");
                return;
            }
            computadora.elegirJugadaComputadora();

            int resultado = usuario.getJugada().ganaA(computadora.getJugada());
            String mensaje = usuario.getNombre() + ": " + usuario.getJugada().getNombre() + "\n" +
                    computadora.getNombre() + ": " + computadora.getJugada().getNombre() + "\n\n";

            if (resultado == 1) {
                mensaje += "Ganaste!";
            } else if (resultado == -1) {
                mensaje += "Perdiste! La computadora gana.";
            } else {
                mensaje += "Empate!";
            }

            JOptionPane.showMessageDialog(null, mensaje,
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);

            int continuar = JOptionPane.showConfirmDialog(null,
                    "Quieres jugar otra vez?", "Continuar", JOptionPane.YES_NO_OPTION);
            if (continuar == JOptionPane.NO_OPTION) {
                jugando = false;
                JOptionPane.showMessageDialog(null, "Gracias por jugar!");
            }
        }
    }
}
