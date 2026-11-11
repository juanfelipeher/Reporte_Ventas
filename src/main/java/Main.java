import modelo.Indicadores;
import modelo.Venta;
import reporte.GeneradorGrafica;
import reporte.GeneradorResumenIA;
import servicio.CalculadorIndicadores;
import servicio.LectorVentas;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Necesario para generar la imagen sin abrir ventanas (tambien en servidores)
        System.setProperty("java.awt.headless", "true");

        String rutaCsv = (args.length > 0) ? args[0] : "datos/ventas.csv";
        String carpetaSalida = "salida";

        try {
            new File(carpetaSalida).mkdirs();

            // 1. Leer datos
            System.out.println("Leyendo archivo: " + rutaCsv);
            List<Venta> ventas = new LectorVentas().leer(rutaCsv);

            // 2. Calcular indicadores
            Indicadores indicadores = new CalculadorIndicadores().calcular(ventas);

            // 3. Generar grafica
            String rutaGrafica = carpetaSalida + "/ventas_por_producto.png";
            new GeneradorGrafica().generar(indicadores, rutaGrafica);

            // 4. Generar resumen y prompt para la IA
            GeneradorResumenIA generador = new GeneradorResumenIA();
            String resumen = generador.construirResumen(indicadores);
            String prompt = generador.construirPrompt(resumen);
            Files.writeString(Paths.get(carpetaSalida, "resumen_indicadores.txt"), resumen);
            Files.writeString(Paths.get(carpetaSalida, "prompt_ia.txt"), prompt);

            // 5. Mostrar resultados en consola
            System.out.println();
            System.out.println(resumen);
            System.out.println("Archivos generados en la carpeta '" + carpetaSalida + "':");
            System.out.println(" - ventas_por_producto.png");
            System.out.println(" - resumen_indicadores.txt");
            System.out.println(" - prompt_ia.txt");
            System.out.println("Ejecucion exitosa.");

        } catch (Exception e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }
}
