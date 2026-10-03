package Servidor_3;

import Recursividad.SaltoRana;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.List;

public class S_SaltoRana {

    public static void configurar(HttpServer servidor) {
        servidor.createContext("/lab3/rana", exchange -> manejar(exchange));
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
            List<String> estados = SaltoRana.resolver();

            if (estados.isEmpty()) {

                UtilidadesServidor3.responder(exchange, 500, "{\"error\":\"No se encontró una solución para el Salto de la Rana.\"}");
                return;
            }

            UtilidadesServidor3.responder(exchange, 200, construirJson(estados));
        } catch (Exception e) {
            UtilidadesServidor3.responder(exchange, 500, "{\"error\":\"Error interno al resolver el Salto de la Rana.\"}");
        }
    }

    private static String construirJson(List<String> estados) {
        StringBuilder json = new StringBuilder();
        json.append("{\"estadoInicial\":\"VVV_CCC\",\"estadoFinal\":\"CCC_VVV\",\"totalMovimientos\":").append(estados.size() - 1).append(",\"estados\":[");
        agregarEstados(json, estados, 0);
        json.append("]}");
        return json.toString();
    }

    private static void agregarEstados(StringBuilder json, List<String> estados, int indice) {
        if (indice == estados.size()) {

            return;
        }

        json.append("\"").append(UtilidadesServidor3.escaparJson(estados.get(indice))).append("\"");

        if (indice < estados.size() - 1) {

            json.append(",");
        }

        agregarEstados(json, estados, indice + 1);
    }
}
