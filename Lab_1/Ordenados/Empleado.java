package Ordenados;

public class Empleado {

    // =====================================================
    // VARIABLES GLOBALES
    // =====================================================

    // Tamaño máximo del arreglo
    static int tam = 0;

    // Arreglo de empleados
    static N_Empleado V[] = null;

    // Última posición ocupada
    // -1 significa que el arreglo está vacío
    static int N = -1;


    // =====================================================
    // INICIALIZAR ARREGLO
    // =====================================================

    public static String InicializarWeb(int nuevoTam) {

        // Validamos que el tamaño sea correcto
        if (nuevoTam <= 0) {
            return "El tamaño del arreglo debe ser mayor que 0";
        }

        // Guardamos el tamaño
        tam = nuevoTam;

        // Creamos el arreglo
        V = new N_Empleado[tam];

        // El arreglo comienza vacío
        N = -1;

        return "Arreglo creado correctamente con capacidad para "
                + tam + " empleados";
    }


    // =====================================================
    // BUSCA
    // =====================================================
    //
    // Este método sigue la lógica del algoritmo BUSCA
    // utilizado para arreglos ordenados.
    //
    // Busca el nombre dentro del arreglo.
    //
    // Si encuentra el empleado:
    // retorna su posición.
    //
    // Si no lo encuentra:
    // retorna -(posicion + 1).
    //
    // Usamos -(posicion + 1) porque en Java -0 es 0.
    // De esta manera podemos representar correctamente
    // que un elemento debería insertarse en la posición 0.
    //
    // Ejemplo:
    //
    // Posición 0 -> retorna -1
    // Posición 1 -> retorna -2
    // Posición 2 -> retorna -3
    //
    // =====================================================

    public static int Busca(
            N_Empleado V[],
            int N,
            String nombre) {

        int i = 0;

        // Avanzamos mientras:
        // 1. No superemos la última posición ocupada.
        // 2. El nombre almacenado sea menor
        //    al nombre que estamos buscando.
        while (
                (i <= N)
                &&
                (V[i]
                    .GetNombre()
                    .compareToIgnoreCase(nombre) < 0)
        ) {

            i++;
        }

        // Si llegamos al final
        // o el nombre encontrado es mayor,
        // significa que el empleado no existe.
        if (
                (i > N)
                ||
                (V[i]
                    .GetNombre()
                    .compareToIgnoreCase(nombre) > 0)
        ) {

            // Retornamos negativa la posición
            // donde debería insertarse.
            return -(i + 1);
        }

        // Si llegamos aquí,
        // encontramos exactamente el nombre.
        return i;
    }


    // =====================================================
    // OBTENER POSICIÓN DE INSERCIÓN
    // =====================================================
    //
    // Convierte el resultado negativo de Busca()
    // en la posición real donde debe insertarse.
    //
    // Ejemplo:
    //
    // Busca devuelve -1 -> posición 0
    // Busca devuelve -2 -> posición 1
    // Busca devuelve -3 -> posición 2
    //
    // =====================================================

    public static int ObtenerPosicionInsercion(int pos) {

        return (-pos) - 1;
    }


    // =====================================================
    // DAR DE ALTA
    // INSERTA ORDENADO
    // =====================================================

