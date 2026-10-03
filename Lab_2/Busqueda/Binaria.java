package Busqueda;

public class Binaria {

    private abstract static class AlgoritmoBusqueda {

        private final int[] vector;

        protected AlgoritmoBusqueda(int[] vector) {
            this.vector = vector.clone();
        }

        public abstract int buscar(int numero);

        protected final int getTam() {
            return vector.length;
        }

        protected final int obtener(int posicion) {
            return vector[posicion];
        }
    }

    private static class BusquedaBinaria
            extends AlgoritmoBusqueda {

        protected BusquedaBinaria(int[] vector) {
            super(vector);
        }

        @Override
        public int buscar(int numero) {
            int inicio = 0;
            int fin = getTam() - 1;

            while (inicio <= fin) {
                int centro = (inicio + fin) / 2;

                if (obtener(centro) == numero) {
                    return centro;
                }

                if (numero < obtener(centro)) {
                    fin = centro - 1;
                } else {
                    inicio = centro + 1;
                }
            }

            return -1;
        }
    }

    // Este método recibe el arreglo ya ordenado.
    // Devuelve su posición o -1 si no existe.
    public static int buscar(int[] vectorOrdenado, int numero) {
        if (vectorOrdenado == null) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo.");
        }

        AlgoritmoBusqueda algoritmo =
                new BusquedaBinaria(vectorOrdenado);

        return algoritmo.buscar(numero);
    }
}