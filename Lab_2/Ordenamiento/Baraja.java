package Ordenamiento;

public class Baraja {

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

        protected final void asignar(int posicion, int valor) {
            vector[posicion] = valor;
        }

        public final int[] getVector() {
            return vector.clone();
        }
    }

    private static class OrdenamientoBaraja
            extends AlgoritmoOrdenamiento {

        protected OrdenamientoBaraja(int[] vector) {
            super(vector);
        }

        @Override
        public void ordenar() {
            for (int i = 1; i < getTam(); i++) {
                int aux = obtener(i);
                int k = i - 1;

                while (k >= 0 && aux < obtener(k)) {
                    asignar(k + 1, obtener(k));
                    k--;
                }

                asignar(k + 1, aux);
            }
        }
    }

    public static int[] ordenar(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo.");
        }

        AlgoritmoOrdenamiento algoritmo = new OrdenamientoBaraja(vector);

        algoritmo.ordenar();

        return algoritmo.getVector();
    }
}