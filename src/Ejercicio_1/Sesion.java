package Ejercicio_1;

public class Sesion {
    private String usuario;
    private float saldo;

    public Sesion(String usuario) {
        this.usuario = usuario;
        this.saldo = 0.0f; // Saldo inicial
    }

    public String getUsuario() {
        return usuario;
    }

    public float getSaldo() {
        return saldo;
    }

    public void agregarInversion(float monto) {
        this.saldo += monto;
    }

    public void reiniciar() {
        this.saldo = 0.0f;
    }
}
