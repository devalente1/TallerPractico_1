package Ejercicio_4;

public class Platillo {
    private String nombre;
    private double precio;

    public Platillo(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String toString() {
        return nombre + " - $" + precio;
    }
}
