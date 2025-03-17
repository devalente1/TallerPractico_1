package Ejercicio_2;
import javax.swing.JOptionPane;


public class TiendaVirtual {
    private static Carrito carrito = new Carrito();

    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        boolean ejecutando = true;

        while (ejecutando) {
            String opcion = JOptionPane.showInputDialog(null,
                    "Bienvenido a su tienda virtual\n" +
                            "Menú de opciones:\n" +
                            "1. Agregar al carrito\n" +
                            "2. Ver carrito\n" +
                            "3. Eliminar producto\n" +
                            "4. Proceder al pago\n" +
                            "5. Salir\n" +
                            "Ingrese el número de la opción:",
                    "Tienda Virtual", JOptionPane.PLAIN_MESSAGE);

            if (opcion == null) { // Si cancela
                JOptionPane.showMessageDialog(null, "Gracias por visitar la tienda.");
                ejecutando = false;
                continue;
            }

            try {
                int eleccion = Integer.parseInt(opcion);
                switch (eleccion) {
                    case 1:
                        agregarProducto();
                        break;
                    case 2:
                        verCarrito();
                        break;
                    case 3:
                        eliminarProducto();
                        break;
                    case 4:
                        procederAlPago();
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "Gracias por comprar con nosotros.");
                        carrito.vaciarCarrito();
                        ejecutando = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida. Elija 1, 2 o 3.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void agregarProducto() {
        try {
            String nombre = JOptionPane.showInputDialog(null,
                    "Ingrese el nombre del producto:",
                    "Agregar al Carrito", JOptionPane.PLAIN_MESSAGE);
            if (nombre == null) return;
            if (nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }

            String precioInput = JOptionPane.showInputDialog(null,
                    "Ingrese el precio del producto:",
                    "Agregar al Carrito", JOptionPane.PLAIN_MESSAGE);
            if (precioInput == null) return;
            double precio = Double.parseDouble(precioInput);
            if (precio <= 0) {
                throw new NumberFormatException("El precio debe ser mayor a 0.");
            }

            String cantidadInput = JOptionPane.showInputDialog(null,
                    "Ingrese la cantidad del producto:",
                    "Agregar al Carrito", JOptionPane.PLAIN_MESSAGE);
            if (cantidadInput == null) return;
            int cantidad = Integer.parseInt(cantidadInput);
            if (cantidad <= 0) {
                throw new NumberFormatException("La cantidad debe ser mayor a 0.");
            }

            Producto producto = new Producto(nombre, precio, cantidad);
            String descripcion = "Producto: " + nombre + "\n" +
                    "Precio unitario: $" + precio + "\n" +
                    "Cantidad: " + cantidad + "\n" +
                    "Subtotal: $" + producto.calcularSubtotal();

            int confirmacion = JOptionPane.showConfirmDialog(null,
                    descripcion, "Confirmar Producto", JOptionPane.OK_CANCEL_OPTION);
            if (confirmacion == JOptionPane.OK_OPTION) {
                carrito.agregarProducto(producto);
                JOptionPane.showMessageDialog(null, "Producto agregado al carrito.");
            } else if (confirmacion == JOptionPane.CANCEL_OPTION) {

                int cancelar = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro que desea cancelar?", "Confirmar Cancelación",
                        JOptionPane.YES_NO_OPTION);
                if (cancelar == JOptionPane.YES_OPTION) {
                    return;
                } else {
                    confirmarProducto(producto);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: Ingrese un número válido mayor a 0 para precio o cantidad.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void confirmarProducto(Producto producto) {
        String descripcion = "Producto: " + producto.getNombre() + "\n" +
                "Precio unitario: $" + producto.getPrecio() + "\n" +
                "Cantidad: " + producto.getCantidad() + "\n" +
                "Subtotal: $" + producto.calcularSubtotal();

        int confirmacion = JOptionPane.showConfirmDialog(null,
                descripcion, "Confirmar Producto", JOptionPane.OK_CANCEL_OPTION);
        if (confirmacion == JOptionPane.OK_OPTION) {
            carrito.agregarProducto(producto);
            JOptionPane.showMessageDialog(null, "Producto agregado al carrito.");
        } else if (confirmacion == JOptionPane.CANCEL_OPTION) {
            int cancelar = JOptionPane.showConfirmDialog(null,
                    "¿Está seguro que desea cancelar?", "Confirmar Cancelación",
                    JOptionPane.YES_NO_OPTION);
            if (cancelar == JOptionPane.YES_OPTION) {
                return;
            } else {
                confirmarProducto(producto);
            }
        }
    }

    private static void verCarrito() {
        String contenido = carrito.mostrarContenido();
        if (carrito.estaVacio()) {
            JOptionPane.showMessageDialog(null, contenido, "Ver Carrito",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Object[] opciones = {"Aceptar", "Seguir comprando"};
        int eleccion = JOptionPane.showOptionDialog(null, contenido, "Ver Carrito",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                opciones, opciones[0]);

        if (eleccion == 0) {
        } else if (eleccion == 1) {
            agregarProducto();
        }
    }
    private static void eliminarProducto() {
        if (carrito.estaVacio()) {
            JOptionPane.showMessageDialog(null, "El carrito está vacío.",
                    "Eliminar Producto", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String contenido = carrito.mostrarContenido();
        String indiceInput = JOptionPane.showInputDialog(null,
                contenido + "\nIngrese el índice del producto a eliminar (1-" + carrito.getProductos().size() + "):",
                "Eliminar Producto", JOptionPane.PLAIN_MESSAGE);
        if (indiceInput == null) return;

        try {
            int indice = Integer.parseInt(indiceInput) - 1;
            if (indice < 0 || indice >= carrito.getProductos().size()) {
                throw new IndexOutOfBoundsException("Índice inválido.");
            }

            Producto producto = carrito.getProductos().get(indice);
            if (producto.getCantidad() == 1) {
                carrito.getProductos().remove(indice);
                int respuesta = JOptionPane.showConfirmDialog(null,
                        "¿Eliminar otro producto?", "Confirmación",
                        JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    eliminarProducto();
                }
            } else {
                String cantidadInput = JOptionPane.showInputDialog(null,
                        "Producto: " + producto.getNombre() + " - Cantidad actual: " + producto.getCantidad() +
                                "\nIngrese la nueva cantidad (0 para eliminar):",
                        "Modificar Cantidad", JOptionPane.PLAIN_MESSAGE);
                if (cantidadInput == null) return;

                int nuevaCantidad = Integer.parseInt(cantidadInput);
                if (nuevaCantidad < 0) {
                    throw new NumberFormatException("La cantidad no puede ser negativa.");
                }
                if (nuevaCantidad == 0) {
                    carrito.getProductos().remove(indice);
                } else if (nuevaCantidad < producto.getCantidad()) {
                    producto.setCantidad(nuevaCantidad);
                } else {
                    JOptionPane.showMessageDialog(null, "La nueva cantidad debe ser menor a la actual.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Error: Índice fuera de rango.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void procederAlPago() {
        if (carrito.estaVacio()) {
            JOptionPane.showMessageDialog(null, "El carrito está vacío. Agregue productos primero.",
                    "Proceder al Pago", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String contenido = carrito.mostrarContenido();
        Object[] opciones = {"Proceder al pago", "Volver"};
        int eleccion = JOptionPane.showOptionDialog(null, contenido, "Proceder al Pago",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                opciones, opciones[0]);

        if (eleccion == 0) {
            JOptionPane.showMessageDialog(null, "Compra exitosa.",
                    "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            carrito.vaciarCarrito();
        }
    }
}
