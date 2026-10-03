package Recursividad;

import java.util.ArrayList;

import java.util.List;
import javax.swing.JOptionPane;

public class Hanoi {

    public static List<String> resolver(int discos) {
        if (discos < 1 || discos > 7) {

            throw new IllegalArgumentException("La cantidad de discos debe estar entre 1 y 7.");
        }

        List<String> movimientos = new ArrayList<>();
        resolverHanoi(discos, "A", "B", "C", movimientos);
        return movimientos;
    }

    private static void resolverHanoi(int discos, String origen, String auxiliar, String destino, List<String> movimientos) {
        if (discos == 1) {

            movimientos.add(origen + " -> " + destino);
            return;
        }

        resolverHanoi(discos - 1, origen, destino, auxiliar, movimientos);
        movimientos.add(origen + " -> " + destino);
        resolverHanoi(discos - 1, auxiliar, origen, destino, movimientos);
    }

    private static int pedirDiscos() {
        String entrada = JOptionPane.showInputDialog(null, "Ingrese la cantidad de discos (1 - 7):", "Torres de Hanoi", JOptionPane.QUESTION_MESSAGE);

        if (entrada == null) {

            return -1;
        }

        try {
            int discos = Integer.parseInt(entrada.trim());

            if (discos < 1 || discos > 7) {

                JOptionPane.showMessageDialog(null, "Solamente se permiten de 1 a 7 discos.", "Error de rango", JOptionPane.ERROR_MESSAGE);
                return pedirDiscos();
            }

            return discos;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un número entero válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            return pedirDiscos();
        }
    }

    private static String construirMensaje(List<String> movimientos, int indice) {
        if (indice == movimientos.size()) {

            return "";
        }

        return (indice + 1) + ". " + movimientos.get(indice) + "\n" + construirMensaje(movimientos, indice + 1);
    }

    public static void main(String[] args) {
        int discos = pedirDiscos();

        if (discos == -1) {

            return;
        }

        List<String> movimientos = resolver(discos);
        String resultado = "Cantidad de discos: " + discos + "\n\nMovimientos (Total: " + movimientos.size() + "):\n\n" + construirMensaje(movimientos, 0);
        JOptionPane.showMessageDialog(null, resultado, "Resultado de Torres de Hanoi", JOptionPane.INFORMATION_MESSAGE);
    }
}
