package Ordenados;

// =====================================================
// CLASE Departamento
// =====================================================
//
// Maneja un arreglo ORDENADO de departamentos.
//
// CRITERIO DE ORDEN:
//
// Extension del departamento.
//
// El arreglo permanece ordenado de forma ASCENDENTE:
//
// 45 m2
// 60 m2
// 75 m2
// 100 m2
//
// No usamos:
// - ArrayList
// - Collections
// - sort()
//
// =====================================================

public class Departamento {


    // =================================================
    // VARIABLES GLOBALES
    // =================================================

    // Tamaño máximo del arreglo
    static int tam = 0;

    // Arreglo de departamentos
    static N_Departamento V[] = null;

    // Última posición ocupada
    // -1 significa que está vacío
    static int N = -1;


    // =================================================
    // INICIALIZAR ARREGLO
    // =================================================

    public static String InicializarWeb(
            int nuevoTam) {

        // Validar tamaño
        if (nuevoTam <= 0) {

            return "El tamaño del arreglo debe ser mayor que 0";
        }


        // Guardamos el tamaño
        tam = nuevoTam;


        // Creamos el arreglo
        V = new N_Departamento[tam];


        // Comienza vacío
        N = -1;


        return "Arreglo creado correctamente con capacidad para "
                + tam
                + " departamentos";
    }


    // =================================================
    // BUSCA
    // =================================================
    //
    // Sigue la lógica de BUSCA para arreglos ordenados.
    //
    // El arreglo está ordenado por EXTENSION.
    //
    // Si encuentra la extensión:
    // retorna su posición.
    //
    // Si no la encuentra:
    // retorna -(posicion + 1).
    //
    // Usamos -(i + 1) porque en Java -0 sigue siendo 0.
    //
    // =================================================

    public static int Busca(
            N_Departamento V[],
            int N,
            float extension) {

        int i = 0;


        // Avanzamos mientras la extensión almacenada
        // sea menor que la extensión buscada.
        while (
                (i <= N)
                &&
                (V[i].GetExtension() < extension)
        ) {

            i++;
        }


        // Si llegamos fuera del arreglo
        // o encontramos una extensión mayor,
        // significa que no existe.
        if (
                (i > N)
                ||
                (V[i].GetExtension() > extension)
        ) {

            return -(i + 1);
        }


        // Si son iguales, fue encontrada.
        return i;
    }


    // =================================================
    // OBTENER POSICION DE INSERCION
    // =================================================

    public static int ObtenerPosicionInsercion(
            int pos) {

        return (-pos) - 1;
    }


    // =================================================
    // BUSCAR POR NUMERO DE APARTAMENTO
    // =================================================
    //
    // IMPORTANTE:
    //
    // El arreglo NO está ordenado por número.
    //
    // Está ordenado por extensión.
    //
    // Por eso para buscar por número debemos
    // recorrer el arreglo secuencialmente.
    //
    // =================================================

    public static int BuscarPorNumero(
            int numeroApartamento) {

        int i = 0;


        while (
                (i <= N)
                &&
                (V[i].GetNumeroApartamento()
                        != numeroApartamento)
        ) {

            i++;
        }


        if (i > N) {

            return -1;
        }


        return i;
    }


    // =================================================
    // 1. DAR DE ALTA
    // INSERTAR ORDENADO
    // =================================================

    public static String AltaWeb(
            String ubicacion,
            float extension,
            float precio,
            int numeroApartamento,
            String nombrePersona) {

        // Verificar inicialización
        if (V == null) {

            return "Primero debe iniciar el arreglo";
        }


        // Verificar espacio
        if (N >= tam - 1) {

            return "No hay espacio en el arreglo";
        }


        // Validar ubicación
        if (
                ubicacion == null
                ||
                ubicacion.trim().isEmpty()
        ) {

            return "La ubicacion no puede estar vacia";
        }


        // Validar extensión
        if (extension <= 0) {

            return "La extension debe ser mayor que 0";
        }


        // Validar precio
        if (precio < 0) {

            return "El precio no puede ser negativo";
        }


        // Validar número
        if (numeroApartamento <= 0) {

            return "El numero de apartamento debe ser mayor que 0";
        }


        // Validar persona
        if (
                nombrePersona == null
                ||
                nombrePersona.trim().isEmpty()
        ) {

            return "El nombre de la persona no puede estar vacio";
        }


        ubicacion = ubicacion.trim();
        nombrePersona = nombrePersona.trim();


        // -------------------------------------------------
        // EVITAR NUMEROS DE APARTAMENTO REPETIDOS
        // -------------------------------------------------

        if (
                BuscarPorNumero(numeroApartamento) != -1
        ) {

            return "El apartamento numero "
                    + numeroApartamento
                    + " ya se encuentra registrado";
        }


        // -------------------------------------------------
        // LLAMAR A BUSCA
        // -------------------------------------------------

        int pos =
                Busca(
                        V,
                        N,
                        extension
                );


        // -------------------------------------------------
        // SI POS ES POSITIVO O CERO
        // LA EXTENSION YA EXISTE
        // -------------------------------------------------
        //
        // Siguiendo la estructura del algoritmo
        // InsertaOrdenado del profesor, no permitimos
        // repetir el valor utilizado como criterio
        // de ordenamiento.
        //
        // -------------------------------------------------

        if (pos >= 0) {

            return "Ya existe un departamento con extension "
                    + extension
                    + " m2";
        }


        // Obtener posición real
        pos =
                ObtenerPosicionInsercion(
                        pos
                );


        // Aumentamos última posición
        N++;


        // -------------------------------------------------
        // DESPLAZAR HACIA LA DERECHA
        // -------------------------------------------------

        for (
                int i = N;
                i > pos;
                i--
        ) {

            V[i] =
                    V[i - 1];
        }


        // -------------------------------------------------
        // INSERTAR DEPARTAMENTO
        // -------------------------------------------------

        V[pos] =
                new N_Departamento();


        V[pos].SetAsignar(
                ubicacion,
                extension,
                precio,
                numeroApartamento,
                nombrePersona
        );


        return "Departamento registrado correctamente";
    }


