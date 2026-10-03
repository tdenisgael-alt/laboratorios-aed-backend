package Servidor_2;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.Map;
import Ordenamiento.Sacudida;

public class S_Sacudida {

    public static void configurar(HttpServer servidor) {
        servidor.createContext("/lab2/sacudida", S_Sacudida::ordenar);
    }

    private static void ordenar(HttpExchange exchange) throws IOException {
        Servidor_2.agregarCORS(exchange);

        if (Servidor_2.esOPTIONS(exchange)) {
            return;
        }

        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            Servidor_2.enviarJson(exchange, 405,
                    "{\"ok\":false,\"mensaje\":\"Método no permitido\"}");
            return;
        }

        try {
            Map<String, String> datos = Servidor_2.leerFormulario(exchange);

            int[] original = Servidor_2.leerArreglo(datos);
            int[] ordenado = Sacudida.ordenar(original.clone());

            String respuesta = "{"
                    + "\"ok\":true,"
                    + "\"metodo\":\"Sacudida\","
                    + "\"original\":" + Servidor_2.arregloAJson(original) + ","
                    + "\"ordenado\":" + Servidor_2.arregloAJson(ordenado)
                    + "}";

            Servidor_2.enviarJson(exchange, 200, respuesta);

        } catch (IllegalArgumentException e) {
            Servidor_2.enviarJson(exchange, 400,
                    "{\"ok\":false,\"mensaje\":\"" + e.getMessage() + "\"}");

        } catch (Exception e) {
            Servidor_2.enviarJson(exchange, 500,
                    "{\"ok\":false,\"mensaje\":\"Ocurrió un error al ordenar el arreglo\"}");
        }
    }
}