package Ordenamiento;

public class Seleccion {

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

    private static class OrdenamientoSeleccion
            extends AlgoritmoOrdenamiento {

        protected OrdenamientoSeleccion(int[] vector) {
            super(vector);
        }

        @Override
        public void ordenar() {
            for (int i = 0; i < getTam() - 1; i++) {
                int posicionMenor = i;

                for (int j = i + 1; j < getTam(); j++) {
                    if (obtener(j) < obtener(posicionMenor)) {
                        posicionMenor = j;
                    }
                }

                if (posicionMenor != i) {
                    intercambiar(i, posicionMenor);
                }
            }
        }
    }

    public static int[] ordenar(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo.");
        }

        AlgoritmoOrdenamiento algoritmo = new OrdenamientoSeleccion(vector);

        algoritmo.ordenar();

        return algoritmo.getVector();
    }
}