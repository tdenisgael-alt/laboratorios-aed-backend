package Servidor_2;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Servidor_2 {

    public static void configurar(HttpServer servidor) {

        servidor.createContext("/lab2", exchange -> {
        	
            agregarCORS(exchange);

            if (esOPTIONS(exchange)) {
                return;
            }

            if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                enviarJson(exchange,405,"{\"ok\":false,\"mensaje\":\"Metodo no permitido\"}");
                return;
            }

            enviarJson(exchange,200,"{\"ok\":true,\"mensaje\":\"Servidor del Laboratorio 2 funcionando\"}");
        });
        
        S_Burbuja.configurar(servidor);
        S_Burbuja_S.configurar(servidor);
        S_Sacudida.configurar(servidor);
        S_Baraja.configurar(servidor);
        S_Seleccion.configurar(servidor);
        S_Shell.configurar(servidor);
        S_Binaria.configurar(servidor);
    }

    // Lee los datos enviados desde la página web.
    public static Map<String, String> leerFormulario(HttpExchange exchange)
            throws IOException {

        String cuerpo = new String(
                exchange.getRequestBody().readAllBytes(),
                StandardCharsets.UTF_8
        );

        Map<String, String> datos = new HashMap<>();

        if (cuerpo.isBlank()) {
            return datos;
        }

        String[] pares = cuerpo.split("&");

        for (String par : pares) {
            String[] claveValor = par.split("=", 2);

            String clave = URLDecoder.decode(
                    claveValor[0],
                    StandardCharsets.UTF_8
            );

            String valor = "";

            if (claveValor.length > 1) {
                valor = URLDecoder.decode(
                        claveValor[1],
                        StandardCharsets.UTF_8
                );
            }

            datos.put(clave, valor);
        }

        return datos;
    }

    // Método único para leer y validar arreglos.
    // Será usado por todos los S_ del Laboratorio 2.
    public static int[] leerArreglo(Map<String, String> datos) {

        String tamTexto = datos.get("tam");
        String elementosTexto = datos.get("elementos");

        if (tamTexto == null || elementosTexto == null) {
            throw new IllegalArgumentException(
                    "Debe enviar el tamaño y los elementos del arreglo."
            );
        }

        int tam;

        try {
            tam = Integer.parseInt(tamTexto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "El tamaño debe ser un número entero."
            );
        }

        if (tam <= 0) {
            throw new IllegalArgumentException(
                    "El tamaño debe ser mayor que cero."
            );
        }

        if (elementosTexto.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Debe ingresar los elementos del arreglo."
            );
        }

        String[] elementos = elementosTexto.trim().split("\\s*,\\s*");

        if (elementos.length != tam) {
            throw new IllegalArgumentException(
                    "La cantidad de elementos debe ser igual al tamaño indicado."
            );
        }

        int[] vector = new int[tam];

        for (int i = 0; i < tam; i++) {
            try {
                vector[i] = Integer.parseInt(elementos[i].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "Todos los elementos deben ser números enteros."
                );
            }

            for (int j = 0; j < i; j++) {
                if (vector[i] == vector[j]) {
                    throw new IllegalArgumentException(
                            "No se permiten números repetidos."
                    );
                }
            }
        }

        return vector;
    }

    public static void agregarCORS(HttpExchange exchange) {
        exchange.getResponseHeaders().set(
                "Access-Control-Allow-Origin",
                "*"
        );

        exchange.getResponseHeaders().set(
                "Access-Control-Allow-Methods",
                "GET, POST, OPTIONS"
        );

        exchange.getResponseHeaders().set(
                "Access-Control-Allow-Headers",
                "Content-Type"
        );
    }

    public static boolean esOPTIONS(HttpExchange exchange)
            throws IOException {

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            exchange.close();
            return true;
        }

        return false;
    }

    public static void enviarJson(
            HttpExchange exchange,
            int codigo,
            String respuesta
    ) throws IOException {

        byte[] bytes = respuesta.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set(
                "Content-Type",
                "application/json; charset=UTF-8"
        );

        exchange.sendResponseHeaders(codigo, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }

    public static String arregloAJson(int[] vector) {
        StringBuilder json = new StringBuilder("[");

        for (int i = 0; i < vector.length; i++) {
            json.append(vector[i]);

            if (i < vector.length - 1) {
                json.append(",");
            }
        }

        json.append("]");

        return json.toString();
    }
}