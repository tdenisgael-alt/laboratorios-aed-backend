package Ordenados;

public class Vendedor {

    // =====================================================
    // VARIABLES GLOBALES
    // =====================================================

    // Tamaño máximo del arreglo
    static int tam = 0;

    // Arreglo de vendedores
    static N_Vendedor V[] = null;

    // Última posición ocupada
    // -1 significa que el arreglo está vacío
    static int N = -1;


    // =====================================================
    // INICIALIZAR ARREGLO
    // =====================================================

    public static String InicializarWeb(
            int nuevoTam) {

        // Validamos el tamaño
        if (nuevoTam <= 0) {

            return "El tamaño del arreglo debe ser mayor que 0";
        }

        // Guardamos el tamaño
        tam = nuevoTam;

        // Creamos el arreglo
        V = new N_Vendedor[tam];

        // Inicialmente está vacío
        N = -1;

        return "Arreglo creado correctamente con capacidad para "
                + tam
                + " vendedores";
    }


    // =====================================================
    // BUSCA
    // =====================================================
    //
    // El arreglo está ordenado alfabéticamente
    // por el NOMBRE DEL VENDEDOR.
    //
    // Si encuentra el nombre:
    // retorna su posición.
    //
    // Si no lo encuentra:
    // retorna -(posicion + 1).
    //
    // =====================================================

    public static int Busca(
            N_Vendedor V[],
            int N,
            String nombre) {

        int i = 0;


        // Avanzamos mientras el nombre almacenado
        // sea alfabéticamente menor al buscado.
        while (
                (i <= N)
                &&
                (V[i]
                    .GetNombre()
                    .compareToIgnoreCase(nombre) < 0)
        ) {

            i++;
        }


        // Si llegamos al final o encontramos
        // un nombre mayor, el vendedor no existe.
        if (
                (i > N)
                ||
                (V[i]
                    .GetNombre()
                    .compareToIgnoreCase(nombre) > 0)
        ) {

            return -(i + 1);
        }


        // El vendedor fue encontrado
        return i;
    }


    // =====================================================
    // OBTENER POSICION DE INSERCION
    // =====================================================

    public static int ObtenerPosicionInsercion(
            int pos) {

        return (-pos) - 1;
    }


    // =====================================================
    // 1. DAR DE ALTA
    // INSERTA ORDENADO
    // =====================================================

    public static String AltaWeb(
            String nombre,
            float totalVentas) {

        // Verificamos que el arreglo esté iniciado
        if (V == null) {

            return "Primero debe iniciar el arreglo";
        }


        // Verificamos espacio
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


        // Eliminamos espacios innecesarios
        nombre = nombre.trim();


        // Validamos ventas
        if (totalVentas < 0) {

            return "El total de ventas no puede ser negativo";
        }


        // -------------------------------------------------
        // BUSCAR VENDEDOR
        // -------------------------------------------------

        int pos =
                Busca(
                        V,
                        N,
                        nombre
                );


        // -------------------------------------------------
        // SI YA EXISTE
        // -------------------------------------------------

        if (pos >= 0) {

            return "El vendedor "
                    + nombre
                    + " ya se encuentra registrado";
        }


        // -------------------------------------------------
        // OBTENER POSICION DE INSERCION
        // -------------------------------------------------

        pos =
                ObtenerPosicionInsercion(
                        pos
                );


        // Aumentamos la última posición
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
        // INSERTAR VENDEDOR
        // -------------------------------------------------

        V[pos] =
                new N_Vendedor();


        V[pos].SetAsignar(
                nombre,
                totalVentas
        );


        return "Vendedor registrado correctamente";
    }


    // =====================================================
    // 2. MODIFICAR TOTAL DE VENTAS
    // =====================================================

    public static String ModificarWeb(
            String nombre,
            float nuevoTotalVentas) {

        // Verificamos inicialización
        if (V == null) {

            return "Primero debe iniciar el arreglo";
        }


        // Verificamos si está vacío
        if (N == -1) {

            return "No hay vendedores registrados";
        }


        // Validamos nombre
        if (
                nombre == null
                ||
                nombre.trim().isEmpty()
        ) {

            return "Ingrese el nombre del vendedor";
        }


        nombre = nombre.trim();


        // Validamos ventas
        if (nuevoTotalVentas < 0) {

            return "El total de ventas no puede ser negativo";
        }


        // -------------------------------------------------
        // BUSCAMOS CON BUSCA
        // -------------------------------------------------

        int pos =
                Busca(
                        V,
                        N,
                        nombre
                );


        // -------------------------------------------------
        // SI NO EXISTE
        // -------------------------------------------------

        if (pos < 0) {

            return "El vendedor "
                    + nombre
                    + " no fue encontrado";
        }


        // -------------------------------------------------
        // MODIFICAMOS TOTAL DE VENTAS
        // -------------------------------------------------

        V[pos].SetTotalVentas(
                nuevoTotalVentas
        );


        return "Total de ventas modificado correctamente";
    }


    // =====================================================
    // 3. LISTAR UN VENDEDOR
    // =====================================================

    public static String ListarUnoWeb(
            String nombre) {

        // Verificamos inicialización
        if (V == null) {

            return "Primero debe iniciar el arreglo";
        }


        // Verificamos registros
        if (N == -1) {

            return "No hay vendedores registrados";
        }


        // Validamos nombre
        if (
                nombre == null
                ||
                nombre.trim().isEmpty()
        ) {

            return "Ingrese el nombre del vendedor";
        }


        nombre = nombre.trim();


        // -------------------------------------------------
        // BUSCAMOS UTILIZANDO BUSCA
        // -------------------------------------------------

        int pos =
                Busca(
                        V,
                        N,
                        nombre
                );


        // -------------------------------------------------
        // SI NO EXISTE
        // -------------------------------------------------

        if (pos < 0) {

            return "El vendedor "
                    + nombre
                    + " no fue encontrado";
        }


        // -------------------------------------------------
        // RETORNAMOS LA INFORMACION
        // -------------------------------------------------

        return "Nombre: "
                + V[pos].GetNombre()

                + "\nTotal de ventas: "
                + V[pos].GetTotalVentas();
    }
}