    public static String AltaWeb(
            String nombre,
            String direccion,
            int edad,
            char sexo,
            int antiguedad) {

        // Verificamos que el arreglo haya sido iniciado
        if (V == null) {
            return "Primero debe iniciar el arreglo";
        }

        // Verificamos si todavía existe espacio
        if (N >= tam - 1) {
            return "No hay espacio en el arreglo";
        }

        // Validamos nombre
        if (
                nombre == null
                ||
                nombre.trim().isEmpty()
        ) {

            return "El nombre no puede estar vacio";
        }

        // Quitamos espacios innecesarios
        nombre = nombre.trim();

        // Validamos dirección
        if (
                direccion == null
                ||
                direccion.trim().isEmpty()
        ) {

            return "La direccion no puede estar vacia";
        }

        direccion = direccion.trim();

        // Validamos edad
        if (edad <= 0) {
            return "La edad debe ser mayor que 0";
        }

        // Validamos antigüedad
        if (antiguedad < 0) {
            return "Los años de antiguedad no pueden ser negativos";
        }

        // Convertimos sexo a mayúscula
        sexo = Character.toUpperCase(sexo);

        // Validamos sexo
        if (sexo != 'M' && sexo != 'F') {
            return "Sexo invalido";
        }


        // -------------------------------------------------
        // LLAMAMOS AL MÉTODO BUSCA
        // -------------------------------------------------

        int pos = Busca(V, N, nombre);


        // -------------------------------------------------
        // SI POS ES MAYOR O IGUAL A 0
        // EL EMPLEADO YA EXISTE
        // -------------------------------------------------

        if (pos >= 0) {

            return "El empleado "
                    + nombre
                    + " ya se encuentra registrado";
        }


        // -------------------------------------------------
        // OBTENER POSICIÓN DONDE DEBE INSERTARSE
        // -------------------------------------------------

        pos = ObtenerPosicionInsercion(pos);


        // -------------------------------------------------
        // AUMENTAMOS N
        // -------------------------------------------------

        N++;


        // -------------------------------------------------
        // DESPLAZAMOS HACIA LA DERECHA
        // -------------------------------------------------
        //
        // Dejamos libre la posición donde debe
        // entrar el nuevo empleado.
        //
        // Ejemplo:
        //
        // Ana
        // Carlos
        // Mario
        //
        // Insertamos Denis:
        //
        // Ana
        // Carlos
        // Denis
        // Mario
        //
        // -------------------------------------------------

        for (int i = N; i > pos; i--) {

            V[i] = V[i - 1];
        }


        // -------------------------------------------------
        // INSERTAMOS EL NUEVO EMPLEADO
        // -------------------------------------------------

        V[pos] = new N_Empleado();

        V[pos].SetAsignar(
                nombre,
                direccion,
                edad,
                sexo,
                antiguedad
        );


        return "Empleado registrado correctamente";
    }


    // =====================================================
    // DAR DE BAJA
    // ELIMINA ORDENADO
    // =====================================================

    public static String BajaWeb(String nombre) {

        // Verificamos que se haya iniciado
        if (V == null) {
            return "Primero debe iniciar el arreglo";
        }

        // Verificamos que existan registros
        if (N == -1) {
            return "No hay empleados registrados";
        }

        // Validamos nombre
        if (
                nombre == null
                ||
                nombre.trim().isEmpty()
        ) {

            return "Ingrese el nombre del empleado";
        }

        nombre = nombre.trim();


        // -------------------------------------------------
        // BUSCAMOS EL EMPLEADO
        // -------------------------------------------------

        int pos = Busca(V, N, nombre);


        // -------------------------------------------------
        // SI POS ES NEGATIVO NO EXISTE
        // -------------------------------------------------

        if (pos < 0) {

            return "El empleado "
                    + nombre
                    + " no fue encontrado";
        }


        // -------------------------------------------------
        // DESPLAZAMOS HACIA LA IZQUIERDA
        // -------------------------------------------------

        for (int i = pos; i < N; i++) {

            V[i] = V[i + 1];
        }


        // Limpiamos la última posición
        V[N] = null;


        // Disminuimos N
        N--;


        return "Empleado eliminado correctamente";
    }


    // =====================================================
    // MODIFICAR AÑOS DE ANTIGÜEDAD
    // =====================================================
    //
    // En este ejercicio solamente modificamos
    // la antigüedad.
    //
    // El arreglo está ordenado por NOMBRE.
    //
    // Como la antigüedad no es el criterio
    // de ordenamiento, podemos modificarla
    // sin alterar el orden del arreglo.
    //
    // =====================================================

