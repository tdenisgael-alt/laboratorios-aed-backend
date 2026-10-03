package Desordenados;

// ---------------------------------------------------------
// IMPORTAMOS SWING
// ---------------------------------------------------------
//
// JOptionPane será utilizado para probar el ejercicio
// directamente desde Java.
//
// JTextArea será utilizado para mostrar los registros
// de una forma más ordenada.
//

import javax.swing.*;


// ---------------------------------------------------------
// CLASE CLIENTE
// ---------------------------------------------------------

public class Cliente {


    // =====================================================
    // VARIABLES GLOBALES
    // =====================================================
    //
    // Estas variables también serán utilizadas posteriormente
    // por los métodos destinados a la página web.
    // =====================================================


    // Tamaño máximo del arreglo.
    //
    // Comienza en 0 porque posteriormente el usuario
    // indicará cuántos clientes desea almacenar.
    static int tam = 0;


    // Arreglo donde almacenaremos objetos N_Cliente.
    //
    // Inicialmente no existe hasta que se indique
    // el tamaño del arreglo.
    static N_Cliente V[] = null;


    // N representa la última posición ocupada.
    //
    // N = -1 significa que el arreglo está vacío.
    //
    // Primer cliente:
    // N = 0
    //
    // Segundo cliente:
    // N = 1
    //
    // etc.
    static int N = -1;



    // =====================================================
    // MÉTODO MAIN
    // =====================================================
    //
    // Este método permite ejecutar el ejercicio directamente
    // desde Eclipse utilizando JOptionPane.
    //
    // La página web utilizará los métodos Web que aparecen
    // posteriormente.
    // =====================================================

    public static void main(String[] args) {


        // -------------------------------------------------
        // PEDIR TAMAÑO DEL ARREGLO
        // -------------------------------------------------

        int tamLocal =
                Integer.parseInt(

                        JOptionPane.showInputDialog(
                                "Ingrese el tamaño del arreglo"
                        )
                );


        // -------------------------------------------------
        // CREAR ARREGLO
        // -------------------------------------------------

        N_Cliente VLocal[] =
                new N_Cliente[tamLocal];


        // -------------------------------------------------
        // POSICIÓN INICIAL
        // -------------------------------------------------
        //
        // -1 significa que todavía no tenemos clientes.
        //

        int NLocal = -1;


        // Variable para guardar la opción del menú.
        int opc;



        // =================================================
        // MENÚ PRINCIPAL
        // =================================================

        do {


            opc =
                    Integer.parseInt(

                            JOptionPane.showInputDialog(

                                    "MENU DE CLIENTES\n\n"

                                    + "1. Dar de alta a un cliente\n"

                                    + "2. Modificar estado moroso\n"

                                    + "3. Dar de baja a un cliente\n"

                                    + "4. Listar un cliente\n"

                                    + "5. Listar todos los clientes\n"

                                    + "6. Salir"
                            )
                    );



            // -------------------------------------------------
            // EVALUAR OPCIÓN
            // -------------------------------------------------

            switch (opc) {


                // =============================================
                // 1. DAR DE ALTA
                // =============================================

                case 1:


                    NLocal =
                            InsertaD(
                                    VLocal,
                                    NLocal,
                                    tamLocal
                            );


                    break;



                // =============================================
                // 2. MODIFICAR ESTADO MOROSO
                // =============================================

                case 2:


                    ModificaMorosoD(
                            VLocal,
                            NLocal
                    );


                    break;



                // =============================================
                // 3. DAR DE BAJA
                // =============================================

                case 3:


                    NLocal =
                            EliminaD(
                                    VLocal,
                                    NLocal
                            );


                    break;



                // =============================================
                // 4. LISTAR CLIENTE DETERMINADO
                // =============================================

                case 4:


                    ImprimirUno(
                            VLocal,
                            NLocal
                    );


                    break;



                // =============================================
                // 5. LISTAR TODOS
                // =============================================

                case 5:


                    Imprimir(
                            VLocal,
                            NLocal
                    );


                    break;



                // =============================================
                // 6. SALIR
                // =============================================

                case 6:


                    JOptionPane.showMessageDialog(
                            null,
                            "Saliendo del programa..."
                    );


                    break;



                // =============================================
                // OPCIÓN INCORRECTA
                // =============================================

                default:


                    JOptionPane.showMessageDialog(
                            null,
                            "Opcion invalida"
                    );

            }


        } while (opc != 6);


    } // fin main



    // =====================================================
    // 1. INSERTAR CLIENTE
    // =====================================================
    //
    // Como trabajamos con un arreglo DESORDENADO,
    // el nuevo cliente se coloca directamente después
    // del último registro.
    // =====================================================

