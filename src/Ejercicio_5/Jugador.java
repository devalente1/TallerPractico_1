package Ejercicio_5;
import javax.swing.JOptionPane;
import java.util.Random;

public class Jugador {
    private String nombre;
    private Jugada jugada;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public void elegirJugadaUsuario() {
        try {
            String input = JOptionPane.showInputDialog(null,
                    "Elige tu jugada:\n1. Piedra\n2. Papel\n3. Tijera",
                    nombre + " - Selecciona", JOptionPane.PLAIN_MESSAGE);
            if (input == null) return;

            int eleccion = Integer.parseInt(input);
            if (eleccion >= 1 && eleccion <= 3) {
                this.jugada = new Jugada(eleccion);
            } else {
                throw new NumberFormatException("Número fuera de rango.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: Ingresa un número válido entre 1 y 3.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            elegirJugadaUsuario();
        }
    }

    public void elegirJugadaComputadora() {
        Random random = new Random();
        int eleccion = random.nextInt(3) + 1; // 1, 2 o 3
        this.jugada = new Jugada(eleccion);
    }

    public Jugada getJugada() {
        return jugada;
    }

    public String getNombre() {
        return nombre;
    }
}
