package Desordenados;

import javax.swing.*;

public class Alumno {

    // =====================================================
    // VARIABLES GLOBALES PARA LA WEB
    // =====================================================

    static int tam = 0;

    static N_Alumno V[] = null;

    static int N = -1;


    // =====================================================
    // MAIN - PROGRAMA DE ESCRITORIO
    // =====================================================

    public static void main(String[] args) {

        int tamLocal =
                Integer.parseInt(
                        JOptionPane.showInputDialog(
                                "Ing. el tam. del arreglo"
                        )
                );

        N_Alumno VLocal[] =
                new N_Alumno[tamLocal];

        int NLocal = -1;

        int opc;


        do {

            opc =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(

                                    "Menu de Opciones\n" +

                                    "1. Dar de alta\n" +

                                    "2. Dar de baja\n" +

                                    "3. Modificar\n" +

                                    "4. Listar un alumno\n" +

                                    "5. Listar todos los registros\n" +

                                    "6. Salir"
                            )
                    );


            switch (opc) {

                case 1:

                    NLocal =
                            InsertaD(
                                    VLocal,
                                    NLocal,
                                    tamLocal
                            );

                    break;


                case 2:

                    NLocal =
                            EliminaD(
                                    VLocal,
                                    NLocal
                            );

                    break;


                case 3:

                    ModificaD(
                            VLocal,
                            NLocal
                    );

                    break;


                case 4:

                    ImprimirUno(
                            VLocal,
                            NLocal
                    );

                    break;


                case 5:

                    Imprimir(
                            VLocal,
                            NLocal
                    );

                    break;


                case 6:

                    break;


                default:

                    JOptionPane.showMessageDialog(
                            null,
                            "Opcion Invalida"
                    );
            }


        } while (opc != 6);

    } // fin main



    // =====================================================
    // 1. INSERTAR DESORDENADO
    // =====================================================

    public static int InsertaD(
            N_Alumno V[],
            int N,
            int tam) {

        if (N < (tam - 1)) {

            N++;


            String nombre =
                    JOptionPane.showInputDialog(
                            "Ing. el nombre completo"
                    );


            int semestre =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    "Ing. numero de semestres cursados"
                            )
                    );


            float promedio =
                    Float.parseFloat(
                            JOptionPane.showInputDialog(
                                    "Ing. calificacion promedio total"
                            )
                    );


            V[N] = new N_Alumno();


            V[N].SetAsignar(
                    nombre,
                    semestre,
                    promedio
            );


        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "No hay espacio"
            );
        }


        return N;

    }



    // =====================================================
    // 2. ELIMINAR DESORDENADO
    // =====================================================

    public static int EliminaD(
            N_Alumno V[],
            int N) {


        if (N == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "No hay alumnos registrados"
            );

            return N;
        }


        String nomB =
                JOptionPane.showInputDialog(
                        "Ing. el nombre del alumno a eliminar"
                );


        int i = 0;


        while (
                (i <= N) &&
                (!nomB.equalsIgnoreCase(
                        V[i].GetNombre()))
        ) {

            i++;
        }


        if (i > N) {

            JOptionPane.showMessageDialog(
                    null,
                    "El alumno "
                            + nomB
                            + " no fue encontrado"
            );

        } else {


            for (int k = i; k < N; k++) {

                V[k] = V[k + 1];
            }


            V[N] = null;

            N--;


            JOptionPane.showMessageDialog(
                    null,
                    "Alumno eliminado correctamente"
            );
        }


        return N;

    }



    // =====================================================
    // 3. MODIFICAR
    // =====================================================

    public static void ModificaD(
            N_Alumno V[],
            int N) {


        if (N == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "No hay alumnos registrados"
            );

            return;
        }


        String nomB =
                JOptionPane.showInputDialog(
                        "Ing. el nombre del alumno a modificar"
                );


        int i = 0;


        while (
                (i <= N) &&
                (!nomB.equalsIgnoreCase(
                        V[i].GetNombre()))
        ) {

            i++;
        }


        if (i > N) {

            JOptionPane.showMessageDialog(
                    null,
                    "El alumno "
                            + nomB
                            + " no fue encontrado"
            );

        } else {


            int semestre =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    "Ing. nuevo numero de semestres"
                            )
                    );


            float promedio =
                    Float.parseFloat(
                            JOptionPane.showInputDialog(
                                    "Ing. nuevo promedio total"
                            )
                    );


            V[i].SetSemestre(
                    semestre
            );


            V[i].SetPromedio(
                    promedio
            );


            JOptionPane.showMessageDialog(
                    null,
                    "Alumno modificado correctamente"
            );
        }

    }



    // =====================================================
    // 4. LISTAR UN ALUMNO
    // =====================================================

    public static void ImprimirUno(
            N_Alumno V[],
            int N) {


        if (N == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "No hay alumnos registrados"
            );

            return;
        }


        String nomB =
                JOptionPane.showInputDialog(
                        "Ing. el nombre del alumno"
                );


        int i = 0;


        while (
                (i <= N) &&
                (!nomB.equalsIgnoreCase(
                        V[i].GetNombre()))
        ) {

            i++;
        }


        if (i > N) {

            JOptionPane.showMessageDialog(
                    null,
                    "El alumno "
                            + nomB
                            + " no fue encontrado"
            );

        } else {


            String tabla =
                    "Nombre\tSemestre\tPromedio\n";


            tabla +=
                    V[i].toString();


            JTextArea imp =
                    new JTextArea();


            imp.setText(
                    tabla
            );


            imp.setEditable(
                    false
            );


            JOptionPane.showMessageDialog(
                    null,
                    imp
            );
        }

    }



    // =====================================================
    // 5. LISTAR TODOS
    // =====================================================

    public static void Imprimir(
            N_Alumno V[],
            int N) {


        if (N == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "No hay alumnos registrados"
            );

            return;
        }


        String tabla =
                "Nombre\tSemestre\tPromedio\n";


        for (int i = 0; i <= N; i++) {

            tabla +=
                    V[i].toString();
        }


        JTextArea imp =
                new JTextArea();


        imp.setText(
                tabla
        );


        imp.setEditable(
                false
        );


        JOptionPane.showMessageDialog(
                null,
                imp
        );

    }



    // =====================================================
    // =====================================================
    //
    //              MÉTODOS PARA LA WEB
    //
    // =====================================================
    // =====================================================



    // =====================================================
    // INICIALIZAR ARREGLO WEB
    // =====================================================

    public static String InicializarWeb(
            int nuevoTam) {


        if (nuevoTam <= 0) {

            return
                    "El tamaño del arreglo debe ser mayor que 0";
        }


        tam =
                nuevoTam;


        V =
                new N_Alumno[tam];


        N =
                -1;


        return
                "Arreglo creado correctamente con capacidad para "
                + tam
                + " alumnos";

    }



    // =====================================================
    // ALTA WEB
    // =====================================================

    public static String AltaWeb(
            String nombre,
            int semestre,
            float promedio) {


        if (V == null) {

            return
                    "Primero debe iniciar el arreglo";
        }


        if (N >= tam - 1) {

            return
                    "No hay espacio en el arreglo";
        }


        N++;


        V[N] =
                new N_Alumno();


        V[N].SetAsignar(
                nombre,
                semestre,
                promedio
        );


        return
                "Alumno registrado correctamente";

    }



    // =====================================================
    // BAJA WEB
    // =====================================================

    public static String BajaWeb(
            String nombre) {


        if (V == null) {

            return
                    "Primero debe iniciar el arreglo";
        }


        if (N == -1) {

            return
                    "No hay alumnos registrados";
        }


        int i =
                0;


        while (
                (i <= N) &&
                (!nombre.equalsIgnoreCase(
                        V[i].GetNombre()))
        ) {

            i++;
        }


        if (i > N) {

            return
                    "El alumno "
                    + nombre
                    + " no fue encontrado";
        }


        for (
                int k = i;
                k < N;
                k++
        ) {

            V[k] =
                    V[k + 1];
        }


        V[N] =
                null;


        N--;


        return
                "Alumno eliminado correctamente";

    }



    // =====================================================
    // MODIFICAR WEB
    // =====================================================

    public static String ModificarWeb(
            String nombre,
            int semestre,
            float promedio) {


        if (V == null) {

            return
                    "Primero debe iniciar el arreglo";
        }


        if (N == -1) {

            return
                    "No hay alumnos registrados";
        }


        int i =
                0;


        while (
                (i <= N) &&
                (!nombre.equalsIgnoreCase(
                        V[i].GetNombre()))
        ) {

            i++;
        }


        if (i > N) {

            return
                    "El alumno "
                    + nombre
                    + " no fue encontrado";
        }


        V[i].SetSemestre(
                semestre
        );


        V[i].SetPromedio(
                promedio
        );


        return
                "Alumno modificado correctamente";

    }



    // =====================================================
    // LISTAR UNO WEB
    // =====================================================

    public static String ListarUnoWeb(
            String nombre) {


        if (V == null) {

            return
                    "Primero debe iniciar el arreglo";
        }


        if (N == -1) {

            return
                    "No hay alumnos registrados";
        }


        int i =
                0;


        while (
                (i <= N) &&
                (!nombre.equalsIgnoreCase(
                        V[i].GetNombre()))
        ) {

            i++;
        }


        if (i > N) {

            return
                    "El alumno "
                    + nombre
                    + " no fue encontrado";
        }


        return
                "Nombre: "
                + V[i].GetNombre()

                + "\nSemestre: "
                + V[i].GetSemestre()

                + "\nPromedio: "
                + V[i].GetPromedio();

    }



    // =====================================================
    // LISTAR TODOS WEB
    // =====================================================

    public static String ListarTodosWeb() {


        if (V == null) {

            return
                    "Primero debe iniciar el arreglo";
        }


        if (N == -1) {

            return
                    "No hay alumnos registrados";
        }


        String tabla =
                "";


        for (
                int i = 0;
                i <= N;
                i++
        ) {


            tabla +=

                    "Nombre: "
                    + V[i].GetNombre()

                    + "\nSemestre: "
                    + V[i].GetSemestre()

                    + "\nPromedio: "
                    + V[i].GetPromedio()

                    + "\n-----------------------------\n";

        }


        return
                tabla;

    }


} // fin clase Alumno