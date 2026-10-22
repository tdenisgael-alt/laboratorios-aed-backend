package Servidor_4;

import Pilas.ConversorExpresiones;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class S_Conversor {

    public static void configurar(HttpServer servidor) {
        servidor.createContext("/lab4/conversor", exchange -> manejar(exchange));
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
            String expresion = datos.getOrDefault("expresion", "");
            String tipo = datos.getOrDefault("tipo", "");
            String json;

            if ("prefija".equals(tipo)) {
                ConversorExpresiones.ResultadoPrefija resultado = ConversorExpresiones.convertirPrefija(expresion);
                json = construirJson(expresion, tipo, resultado.prefija, resultado.pasos, resultado.estadoPilaFinal);
            } else if ("postfija".equals(tipo)) {
                ConversorExpresiones.Resultado resultado = ConversorExpresiones.convertirPostfija(expresion);
                json = construirJson(expresion, tipo, resultado.postfija, resultado.pasos, resultado.estadoPilaFinal);
            } else {
                throw new IllegalArgumentException("El tipo debe ser 'prefija' o 'postfija'.");
            }

            UtilidadesServidor4.responder(exchange, 200, json);
        } catch (IllegalArgumentException e) {
            UtilidadesServidor4.responder(exchange, 400,
                    "{\"error\":\"" + UtilidadesServidor4.escaparJson(e.getMessage()) + "\"}");
        } catch (Exception e) {
            UtilidadesServidor4.responder(exchange, 500, "{\"error\":\"Error interno al convertir la expresión.\"}");
        }
    }

    private static String construirJson(String expresion, String tipo, String resultado,
            List<ConversorExpresiones.Paso> pasos, String estadoPilaFinal) {
        StringBuilder json = new StringBuilder();
        json.append("{\"expresion\":\"").append(UtilidadesServidor4.escaparJson(expresion))
                .append("\",\"tipo\":\"").append(UtilidadesServidor4.escaparJson(tipo))
                .append("\",\"resultado\":\"").append(UtilidadesServidor4.escaparJson(resultado))
                .append("\",\"totalPasos\":").append(pasos.size())
                .append(",\"estadoPilaFinal\":\"").append(UtilidadesServidor4.escaparJson(estadoPilaFinal))
                .append("\",\"pasos\":[");
        agregarPasos(json, pasos, 0);
        json.append("]}");
        return json.toString();
    }

    private static void agregarPasos(StringBuilder json, List<ConversorExpresiones.Paso> pasos, int indice) {
        if (indice == pasos.size()) {
            return;
        }

        ConversorExpresiones.Paso paso = pasos.get(indice);
        json.append("{\"numero\":").append(paso.numero)
                .append(",\"elemento\":\"").append(UtilidadesServidor4.escaparJson(paso.elemento))
                .append("\",\"accion\":\"").append(UtilidadesServidor4.escaparJson(paso.accion))
                .append("\",\"estadoPila\":\"").append(UtilidadesServidor4.escaparJson(paso.estadoPila))
                .append("\",\"resultadoParcial\":\"").append(UtilidadesServidor4.escaparJson(paso.resultadoParcial))
                .append("\"}");

        if (indice < pasos.size() - 1) {
            json.append(",");
        }
        agregarPasos(json, pasos, indice + 1);
    }
}
