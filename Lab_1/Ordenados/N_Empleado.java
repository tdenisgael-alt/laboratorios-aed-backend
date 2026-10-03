package Ordenados;

public class N_Empleado {

    private String nombre;
    private String direccion;
    private int edad;
    private char sexo;
    private int antiguedad;

    public N_Empleado() {
    }

    public N_Empleado(
            String n,
            String d,
            int e,
            char s,
            int a) {

        nombre = n;
        direccion = d;
        edad = e;
        sexo = s;
        antiguedad = a;
    }

    public void SetAsignar(
            String n,
            String d,
            int e,
            char s,
            int a) {

        nombre = n;
        direccion = d;
        edad = e;
        sexo = s;
        antiguedad = a;
    }

    public String GetNombre() {
        return nombre;
    }

    public String GetDireccion() {
        return direccion;
    }

    public int GetEdad() {
        return edad;
    }

    public char GetSexo() {
        return sexo;
    }

    public int GetAntiguedad() {
        return antiguedad;
    }

    public void SetNombre(String n) {
        nombre = n;
    }

    public void SetDireccion(String d) {
        direccion = d;
    }

    public void SetEdad(int e) {
        edad = e;
    }

    public void SetSexo(char s) {
        sexo = s;
    }

    public void SetAntiguedad(int a) {
        antiguedad = a;
    }

    @Override
    public String toString() {
        return nombre + "\t"
                + direccion + "\t"
                + edad + "\t"
                + sexo + "\t"
                + antiguedad + "\n";
    }
}