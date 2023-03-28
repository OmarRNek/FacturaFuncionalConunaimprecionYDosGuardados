import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Factura {
    Date fecha;
    private int numeroFactura;
    private List<ItemFactura> items;

    public Factura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
        this.items = new ArrayList<ItemFactura>();
    }

    public void addItem(ItemFactura item) {
        items.add(item);
    }

    public void imprimirFactura() {
        double totalFactura = 0.0;
        double iva;
        double totalConIva;
        System.out.println("Número de factura: " + numeroFactura);
        System.out.println("Cliente:");
        System.out.println("Nombre: " + Cliente.getNombre());
        System.out.println("Direccion: " + Cliente.getDireccion());
        System.out.println("Codigo postal: " + Cliente.getCodigoPostal());
        System.out.println("Detalles de factura:");
        System.out.println("Producto \t Cantidad \t Precio \t SubTotal");
        for (ItemFactura item : items) {
            double totalLinea = item.getCantidad() * item.getPrecio();
            System.out.println(item.getProducto().getDescripcion() + "\t" + item.getCantidad() + "\t" + item.getPrecio() + "\t" + totalLinea);
            totalFactura += totalLinea;
        }
        iva = totalFactura * 0.16;
        totalConIva = totalFactura + iva;
        System.out.println("Total: " + totalConIva);
    }

    public void guardarEnBlogNotas(String nombreArchivo) {
        try {
            FileWriter archivo = new FileWriter(nombreArchivo);
            archivo.write("Nombre,Dirección,Codigo postal,Producto,Cantidad,Precio,SubTotal,Total\n");
            archivo.write(Cliente.getNombre() + "," + Cliente.getDireccion() + "," + Cliente.getCodigoPostal() + "\n");
            double totalFactura = 0.0;
            double iva;
            double totalConIva;
            for (ItemFactura item : items) {
                Producto producto = item.getProducto();
                int cantidad = item.getCantidad();
                double precio = item.getPrecio();
                double totalItem = cantidad * precio;
                totalFactura += totalItem;
                archivo.write("," + item.getProducto().getDescripcion() + "," + cantidad + "," + precio + "," + totalItem + "\n");
            }
            iva = totalFactura * 0.16;
            totalConIva = totalFactura + iva;
            archivo.write("Total:," + totalConIva + "\n");
            archivo.close();
            System.out.println("Factura guardada en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar la factura en " + nombreArchivo + ": " + e.getMessage());
        }
    }

    public void guardarFacturaEnExcel(String nombreFactura) {
        double totalFactura = 0.0;
        double iva;
        double totalConIva;
        XSSFWorkbook workbook = new XSSFWorkbook();
        //nombreFactura;
        try {
            FileOutputStream outputStream = new FileOutputStream(String.valueOf(nombreFactura));

            // Crear hoja de cálculo
            org.apache.poi.ss.usermodel.Sheet hoja = workbook.createSheet("Factura");

            // Crear filas y celdas
            int filaNum = 0;
            Row fila = hoja.createRow(filaNum++);
            Cell celda = fila.createCell(0);
            celda.setCellValue("Número de factura");
            celda = fila.createCell(1);
            celda.setCellValue(numeroFactura);

            /*fila = hoja.createRow(filaNum++);
            celda = fila.createCell(0);
            celda.setCellValue("Fecha de factura");
            celda = fila.createCell(1);
            celda.setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(fecha));*/

            fila = hoja.createRow(filaNum++);
            celda = fila.createCell(0);
            celda.setCellValue("Nombre del cliente");
            celda = fila.createCell(1);
            celda.setCellValue(Cliente.getNombre());

            fila = hoja.createRow(filaNum++);
            celda = fila.createCell(0);
            celda.setCellValue("Direccion del cliente");
            celda = fila.createCell(1);
            celda.setCellValue(Cliente.getDireccion());

            fila = hoja.createRow(filaNum++);
            celda = fila.createCell(0);
            celda.setCellValue("Codigo postal");
            celda = fila.createCell(1);
            celda.setCellValue(Cliente.getCodigoPostal());

            fila = hoja.createRow(filaNum + 2);
            celda = fila.createCell(0);
            celda.setCellValue("Detalles de factura");

            fila = hoja.createRow(filaNum++);
            celda = fila.createCell(0);
            celda.setCellValue("Cantidad");
            celda = fila.createCell(1);
            celda.setCellValue("Producto");
            celda = fila.createCell(2);
            celda.setCellValue("Precio");
            celda = fila.createCell(3);
            celda.setCellValue("SubTotal");


            for (ItemFactura item : items) {

                double subtotal = item.getCantidad() * item.getPrecio();

                fila = hoja.createRow(filaNum++);
                celda = fila.createCell(0);
                celda.setCellValue(item.getCantidad());
                celda = fila.createCell(1);
                celda.setCellValue(item.getProducto().getDescripcion());
                celda = fila.createCell(2);
                celda.setCellValue(item.getPrecio());
                celda = fila.createCell(3);
                celda.setCellValue(subtotal);

                totalFactura += subtotal;

            }

            iva = totalFactura * 0.16;
            totalConIva = totalFactura + iva;

            fila = hoja.createRow(filaNum++);
            celda = fila.createCell(2);
            celda.setCellValue("Total");
            celda = fila.createCell(3);
            celda.setCellValue(totalConIva);

            // Ajustar el ancho de las columnas
            hoja.autoSizeColumn(0);
            hoja.autoSizeColumn(1);
            hoja.autoSizeColumn(2);
            hoja.autoSizeColumn(3);

            // Guardar archivo
            workbook.write(outputStream);
            workbook.close();
            System.out.println("Factura guardada en archivo Excel. " + nombreFactura);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