    public static int InsertaD(
            N_Cliente V[],
            int N,
            int tam) {


        // -------------------------------------------------
        // COMPROBAR SI HAY ESPACIO
        // -------------------------------------------------

        if (N < (tam - 1)) {


            // Avanzamos a la siguiente posición.
            N++;


            // -------------------------------------------------
            // PEDIR NOMBRE
            // -------------------------------------------------

            String nombre =
                    JOptionPane.showInputDialog(
                            "Ingrese el nombre del cliente"
                    );


            // -------------------------------------------------
            // PEDIR TELÉFONO
            // -------------------------------------------------

            String telefono =
                    JOptionPane.showInputDialog(
                            "Ingrese el telefono"
                    );


            // -------------------------------------------------
            // PEDIR SALDO
            // -------------------------------------------------

            float saldo =
                    Float.parseFloat(

                            JOptionPane.showInputDialog(
                                    "Ingrese el saldo"
                            )
                    );


            // -------------------------------------------------
            // PEDIR ESTADO MOROSO
            // -------------------------------------------------

            int opcionMoroso =
                    JOptionPane.showConfirmDialog(

                            null,

                            "¿El cliente es moroso?",

                            "Estado moroso",

                            JOptionPane.YES_NO_OPTION
                    );


            // Si selecciona Sí -> true.
            // Si selecciona No -> false.

            boolean moroso =
                    opcionMoroso
                            == JOptionPane.YES_OPTION;


            // -------------------------------------------------
            // CREAR OBJETO
            // -------------------------------------------------

            V[N] =
                    new N_Cliente();


            // -------------------------------------------------
            // ASIGNAR DATOS
            // -------------------------------------------------

            V[N].SetAsignar(

                    nombre,
                    telefono,
                    saldo,
                    moroso
            );


            JOptionPane.showMessageDialog(
                    null,
                    "Cliente registrado correctamente"
            );


        } else {


            // Si N ya llegó a tam - 1,
            // significa que el arreglo está lleno.

            JOptionPane.showMessageDialog(
                    null,
                    "No hay espacio en el arreglo"
            );

        }


        return N;

    } // fin InsertaD



    // =====================================================
    // 2. MODIFICAR ESTADO MOROSO
    // =====================================================

    public static void ModificaMorosoD(
            N_Cliente V[],
            int N) {


        // -------------------------------------------------
        // VERIFICAR SI ESTÁ VACÍO
        // -------------------------------------------------

        if (N == -1) {


            JOptionPane.showMessageDialog(
                    null,
                    "No hay clientes registrados"
            );


            return;
        }


        // -------------------------------------------------
        // PEDIR CLIENTE
        // -------------------------------------------------

        String nombre =
                JOptionPane.showInputDialog(
                        "Ingrese el nombre del cliente"
                );


        // -------------------------------------------------
        // BÚSQUEDA SECUENCIAL
        // -------------------------------------------------

        int i = 0;


        while (
                (i <= N)
                &&
                (!nombre.equalsIgnoreCase(
                        V[i].GetNombre()))
        ) {


            i++;

        }


        // -------------------------------------------------
        // CLIENTE NO ENCONTRADO
        // -------------------------------------------------

        if (i > N) {


            JOptionPane.showMessageDialog(
                    null,
                    "El cliente "
                            + nombre
                            + " no fue encontrado"
            );


        } else {


            // -------------------------------------------------
            // PEDIR NUEVO ESTADO
            // -------------------------------------------------

            int opcion =
                    JOptionPane.showConfirmDialog(

                            null,

                            "¿El cliente es moroso?",

                            "Modificar estado",

                            JOptionPane.YES_NO_OPTION
                    );


            boolean nuevoEstado =
                    opcion
                            == JOptionPane.YES_OPTION;


            // -------------------------------------------------
            // MODIFICAR
            // -------------------------------------------------

            V[i].SetMoroso(
                    nuevoEstado
            );


            JOptionPane.showMessageDialog(
                    null,
                    "Estado moroso modificado correctamente"
            );

        }


    } // fin ModificaMorosoD



    // =====================================================
    // 3. ELIMINAR CLIENTE
    // =====================================================

