package Pilas;

import java.util.ArrayList;
import java.util.List;

/**
 * Adaptación sin Swing de las conversiones de ConversorExpresiones.java.
 * Conserva la pila manual, las precedencias y los pasos del original.
 */
public final class ConversorExpresiones {

    private static final int MAX = 100;
    private final String[] pila = new String[MAX];
    private int tope = -1;

    private static final int MAX_PASOS = 500;
    private int totalPasos = 0;
    private final int[] historialPaso = new int[MAX_PASOS];
    private final String[] historialElemento = new String[MAX_PASOS];
    private final String[] historialAccion = new String[MAX_PASOS];
    private final String[] historialEstadoPila = new String[MAX_PASOS];
    private final String[] historialResultadoParcial = new String[MAX_PASOS];

    private ConversorExpresiones() {
    }

    /**
     * Convierte una expresión usando un estado independiente por llamada.
     * Admite operandos alfanuméricos, paréntesis y operadores binarios + - * / ^.
     * Los tokens del resultado quedan separados por espacios.
     *
     * @throws IllegalArgumentException si la expresión es inválida o excede
     *         los límites de 100 elementos en la pila o 500 pasos.
     */
    public static Resultado convertirPostfija(String expresion) {
        return new ConversorExpresiones().ejecutarConversionPostfija(expresion);
    }

    /**
     * Convierte a prefija recorriendo los tokens de derecha a izquierda.
     * Cada llamada mantiene su propia pila e historial, como en convertirPostfija.
     *
     * @throws IllegalArgumentException si la expresión es inválida o excede
     *         los límites de 100 elementos en la pila, 100 tokens de salida
     *         o 500 pasos.
     */
    public static ResultadoPrefija convertirPrefija(String expresion) {
        return new ConversorExpresiones().ejecutarConversionPrefija(expresion);
    }

    public static final class ResultadoPrefija {
        public final String prefija;
        public final List<Paso> pasos;
        public final String estadoPilaFinal;

        private ResultadoPrefija(String prefija, List<Paso> pasos, String estadoPilaFinal) {
            this.prefija = prefija;
            this.pasos = List.copyOf(pasos);
            this.estadoPilaFinal = estadoPilaFinal;
        }
    }

    public static final class Resultado {
        public final String postfija;
        public final List<Paso> pasos;
        public final String estadoPilaFinal;

        private Resultado(String postfija, List<Paso> pasos, String estadoPilaFinal) {
            this.postfija = postfija;
            this.pasos = List.copyOf(pasos);
            this.estadoPilaFinal = estadoPilaFinal;
        }
    }

    public static final class Paso {
        public final int numero;
        public final String elemento;
        public final String accion;
        /** Estado desde la base hasta la cima, con el formato del original. */
        public final String estadoPila;
        public final String resultadoParcial;

        private Paso(int numero, String elemento, String accion,
                String estadoPila, String resultadoParcial) {
            this.numero = numero;
            this.elemento = elemento;
            this.accion = accion;
            this.estadoPila = estadoPila;
            this.resultadoParcial = resultadoParcial;
        }
    }

    private boolean pilaLlena() {
        return tope == MAX - 1;
    }

    private boolean pilaVacia() {
        return tope == -1;
    }

    private void push(String dato) {
        if (pilaLlena()) {
            throw new IllegalArgumentException("Desbordamiento - Pila llena (máximo 100 elementos).");
        }
        tope = tope + 1;
        pila[tope] = dato;
    }

    private String pop() {
        if (pilaVacia()) {
            throw new IllegalStateException("Subdesbordamiento - Pila vacía.");
        }
        String dato = pila[tope];
        tope = tope - 1;
        return dato;
    }

    private String obtenerCima() {
        return pilaVacia() ? null : pila[tope];
    }

