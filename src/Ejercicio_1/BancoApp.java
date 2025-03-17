package Ejercicio_1;
import javax.swing.JOptionPane;

public class BancoApp {
    private static Sesion sesionActual;

    public static void main(String[] args) {
        while (true) {
            String usuario = JOptionPane.showInputDialog(null,
                    "Ingrese su nombre de usuario (o 'salir' para cerrar):",
                    "Login", JOptionPane.PLAIN_MESSAGE);

            if (usuario == null || usuario.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Usuario no válido. Intente de nuevo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            if (usuario.equalsIgnoreCase("salir")) {
                JOptionPane.showMessageDialog(null, "Programa terminado.");
                return;
            }

            sesionActual = new Sesion(usuario);
            mostrarMenuPrincipal();
            break;
        }
    }

    private static void mostrarMenuPrincipal() {
        boolean enSesion = true;

        while (enSesion) {
            String opcion = JOptionPane.showInputDialog(null,
                    "Bienvenido, " + sesionActual.getUsuario() + "\n" +
                            "Menú de opciones:\n" +
                            "1. Simular inversión\n" +
                            "2. Invertir\n" +
                            "3. Mi saldo actual\n" +
                            "4. Salir\n" +
                            "Ingrese el número de la opción:",
                    "Menú Principal", JOptionPane.PLAIN_MESSAGE);

            if (opcion == null) {
                JOptionPane.showMessageDialog(null, "Volviendo al login...");
                enSesion = false;
                continue;
            }

            try {
                int eleccion = Integer.parseInt(opcion);
                switch (eleccion) {
                    case 1:
                        simularInversion();
                        break;
                    case 2:
                        realizarInversion();
                        break;
                    case 3:
                        mostrarSaldo();
                        break;
                    case 4:
                        sesionActual.reiniciar();
                        JOptionPane.showMessageDialog(null, "Sesión cerrada. Volviendo al login...");
                        enSesion = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida. Elija 1, 2, 3 o 4.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void simularInversion() {
        try {
            String input = JOptionPane.showInputDialog(null,
                    "Ingrese el valor de la inversión a simular:",
                    "Simular Inversión", JOptionPane.PLAIN_MESSAGE);
            if (input == null) return;

            float valor = Float.parseFloat(input);
            if (valor <= 0) {
                throw new NumberFormatException("El valor debe ser mayor a 0");
            }

            CuentaBancaria simulacion = new CuentaBancaria(valor);
            float intereses = simulacion.calcularIntereses();
            float resultado = simulacion.calcularDineroFinal();

            JOptionPane.showMessageDialog(null,
                    "Simulación:\n" +
                            "Inversión inicial: $" + valor + "\n" +
                            "Intereses (10%): $" + intereses + "\n" +
                            "Dinero final: $" + resultado,
                    "Resultado de la Simulación", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: Ingrese un número válido mayor a 0.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void realizarInversion() {
        try {
            String input = JOptionPane.showInputDialog(null,
                    "Ingrese el valor a invertir:",
                    "Invertir", JOptionPane.PLAIN_MESSAGE);
            if (input == null) return;

            float valor = Float.parseFloat(input);
            if (valor <= 0) {
                throw new NumberFormatException("El valor debe ser mayor a 0");
            }

            sesionActual.agregarInversion(valor);
            JOptionPane.showMessageDialog(null,
                    "Inversión de $" + valor + " realizada con éxito.",
                    "Inversión Confirmada", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: Ingrese un número válido mayor a 0.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void mostrarSaldo() {
        JOptionPane.showMessageDialog(null,
                "Usuario: " + sesionActual.getUsuario() + "\n" +
                        "Saldo actual: $" + sesionActual.getSaldo(),
                "Mi Saldo Actual", JOptionPane.INFORMATION_MESSAGE);
    }
}