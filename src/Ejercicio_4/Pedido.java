package Ejercicio_4;
import java.util.ArrayList;

public class Pedido {
    private ArrayList<Platillo> platillosPedidos;
    private ArrayList<Integer> cantidades;

    public Pedido() {
        platillosPedidos = new ArrayList<>();
        cantidades = new ArrayList<>();
    }

    public void agregarPlatillo(Platillo platillo, int cantidad) {
        platillosPedidos.add(platillo);
        cantidades.add(cantidad);
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < platillosPedidos.size(); i++) {
            total += platillosPedidos.get(i).getPrecio() * cantidades.get(i);
        }
        return total;
    }

    public String mostrarPedido() {
        if (platillosPedidos.isEmpty()) {
            return "No hay pedidos aún.";
        }
        StringBuilder pedidoTexto = new StringBuilder("Tu pedido actual:\n");
        double total = 0;
        for (int i = 0; i < platillosPedidos.size(); i++) {
            Platillo p = platillosPedidos.get(i);
            int cant = cantidades.get(i);
            double subtotal = p.getPrecio() * cant;
            pedidoTexto.append(cant + " x " + p.getNombre() + " - $" + subtotal + "\n");
            total += subtotal;
        }
        pedidoTexto.append("Total: $" + total);
        return pedidoTexto.toString();
    }

    public boolean estaVacio() {
        return platillosPedidos.isEmpty();
    }

    public void vaciar() {
        platillosPedidos.clear();
        cantidades.clear();
    }
}
