package Ejercicio_1;

public class CuentaBancaria {
    private float inversion;

    public CuentaBancaria(float inversionInicial) {
        this.inversion = inversionInicial;
    }

    public float getInversion() {
        return inversion;
    }

    public void setInversion(float inversion) {
        this.inversion = inversion;
    }

    public float calcularIntereses() {
        return inversion * 0.10f;
    }

    public float calcularDineroFinal() {
        float intereses = calcularIntereses();
        if (intereses > 70000) {
            inversion += intereses;
        }
        return inversion;
    }
}