    public static int EliminaD(
            N_Cliente V[],
            int N) {


        // -------------------------------------------------
        // COMPROBAR ARREGLO VACÍO
        // -------------------------------------------------

        if (N == -1) {


            JOptionPane.showMessageDialog(
                    null,
                    "No hay clientes registrados"
            );


            return N;

        }


        // -------------------------------------------------
        // PEDIR NOMBRE
        // -------------------------------------------------

        String nombre =
                JOptionPane.showInputDialog(
                        "Ingrese el nombre del cliente a eliminar"
                );


        // -------------------------------------------------
        // BUSCAR CLIENTE
        // -------------------------------------------------

        int i = 0;


        while (
                (i <= N)
                &&
                (!nombre.equalsIgnoreCase(
                        V[i].GetNombre()))
        ) {


            i++;

        }


        // -------------------------------------------------
        // NO ENCONTRADO
        // -------------------------------------------------

        if (i > N) {


            JOptionPane.showMessageDialog(
                    null,
                    "El cliente "
                            + nombre
                            + " no fue encontrado"
            );


        } else {


            // -------------------------------------------------
            // DESPLAZAR ELEMENTOS
            // -------------------------------------------------
            //
            // Como eliminamos una posición,
            // movemos todos los registros posteriores
            // una posición hacia la izquierda.
            //

            for (
                    int k = i;
                    k < N;
                    k++
            ) {


                V[k] =
                        V[k + 1];

            }


            // Limpiamos la última posición.

            V[N] = null;


            // Disminuimos N.

            N--;


            JOptionPane.showMessageDialog(
                    null,
                    "Cliente eliminado correctamente"
            );

        }


        return N;

    } // fin EliminaD



    // =====================================================
    // 4. IMPRIMIR UN CLIENTE
    // =====================================================

    public static void ImprimirUno(
            N_Cliente V[],
            int N) {


        // -------------------------------------------------
        // VERIFICAR SI ESTÁ VACÍO
        // -------------------------------------------------

        if (N == -1) {


            JOptionPane.showMessageDialog(
                    null,
                    "No hay clientes registrados"
            );


            return;

        }


        // -------------------------------------------------
        // PEDIR NOMBRE
        // -------------------------------------------------

        String nombre =
                JOptionPane.showInputDialog(
                        "Ingrese el nombre del cliente"
                );


        // -------------------------------------------------
        // BUSCAR
        // -------------------------------------------------

        int i = 0;


        while (
                (i <= N)
                &&
                (!nombre.equalsIgnoreCase(
                        V[i].GetNombre()))
        ) {


            i++;

        }


        // -------------------------------------------------
        // NO ENCONTRADO
        // -------------------------------------------------

        if (i > N) {


            JOptionPane.showMessageDialog(
                    null,
                    "El cliente "
                            + nombre
                            + " no fue encontrado"
            );


        } else {


            // -------------------------------------------------
            // PREPARAR INFORMACIÓN
            // -------------------------------------------------

            String informacion =

                    "Nombre: "
                    + V[i].GetNombre()

                    + "\nTelefono: "
                    + V[i].GetTelefono()

                    + "\nSaldo: "
                    + V[i].GetSaldo()

                    + "\nMoroso: "
                    + (
                            V[i].GetMoroso()
                            ? "Si"
                            : "No"
                    );


            // -------------------------------------------------
            // MOSTRAR
            // -------------------------------------------------

            JOptionPane.showMessageDialog(
                    null,
                    informacion
            );

        }


    } // fin ImprimirUno



    // =====================================================
    // 5. IMPRIMIR TODOS
    // =====================================================

    public static void Imprimir(
            N_Cliente V[],
            int N) {


        // -------------------------------------------------
        // VERIFICAR ARREGLO VACÍO
        // -------------------------------------------------

        if (N == -1) {


            JOptionPane.showMessageDialog(
                    null,
                    "No hay clientes registrados"
            );


            return;

        }


        // -------------------------------------------------
        // CREAR TABLA
        // -------------------------------------------------

        String tabla =

                "Nombre\tTelefono\tSaldo\tMoroso\n";


        // -------------------------------------------------
        // RECORRER ARREGLO
        // -------------------------------------------------

        for (
                int i = 0;
                i <= N;
                i++
        ) {


            tabla +=
                    V[i].toString();

        }


        // -------------------------------------------------
        // MOSTRAR TABLA
        // -------------------------------------------------

        JTextArea area =
                new JTextArea();


        area.setText(
                tabla
        );


        area.setEditable(
                false
        );


        JOptionPane.showMessageDialog(
                null,
                area
        );


    } // fin Imprimir



    // =====================================================
    // =====================================================
    //
    //               MÉTODOS PARA LA WEB
    //
    // =====================================================
    // =====================================================



    // =====================================================
    // INICIALIZAR ARREGLO WEB
    // =====================================================

    public static String InicializarWeb(
            int nuevoTam) {


        // El tamaño debe ser mayor que cero.

        if (nuevoTam <= 0) {


            return
                    "El tamaño debe ser mayor que 0";

        }


        // Guardamos el nuevo tamaño.

        tam =
                nuevoTam;


        // Creamos el arreglo.

        V =
                new N_Cliente[tam];


        // Reiniciamos N.

        N =
                -1;


        return
                "Arreglo creado correctamente con capacidad para "
                + tam
                + " clientes";

    }



