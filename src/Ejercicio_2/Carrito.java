package Ejercicio_2;
import java.util.ArrayList;

public class Carrito {

    private ArrayList<Producto> productos;

    public Carrito() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularSubtotal();
        }
        return total;
    }

    /*public double calcularTotalConDescuento() {
        double total = calcularTotal();
        if (total > 100000) {
            return total * 0.8;
        }
        return total;
    }*/

    public String mostrarContenido() {
        if (productos.isEmpty()) {
            return "El carrito está vacío.";
        }
        StringBuilder contenido = new StringBuilder("Productos en el carrito:\n");
        double totalSinDescuento = 0;
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            double subtotal = p.calcularSubtotal();
            contenido.append(i + 1).append(". ")
                    .append(p.getNombre()).append(" - Cantidad: ")
                    .append(p.getCantidad()).append(" - Subtotal: $")
                    .append(subtotal).append("\n");
            totalSinDescuento += subtotal;
        }
        contenido.append("\nTotal sin descuento: $").append(totalSinDescuento);
        if (totalSinDescuento > 100000) {
            double totalConDescuento = totalSinDescuento * 0.8;
            contenido.append("\nTotal con 20% descuento: $").append(totalConDescuento);
        }
        return contenido.toString();
    }

    public void vaciarCarrito() {
        productos.clear();
    }

    public boolean estaVacio() {
        return productos.isEmpty();
    }
}
