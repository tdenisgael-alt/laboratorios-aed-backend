package Desordenados;

public class N_Alumno extends Object {

    // -----------------------------------------------------
    // ATRIBUTOS
    // -----------------------------------------------------

    private String nombre;
    private int semestre;
    private float promedio;


    // -----------------------------------------------------
    // CONSTRUCTOR VACÍO
    // -----------------------------------------------------

    public N_Alumno() {
    }


    // -----------------------------------------------------
    // CONSTRUCTOR CON PARÁMETROS
    // -----------------------------------------------------

    public N_Alumno(
            String n,
            int s,
            float p) {

        nombre = n;
        semestre = s;
        promedio = p;
    }


    // -----------------------------------------------------
    // ASIGNAR TODOS LOS DATOS
    // -----------------------------------------------------

    public void SetAsignar(
            String n,
            int s,
            float p) {

        nombre = n;
        semestre = s;
        promedio = p;
    }


    // -----------------------------------------------------
    // GETTERS
    // -----------------------------------------------------

    public String GetNombre() {
        return nombre;
    }

    public int GetSemestre() {
        return semestre;
    }

    public float GetPromedio() {
        return promedio;
    }


    // -----------------------------------------------------
    // SETTERS
    // -----------------------------------------------------

    public void SetNombre(String n) {
        nombre = n;
    }

    public void SetSemestre(int s) {
        semestre = s;
    }

    public void SetPromedio(float p) {
        promedio = p;
    }


    // -----------------------------------------------------
    // MOSTRAR DATOS
    // -----------------------------------------------------

    @Override
    public String toString() {

        return nombre + "\t"
                + semestre + "\t"
                + promedio + "\n";
    }

} // fin clase N_Alumno