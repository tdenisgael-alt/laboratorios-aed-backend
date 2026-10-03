package Ordenamiento;

public class Burbuja_S {

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
    private static class OrdenamientoBurbujaSenal
            extends AlgoritmoOrdenamiento {

        protected OrdenamientoBurbujaSenal(int[] vector) {
            super(vector);
        }

        @Override
        public void ordenar() {
            int i = 1;
            boolean band = false;

            while (i < getTam() && !band) {
                band = true;

                for (int j = 0; j < getTam() - 1; j++) {
                    if (obtener(j) > obtener(j + 1)) {
                        intercambiar(j, j + 1);
                        band = false;
                    }
                }

                i++;
            }
        }
    }

    // Método que usará S_Burbuja_S.java.
    public static int[] ordenar(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo.");
        }

        AlgoritmoOrdenamiento algoritmo = new OrdenamientoBurbujaSenal(vector);

        algoritmo.ordenar();

        return algoritmo.getVector();
    }
}