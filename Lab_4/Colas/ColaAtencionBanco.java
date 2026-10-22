package Colas;

import java.util.ArrayList;
import java.util.List;

/** Cola lineal de atención bancaria, independiente de Swing. */
public class ColaAtencionBanco {

    private static final int MAX = 5;
    private final String[] cola = new String[MAX];
    private int frente = -1;
    private int fin = -1;

    public boolean colaLlena() {
        return fin == MAX - 1;
    }

    public boolean colaVacia() {
        return frente == -1 || frente > fin;
    }

    /** Inserta una persona al final de la cola y devuelve el nombre almacenado. */
    public String insertar(String persona) {
        if (persona == null || persona.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingrese el nombre de la persona.");
        }
        if (colaLlena()) {
            throw new IllegalStateException("Desbordamiento - La cola está llena.");
        }

        if (frente == -1) {
            frente = 0;
        }
        fin = fin + 1;
        String nombre = persona.trim();
        cola[fin] = nombre;
        return nombre;
    }

    /** Elimina y devuelve a la persona situada al frente de la cola. */
    public String eliminar() {
        if (colaVacia()) {
            throw new IllegalStateException(
                    "Subdesbordamiento - La cola está vacía, no hay nadie para atender.");
        }

        String atendido = cola[frente];
        cola[frente] = null;
        frente = frente + 1;

        if (frente > fin) {
            frente = -1;
            fin = -1;
        }
        return atendido;
    }

    /** Devuelve la próxima persona sin eliminarla. */
    public String obtenerProximoAtender() {
        if (colaVacia()) {
            throw new IllegalStateException("La cola está vacía, no hay personas en espera.");
        }
        return cola[frente];
    }

    public int obtenerCantidadElementos() {
        return colaVacia() ? 0 : fin - frente + 1;
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

    /** Devuelve una copia de las personas desde el frente hasta el final. */
    public List<String> obtenerElementos() {
        List<String> elementos = new ArrayList<>(obtenerCantidadElementos());
        if (!colaVacia()) {
            for (int i = frente; i <= fin; i++) {
                elementos.add(cola[i]);
            }
        }
        return List.copyOf(elementos);
    }
}
