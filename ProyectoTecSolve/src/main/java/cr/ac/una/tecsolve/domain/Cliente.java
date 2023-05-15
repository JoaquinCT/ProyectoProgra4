
package cr.ac.una.proyecto.domain;

/**
 *
 * @author Maizeth Cisneros
 */
public class Cliente extends Persona {

    private int id_cliente;

    public Cliente() {
    }

    public Cliente(int id_cliente, String nombre, String apellido, String cedula, String numero, String correo) {
        super(cedula, nombre, apellido, numero, correo);
        this.id_cliente = id_cliente;
    }
    
    public Cliente(String nombre, String apellido, String cedula, String numero, String correo) {
        super(cedula, nombre, apellido, numero, correo);
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
