package Recursividad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

public class QuickSort {

    public static List<int[]> ordenar(int[] arreglo) {
        return ordenarConTraza(arreglo).estados;
    }

    public static Resultado ordenarConTraza(int[] arreglo) {
        if (arreglo == null || arreglo.length == 0) {

            throw new IllegalArgumentException("El arreglo no puede estar vacío.");
        }

        List<int[]> estados = new ArrayList<>();
        List<Particion> traza = new ArrayList<>();
        estados.add(Arrays.copyOf(arreglo, arreglo.length));
        quickSortRec(arreglo, 0, arreglo.length - 1, estados, traza, 0);
        return new Resultado(estados, traza);
    }

    private static void quickSortRec(int[] arreglo, int bajo, int alto, List<int[]> estados, List<Particion> traza, int nivel) {
        if (bajo < alto) {
            int[] subarregloAntes = Arrays.copyOfRange(arreglo, bajo, alto + 1);
            int pivote = arreglo[alto];
            int posicionPivote = particionar(arreglo, bajo, alto, estados);
            int[] subarregloDespues = Arrays.copyOfRange(arreglo, bajo, alto + 1);
            traza.add(new Particion(bajo, alto, nivel, pivote, posicionPivote, subarregloAntes, subarregloDespues));
            quickSortRec(arreglo, bajo, posicionPivote - 1, estados, traza, nivel + 1);
            quickSortRec(arreglo, posicionPivote + 1, alto, estados, traza, nivel + 1);
        }
    }

    public static class Resultado {
        public final List<int[]> estados;
        public final List<Particion> traza;

        public Resultado(List<int[]> estados, List<Particion> traza) {
            this.estados = estados;
            this.traza = traza;
        }
    }

    public static class Particion {
        public final int inicio;
        public final int fin;
        public final int nivel;
        public final int pivote;
        public final int posicionPivote;
        public final int[] subarregloAntes;
        public final int[] subarregloDespues;

        public Particion(int inicio, int fin, int nivel, int pivote, int posicionPivote, int[] subarregloAntes, int[] subarregloDespues) {
            this.inicio = inicio;
            this.fin = fin;
            this.nivel = nivel;
            this.pivote = pivote;
            this.posicionPivote = posicionPivote;
            this.subarregloAntes = subarregloAntes;
            this.subarregloDespues = subarregloDespues;
        }
    }

    private static int particionar(int[] arreglo, int bajo, int alto, List<int[]> estados) {
        int pivote = arreglo[alto];
        return particionarRecursivo(arreglo, bajo, alto, pivote, bajo - 1, bajo, estados);
    }

    private static int particionarRecursivo(int[] arreglo, int bajo, int alto, int pivote, int i, int j, List<int[]> estados) {
        if (j < alto) {
            int nuevoI = i;

            if (arreglo[j] < pivote) {
                nuevoI++;

                if (nuevoI != j) {

                    intercambiar(arreglo, nuevoI, j, estados);
                }
            }

            return particionarRecursivo(arreglo, bajo, alto, pivote, nuevoI, j + 1, estados);
        }

        if (i + 1 != alto) {

            intercambiar(arreglo, i + 1, alto, estados);
        }

        return i + 1;
    }

    private static void intercambiar(int[] arreglo, int i, int j, List<int[]> estados) {
        int temporal = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temporal;
        estados.add(Arrays.copyOf(arreglo, arreglo.length));
    }

    private static int[] convertirEntrada(String[] partes, int indice, int[] arreglo) {
        if (indice == partes.length) {

            return arreglo;
        }

        arreglo[indice] = Integer.parseInt(partes[indice].trim());
        return convertirEntrada(partes, indice + 1, arreglo);
    }

    private static String arregloATexto(int[] arreglo, int indice) {
        if (indice == arreglo.length) {

            return "";
        }

        String separador = indice == arreglo.length - 1 ? "" : " ";
        return arreglo[indice] + separador + arregloATexto(arreglo, indice + 1);
    }

    private static String construirMensaje(List<int[]> estados, int indice) {
        if (indice == estados.size()) {

            return "";
        }

        return (indice + 1) + ". " + arregloATexto(estados.get(indice), 0) + "\n" + construirMensaje(estados, indice + 1);
    }

    public static void main(String[] args) {
        String entrada = JOptionPane.showInputDialog(null, "Ingrese los números separados por espacios:\nEjemplo: 67 9 7 12 15 6 3 1 4 2", "Quick Sort", JOptionPane.QUESTION_MESSAGE);

        if (entrada == null || entrada.trim().isEmpty()) {

            return;
        }

        try {
            String[] partes = entrada.trim().split("\\s+");
            int[] arreglo = convertirEntrada(partes, 0, new int[partes.length]);
            List<int[]> estados = ordenar(arreglo);
            JOptionPane.showMessageDialog(null, "Estados del ordenamiento:\n\n" + construirMensaje(estados, 0), "Quick Sort", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese únicamente números enteros separados por espacios.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
}
