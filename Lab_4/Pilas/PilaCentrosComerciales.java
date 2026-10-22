package Pilas;

import java.util.ArrayList;
import java.util.List;

/** Pila manual de nombres de centros comerciales, independiente de Swing. */
public class PilaCentrosComerciales {

    private static final int MAX = 5;
    private final String[] pilaCentros = new String[MAX];
    private int tope = -1;

    public boolean pilaLlena() {
        return tope == MAX - 1;
    }

    public boolean pilaVacia() {
        return tope == -1;
    }

    /** Inserta un nombre en la cima y devuelve el valor almacenado. */
    public String push(String dato) {
        if (dato == null || dato.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingrese el nombre de un centro comercial.");
        }
        if (pilaLlena()) {
            throw new IllegalStateException("Desbordamiento - Pila llena.");
        }

        String nombre = dato.trim();
        tope = tope + 1;
        pilaCentros[tope] = nombre;
        return nombre;
    }

    /** Elimina y devuelve el nombre situado en la cima. */
    public String pop() {
        if (pilaVacia()) {
            throw new IllegalStateException("Subdesbordamiento - Pila vacía.");
        }

        String dato = pilaCentros[tope];
        pilaCentros[tope] = null;
        tope = tope - 1;
        return dato;
    }

    /** Devuelve la cima sin eliminarla. */
    public String obtenerCima() {
        if (pilaVacia()) {
            throw new IllegalStateException("La pila está vacía.");
        }
        return pilaCentros[tope];
    }

    public int obtenerCantidad() {
        return tope + 1;
    }

    public int obtenerCapacidad() {
        return MAX;
    }

    /** Devuelve una copia de los elementos, ordenados desde la cima hasta la base. */
    public List<String> obtenerElementos() {
        List<String> elementos = new ArrayList<>(obtenerCantidad());
        for (int i = tope; i >= 0; i--) {
            elementos.add(pilaCentros[i]);
        }
        return List.copyOf(elementos);
    }
}