    // =================================================
    // 2. DAR DE BAJA
    // =================================================
    //
    // Usaremos el número de apartamento para
    // identificar qué departamento será liberado.
    //
    // =================================================

    public static String BajaWeb(
            int numeroApartamento) {

        // Verificar arreglo
        if (V == null) {

            return "Primero debe iniciar el arreglo";
        }


        // Verificar registros
        if (N == -1) {

            return "No hay departamentos registrados";
        }


        // Buscar por número
        int pos =
                BuscarPorNumero(
                        numeroApartamento
                );


        // No encontrado
        if (pos == -1) {

            return "El apartamento numero "
                    + numeroApartamento
                    + " no fue encontrado";
        }


        // -------------------------------------------------
        // DESPLAZAR HACIA LA IZQUIERDA
        // -------------------------------------------------

        for (
                int i = pos;
                i < N;
                i++
        ) {

            V[i] =
                    V[i + 1];
        }


        // Limpiar última posición
        V[N] = null;


        // Disminuir N
        N--;


        return "Departamento liberado correctamente";
    }


    // =================================================
    // 3. MODIFICAR PRECIO
    // =================================================
    //
    // El enunciado pide modificar el precio
    // mediante el NUMERO DE APARTAMENTO.
    //
    // No modificamos la extensión.
    //
    // Por lo tanto el orden del arreglo no cambia.
    //
    // =================================================

    public static String ModificarWeb(
            int numeroApartamento,
            float nuevoPrecio) {

        // Verificar arreglo
        if (V == null) {

            return "Primero debe iniciar el arreglo";
        }


        // Verificar registros
        if (N == -1) {

            return "No hay departamentos registrados";
        }


        // Validar precio
        if (nuevoPrecio < 0) {

            return "El precio no puede ser negativo";
        }


        // Buscar apartamento
        int pos =
                BuscarPorNumero(
                        numeroApartamento
                );


        // No encontrado
        if (pos == -1) {

            return "El apartamento numero "
                    + numeroApartamento
                    + " no fue encontrado";
        }


        // Modificar solamente precio
        V[pos].SetPrecio(
                nuevoPrecio
        );


        return "Precio modificado correctamente";
    }


    // =================================================
    // 4. LISTAR UN DEPARTAMENTO
    // =================================================
    //
    // Para identificarlo usamos el número
    // de apartamento.
    //
    // =================================================

    public static String ListarUnoWeb(
            int numeroApartamento) {

        // Verificar arreglo
        if (V == null) {

            return "Primero debe iniciar el arreglo";
        }


        // Verificar registros
        if (N == -1) {

            return "No hay departamentos registrados";
        }


        // Buscar apartamento
        int pos =
                BuscarPorNumero(
                        numeroApartamento
                );


        // No encontrado
        if (pos == -1) {

            return "El apartamento numero "
                    + numeroApartamento
                    + " no fue encontrado";
        }


        // Retornar información completa
        return "Ubicacion: "
                + V[pos].GetUbicacion()

                + "\nExtension: "
                + V[pos].GetExtension()
                + " m2"

                + "\nPrecio: "
                + V[pos].GetPrecio()

                + "\nNumero de apartamento: "
                + V[pos].GetNumeroApartamento()

                + "\nPersona que renta: "
                + V[pos].GetNombrePersona();
    }


    // =================================================
    // 5. LISTAR TODOS
    // =================================================
    //
    // El arreglo ya está ordenado ascendentemente
    // por extensión.
    //
    // NO hacemos ningún sort aquí.
    //
    // =================================================

    public static String ListarTodosWeb() {

        // Verificar arreglo
        if (V == null) {

            return "Primero debe iniciar el arreglo";
        }


        // Verificar registros
        if (N == -1) {

            return "No hay departamentos registrados";
        }


        String resultado = "";


        // Recorrer arreglo en el orden almacenado
        for (
                int i = 0;
                i <= N;
                i++
        ) {

            // El símbolo | será utilizado por
            // JavaScript para construir la tabla.

            resultado +=
                    V[i].GetUbicacion()

                    + "|"

                    + V[i].GetExtension()

                    + "|"

                    + V[i].GetPrecio()

                    + "|"

                    + V[i].GetNumeroApartamento()

                    + "|"

                    + V[i].GetNombrePersona()

                    + "\n";
        }


        return resultado;
    }

}