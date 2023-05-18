
package cr.ac.una.tecsolve.domain;

/**
 *
 * @author Maizeth Cisneros
 */
public class Cliente extends Persona {

    private int id_cliente;
    private String correo;

    public Cliente() {
    }

    public Cliente(int id_cliente, String nombre, String apellido, String cedula, String numero, String correo) {
        super(nombre, apellido, cedula, numero);
        this.correo = correo;
        this.id_cliente = id_cliente;
    }
    
    public Cliente(String nombre, String apellido, String cedula, String numero, String correo) {
        super(nombre, apellido, cedula, numero);
        this.correo = correo;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}