    public static String ModificarWeb(
            String nombre,
            int nuevaAntiguedad) {

        // Verificamos inicialización
        if (V == null) {
            return "Primero debe iniciar el arreglo";
        }

        // Verificamos registros
        if (N == -1) {
            return "No hay empleados registrados";
        }

        // Validamos nombre
        if (
                nombre == null
                ||
                nombre.trim().isEmpty()
        ) {

            return "Ingrese el nombre del empleado";
        }

        nombre = nombre.trim();


        // Validamos antigüedad
        if (nuevaAntiguedad < 0) {

            return "Los años de antiguedad no pueden ser negativos";
        }


        // -------------------------------------------------
        // BUSCAMOS EL EMPLEADO
        // -------------------------------------------------

        int pos = Busca(V, N, nombre);


        // Si no existe
        if (pos < 0) {

            return "El empleado "
                    + nombre
                    + " no fue encontrado";
        }


        // -------------------------------------------------
        // MODIFICAMOS ANTIGÜEDAD
        // -------------------------------------------------

        V[pos].SetAntiguedad(
                nuevaAntiguedad
        );


        return "Años de antiguedad modificados correctamente";
    }


    // =====================================================
    // LISTAR UN EMPLEADO
    // =====================================================

    public static String ListarUnoWeb(
            String nombre) {

        // Verificamos inicialización
        if (V == null) {
            return "Primero debe iniciar el arreglo";
        }

        // Verificamos registros
        if (N == -1) {
            return "No hay empleados registrados";
        }

        // Validamos nombre
        if (
                nombre == null
                ||
                nombre.trim().isEmpty()
        ) {

            return "Ingrese el nombre del empleado";
        }

        nombre = nombre.trim();


        // -------------------------------------------------
        // BUSCAMOS UTILIZANDO BUSCA
        // -------------------------------------------------

        int pos = Busca(V, N, nombre);


        // Si no existe
        if (pos < 0) {

            return "El empleado "
                    + nombre
                    + " no fue encontrado";
        }


        // -------------------------------------------------
        // CONVERTIMOS EL SEXO A TEXTO
        // -------------------------------------------------

        String sexoTexto;

        if (
                Character.toUpperCase(
                        V[pos].GetSexo()
                ) == 'M'
        ) {

            sexoTexto = "Masculino";

        } else {

            sexoTexto = "Femenino";
        }


        // -------------------------------------------------
        // RETORNAMOS LOS DATOS
        // -------------------------------------------------

        return "Nombre: "
                + V[pos].GetNombre()

                + "\nDireccion: "
                + V[pos].GetDireccion()

                + "\nEdad: "
                + V[pos].GetEdad()

                + "\nSexo: "
                + sexoTexto

                + "\nAntiguedad: "
                + V[pos].GetAntiguedad()

                + " años";
    }


    // =====================================================
    // LISTAR TODOS LOS EMPLEADOS
    // =====================================================

    public static String ListarTodosWeb() {

        // Verificamos inicialización
        if (V == null) {
            return "Primero debe iniciar el arreglo";
        }

        // Verificamos registros
        if (N == -1) {
            return "No hay empleados registrados";
        }


        String resultado = "";


        // -------------------------------------------------
        // RECORREMOS EL ARREGLO
        // -------------------------------------------------
        //
        // NO ordenamos aquí.
        //
        // El arreglo ya está ordenado porque AltaWeb()
        // inserta cada empleado en su posición correcta.
        //
        // -------------------------------------------------

        for (int i = 0; i <= N; i++) {

            String sexoTexto;


            if (
                    Character.toUpperCase(
                            V[i].GetSexo()
                    ) == 'M'
            ) {

                sexoTexto = "Masculino";

            } else {

                sexoTexto = "Femenino";
            }


            // El símbolo | permite que JavaScript
            // separe posteriormente las columnas
            // de la tabla HTML.

            resultado +=
                    V[i].GetNombre()
                    + "|"
                    + V[i].GetDireccion()
                    + "|"
                    + V[i].GetEdad()
                    + "|"
                    + sexoTexto
                    + "|"
                    + V[i].GetAntiguedad()
                    + "\n";
        }


        return resultado;
    }
}