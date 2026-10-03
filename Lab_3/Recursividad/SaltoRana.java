package Recursividad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;

public class SaltoRana {

    private static final String ESTADO_INICIAL = "VVV_CCC";
    private static final String ESTADO_FINAL = "CCC_VVV";

    public static List<String> resolver() {
        List<String> estados = new ArrayList<>();
        Set<String> visitados = new HashSet<>();

        estados.add(ESTADO_INICIAL);
        visitados.add(ESTADO_INICIAL);

        boolean encontrado = buscarSolucion(ESTADO_INICIAL, estados, visitados);

        if (!encontrado) {

            return new ArrayList<>();
        }

        return estados;
    }

    private static boolean buscarSolucion(String estadoActual, List<String> estados, Set<String> visitados) {
        if (estadoActual.equals(ESTADO_FINAL)) {

            return true;
        }

        int posVacio = estadoActual.indexOf('_');

        if (posVacio - 1 >= 0 && estadoActual.charAt(posVacio - 1) == 'V') {
            String nuevoEstado = intercambiar(estadoActual, posVacio - 1, posVacio);

            if (intentarMovimiento(nuevoEstado, estados, visitados)) {

                return true;
            }
        }

        if (posVacio - 2 >= 0 && estadoActual.charAt(posVacio - 2) == 'V' && estadoActual.charAt(posVacio - 1) == 'C') {
            String nuevoEstado = intercambiar(estadoActual, posVacio - 2, posVacio);

            if (intentarMovimiento(nuevoEstado, estados, visitados)) {

                return true;
            }
        }

        if (posVacio + 1 < estadoActual.length() && estadoActual.charAt(posVacio + 1) == 'C') {
            String nuevoEstado = intercambiar(estadoActual, posVacio + 1, posVacio);

            if (intentarMovimiento(nuevoEstado, estados, visitados)) {

                return true;
            }
        }

        if (posVacio + 2 < estadoActual.length() && estadoActual.charAt(posVacio + 2) == 'C' && estadoActual.charAt(posVacio + 1) == 'V') {
            String nuevoEstado = intercambiar(estadoActual, posVacio + 2, posVacio);

            if (intentarMovimiento(nuevoEstado, estados, visitados)) {

                return true;
            }
        }

        return false;
    }

    private static boolean intentarMovimiento(String nuevoEstado, List<String> estados, Set<String> visitados) {
        if (visitados.contains(nuevoEstado)) {

            return false;
        }

        visitados.add(nuevoEstado);
        estados.add(nuevoEstado);

        if (buscarSolucion(nuevoEstado, estados, visitados)) {

            return true;
        }

        estados.remove(estados.size() - 1);
        return false;
    }

    private static String intercambiar(String estado, int i, int j) {
        char[] caracteres = estado.toCharArray();
        char temporal = caracteres[i];
        caracteres[i] = caracteres[j];
        caracteres[j] = temporal;
        return new String(caracteres);
    }

    private static String construirMensaje(List<String> estados, int indice) {
        if (indice == estados.size()) {

            return "";
        }

        return (indice + 1) + ". " + estados.get(indice) + "\n" + construirMensaje(estados, indice + 1);
    }

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Haga clic en Aceptar para iniciar la resolución del Salto de la Rana.", "Salto de la Rana", JOptionPane.INFORMATION_MESSAGE);

        List<String> estados = resolver();

        if (estados.isEmpty()) {

            JOptionPane.showMessageDialog(null, "No se encontró una solución válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String resultado = "¡Solución encontrada!\n\n" + construirMensaje(estados, 0);
        JOptionPane.showMessageDialog(null, resultado, "Resultado - Salto de la Rana", JOptionPane.INFORMATION_MESSAGE);
    }
}
