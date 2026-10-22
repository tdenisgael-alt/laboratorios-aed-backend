package Servidor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import Desordenados.Alumno;
import Desordenados.Cliente;
import Desordenados.Empleado;
import Servidor_2.Servidor_2;
import Servidor_3.Servidor_3;
import Servidor_4.Servidor_4;

public class Servidor {
    public static void main(String[] args) {
        try {
            String puertoConfigurado = System.getenv("PORT");
            int puerto = puertoConfigurado == null || puertoConfigurado.isBlank()
                    ? 8080
                    : Integer.parseInt(puertoConfigurado);
            HttpServer servidor = HttpServer.create(new InetSocketAddress("0.0.0.0", puerto), 0);
            servidor.createContext("/", intercambio -> {
                agregarCORS(intercambio);
                enviarRespuesta(intercambio, 200, "Servidor Java en ejecucion");
            });

            servidor.createContext("/desordenado1/iniciar", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    int tam = Integer.parseInt(datos.get("tam"));
                    String respuesta = Alumno.InicializarWeb(tam);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado1/alta", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    int semestre = Integer.parseInt(datos.get("semestre"));
                    float promedio = Float.parseFloat(datos.get("promedio"));
                    String respuesta = Alumno.AltaWeb(nombre, semestre, promedio);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado1/baja", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    String respuesta = Alumno.BajaWeb(nombre);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado1/modificar", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    int semestre = Integer.parseInt(datos.get("semestre"));
                    float promedio = Float.parseFloat(datos.get("promedio"));
                    String respuesta = Alumno.ModificarWeb(nombre, semestre, promedio);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado1/listaruno", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    String respuesta = Alumno.ListarUnoWeb(nombre);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado1/listartodos", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("GET")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    String respuesta = Alumno.ListarTodosWeb();
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado2/iniciar", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    int tam = Integer.parseInt(datos.get("tam"));
                    String respuesta = Cliente.InicializarWeb(tam);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado2/alta", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    String telefono = datos.get("telefono");
                    float saldo = Float.parseFloat(datos.get("saldo"));
                    boolean moroso = Boolean.parseBoolean(datos.get("moroso"));
                    String respuesta = Cliente.AltaWeb(nombre, telefono, saldo, moroso);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado2/modificar", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    boolean moroso = Boolean.parseBoolean(datos.get("moroso"));
                    String respuesta = Cliente.ModificarWeb(nombre, moroso);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado2/baja", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    String respuesta = Cliente.BajaWeb(nombre);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado2/listaruno", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    String respuesta = Cliente.ListarUnoWeb(nombre);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado2/listartodos", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("GET")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    String respuesta = Cliente.ListarTodosWeb();
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado3/iniciar", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    int tam = Integer.parseInt(datos.get("tam"));
                    String respuesta = Empleado.IniciarWeb(tam);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado3/alta", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    String sexoTexto = datos.get("sexo");
                    char sexo = sexoTexto.toUpperCase().charAt(0);
                    int edad = Integer.parseInt(datos.get("edad"));
                    String respuesta = Empleado.AltaWeb(nombre, sexo, edad);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado3/baja", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    String respuesta = Empleado.BajaWeb(nombre);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado3/modificar", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    int edad = Integer.parseInt(datos.get("edad"));
                    String respuesta = Empleado.ModificarWeb(nombre, edad);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado3/listarvarones", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("GET")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    String respuesta = Empleado.ListarVaronesWeb();
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado3/listaruno", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    String respuesta = Empleado.ListarUnoWeb(nombre);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/desordenado3/listartodos", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("GET")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    String respuesta = Empleado.ListarTodosWeb();
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado1/iniciar", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    int tam = Integer.parseInt(datos.get("tam"));
                    String respuesta = Ordenados.Empleado.InicializarWeb(tam);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado1/alta", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    String direccion = datos.get("direccion");
                    int edad = Integer.parseInt(datos.get("edad"));
                    char sexo = datos.get("sexo").toUpperCase().charAt(0);
                    int antiguedad = Integer.parseInt(datos.get("antiguedad"));
                    String respuesta = Ordenados.Empleado.AltaWeb(nombre, direccion, edad, sexo, antiguedad);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado1/baja", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    String respuesta = Ordenados.Empleado.BajaWeb(nombre);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado1/modificar", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    int antiguedad = Integer.parseInt(datos.get("antiguedad"));
                    String respuesta = Ordenados.Empleado.ModificarWeb(nombre, antiguedad);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado1/listaruno", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    String respuesta = Ordenados.Empleado.ListarUnoWeb(nombre);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado1/listartodos", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("GET")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    String respuesta = Ordenados.Empleado.ListarTodosWeb();
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado2/iniciar", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    int tam = Integer.parseInt(datos.get("tam"));
                    String respuesta = Ordenados.Departamento.InicializarWeb(tam);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado2/alta", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String ubicacion = datos.get("ubicacion");
                    float extension = Float.parseFloat(datos.get("extension"));
                    float precio = Float.parseFloat(datos.get("precio"));
                    int numeroApartamento = Integer.parseInt(datos.get("numeroApartamento"));
                    String nombrePersona = datos.get("nombrePersona");
                    String respuesta = Ordenados.Departamento.AltaWeb(ubicacion, extension, precio, numeroApartamento, nombrePersona);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado2/baja", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    int numeroApartamento = Integer.parseInt(datos.get("numeroApartamento"));
                    String respuesta = Ordenados.Departamento.BajaWeb(numeroApartamento);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado2/modificar", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    int numeroApartamento = Integer.parseInt(datos.get("numeroApartamento"));
                    float nuevoPrecio = Float.parseFloat(datos.get("precio"));
                    String respuesta = Ordenados.Departamento.ModificarWeb(numeroApartamento, nuevoPrecio);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado2/listaruno", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    int numeroApartamento = Integer.parseInt(datos.get("numeroApartamento"));
                    String respuesta = Ordenados.Departamento.ListarUnoWeb(numeroApartamento);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado2/listartodos", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("GET")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    String respuesta = Ordenados.Departamento.ListarTodosWeb();
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado3/iniciar", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    int tam = Integer.parseInt(datos.get("tam"));
                    String respuesta = Ordenados.Vendedor.InicializarWeb(tam);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado3/alta", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    float totalVentas = Float.parseFloat(datos.get("totalVentas"));
                    String respuesta = Ordenados.Vendedor.AltaWeb(nombre, totalVentas);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado3/modificar", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    float totalVentas = Float.parseFloat(datos.get("totalVentas"));
                    String respuesta = Ordenados.Vendedor.ModificarWeb(nombre, totalVentas);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            servidor.createContext("/ordenado3/listaruno", intercambio -> {
                agregarCORS(intercambio);
                if (esOPTIONS(intercambio)) {
                    return;
                }
                if (!intercambio.getRequestMethod().equalsIgnoreCase("POST")) {
                    enviarRespuesta(intercambio, 405, "Metodo no permitido");
                    return;
                }
                try {
                    Map<String, String> datos = leerFormulario(intercambio);
                    String nombre = datos.get("nombre");
                    String respuesta = Ordenados.Vendedor.ListarUnoWeb(nombre);
                    enviarRespuesta(intercambio, 200, respuesta);
                } catch (Exception e) {
                    enviarRespuesta(intercambio, 400, "Error: " + e.getMessage());
                }
            });

            Servidor_2.configurar(servidor);
            Servidor_3.configurar(servidor);
            Servidor_4.configurar(servidor);
            servidor.start();
            System.out.println("Servidor iniciado en el puerto " + puerto);
        } catch (Exception e) {
            System.out.println("Error al iniciar el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Map<String, String> leerFormulario(HttpExchange intercambio) throws IOException {
        byte[] cuerpo = intercambio.getRequestBody().readAllBytes();
        String formulario = new String(cuerpo, StandardCharsets.UTF_8);
        Map<String, String> datos = new HashMap<>();
        if (formulario.isEmpty()) {
            return datos;
        }
        String[] parametros = formulario.split("&");
        for (String parametro : parametros) {
            String[] partes = parametro.split("=", 2);
            if (partes.length == 2) {
                String clave = URLDecoder.decode(partes[0], StandardCharsets.UTF_8);
                String valor = URLDecoder.decode(partes[1], StandardCharsets.UTF_8);
                datos.put(clave, valor);
            }
        }
        return datos;
    }

    public static void enviarRespuesta(HttpExchange intercambio, int codigo, String respuesta) throws IOException {
        byte[] bytes = respuesta.getBytes(StandardCharsets.UTF_8);
        intercambio.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        intercambio.sendResponseHeaders(codigo, bytes.length);
        try (OutputStream salida = intercambio.getResponseBody()) {
            salida.write(bytes);
        }
    }

    public static void agregarCORS(HttpExchange intercambio) {
        intercambio.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        intercambio.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        intercambio.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
    }

    public static boolean esOPTIONS(HttpExchange intercambio) throws IOException {
        if (intercambio.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            intercambio.sendResponseHeaders(204, -1);
            intercambio.close();
            return true;
        }
        return false;
    }
}
