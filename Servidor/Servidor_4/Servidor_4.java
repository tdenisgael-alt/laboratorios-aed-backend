package Servidor_4;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

public class Servidor_4 {

    public static void configurar(HttpServer servidor) {
        servidor.createContext("/lab4", exchange -> manejarRaiz(exchange));
        S_Conversor.configurar(servidor);
        S_Pila.configurar(servidor);
        S_Cola.configurar(servidor);
        S_ColaCircular.configurar(servidor);
    }

    private static void manejarRaiz(HttpExchange exchange) throws IOException {
        UtilidadesServidor4.aplicarCors(exchange);

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            exchange.close();
            return;
        }

        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {

            UtilidadesServidor4.responder(exchange, 405, "{\"error\":\"Método no permitido. Use GET.\"}");
            return;
        }

        UtilidadesServidor4.responder(exchange, 200, "{\"mensaje\":\"Servidor del Laboratorio 4 funcionando\"}");
    }
}
