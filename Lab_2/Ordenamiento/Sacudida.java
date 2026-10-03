package Ordenamiento;

public class Sacudida {

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

    private static class OrdenamientoSacudida
            extends AlgoritmoOrdenamiento {

        protected OrdenamientoSacudida(int[] vector) {
            super(vector);
        }

        @Override
        public void ordenar() {
            int izquierda = 0;
            int derecha = getTam() - 1;
            boolean intercambio = true;

            while (izquierda < derecha && intercambio) {
                intercambio = false;

                // Recorrido de izquierda a derecha.
                for (int i = izquierda; i < derecha; i++) {
                    if (obtener(i) > obtener(i + 1)) {
                        intercambiar(i, i + 1);
                        intercambio = true;
                    }
                }

                derecha--;

                if (!intercambio) {
                    break;
                }

                intercambio = false;

                // Recorrido de derecha a izquierda.
                for (int i = derecha; i > izquierda; i--) {
                    if (obtener(i) < obtener(i - 1)) {
                        intercambiar(i, i - 1);
                        intercambio = true;
                    }
                }

                izquierda++;
            }
        }
    }

    public static int[] ordenar(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo.");
        }

        AlgoritmoOrdenamiento algoritmo = new OrdenamientoSacudida(vector);

        algoritmo.ordenar();

        return algoritmo.getVector();
    }
}