import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de factura: ");
        int numeroFactura = scanner.nextInt();

        System.out.print("Ingrese el nombre del cliente: ");
        String nombreCliente = scanner.next();

        System.out.print("Ingrese la direccion del cliente: ");
        String direccionCliente = scanner.next();

        System.out.print("Ingrese el codigo postal del cliente: ");
        String codigoPostalCliente = scanner.next();

        Cliente cliente = new Cliente(nombreCliente, direccionCliente, codigoPostalCliente);

        Factura factura = new Factura(numeroFactura);

        boolean seguirAgregando = true;

        while (seguirAgregando) {

            System.out.print("Ingrese el nombre del producto: ");
            String nombreProducto = scanner.next();

            System.out.print("Ingrese la cantidad: ");
            int cantidad = scanner.nextInt();

            System.out.print("Ingrese el precio unitario: ");
            double precio = scanner.nextDouble();

            factura.addItem(new ItemFactura(new Producto(nombreProducto), cantidad, precio));
            System.out.print("¿Desea agregar otro producto a la factura? (si/no): ");

            String respuesta = scanner.next();

            if (respuesta.equalsIgnoreCase("no")) {
                seguirAgregando = false;
            }
        }
        factura.imprimirFactura();
        factura.guardarEnBlogNotas(numeroFactura + "_" + nombreCliente + ".txt");

        factura.guardarFacturaEnExcel(numeroFactura + "_" + nombreCliente + ".xlsx");
    }
}





