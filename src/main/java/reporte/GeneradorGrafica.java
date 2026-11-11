package reporte;

import modelo.Indicadores;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class GeneradorGrafica {

    public void generar(Indicadores indicadores, String rutaPng) throws Exception {
        // 1. Datos de la grafica
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        for (Map.Entry<String, Long> entrada : indicadores.getValorPorProducto().entrySet()) {
            datos.addValue(entrada.getValue(), "Valor vendido", entrada.getKey());
        }

        // 2. Grafica de barras con titulo y nombres de ejes
        JFreeChart grafica = ChartFactory.createBarChart(
                "Ventas por producto (semana del 1 al 7 de octubre de 2026)",
                "Producto",
                "Valor vendido (COP)",
                datos,
                PlotOrientation.VERTICAL,
                false, true, false);

        // 3. Formato: fondo blanco, letras grandes y valores sobre las barras
        grafica.setBackgroundPaint(Color.WHITE);
        grafica.getTitle().setFont(new Font("SansSerif", Font.BOLD, 20));

        CategoryPlot plot = grafica.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 15));
        plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 14));
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
        plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 15));
        plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 13));

        NumberFormat formatoPesos = NumberFormat.getInstance(Locale.forLanguageTag("es-CO"));
        BarRenderer barras = (BarRenderer) plot.getRenderer();
        barras.setSeriesPaint(0, new Color(70, 110, 160));
        barras.setSeriesOutlinePaint(0, Color.BLACK);
        barras.setDrawBarOutline(true);
        // Muestra el valor sobre cada barra, por ejemplo $10.500.000
        barras.setDefaultItemLabelGenerator(
                new StandardCategoryItemLabelGenerator("${2}", formatoPesos));
        barras.setDefaultItemLabelsVisible(true);
        barras.setDefaultItemLabelFont(new Font("SansSerif", Font.BOLD, 13));

        // 4. Guardar como imagen PNG
        ChartUtils.saveChartAsPNG(new File(rutaPng), grafica, 900, 560);
    }
}
