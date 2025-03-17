package Ejercicio_5;

public class Jugada {
    private String nombre;
    private int valor;

    public Jugada(int valor) {
        this.valor = valor;
        switch (valor) {
            case 1: this.nombre = "Piedra"; break;
            case 2: this.nombre = "Papel"; break;
            case 3: this.nombre = "Tijera"; break;
            default: this.nombre = "Desconocido";
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int ganaA(Jugada otra) {
        if (this.valor == otra.valor) {
            return 0; // Empate
        }
        if ((this.valor == 1 && otra.valor == 3) || // Piedra gana a Tijera
                (this.valor == 2 && otra.valor == 1) || // Papel gana a Piedra
                (this.valor == 3 && otra.valor == 2)) { // Tijera gana a Papel
            return 1; // Ganas
        }
        return -1; // Pierdes
    }
}
