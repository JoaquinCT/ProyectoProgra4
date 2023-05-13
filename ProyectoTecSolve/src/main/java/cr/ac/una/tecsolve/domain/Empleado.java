
package cr.ac.una.tecsolve.domain;

/**
 *
 * @author Juan Dolmus
 */
public class Empleado extends Persona{
    private String contrasenia;
    private String puesto;
    private double salario;

    public Empleado() {
    }

    public Empleado(int id, String nombre, String apellido, String cedula, String numeroTelefono, String contrasenia, String puesto, double salario) {
        super(id, nombre, apellido, cedula, numeroTelefono);
        this.contrasenia = contrasenia;
        this.puesto = puesto;
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    
}