    // =====================================================
    // ALTA WEB
    // =====================================================

    public static String AltaWeb(

            String nombre,
            String telefono,
            float saldo,
            boolean moroso) {


        // Primero debe existir el arreglo.

        if (V == null) {


            return
                    "Primero debe iniciar el arreglo";

        }


        // Comprobar espacio.

        if (N >= tam - 1) {


            return
                    "No hay espacio en el arreglo";

        }


        // Avanzar posición.

        N++;


        // Crear objeto.

        V[N] =
                new N_Cliente();


        // Asignar información.

        V[N].SetAsignar(

                nombre,
                telefono,
                saldo,
                moroso
        );


        return
                "Cliente registrado correctamente";

    }



    // =====================================================
    // MODIFICAR MOROSO WEB
    // =====================================================

    public static String ModificarWeb(

            String nombre,
            boolean moroso) {


        // Verificar inicialización.

        if (V == null) {


            return
                    "Primero debe iniciar el arreglo";

        }


        // Verificar registros.

        if (N == -1) {


            return
                    "No hay clientes registrados";

        }


        // Buscar desde posición cero.

        int i =
                0;


        while (
                (i <= N)
                &&
                (!nombre.equalsIgnoreCase(
                        V[i].GetNombre()))
        ) {


            i++;

        }


        // Si no existe.

        if (i > N) {


            return
                    "El cliente "
                    + nombre
                    + " no fue encontrado";

        }


        // Modificar únicamente moroso.

        V[i].SetMoroso(
                moroso
        );


        return
                "Estado moroso modificado correctamente";

    }



    // =====================================================
    // BAJA WEB
    // =====================================================

    public static String BajaWeb(
            String nombre) {


        // Verificar arreglo.

        if (V == null) {


            return
                    "Primero debe iniciar el arreglo";

        }


        // Verificar registros.

        if (N == -1) {


            return
                    "No hay clientes registrados";

        }


        // Buscar.

        int i =
                0;


        while (
                (i <= N)
                &&
                (!nombre.equalsIgnoreCase(
                        V[i].GetNombre()))
        ) {


            i++;

        }


        // No encontrado.

        if (i > N) {


            return
                    "El cliente "
                    + nombre
                    + " no fue encontrado";

        }


        // Desplazar elementos.

        for (
                int k = i;
                k < N;
                k++
        ) {


            V[k] =
                    V[k + 1];

        }


        // Limpiar última posición.

        V[N] =
                null;


        // Reducir N.

        N--;


        return
                "Cliente eliminado correctamente";

    }



    // =====================================================
    // LISTAR UNO WEB
    // =====================================================

    public static String ListarUnoWeb(
            String nombre) {


        // Verificar arreglo.

        if (V == null) {


            return
                    "Primero debe iniciar el arreglo";

        }


        // Verificar registros.

        if (N == -1) {


            return
                    "No hay clientes registrados";

        }


        // Buscar.

        int i =
                0;


        while (
                (i <= N)
                &&
                (!nombre.equalsIgnoreCase(
                        V[i].GetNombre()))
        ) {


            i++;

        }


        // No encontrado.

        if (i > N) {


            return
                    "El cliente "
                    + nombre
                    + " no fue encontrado";

        }


        // Retornar información completa.

        return

                "Nombre: "
                + V[i].GetNombre()

                + "\nTelefono: "
                + V[i].GetTelefono()

                + "\nSaldo: "
                + V[i].GetSaldo()

                + "\nMoroso: "
                + (
                        V[i].GetMoroso()
                        ? "Si"
                        : "No"
                );

    }



    // =====================================================
    // LISTAR TODOS WEB
    // =====================================================

    public static String ListarTodosWeb() {


        // Verificar arreglo.

        if (V == null) {


            return
                    "Primero debe iniciar el arreglo";

        }


        // Verificar registros.

        if (N == -1) {


            return
                    "No hay clientes registrados";

        }


        // Variable donde construiremos la respuesta.

        String lista =
                "";


        // Recorrer todos los clientes.

        for (
                int i = 0;
                i <= N;
                i++
        ) {


            lista +=

                    "Nombre: "
                    + V[i].GetNombre()

                    + "\nTelefono: "
                    + V[i].GetTelefono()

                    + "\nSaldo: "
                    + V[i].GetSaldo()

                    + "\nMoroso: "
                    + (
                            V[i].GetMoroso()
                            ? "Si"
                            : "No"
                    )

                    + "\n-----------------------------\n";

        }


        // Devolver información al servidor.

        return lista;

    }


} // fin clase Cliente