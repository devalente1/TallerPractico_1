package Ejercicio_4;
import javax.swing.JOptionPane;

public class Restaurante {
    private static Menu menu = new Menu();
    private static Pedido pedido = new Pedido();

    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        boolean ejecutando = true;

        while (ejecutando) {
            String opcion = JOptionPane.showInputDialog(null,
                    "Bienvenido al Restaurante\n" +
                            "Menú de opciones:\n" +
                            "1. Ver el menú\n" +
                            "2. Hacer un pedido\n" +
                            "3. Ver tu pedido actual\n" +
                            "4. Pagar y salir\n" +
                            "5. Salir\n" +
                            "Ingrese el número de la opción:",
                    "Restaurante", JOptionPane.PLAIN_MESSAGE);

            if (opcion == null) {
                JOptionPane.showMessageDialog(null, "Gracias por visitar el restaurante.");
                ejecutando = false;
                continue;
            }

            try {
                int eleccion = Integer.parseInt(opcion);
                switch (eleccion) {
                    case 1:
                        JOptionPane.showMessageDialog(null, menu.mostrarMenu(),
                                "Menú", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 2:
                        hacerPedido();
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, pedido.mostrarPedido(),
                                "Pedido Actual", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 4:
                        pagarYSalir();
                        ejecutando = false;
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "Gracias por visitar el restaurante.");
                        ejecutando = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida. Elija 1-5.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void hacerPedido() {
        while (true) {
            String indiceInput = JOptionPane.showInputDialog(null,
                    menu.mostrarMenu() + "\nIngrese el número del platillo a pedir (1-" + menu.getTamanio() + "):",
                    "Hacer Pedido", JOptionPane.PLAIN_MESSAGE);
            if (indiceInput == null) return;

            try {
                int indice = Integer.parseInt(indiceInput) - 1;
                Platillo platillo = menu.getPlatillo(indice);
                if (platillo == null) {
                    JOptionPane.showMessageDialog(null, "Platillo no válido.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                String cantidadInput = JOptionPane.showInputDialog(null,
                        "Ingrese la cantidad de " + platillo.getNombre() + ":",
                        "Hacer Pedido", JOptionPane.PLAIN_MESSAGE);
                if (cantidadInput == null) return;

                int cantidad = Integer.parseInt(cantidadInput);
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                pedido.agregarPlatillo(platillo, cantidad);
                int mas = JOptionPane.showConfirmDialog(null,
                        "¿Desea pedir algo más?", "Continuar", JOptionPane.YES_NO_OPTION);
                if (mas == JOptionPane.NO_OPTION) {
                    break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void pagarYSalir() {
        if (pedido.estaVacio()) {
            JOptionPane.showMessageDialog(null, "No hay nada que pagar. ¡Hasta pronto!",
                    "Pagar", JOptionPane.INFORMATION_MESSAGE);
            mostrarMenuPrincipal();
        } else {
            JOptionPane.showMessageDialog(null,
                    pedido.mostrarPedido() + "\n\nPago exitoso. ¡Gracias por su visita!",
                    "Pagar", JOptionPane.INFORMATION_MESSAGE);
            pedido.vaciar();
            mostrarMenuPrincipal();
        }
    }
}
