public class Cliente {
    private static String nombre;
    private static String direccion;
    private static String codigoPostal;

    public Cliente(String nombre, String direccion, String codigoPostal){
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
    }

    public static String getNombre() {
        return nombre;
    }

    public static String getDireccion() {
        return direccion;
    }

    public static String getCodigoPostal() {
        return codigoPostal;
    }
}