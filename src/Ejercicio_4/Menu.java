package Ejercicio_4;
import java.util.ArrayList;

public class Menu {
    private ArrayList<Platillo> platillos;

    public Menu() {
        platillos = new ArrayList<>();
        // Agregar productos explícitos con precios
        platillos.add(new Platillo("Hamburguesa", 15000));
        platillos.add(new Platillo("Hot Dog", 10000));
        platillos.add(new Platillo("Pizza", 20000));
        platillos.add(new Platillo("Gaseosa", 3000));
        platillos.add(new Platillo("Cerveza", 5000));
        platillos.add(new Platillo("Agua", 2000));
    }

    public String mostrarMenu() {
        StringBuilder menuTexto = new StringBuilder("Menú del Restaurante:\n");
        for (int i = 0; i < platillos.size(); i++) {
            menuTexto.append((i + 1) + ". " + platillos.get(i).toString() + "\n");
        }
        return menuTexto.toString();
    }

    public Platillo getPlatillo(int indice) {
        if (indice >= 0 && indice < platillos.size()) {
            return platillos.get(indice);
        }
        return null;
    }

    public int getTamanio() {
        return platillos.size();
    }
}
