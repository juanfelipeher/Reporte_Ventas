package modelo;


public class Venta {

    private String fecha;
    private String producto;
    private int cantidad;
    private long valor; // valor total de la venta (en pesos)

    public Venta(String fecha, String producto, int cantidad, long valor) {
        this.fecha = fecha;
        this.producto = producto;
        this.cantidad = cantidad;
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public long getValor() {
        return valor;
    }
}
