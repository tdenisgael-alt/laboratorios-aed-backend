package Servidor_4;

import Pilas.PilaCentrosComerciales;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class S_Pila {

    private static final String COOKIE_SESION = "AED_LAB4_SESION";
    private static final ConcurrentMap<String, PilaCentrosComerciales> PILAS = new ConcurrentHashMap<>();

    public static void configurar(HttpServer servidor) {
        servidor.createContext("/lab4/pila", exchange -> manejar(exchange));
    }

    private static void manejar(HttpExchange exchange) throws IOException {
        UtilidadesServidor4.aplicarCors(exchange);

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            exchange.close();
            return;
        }

        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            UtilidadesServidor4.responder(exchange, 405, "{\"error\":\"Método no permitido. Use POST.\"}");
            return;
        }

        try {
            Map<String, String> datos = UtilidadesServidor4.leerFormulario(exchange);
            String sesion = obtenerSesion(exchange, datos.getOrDefault("sesion", ""));
            String operacion = datos.getOrDefault("operacion", "").trim().toLowerCase();
            PilaCentrosComerciales pila = PILAS.computeIfAbsent(sesion,
                    clave -> new PilaCentrosComerciales());

            String elemento;
            String json;
            synchronized (pila) {
                switch (operacion) {
                    case "insertar":
                        elemento = pila.push(datos.getOrDefault("nombre", ""));
                        break;
                    case "eliminar":
                        elemento = pila.pop();
                        break;
                    case "cima":
                        elemento = pila.obtenerCima();
                        break;
                    case "estado":
                        elemento = null;
                        break;
                    default:
                        throw new IllegalArgumentException(
                                "La operación debe ser 'insertar', 'eliminar', 'cima' o 'estado'.");
                }
                json = construirJson(sesion, operacion, elemento, pila);
            }

            UtilidadesServidor4.responder(exchange, 200, json);
        } catch (IllegalArgumentException | IllegalStateException e) {
            UtilidadesServidor4.responder(exchange, 400,
                    "{\"error\":\"" + UtilidadesServidor4.escaparJson(e.getMessage()) + "\"}");
        } catch (Exception e) {
            UtilidadesServidor4.responder(exchange, 500,
                    "{\"error\":\"Error interno al procesar la pila.\"}");
        }
    }

    private static String obtenerSesion(HttpExchange exchange, String sesionFormulario) {
        String sesion = sesionFormulario.trim();
        if (sesion.isEmpty()) {
            sesion = leerCookieSesion(exchange);
        }
        if (sesion.isEmpty()) {
            sesion = UUID.randomUUID().toString();
        } else {
            try {
                sesion = UUID.fromString(sesion).toString();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("El identificador de sesión no es válido.");
            }
        }

        exchange.getResponseHeaders().set("Set-Cookie",
                COOKIE_SESION + "=" + sesion + "; Path=/lab4; HttpOnly; SameSite=Lax");
        return sesion;
    }

    private static String leerCookieSesion(HttpExchange exchange) {
        List<String> cabecerasCookie = exchange.getRequestHeaders().get("Cookie");
        if (cabecerasCookie == null) {
            return "";
        }
        for (String cabecera : cabecerasCookie) {
            String[] cookies = cabecera.split(";");
            for (String cookie : cookies) {
                String[] partes = cookie.trim().split("=", 2);
                if (partes.length == 2 && COOKIE_SESION.equals(partes[0])) {
                    return partes[1];
                }
            }
        }
        return "";
    }

    private static String construirJson(String sesion, String operacion, String elemento,
            PilaCentrosComerciales pila) {
        StringBuilder json = new StringBuilder();
        json.append("{\"sesion\":\"").append(UtilidadesServidor4.escaparJson(sesion))
                .append("\",\"operacion\":\"").append(UtilidadesServidor4.escaparJson(operacion))
                .append("\",\"elemento\":");
        agregarCadenaONulo(json, elemento);
        json.append(",\"estado\":{\"cantidad\":").append(pila.obtenerCantidad())
                .append(",\"capacidad\":").append(pila.obtenerCapacidad())
                .append(",\"vacia\":").append(pila.pilaVacia())
                .append(",\"llena\":").append(pila.pilaLlena())
                .append(",\"cima\":");
        agregarCadenaONulo(json, pila.pilaVacia() ? null : pila.obtenerCima());
        json.append(",\"elementos\":[");
        agregarElementos(json, pila.obtenerElementos(), 0);
        json.append("]}}");
        return json.toString();
    }

    private static void agregarCadenaONulo(StringBuilder json, String valor) {
        if (valor == null) {
            json.append("null");
        } else {
            json.append("\"").append(UtilidadesServidor4.escaparJson(valor)).append("\"");
        }
    }

    private static void agregarElementos(StringBuilder json, List<String> elementos, int indice) {
        if (indice == elementos.size()) {
            return;
        }
        agregarCadenaONulo(json, elementos.get(indice));
        if (indice < elementos.size() - 1) {
            json.append(",");
        }
        agregarElementos(json, elementos, indice + 1);
    }
}
