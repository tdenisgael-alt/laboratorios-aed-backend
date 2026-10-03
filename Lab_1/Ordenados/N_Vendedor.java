package Ordenados;

public class N_Vendedor {

    // =====================================================
    // ATRIBUTOS
    // =====================================================

    private String nombre;
    private float totalVentas;


    // =====================================================
    // CONSTRUCTOR VACIO
    // =====================================================

    public N_Vendedor() {
    }


    // =====================================================
    // CONSTRUCTOR CON PARAMETROS
    // =====================================================

    public N_Vendedor(
            String n,
            float ventas) {

        nombre = n;
        totalVentas = ventas;
    }


    // =====================================================
    // ASIGNAR TODOS LOS DATOS
    // =====================================================

    public void SetAsignar(
            String n,
            float ventas) {

        nombre = n;
        totalVentas = ventas;
    }


    // =====================================================
    // GETTERS
    // =====================================================

    public String GetNombre() {
        return nombre;
    }


    public float GetTotalVentas() {
        return totalVentas;
    }


    // =====================================================
    // SETTERS
    // =====================================================

    public void SetNombre(String n) {
        nombre = n;
    }


    public void SetTotalVentas(float ventas) {
        totalVentas = ventas;
    }


    // =====================================================
    // MOSTRAR DATOS
    // =====================================================

    @Override
    public String toString() {

        return nombre
                + "\t"
                + totalVentas
                + "\n";
    }
}