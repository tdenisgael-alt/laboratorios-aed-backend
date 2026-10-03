package Desordenados;

// -----------------------------------------------------
// CLASE N_EMPLEADO
// -----------------------------------------------------
//
// Esta clase representa UN empleado.
//
// Cada objeto N_Empleado almacenará:
// - Nombre
// - Sexo
// - Edad
//
// Los objetos de esta clase serán almacenados
// posteriormente dentro de un arreglo desordenado.
// -----------------------------------------------------

public class N_Empleado {

    // -------------------------------------------------
    // ATRIBUTOS
    // -------------------------------------------------

    private String nombre;
    private char sexo;
    private int edad;


    // -------------------------------------------------
    // CONSTRUCTOR VACÍO
    // -------------------------------------------------

    public N_Empleado() {
    }


    // -------------------------------------------------
    // CONSTRUCTOR CON PARÁMETROS
    // -------------------------------------------------

    public N_Empleado(String n, char s, int e) {

        nombre = n;
        sexo = s;
        edad = e;
    }


    // -------------------------------------------------
    // ASIGNAR TODOS LOS DATOS
    // -------------------------------------------------

    public void SetAsignar(String n, char s, int e) {

        nombre = n;
        sexo = s;
        edad = e;
    }


    // -------------------------------------------------
    // GETTERS
    // -------------------------------------------------

    public String GetNombre() {
        return nombre;
    }


    public char GetSexo() {
        return sexo;
    }


    public int GetEdad() {
        return edad;
    }


    // -------------------------------------------------
    // SETTERS
    // -------------------------------------------------

    public void SetSexo(char s) {
        sexo = s;
    }


    public void SetEdad(int e) {
        edad = e;
    }


    // -------------------------------------------------
    // TOSTRING
    // -------------------------------------------------

    @Override
    public String toString() {

        return nombre + "\t"
                + sexo + "\t"
                + edad + "\n";
    }

} // fin clase N_Empleado