package Ordenados;

// =====================================================
// CLASE N_Departamento
// =====================================================
//
// Representa UN departamento rentado.
//
// Datos:
// - Ubicacion
// - Extension en metros cuadrados
// - Precio
// - Numero de apartamento
// - Nombre de la persona que lo renta
//
// =====================================================

public class N_Departamento {

    // =================================================
    // ATRIBUTOS
    // =================================================

    private String ubicacion;
    private float extension;
    private float precio;
    private int numeroApartamento;
    private String nombrePersona;


    // =================================================
    // CONSTRUCTOR VACIO
    // =================================================

    public N_Departamento() {
    }


    // =================================================
    // CONSTRUCTOR CON PARAMETROS
    // =================================================

    public N_Departamento(
            String u,
            float e,
            float p,
            int n,
            String persona) {

        ubicacion = u;
        extension = e;
        precio = p;
        numeroApartamento = n;
        nombrePersona = persona;
    }


    // =================================================
    // ASIGNAR TODOS LOS DATOS
    // =================================================

    public void SetAsignar(
            String u,
            float e,
            float p,
            int n,
            String persona) {

        ubicacion = u;
        extension = e;
        precio = p;
        numeroApartamento = n;
        nombrePersona = persona;
    }


    // =================================================
    // GETTERS
    // =================================================

    public String GetUbicacion() {
        return ubicacion;
    }


    public float GetExtension() {
        return extension;
    }


    public float GetPrecio() {
        return precio;
    }


    public int GetNumeroApartamento() {
        return numeroApartamento;
    }


    public String GetNombrePersona() {
        return nombrePersona;
    }


    // =================================================
    // SETTERS
    // =================================================

    public void SetUbicacion(String u) {
        ubicacion = u;
    }


    public void SetExtension(float e) {
        extension = e;
    }


    public void SetPrecio(float p) {
        precio = p;
    }


    public void SetNumeroApartamento(int n) {
        numeroApartamento = n;
    }


    public void SetNombrePersona(String persona) {
        nombrePersona = persona;
    }


    // =================================================
    // MOSTRAR DATOS
    // =================================================

    @Override
    public String toString() {

        return ubicacion + "\t"
                + extension + "\t"
                + precio + "\t"
                + numeroApartamento + "\t"
                + nombrePersona + "\n";
    }

}