package Desordenados;

// ---------------------------------------------------------
// CLASE N_Cliente
// ---------------------------------------------------------
//
// Esta clase representa UN cliente.
//
// Aquí únicamente guardamos los datos correspondientes
// a cada cliente:
//
//      - Nombre
//      - Teléfono
//      - Saldo
//      - Estado moroso
//
// La lógica para insertar, eliminar, modificar y buscar
// clientes estará en la clase Cliente.
//
// ---------------------------------------------------------

public class N_Cliente extends Object {

    // -----------------------------------------------------
    // ATRIBUTOS DEL CLIENTE
    // -----------------------------------------------------

    // Guarda el nombre completo del cliente.
    private String nombre;

    // El teléfono se guarda como String.
    // Es mejor que usar int porque un teléfono no se utiliza
    // para realizar operaciones matemáticas.
    private String telefono;

    // Guarda el saldo correspondiente al cliente.
    private float saldo;

    // Guarda el estado moroso.
    //
    // true  = el cliente es moroso.
    // false = el cliente no es moroso.
    private boolean moroso;


    // -----------------------------------------------------
    // CONSTRUCTOR VACÍO
    // -----------------------------------------------------
    //
    // Permite crear primero el objeto y posteriormente
    // asignarle los datos mediante SetAsignar().
    //
    // Ejemplo:
    //
    // N_Cliente cliente = new N_Cliente();
    //

    public N_Cliente() {

    }


    // -----------------------------------------------------
    // CONSTRUCTOR CON PARÁMETROS
    // -----------------------------------------------------
    //
    // Permite crear un cliente enviando todos sus datos
    // directamente al constructor.
    //
    // n = nombre
    // t = teléfono
    // s = saldo
    // m = moroso
    //

    public N_Cliente(
            String n,
            String t,
            float s,
            boolean m) {

        nombre = n;
        telefono = t;
        saldo = s;
        moroso = m;
    }


    // -----------------------------------------------------
    // MÉTODO PARA ASIGNAR TODOS LOS DATOS
    // -----------------------------------------------------
    //
    // Este método será utilizado principalmente cuando
    // insertemos un nuevo cliente en el arreglo.
    //

    public void SetAsignar(
            String n,
            String t,
            float s,
            boolean m) {

        nombre = n;
        telefono = t;
        saldo = s;
        moroso = m;
    }


    // -----------------------------------------------------
    // GET NOMBRE
    // -----------------------------------------------------
    //
    // Devuelve el nombre almacenado en el objeto.
    //

    public String GetNombre() {

        return nombre;
    }


    // -----------------------------------------------------
    // GET TELÉFONO
    // -----------------------------------------------------
    //
    // Devuelve el número telefónico del cliente.
    //

    public String GetTelefono() {

        return telefono;
    }


    // -----------------------------------------------------
    // GET SALDO
    // -----------------------------------------------------
    //
    // Devuelve el saldo del cliente.
    //

    public float GetSaldo() {

        return saldo;
    }


    // -----------------------------------------------------
    // GET MOROSO
    // -----------------------------------------------------
    //
    // Devuelve:
    //
    // true  -> si es moroso.
    // false -> si no es moroso.
    //

    public boolean GetMoroso() {

        return moroso;
    }


    // -----------------------------------------------------
    // SET NOMBRE
    // -----------------------------------------------------
    //
    // Permite cambiar el nombre del cliente.
    //

    public void SetNombre(String n) {

        nombre = n;
    }


    // -----------------------------------------------------
    // SET TELÉFONO
    // -----------------------------------------------------
    //
    // Permite cambiar el teléfono.
    //

    public void SetTelefono(String t) {

        telefono = t;
    }


    // -----------------------------------------------------
    // SET SALDO
    // -----------------------------------------------------
    //
    // Permite modificar el saldo.
    //

    public void SetSaldo(float s) {

        saldo = s;
    }


    // -----------------------------------------------------
    // SET MOROSO
    // -----------------------------------------------------
    //
    // Este es especialmente importante para el ejercicio,
    // porque la opción número 2 solicita modificar
    // específicamente el estado moroso de un cliente.
    //

    public void SetMoroso(boolean m) {

        moroso = m;
    }


    // -----------------------------------------------------
    // MÉTODO toString()
    // -----------------------------------------------------
    //
    // Permite convertir todos los datos del cliente
    // en una cadena de texto.
    //
    // Será útil para mostrar registros.
    //

    @Override
    public String toString() {

        return nombre + "\t"
                + telefono + "\t"
                + saldo + "\t"
                + (moroso ? "Si" : "No")
                + "\n";
    }

} // fin clase N_Cliente