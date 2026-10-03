package Ordenamiento;

public class Shell {

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

    private static class OrdenamientoShell
            extends AlgoritmoOrdenamiento {

        protected OrdenamientoShell(int[] vector) {
            super(vector);
        }

        @Override
        public void ordenar() {
            int salto = getTam() / 2;

            while (salto > 0) {
                for (int i = salto; i < getTam(); i++) {
                    int aux = obtener(i);
                    int j = i;

                    while (j >= salto && obtener(j - salto) > aux) {
                        asignar(j, obtener(j - salto));
                        j = j - salto;
                    }

                    asignar(j, aux);
                }

                salto = salto / 2;
            }
        }
    }

    public static int[] ordenar(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo.");
        }

        AlgoritmoOrdenamiento algoritmo =
                new OrdenamientoShell(vector);

        algoritmo.ordenar();

        return algoritmo.getVector();
    }
}