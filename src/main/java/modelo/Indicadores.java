package modelo;

import java.util.Map;

public class Indicadores {

    private long valorTotal;
    private int cantidadTotal;
    private String productoTop;
    private long valorProductoTop;
    private double promedioPorRegistro;
    private int numeroRegistros;
    private Map<String, Long> valorPorProducto; // valor vendido por cada producto

    public Indicadores(long valorTotal, int cantidadTotal, String productoTop,
                        long valorProductoTop, double promedioPorRegistro,
                        int numeroRegistros, Map<String, Long> valorPorProducto) {
        this.valorTotal = valorTotal;
        this.cantidadTotal = cantidadTotal;
        this.productoTop = productoTop;
        this.valorProductoTop = valorProductoTop;
        this.promedioPorRegistro = promedioPorRegistro;
        this.numeroRegistros = numeroRegistros;
        this.valorPorProducto = valorPorProducto;
    }

    public long getValorTotal() {
        return valorTotal;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public String getProductoTop() {
        return productoTop;
    }

    public long getValorProductoTop() {
        return valorProductoTop;
    }

    public double getPromedioPorRegistro() {
        return promedioPorRegistro;
    }

    public int getNumeroRegistros() {
        return numeroRegistros;
    }

    public Map<String, Long> getValorPorProducto() {
        return valorPorProducto;
    }
}
