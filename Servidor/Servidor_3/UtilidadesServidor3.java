package Servidor_3;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class UtilidadesServidor3 {

    public static void aplicarCors(HttpExchange exchange) {
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
    }

    public static void responder(HttpExchange exchange, int codigo, String cuerpo) throws IOException {
        aplicarCors(exchange);
        byte[] respuesta = cuerpo.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(codigo, respuesta.length);
        exchange.getResponseBody().write(respuesta);
        exchange.getResponseBody().close();
    }

    public static Map<String, String> leerFormulario(HttpExchange exchange) throws IOException {
        String cuerpo = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Map<String, String> datos = new HashMap<>();

        if (cuerpo.isBlank()) {

            return datos;
        }

        String[] pares = cuerpo.split("&");
        leerPares(pares, 0, datos);
        return datos;
    }

    private static void leerPares(String[] pares, int indice, Map<String, String> datos) {
        if (indice == pares.length) {

            return;
        }

        String[] partes = pares[indice].split("=", 2);
        String clave = decodificar(partes[0]);
        String valor = partes.length > 1 ? decodificar(partes[1]) : "";
        datos.put(clave, valor);
        leerPares(pares, indice + 1, datos);
    }

    private static String decodificar(String valor) {
        return URLDecoder.decode(valor, StandardCharsets.UTF_8);
    }

    public static String escaparJson(String texto) {
        return texto.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }
}
