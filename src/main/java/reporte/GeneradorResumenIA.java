package reporte;

import modelo.Indicadores;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;


public class GeneradorResumenIA {

    private NumberFormat formato = NumberFormat.getInstance(Locale.forLanguageTag("es-CO"));

    public String construirResumen(Indicadores indicadores) {
        StringBuilder resumen = new StringBuilder();
        resumen.append("RESUMEN DE INDICADORES (calculados por Java)\n");
        resumen.append("- Registros procesados: ").append(indicadores.getNumeroRegistros()).append("\n");
        resumen.append("- Valor total de ventas: $").append(formato.format(indicadores.getValorTotal())).append(" COP\n");
        resumen.append("- Cantidad total vendida: ").append(indicadores.getCantidadTotal()).append(" unidades\n");
        resumen.append("- Producto con mayor valor vendido: ").append(indicadores.getProductoTop())
                .append(" ($").append(formato.format(indicadores.getValorProductoTop())).append(" COP)\n");
        resumen.append("- Valor promedio por registro: $")
                .append(formato.format(Math.round(indicadores.getPromedioPorRegistro()))).append(" COP\n");
        resumen.append("- Valor vendido por producto:\n");
        for (Map.Entry<String, Long> entrada : indicadores.getValorPorProducto().entrySet()) {
            resumen.append("    * ").append(entrada.getKey()).append(": $")
                    .append(formato.format(entrada.getValue())).append(" COP\n");
        }
        return resumen.toString();
    }

    public String construirPrompt(String resumen) {
        return "Actua como analista comercial de una pequena empresa.\n"
                + "Con los siguientes indicadores semanales de ventas, redacta un reporte ejecutivo\n"
                + "dirigido al gerente, de maximo tres parrafos.\n\n"
                + "Reglas:\n"
                + "1. Usa unicamente los datos entregados; no inventes cifras ni calcules valores nuevos.\n"
                + "2. Menciona el valor total, la cantidad vendida, el producto lider y el promedio por registro.\n"
                + "3. Termina con una recomendacion breve para la proxima semana.\n"
                + "4. Usa un lenguaje claro y profesional.\n\n"
                + resumen;
    }
}
