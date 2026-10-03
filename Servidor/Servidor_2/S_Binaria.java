package Servidor_2;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.Map;

import Busqueda.Binaria;
import Ordenamiento.Baraja;
import Ordenamiento.Burbuja;
import Ordenamiento.Burbuja_S;
import Ordenamiento.Sacudida;
import Ordenamiento.Seleccion;
import Ordenamiento.Shell;

public class S_Binaria {

    public static void configurar(HttpServer servidor) {
        servidor.createContext(
                "/lab2/binaria",
                S_Binaria::buscar
        );
    }

    private static void buscar(HttpExchange exchange)
            throws IOException {

        Servidor_2.agregarCORS(exchange);

        if (Servidor_2.esOPTIONS(exchange)) {
            return;
        }

        if (!"POST".equalsIgnoreCase(
                exchange.getRequestMethod())) {

            Servidor_2.enviarJson(
                    exchange,
                    405,
                    "{\"ok\":false,\"mensaje\":\"Método no permitido\"}"
            );
            return;
        }

        try {
            Map<String, String> datos =
                    Servidor_2.leerFormulario(exchange);

            int[] original =
                    Servidor_2.leerArreglo(datos);

            String metodo = datos.get("metodo");
            String tipo = datos.get("tipo");
            String valorTexto = datos.get("valor");

            if (metodo == null
                    || metodo.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Debe seleccionar un método de ordenamiento."
                );
            }

            if (tipo == null
                    || tipo.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Debe seleccionar si desea buscar por número o por índice."
                );
            }

            if (valorTexto == null
                    || valorTexto.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Debe ingresar el número o índice que desea buscar."
                );
            }

            int valor;

            try {
                valor = Integer.parseInt(
                        valorTexto.trim()
                );

            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "El valor ingresado debe ser un número entero."
                );
            }

            metodo = metodo.trim().toLowerCase();
            tipo = tipo.trim().toLowerCase();

            int[] ordenado = ordenarArreglo(
                    original.clone(),
                    metodo
            );

            String respuesta;

            if ("numero".equals(tipo)) {
                respuesta = buscarPorNumero(
                        original,
                        ordenado,
                        metodo,
                        valor
                );

            } else if ("indice".equals(tipo)) {
                respuesta = buscarPorIndice(
                        original,
                        ordenado,
                        metodo,
                        valor
                );

            } else {
                throw new IllegalArgumentException(
                        "El tipo de búsqueda debe ser numero o indice."
                );
            }

            Servidor_2.enviarJson(
                    exchange,
                    200,
                    respuesta
            );

        } catch (IllegalArgumentException e) {
            Servidor_2.enviarJson(
                    exchange,
                    400,
                    "{\"ok\":false,\"mensaje\":\""
                            + e.getMessage()
                            + "\"}"
            );

        } catch (Exception e) {
            Servidor_2.enviarJson(
                    exchange,
                    500,
                    "{\"ok\":false,\"mensaje\":\"Ocurrió un error al realizar la búsqueda\"}"
            );
        }
    }

    private static String buscarPorNumero(
            int[] original,
            int[] ordenado,
            String metodo,
            int numeroBuscado
    ) {
        int indiceDesordenado =
                buscarEnOriginal(
                        original,
                        numeroBuscado
                );

        int indiceOrdenado =
                Binaria.buscar(
                        ordenado,
                        numeroBuscado
                );

        boolean encontrado =
                indiceOrdenado != -1;

        String mensaje;

        if (encontrado) {
            mensaje = "Número encontrado";
        } else {
            mensaje = "Número no encontrado";
        }

        return "{"
                + "\"ok\":true,"
                + "\"metodoOrdenamiento\":\""
                + metodo + "\","
                + "\"tipoBusqueda\":\"numero\","
                + "\"original\":"
                + Servidor_2.arregloAJson(original)
                + ","
                + "\"ordenado\":"
                + Servidor_2.arregloAJson(ordenado)
                + ","
                + "\"numeroBuscado\":"
                + numeroBuscado
                + ","
                + "\"encontrado\":"
                + encontrado
                + ","
                + "\"indiceDesordenado\":"
                + indiceDesordenado
                + ","
                + "\"posicionDesordenada\":"
                + (encontrado
                        ? indiceDesordenado + 1
                        : -1)
                + ","
                + "\"indiceOrdenado\":"
                + indiceOrdenado
                + ","
                + "\"posicionOrdenada\":"
                + (encontrado
                        ? indiceOrdenado + 1
                        : -1)
                + ","
                + "\"mensaje\":\""
                + mensaje
                + "\""
                + "}";
    }

    private static String buscarPorIndice(
            int[] original,
            int[] ordenado,
            String metodo,
            int indice
    ) {
        if (indice < 0
                || indice >= original.length) {

            throw new IllegalArgumentException(
                    "El índice debe estar entre 0 y "
                            + (original.length - 1)
                            + "."
            );
        }

        int numeroDesordenado =
                original[indice];

        int numeroOrdenado =
                ordenado[indice];

        return "{"
                + "\"ok\":true,"
                + "\"metodoOrdenamiento\":\""
                + metodo + "\","
                + "\"tipoBusqueda\":\"indice\","
                + "\"original\":"
                + Servidor_2.arregloAJson(original)
                + ","
                + "\"ordenado\":"
                + Servidor_2.arregloAJson(ordenado)
                + ","
                + "\"indiceConsultado\":"
                + indice
                + ","
                + "\"posicion\":"
                + (indice + 1)
                + ","
                + "\"numeroDesordenado\":"
                + numeroDesordenado
                + ","
                + "\"numeroOrdenado\":"
                + numeroOrdenado
                + ","
                + "\"mensaje\":\"Valores encontrados en el índice "
                + indice
                + "\""
                + "}";
    }

    private static int buscarEnOriginal(
            int[] vector,
            int numero
    ) {
        for (int i = 0;
                i < vector.length;
                i++) {

            if (vector[i] == numero) {
                return i;
            }
        }

        return -1;
    }

    private static int[] ordenarArreglo(
            int[] vector,
            String metodo
    ) {
        switch (metodo) {
            case "burbuja":
                return Burbuja.ordenar(vector);

            case "burbuja-senal":
                return Burbuja_S.ordenar(vector);

            case "sacudida":
                return Sacudida.ordenar(vector);

            case "baraja":
                return Baraja.ordenar(vector);

            case "seleccion":
                return Seleccion.ordenar(vector);

            case "shell":
                return Shell.ordenar(vector);

            default:
                throw new IllegalArgumentException(
                        "El método de ordenamiento seleccionado no es válido."
                );
        }
    }
}