package Recursividad;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class OchoReinas {

    public static List<int[]> resolver() {
        int[] reinas = new int[8];
        reinas[0] = 0;

        boolean solucion = colocarReina(reinas, 1);

        if (!solucion) {

            return new ArrayList<>();
        }

        List<int[]> posiciones = new ArrayList<>();
        guardarPosiciones(reinas, 0, posiciones);
        return posiciones;
    }

    public static List<List<int[]>> resolverTodas() {
        int[] reinas = new int[8];
        reinas[0] = 0;

        List<List<int[]>> soluciones = new ArrayList<>();
        buscarSoluciones(reinas, 1, soluciones);
        return soluciones;
    }

    private static void buscarSoluciones(int[] reinas, int columna, List<List<int[]>> soluciones) {
        if (columna == 8) {
            List<int[]> posiciones = new ArrayList<>();
            guardarPosiciones(reinas, 0, posiciones);
            soluciones.add(posiciones);
            return;
        }

        intentarFilasParaTodas(reinas, columna, 0, soluciones);
    }

    private static void intentarFilasParaTodas(int[] reinas, int columna, int fila, List<List<int[]>> soluciones) {
        if (fila == 8) {
            return;
        }

        if (esSeguro(reinas, columna, fila, 0)) {
            reinas[columna] = fila;
            buscarSoluciones(reinas, columna + 1, soluciones);
        }

        intentarFilasParaTodas(reinas, columna, fila + 1, soluciones);
    }

    private static boolean colocarReina(int[] reinas, int columna) {
        if (columna == 8) {

            return true;
        }

        return intentarFila(reinas, columna, 0);
    }

    private static boolean intentarFila(int[] reinas, int columna, int fila) {
        if (fila == 8) {

            return false;
        }

        if (esSeguro(reinas, columna, fila, 0)) {
            reinas[columna] = fila;

            if (colocarReina(reinas, columna + 1)) {

                return true;
            }
        }

        return intentarFila(reinas, columna, fila + 1);
    }

    private static boolean esSeguro(int[] reinas, int columna, int fila, int columnaAnterior) {
        if (columnaAnterior == columna) {

            return true;
        }

        if (reinas[columnaAnterior] == fila) {

            return false;
        }

        if (Math.abs(reinas[columnaAnterior] - fila) == Math.abs(columnaAnterior - columna)) {

            return false;
        }

        return esSeguro(reinas, columna, fila, columnaAnterior + 1);
    }

    private static void guardarPosiciones(int[] reinas, int columna, List<int[]> posiciones) {
        if (columna == 8) {

            return;
        }

        posiciones.add(new int[] { columna, reinas[columna] });
        guardarPosiciones(reinas, columna + 1, posiciones);
    }

    private static String construirMensaje(List<int[]> posiciones, int indice) {
        if (indice == posiciones.size()) {

            return "";
        }

        int[] posicion = posiciones.get(indice);
        return "Reina " + (indice + 1) + " -> (" + posicion[0] + "," + posicion[1] + ")\n" + construirMensaje(posiciones, indice + 1);
    }

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Resolviendo el problema de las 8 Reinas...\nPrimera reina obligatoria en (0,0).", "8 Reinas", JOptionPane.INFORMATION_MESSAGE);

        List<int[]> posiciones = resolver();

        if (posiciones.isEmpty()) {

            JOptionPane.showMessageDialog(null, "No se encontró una solución válida.", "8 Reinas", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "Posiciones encontradas:\n\n" + construirMensaje(posiciones, 0), "8 Reinas", JOptionPane.INFORMATION_MESSAGE);
    }
}
