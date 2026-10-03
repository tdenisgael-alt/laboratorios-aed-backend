package Desordenados;

import javax.swing.*;

// -----------------------------------------------------
// CLASE EMPLEADO
// -----------------------------------------------------
//
// Maneja un arreglo DESORDENADO de objetos N_Empleado.
//
// Operaciones:
// 1. Alta
// 2. Baja
// 3. Modificar edad
// 4. Listar empleados varones
// 5. Listar un empleado
// 6. Salir
//
// -----------------------------------------------------

public class Empleado {

    // -------------------------------------------------
    // VARIABLES GLOBALES
    // -------------------------------------------------

    // Tamaño del arreglo.
    static int tam = 100;

    // Arreglo donde se almacenan los empleados.
    static N_Empleado V[] = new N_Empleado[tam];

    // Última posición ocupada.
    // -1 significa que el arreglo está vacío.
    static int N = -1;


    // =================================================
    // MAIN
    // =================================================

    public static void main(String[] args) {

        int opc;

        do {

            opc = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "MENÚ DE EMPLEADOS\n\n"
                            + "1. Dar de alta\n"
                            + "2. Dar de baja\n"
                            + "3. Actualizar edad\n"
                            + "4. Imprimir empleados varones\n"
                            + "5. Imprimir un empleado\n"
                            + "6. Salir"
                    )
            );

