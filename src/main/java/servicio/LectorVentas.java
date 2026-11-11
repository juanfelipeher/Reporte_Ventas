package servicio;

import com.opencsv.CSVReader;
import modelo.Venta;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LectorVentas {

    public List<Venta> leer(String rutaArchivo) throws Exception {
        List<Venta> ventas = new ArrayList<>();

        try (CSVReader lector = new CSVReader(new FileReader(rutaArchivo))) {
            lector.readNext(); 

            String[] fila;
            int numeroLinea = 1;
            while ((fila = lector.readNext()) != null) {
                numeroLinea++;
                try {
                    String fecha = fila[0].trim();
                    String producto = fila[1].trim();
                    int cantidad = Integer.parseInt(fila[2].trim());
                    long valor = Long.parseLong(fila[3].trim());
                    ventas.add(new Venta(fecha, producto, cantidad, valor));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    // Si una fila esta mal escrita se avisa y se continua con las demas
                    System.out.println("Aviso: se omitio la linea " + numeroLinea + " por datos invalidos.");
                }
            }
        }
        return ventas;
    }
}
