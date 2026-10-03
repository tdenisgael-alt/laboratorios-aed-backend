package Servidor_3;

import Recursividad.QuickSort;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class S_QuickSort {

    public static void configurar(HttpServer servidor) {
        servidor.createContext("/lab3/quicksort", exchange -> manejar(exchange));
    }

    private static void manejar(HttpExchange exchange) throws IOException {
        UtilidadesServidor3.aplicarCors(exchange);

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            exchange.close();
            return;
        }

        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {

            UtilidadesServidor3.responder(exchange, 405, "{\"error\":\"Método no permitido. Use POST.\"}");
            return;
        }

        try {
            Map<String, String> datos = UtilidadesServidor3.leerFormulario(exchange);
            int[] arreglo = convertirArreglo(datos.getOrDefault("arreglo", ""));
            QuickSort.Resultado resultado = QuickSort.ordenarConTraza(arreglo);
            UtilidadesServidor3.responder(exchange, 200, construirJson(resultado));
        } catch (NumberFormatException e) {
            UtilidadesServidor3.responder(exchange, 400, "{\"error\":\"El arreglo debe contener únicamente números enteros.\"}");
        } catch (IllegalArgumentException e) {
            UtilidadesServidor3.responder(exchange, 400, "{\"error\":\"" + UtilidadesServidor3.escaparJson(e.getMessage()) + "\"}");
        } catch (Exception e) {
            UtilidadesServidor3.responder(exchange, 500, "{\"error\":\"Error interno al ejecutar Quick Sort.\"}");
        }
    }

    private static int[] convertirArreglo(String entrada) {
        String limpio = entrada == null ? "" : entrada.trim();

        if (limpio.isEmpty()) {

            throw new IllegalArgumentException("Ingrese al menos un número.");
        }

        String[] partes = limpio.split("[\\s,]+");
        int[] arreglo = new int[partes.length];
        llenarArreglo(partes, arreglo, 0);
        return arreglo;
    }

    private static void llenarArreglo(String[] partes, int[] arreglo, int indice) {
        if (indice == partes.length) {

            return;
        }

        arreglo[indice] = Integer.parseInt(partes[indice]);
        llenarArreglo(partes, arreglo, indice + 1);
    }

    private static String construirJson(QuickSort.Resultado resultado) {
        StringBuilder json = new StringBuilder();
        json.append("{\"totalEstados\":").append(resultado.estados.size()).append(",\"estados\":[");
        agregarEstados(json, resultado.estados, 0);
        json.append("],\"traza\":[");
        agregarTraza(json, resultado.traza, 0);
        json.append("]}");
        return json.toString();
    }

    private static void agregarTraza(StringBuilder json, List<QuickSort.Particion> traza, int indice) {
        if (indice == traza.size()) {
            return;
        }

        QuickSort.Particion particion = traza.get(indice);
        json.append("{\"inicio\":").append(particion.inicio)
                .append(",\"fin\":").append(particion.fin)
                .append(",\"nivel\":").append(particion.nivel)
                .append(",\"pivote\":").append(particion.pivote)
                .append(",\"posicionPivote\":").append(particion.posicionPivote)
                .append(",\"subarregloAntes\":");
        agregarArreglo(json, particion.subarregloAntes, 0);
        json.append(",\"subarregloDespues\":");
        agregarArreglo(json, particion.subarregloDespues, 0);
        json.append(",\"hijos\":[");
        boolean tieneIzquierda = particion.inicio <= particion.posicionPivote - 1;
        boolean tieneDerecha = particion.posicionPivote + 1 <= particion.fin;
        if (tieneIzquierda) {
            agregarHijo(json, particion.inicio, particion.posicionPivote - 1, particion.nivel + 1, particion.subarregloDespues, particion.inicio);
        }
        if (tieneIzquierda && tieneDerecha) {
            json.append(",");
        }
        if (tieneDerecha) {
            agregarHijo(json, particion.posicionPivote + 1, particion.fin, particion.nivel + 1, particion.subarregloDespues, particion.inicio);
        }
        json.append("]}");

        if (indice < traza.size() - 1) {
            json.append(",");
        }
        agregarTraza(json, traza, indice + 1);
    }

    private static void agregarHijo(StringBuilder json, int inicio, int fin, int nivel, int[] subarreglo, int base) {
        if (inicio > fin) {
            return;
        }

        json.append("{\"inicio\":").append(inicio)
                .append(",\"fin\":").append(fin)
                .append(",\"nivel\":").append(nivel)
                .append(",\"subarreglo\":");
        int inicioRelativo = inicio - base;
        int finRelativo = fin - base + 1;
        int[] hijo = java.util.Arrays.copyOfRange(subarreglo, inicioRelativo, finRelativo);
        agregarArreglo(json, hijo, 0);
        json.append("}");
    }

    private static void agregarEstados(StringBuilder json, List<int[]> estados, int indice) {
        if (indice == estados.size()) {

            return;
        }

        agregarArreglo(json, estados.get(indice), 0);

        if (indice < estados.size() - 1) {

            json.append(",");
        }

        agregarEstados(json, estados, indice + 1);
    }

    private static void agregarArreglo(StringBuilder json, int[] arreglo, int indice) {
        if (indice == 0) {

            json.append("[");
        }

        if (indice == arreglo.length) {

            json.append("]");
            return;
        }

        json.append(arreglo[indice]);

        if (indice < arreglo.length - 1) {

            json.append(",");
        }

        agregarArreglo(json, arreglo, indice + 1);
    }
}