    private String obtenerEstadoPila() {
        if (pilaVacia()) {
            return "[Vacía]";
        }
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i <= tope; i++) {
            sb.append(pila[i]).append(i == tope ? "" : " | ");
        }
        sb.append(" ]");
        return sb.toString();
    }

    private void registrarPaso(String elemento, String accion, String resultadoParcial) {
        // El original truncaba el historial; aquí se informa el límite al llamador.
        if (totalPasos == MAX_PASOS) {
            throw new IllegalArgumentException("La conversión excede el máximo de 500 pasos.");
        }
        historialPaso[totalPasos] = totalPasos + 1;
        historialElemento[totalPasos] = elemento;
        historialAccion[totalPasos] = accion;
        historialEstadoPila[totalPasos] = obtenerEstadoPila();
        historialResultadoParcial[totalPasos] = resultadoParcial;
        totalPasos++;
    }

    private String[] tokenizar(String expresion) {
        if (expresion == null) return new String[0];
        expresion = expresion.trim();
        if (expresion.isEmpty()) return new String[0];

        String[] tempTokens = new String[expresion.length()];
        int tokenCount = 0;
        int i = 0;

        while (i < expresion.length()) {
            char c = expresion.charAt(i);
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }
            if (Character.isLetterOrDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < expresion.length() && Character.isLetterOrDigit(expresion.charAt(i))) {
                    sb.append(expresion.charAt(i));
                    i++;
                }
                tempTokens[tokenCount++] = sb.toString();
            } else if (esOperador(String.valueOf(c)) || c == '(' || c == ')') {
                tempTokens[tokenCount++] = String.valueOf(c);
                i++;
            } else {
                return null;
            }
        }

        String[] tokens = new String[tokenCount];
        System.arraycopy(tempTokens, 0, tokens, 0, tokenCount);
        return tokens;
    }

    private boolean esOperador(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*")
                || token.equals("/") || token.equals("^");
    }

    private int precedencia(String op) {
        switch (op) {
            case "+": case "-": return 1;
            case "*": case "/": return 2;
            case "^": return 3;
            default: return -1;
        }
    }

    private void validarExpresion(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            throw new IllegalArgumentException(
                    "La expresión introducida está vacía o contiene caracteres no permitidos.");
        }

        int parentesis = 0;
        boolean esperarOperando = true;
        for (String token : tokens) {
            if (token.equals("(")) {
                // Evita aceptar multiplicaciones implícitas como A(B).
                if (!esperarOperando) {
                    throw new IllegalArgumentException("Falta un operador antes de '('.");
                }
                parentesis++;
                esperarOperando = true;
            } else if (token.equals(")")) {
                parentesis--;
                if (parentesis < 0) {
                    throw new IllegalArgumentException("Paréntesis desbalanceados: hay un ')' sin '('.");
                }
                // Evita paréntesis vacíos y operadores sin operando, como (A+).
                if (esperarOperando) {
                    throw new IllegalArgumentException("Falta un operando antes de ')'.");
                }
                esperarOperando = false;
            } else if (esOperador(token)) {
                if (esperarOperando) {
                    throw new IllegalArgumentException("Operador '" + token + "' en posición inválida.");
                }
                esperarOperando = true;
            } else {
                if (!esperarOperando) {
                    throw new IllegalArgumentException("Falta un operador entre operandos: " + token);
                }
                esperarOperando = false;
            }
        }

        if (parentesis != 0) {
            throw new IllegalArgumentException("Paréntesis desbalanceados: existen paréntesis sin cerrar.");
        }
        if (esperarOperando) {
            throw new IllegalArgumentException("La expresión no puede terminar con un operador.");
        }
    }

    private Resultado ejecutarConversionPostfija(String expresion) {
        String[] tokens = tokenizar(expresion);
        validarExpresion(tokens);
        StringBuilder salida = new StringBuilder();

        // Recorrido, precedencia y asociatividad de la conversión original.
        for (String token : tokens) {
            if (!esOperador(token) && !token.equals("(") && !token.equals(")")) {
                salida.append(token).append(" ");
                registrarPaso(token, "Lectura de operando: Añadir a salida", salida.toString().trim());
            } else if (token.equals("(")) {
                push(token);
                registrarPaso(token, "PUSH '(' a la pila", salida.toString().trim());
            } else if (token.equals(")")) {
                while (!pilaVacia() && !obtenerCima().equals("(")) {
                    String op = pop();
                    salida.append(op).append(" ");
                    registrarPaso(token, "POP '" + op + "' por paréntesis de cierre y añadir a salida",
                            salida.toString().trim());
                }
                if (!pilaVacia() && obtenerCima().equals("(")) {
                    pop();
                    registrarPaso(token, "POP '(' descartado", salida.toString().trim());
                }
            } else if (esOperador(token)) {
                while (!pilaVacia() && !obtenerCima().equals("(") &&
                        (precedencia(obtenerCima()) > precedencia(token) ||
                        (precedencia(obtenerCima()) == precedencia(token) && !token.equals("^")))) {
                    String op = pop();
                    salida.append(op).append(" ");
                    registrarPaso(token, "POP '" + op + "' por precedencia y añadir a salida",
                            salida.toString().trim());
                }
                push(token);
                registrarPaso(token, "PUSH operador '" + token + "' a la pila", salida.toString().trim());
            }
        }

        while (!pilaVacia()) {
            String op = pop();
            salida.append(op).append(" ");
            registrarPaso("FIN", "POP final '" + op + "' y añadir a salida", salida.toString().trim());
        }

        List<Paso> pasos = new ArrayList<>(totalPasos);
        for (int i = 0; i < totalPasos; i++) {
            pasos.add(new Paso(historialPaso[i], historialElemento[i], historialAccion[i],
                    historialEstadoPila[i], historialResultadoParcial[i]));
        }
        return new Resultado(salida.toString().trim(), pasos, obtenerEstadoPila());
    }

    private ResultadoPrefija ejecutarConversionPrefija(String expresion) {
        String[] tokens = tokenizar(expresion);
        validarExpresion(tokens);

        // El original usa String[MAX] para la salida. Validar antes de escribir
        // evita un desbordamiento sin cambiar el recorrido de conversión.
        int tokensSalida = 0;
        for (String token : tokens) {
            if (!token.equals("(") && !token.equals(")")) {
                tokensSalida++;
            }
        }
        if (tokensSalida > MAX) {
            throw new IllegalArgumentException("La salida prefija excede el máximo de 100 tokens.");
        }

        String[] salidaPrefija = new String[MAX];
        int countSalida = 0;

        // Recorrido de derecha a izquierda del original. Con precedencia igual,
        // solo ^ desapila: conserva su asociatividad derecha y la izquierda de + - * /.
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];

            if (!esOperador(token) && !token.equals("(") && !token.equals(")")) {
                salidaPrefija[countSalida++] = token;
                registrarPaso(token, "Lectura de operando: Añadir a salida", obtenerCadenaInvertida(salidaPrefija, countSalida));
            } else if (token.equals(")")) {
                push(token);
                registrarPaso(token, "PUSH ')' a la pila", obtenerCadenaInvertida(salidaPrefija, countSalida));
            } else if (token.equals("(")) {
                while (!pilaVacia() && !obtenerCima().equals(")")) {
                    String op = pop();
                    salidaPrefija[countSalida++] = op;
                    registrarPaso(token, "POP '" + op + "' por paréntesis de apertura y añadir a salida", obtenerCadenaInvertida(salidaPrefija, countSalida));
                }
                if (!pilaVacia() && obtenerCima().equals(")")) {
                    pop();
                    registrarPaso(token, "POP ')' descartado", obtenerCadenaInvertida(salidaPrefija, countSalida));
                }
            } else if (esOperador(token)) {
                while (!pilaVacia() && !obtenerCima().equals(")") &&
                        (precedencia(obtenerCima()) > precedencia(token) ||
                        (precedencia(obtenerCima()) == precedencia(token) && token.equals("^")))) {
                    String op = pop();
                    salidaPrefija[countSalida++] = op;
                    registrarPaso(token, "POP '" + op + "' por precedencia y añadir a salida", obtenerCadenaInvertida(salidaPrefija, countSalida));
                }
                push(token);
                registrarPaso(token, "PUSH operador '" + token + "' a la pila", obtenerCadenaInvertida(salidaPrefija, countSalida));
            }
        }

        while (!pilaVacia()) {
            String op = pop();
            salidaPrefija[countSalida++] = op;
            registrarPaso("FIN", "POP final '" + op + "' y añadir a salida", obtenerCadenaInvertida(salidaPrefija, countSalida));
        }

        List<Paso> pasos = new ArrayList<>(totalPasos);
        for (int i = 0; i < totalPasos; i++) {
            pasos.add(new Paso(historialPaso[i], historialElemento[i], historialAccion[i],
                    historialEstadoPila[i], historialResultadoParcial[i]));
        }
        return new ResultadoPrefija(obtenerCadenaInvertida(salidaPrefija, countSalida),
                pasos, obtenerEstadoPila());
    }

    private String obtenerCadenaInvertida(String[] arr, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = count - 1; i >= 0; i--) {
            sb.append(arr[i]).append(i == 0 ? "" : " ");
        }
        return sb.toString();
    }
}
