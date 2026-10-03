package Servidor_3;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

public class Servidor_3 {

    public static void configurar(HttpServer servidor) {
        servidor.createContext("/lab3", exchange -> manejarRaiz(exchange));
        S_Hanoi.configurar(servidor);
        S_SaltoRana.configurar(servidor);
        S_QuickSort.configurar(servidor);
        S_OchoReinas.configurar(servidor);
    }

    private static void manejarRaiz(HttpExchange exchange) throws IOException {
        UtilidadesServidor3.aplicarCors(exchange);

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            exchange.close();
            return;
        }

        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {

            UtilidadesServidor3.responder(exchange, 405, "{\"error\":\"Método no permitido. Use GET.\"}");
            return;
        }

        UtilidadesServidor3.responder(exchange, 200, "{\"mensaje\":\"Servidor del Laboratorio 3 funcionando\"}");
    }
}
