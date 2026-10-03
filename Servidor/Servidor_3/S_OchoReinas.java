package Servidor_3;

import Recursividad.OchoReinas;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.List;

public class S_OchoReinas {

    public static void configurar(HttpServer servidor) {
        servidor.createContext("/lab3/reinas", exchange -> manejar(exchange));
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
            List<List<int[]>> soluciones = OchoReinas.resolverTodas();

            if (soluciones.isEmpty()) {

                UtilidadesServidor3.responder(exchange, 500, "{\"error\":\"No se encontró una solución válida para las 8 Reinas.\"}");
                return;
            }

            UtilidadesServidor3.responder(exchange, 200, construirJson(soluciones));
        } catch (Exception e) {
            UtilidadesServidor3.responder(exchange, 500, "{\"error\":\"Error interno al resolver las 8 Reinas.\"}");
        }
    }

    private static String construirJson(List<List<int[]>> soluciones) {
        StringBuilder json = new StringBuilder();
        json.append("{\"totalSoluciones\":").append(soluciones.size()).append(",\"soluciones\":[");
        agregarSoluciones(json, soluciones, 0);
        json.append("]}");
        return json.toString();
    }

    private static void agregarSoluciones(StringBuilder json, List<List<int[]>> soluciones, int indice) {
        if (indice == soluciones.size()) {
            return;
        }

        json.append("{\"numero\":").append(indice + 1).append(",\"posiciones\":[");
        agregarPosiciones(json, soluciones.get(indice), 0);
        json.append("]}");

        if (indice < soluciones.size() - 1) {
            json.append(",");
        }

        agregarSoluciones(json, soluciones, indice + 1);
    }

    private static void agregarPosiciones(StringBuilder json, List<int[]> posiciones, int indice) {
        if (indice == posiciones.size()) {

            return;
        }

        int[] posicion = posiciones.get(indice);
        json.append("{\"reina\":").append(indice + 1).append(",\"columna\":").append(posicion[0]).append(",\"fila\":").append(posicion[1]).append("}");

        if (indice < posiciones.size() - 1) {

            json.append(",");
        }

        agregarPosiciones(json, posiciones, indice + 1);
    }
}
