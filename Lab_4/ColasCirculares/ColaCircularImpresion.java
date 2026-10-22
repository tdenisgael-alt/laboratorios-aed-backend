package ColasCirculares;

import java.util.ArrayList;
import java.util.List;

/** Cola circular manual de trabajos de impresión, independiente de Swing. */
public class ColaCircularImpresion {

    private static final int MAX = 10;
    private final String[] colaCircular = new String[MAX];
    private int frente = -1;
    private int fin = -1;

    public boolean colaLlena() {
        return (fin + 1) % MAX == frente;
    }

    public boolean colaVacia() {
        return frente == -1;
    }

    /** Inserta un trabajo al final de la cola y devuelve el nombre almacenado. */
    public String insertar(String trabajo) {
        if (trabajo == null || trabajo.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingrese el nombre del trabajo de impresión.");
        }
        if (colaLlena()) {
            throw new IllegalStateException("La cola circular de impresión está llena.");
        }

        if (colaVacia()) {
            frente = 0;
            fin = 0;
        } else {
            fin = (fin + 1) % MAX;
        }

        String nombre = trabajo.trim();
        colaCircular[fin] = nombre;
        return nombre;
    }

    /** Elimina y devuelve el trabajo situado al frente de la cola. */
    public String eliminar() {
        if (colaVacia()) {
            throw new IllegalStateException(
                    "La cola circular está vacía, no hay trabajos pendientes para imprimir.");
        }

        String impreso = colaCircular[frente];
        colaCircular[frente] = null;

        if (frente == fin) {
            frente = -1;
            fin = -1;
        } else {
            frente = (frente + 1) % MAX;
        }
        return impreso;
    }

    /** Devuelve el próximo trabajo sin eliminarlo. */
    public String obtenerProximoImprimir() {
        if (colaVacia()) {
            throw new IllegalStateException(
                    "La cola circular está vacía, no hay trabajos pendientes para imprimir.");
        }
        return colaCircular[frente];
    }

    public int obtenerCantidadTrabajos() {
        if (colaVacia()) {
            return 0;
        }
        if (fin >= frente) {
            return fin - frente + 1;
        }
        return MAX - frente + fin + 1;
    }

    public int obtenerCapacidad() {
        return MAX;
    }

    public int obtenerFrente() {
        return frente;
    }

    public int obtenerFin() {
        return fin;
    }

    /** Devuelve una copia de los trabajos en su orden lógico FIFO. */
    public List<String> obtenerElementos() {
        List<String> elementos = new ArrayList<>(obtenerCantidadTrabajos());
        if (!colaVacia()) {
            int indice = frente;
            while (true) {
                elementos.add(colaCircular[indice]);
                if (indice == fin) {
                    break;
                }
                indice = (indice + 1) % MAX;
            }
        }
        return List.copyOf(elementos);
    }
}
