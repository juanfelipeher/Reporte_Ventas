package servicio;

import modelo.Indicadores;
import modelo.Venta;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class CalculadorIndicadores {

    public Indicadores calcular(List<Venta> ventas) {
        if (ventas.isEmpty()) {
            throw new IllegalArgumentException("No hay ventas para procesar.");
        }

        long valorTotal = 0;
        int cantidadTotal = 0;
        Map<String, Long> valorPorProducto = new LinkedHashMap<>();

        for (Venta venta : ventas) {
            valorTotal += venta.getValor();
            cantidadTotal += venta.getCantidad();

            // Acumula el valor vendido de cada producto
            long acumulado = valorPorProducto.getOrDefault(venta.getProducto(), 0L);
            valorPorProducto.put(venta.getProducto(), acumulado + venta.getValor());
        }

        // Busca el producto con mayor valor vendido
        String productoTop = "";
        long valorProductoTop = 0;
        for (Map.Entry<String, Long> entrada : valorPorProducto.entrySet()) {
            if (entrada.getValue() > valorProductoTop) {
                valorProductoTop = entrada.getValue();
                productoTop = entrada.getKey();
            }
        }

        double promedio = (double) valorTotal / ventas.size();

        return new Indicadores(valorTotal, cantidadTotal, productoTop,
                valorProductoTop, promedio, ventas.size(), valorPorProducto);
    }
}