            switch (opc) {

                // -------------------------------------
                // ALTA
                // -------------------------------------

                case 1:

                    String nombre =
                            JOptionPane.showInputDialog(
                                    "Ingrese el nombre");

                    String entradaSexo =
                            JOptionPane.showInputDialog(
                                    "Ingrese sexo (M/F)");

                    char sexo =
                            entradaSexo.toUpperCase().charAt(0);

                    int edad =
                            Integer.parseInt(
                                    JOptionPane.showInputDialog(
                                            "Ingrese la edad"));

                    N = InsertaD(
                            V,
                            N,
                            tam,
                            nombre,
                            sexo,
                            edad);

                    break;


                // -------------------------------------
                // BAJA
                // -------------------------------------

                case 2:

                    N = EliminaD(V, N);

                    break;


                // -------------------------------------
                // MODIFICAR EDAD
                // -------------------------------------

                case 3:

                    ModificaD(V, N);

                    break;


                // -------------------------------------
                // LISTAR VARONES
                // -------------------------------------

                case 4:

                    ImprimirVarones(V, N);

                    break;


                // -------------------------------------
                // LISTAR UNO
                // -------------------------------------

                case 5:

                    ImprimirUno(V, N);

                    break;


                // -------------------------------------
                // SALIR
                // -------------------------------------

                case 6:

                    break;


                default:

                    JOptionPane.showMessageDialog(
                            null,
                            "Opción inválida");
            }

        } while (opc != 6);

    } // fin main


    // =================================================
    // INSERTAR DESORDENADO
    // =================================================

    public static int InsertaD(
            N_Empleado V[],
            int N,
            int tam,
            String nombre,
            char sexo,
            int edad) {

        // Comprobar si existe espacio.
        if (N < (tam - 1)) {

            // Avanzamos una posición.
            N++;

            // Creamos el empleado.
            V[N] = new N_Empleado();

            // Asignamos los datos.
            V[N].SetAsignar(
                    nombre,
                    sexo,
                    edad);

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "No hay espacio");
        }

        return N;

    } // fin InsertaD


    // =================================================
    // ELIMINAR DESORDENADO
    // =================================================

    public static int EliminaD(
            N_Empleado V[],
            int N) {

        String nomB;

        int i;
        int k;

        // Nombre que queremos eliminar.
        nomB =
                JOptionPane.showInputDialog(
                        "Ingrese el nombre del empleado a eliminar");

        // Empezamos desde cero.
        i = 0;

        // Búsqueda secuencial.
        while ((i <= N)
                && (!nomB.equalsIgnoreCase(
                        V[i].GetNombre()))) {

            i++;
        }


        // No encontrado.
        if (i > N) {

            JOptionPane.showMessageDialog(
                    null,
                    "El empleado "
                    + nomB
                    + " no fue encontrado");

        } else {

            // Desplazamos los elementos
            // hacia la izquierda.
            for (k = i; k < N; k++) {

                V[k] = V[k + 1];
            }

            // Reducimos la última posición.
            N--;
        }

        return N;

    } // fin EliminaD


    // =================================================
    // MODIFICAR EDAD
    // =================================================

    public static void ModificaD(
            N_Empleado V[],
            int N) {

        String nomB;

        int i;
        int edad;

        // Pedimos empleado.
        nomB =
                JOptionPane.showInputDialog(
                        "Ingrese el nombre del empleado");

        i = 0;


        // Búsqueda secuencial.
        while ((i <= N)
                && (!nomB.equalsIgnoreCase(
                        V[i].GetNombre()))) {

            i++;
        }


        // No encontrado.
        if (i > N) {

            JOptionPane.showMessageDialog(
                    null,
                    "El empleado "
                    + nomB
                    + " no fue encontrado");

        } else {

            // Solamente modificamos la edad,
            // tal como pide el ejercicio.

            edad =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    "Ingrese la nueva edad"));

            V[i].SetEdad(edad);

            JOptionPane.showMessageDialog(
                    null,
                    "Edad actualizada correctamente");
        }

    } // fin ModificaD


    // =================================================
    // IMPRIMIR EMPLEADOS VARONES
    // =================================================

    public static void ImprimirVarones(
            N_Empleado V[],
            int N) {

        String tabla =
                "Nombre\tSexo\tEdad\n";

        boolean encontrado = false;


        // Recorremos todo el arreglo.
        for (int i = 0; i <= N; i++) {

            // Solamente agregamos los empleados
            // cuyo sexo sea M.
            if (Character.toUpperCase(
                    V[i].GetSexo()) == 'M') {

                tabla += V[i].toString();

                encontrado = true;
            }
        }


        // Si encontramos por lo menos un varón.
        if (encontrado) {

            JTextArea imp =
                    new JTextArea();

            imp.setText(tabla);

            imp.setEditable(false);

            JOptionPane.showMessageDialog(
                    null,
                    imp);

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "No hay empleados varones registrados");
        }

    } // fin ImprimirVarones


    // =================================================
    // IMPRIMIR UN EMPLEADO
    // =================================================

    public static void ImprimirUno(
            N_Empleado V[],
            int N) {

        String nomB;
        String tabla;

        int i;


        // Nombre a buscar.
        nomB =
                JOptionPane.showInputDialog(
                        "Ingrese el nombre del empleado");


        i = 0;


        // Búsqueda secuencial.
        while ((i <= N)
                && (!nomB.equalsIgnoreCase(
                        V[i].GetNombre()))) {

            i++;
        }


        // No encontrado.
        if (i > N) {

            JOptionPane.showMessageDialog(
                    null,
                    "El empleado "
                    + nomB
                    + " no fue encontrado");

        } else {

            tabla =
                    "Nombre\tSexo\tEdad\n";

            tabla += V[i].toString();


            JTextArea imp =
                    new JTextArea();

            imp.setText(tabla);

            imp.setEditable(false);


            JOptionPane.showMessageDialog(
                    null,
                    imp);
        }

    } // fin ImprimirUno


    // =================================================
    // MÉTODOS PARA LA WEB
    // =================================================


    // -------------------------------------------------
    // INICIAR ARREGLO DESDE HTML
    // -------------------------------------------------

    public static String IniciarWeb(int tamaño) {

        if (tamaño <= 0) {

            return "El tamaño debe ser mayor que 0";
        }

        tam = tamaño;

        V = new N_Empleado[tam];

        N = -1;

        return "Arreglo iniciado con tamaño " + tam;
    }


    // -------------------------------------------------
    // ALTA DESDE HTML
    // -------------------------------------------------

    public static String AltaWeb(
            String nombre,
            char sexo,
            int edad) {

        // Comprobar espacio.
        if (N >= tam - 1) {

            return "No hay espacio";
        }


        // Validar sexo.
        sexo =
                Character.toUpperCase(sexo);

        if (sexo != 'M' && sexo != 'F') {

            return "Sexo inválido";
        }


        // Insertar.
        N++;

        V[N] =
                new N_Empleado(
                        nombre,
                        sexo,
                        edad);

        return "Empleado registrado correctamente";
    }


    // -------------------------------------------------
    // BAJA DESDE HTML
    // -------------------------------------------------

    public static String BajaWeb(
            String nombre) {

        if (N == -1) {

            return "El arreglo está vacío";
        }


        int i = 0;


        // Buscar empleado.
        while ((i <= N)
                && (!nombre.equalsIgnoreCase(
                        V[i].GetNombre()))) {

            i++;
        }


        if (i > N) {

            return "El empleado "
                    + nombre
                    + " no fue encontrado";
        }


        // Desplazamiento.
        for (int k = i; k < N; k++) {

            V[k] = V[k + 1];
        }


        // Limpiar última posición.
        V[N] = null;

        N--;


        return "Empleado eliminado correctamente";
    }


    // -------------------------------------------------
    // MODIFICAR EDAD DESDE HTML
    // -------------------------------------------------

    public static String ModificarWeb(
            String nombre,
            int nuevaEdad) {

        int i = 0;


        // Buscar empleado.
        while ((i <= N)
                && (!nombre.equalsIgnoreCase(
                        V[i].GetNombre()))) {

            i++;
        }


        if (i > N) {

            return "El empleado "
                    + nombre
                    + " no fue encontrado";
        }


        // ÚNICAMENTE modificamos la edad.
        V[i].SetEdad(nuevaEdad);


        return "Edad actualizada correctamente";
    }


    // -------------------------------------------------
    // LISTAR UN EMPLEADO DESDE HTML
    // -------------------------------------------------

    public static String ListarUnoWeb(
            String nombre) {

        int i = 0;


        // Buscar.
        while ((i <= N)
                && (!nombre.equalsIgnoreCase(
                        V[i].GetNombre()))) {

            i++;
        }


        if (i > N) {

            return "El empleado "
                    + nombre
                    + " no fue encontrado";
        }


        // Devolver datos.
        return "Nombre: "
                + V[i].GetNombre()
                + "\nSexo: "
                + V[i].GetSexo()
                + "\nEdad: "
                + V[i].GetEdad();
    }


    // -------------------------------------------------
    // LISTAR VARONES DESDE HTML
    // -------------------------------------------------

    public static String ListarVaronesWeb() {

        if (N == -1) {

            return "No hay empleados registrados";
        }


        String resultado = "";

        boolean encontrado = false;


        for (int i = 0; i <= N; i++) {

            if (Character.toUpperCase(
                    V[i].GetSexo()) == 'M') {

                resultado +=
                        V[i].GetNombre()
                        + "|"
                        + V[i].GetSexo()
                        + "|"
                        + V[i].GetEdad()
                        + "\n";

                encontrado = true;
            }
        }


        if (!encontrado) {

            return "No hay empleados varones registrados";
        }


        return resultado;
    }

 // =================================================
 // LISTAR TODOS LOS EMPLEADOS DESDE HTML
 // =================================================
 //
 // Este método devuelve TODOS los empleados,
 // sin importar si son hombres o mujeres.
 //
 // Se usará para mostrar una tabla general
 // en una nueva ventana del navegador.
 //

 public static String ListarTodosWeb() {

     // Si el arreglo está vacío.
     if (N == -1) {

         return "No hay empleados registrados";
     }


     // Variable donde acumularemos los registros.
     String resultado = "";


     // Recorremos todos los empleados almacenados.
     for (int i = 0; i <= N; i++) {

         resultado +=
                 V[i].GetNombre()
                 + "|"
                 + V[i].GetSexo()
                 + "|"
                 + V[i].GetEdad()
                 + "\n";
     }


     // Devolvemos todos los registros al servidor.
     return resultado;

 } // fin ListarTodosWeb
    
} // fin clase Empleado