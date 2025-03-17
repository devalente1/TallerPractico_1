package Ejercicio_3;

public class NumeroLiteral {
    private int numero;

    public NumeroLiteral(int numero) {
        this.numero = numero;
    }

    public boolean esValido() {
        return numero >= 1 && numero <= 10;
    }

    public String convertirALetras() {
        if (!esValido()) {
            return "El número no está comprendido entre 1 y 10";
        }

        switch (numero) {
            case 1: return "Uno";
            case 2: return "Dos";
            case 3: return "Tres";
            case 4: return "Cuatro";
            case 5: return "Cinco";
            case 6: return "Seis";
            case 7: return "Siete";
            case 8: return "Ocho";
            case 9: return "Nueve";
            case 10: return "Diez";
            default: return "Error inesperado";
        }
    }
}
