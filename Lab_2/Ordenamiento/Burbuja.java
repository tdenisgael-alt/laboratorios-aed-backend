package Ordenamiento;

public class Burbuja {

    // Clase padre: abstracción y encapsulamiento.
    private abstract static class AlgoritmoOrdenamiento {

        private final int[] vector;

        protected AlgoritmoOrdenamiento(int[] vector) {
            this.vector = vector.clone();
        }

        public abstract void ordenar();

        protected final int getTam() {
            return vector.length;
        }

        protected final int obtener(int posicion) {
            return vector[posicion];
        }

        protected final void intercambiar(int posicion1, int posicion2) {
            int temporal = vector[posicion1];
            vector[posicion1] = vector[posicion2];
            vector[posicion2] = temporal;
        }

        public final int[] getVector() {
            return vector.clone();
        }
    }

    // Clase hija: herencia y polimorfismo.
    private static class OrdenamientoBurbuja
            extends AlgoritmoOrdenamiento {

        protected OrdenamientoBurbuja(int[] vector) {
            super(vector);
        }

        @Override
        public void ordenar() {
            for (int p = 1; p < getTam(); p++) {
                for (int i = 0; i < getTam() - p; i++) {
                    if (obtener(i) > obtener(i + 1)) {
                        intercambiar(i, i + 1);
                    }
                }
            }
        }
    }

    // Método que usará S_Burbuja.java.
    public static int[] ordenar(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo.");
        }

        AlgoritmoOrdenamiento algoritmo = new OrdenamientoBurbuja(vector);

        algoritmo.ordenar();

        return algoritmo.getVector();
    }
}