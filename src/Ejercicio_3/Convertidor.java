package Ejercicio_3;
import javax.swing.JOptionPane;

public class Convertidor {
    public static void main(String[] args) {
        try {
            String input = JOptionPane.showInputDialog(null,
                    "Ingrese un número entero entre 1 y 10:",
                    "Convertidor de Números", JOptionPane.PLAIN_MESSAGE);

            if (input == null) {
                JOptionPane.showMessageDialog(null, "Programa terminado.");
                return;
            }

            int numero = Integer.parseInt(input);

            NumeroLiteral numeroObj = new NumeroLiteral(numero);

            String resultado = numeroObj.convertirALetras();
            JOptionPane.showMessageDialog(null, resultado,
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: Debe ingresar un número entero válido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
