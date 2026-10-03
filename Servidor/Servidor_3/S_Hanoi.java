package Servidor_3;

import Recursividad.Hanoi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class S_Hanoi {

    public static void configurar(HttpServer servidor) {
        servidor.createContext("/lab3/hanoi", exchange -> manejar(exchange));
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
            int discos = Integer.parseInt(datos.getOrDefault("discos", ""));
            List<String> movimientos = Hanoi.resolver(discos);
            UtilidadesServidor3.responder(exchange, 200, construirJson(discos, movimientos));
        } catch (NumberFormatException e) {
            UtilidadesServidor3.responder(exchange, 400, "{\"error\":\"Ingrese una cantidad de discos válida.\"}");
        } catch (IllegalArgumentException e) {
            UtilidadesServidor3.responder(exchange, 400, "{\"error\":\"" + UtilidadesServidor3.escaparJson(e.getMessage()) + "\"}");
        } catch (Exception e) {
            UtilidadesServidor3.responder(exchange, 500, "{\"error\":\"Error interno al resolver Torres de Hanoi.\"}");
        }
    }

    private static String construirJson(int discos, List<String> movimientos) {
        StringBuilder json = new StringBuilder();
        json.append("{\"discos\":").append(discos).append(",\"totalMovimientos\":").append(movimientos.size()).append(",\"movimientos\":[");
        agregarMovimientos(json, movimientos, 0);
        json.append("]}");
        return json.toString();
    }

    private static void agregarMovimientos(StringBuilder json, List<String> movimientos, int indice) {
        if (indice == movimientos.size()) {

            return;
        }

        String[] partes = movimientos.get(indice).split(" -> ");
        json.append("{\"numero\":").append(indice + 1).append(",\"origen\":\"").append(partes[0]).append("\",\"destino\":\"").append(partes[1]).append("\"}");

        if (indice < movimientos.size() - 1) {

            json.append(",");
        }

        agregarMovimientos(json, movimientos, indice + 1);
    }
}
