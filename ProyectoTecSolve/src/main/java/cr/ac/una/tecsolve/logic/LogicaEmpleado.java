
package cr.ac.una.tecsolve.logic;

import cr.ac.una.tecsolve.data.DataEmpleado;
import cr.ac.una.tecsolve.domain.Empleado;
import java.util.LinkedList;

/**
 *
 * @author Juan Dolmus
 */
public class LogicaEmpleado {
    
    DataEmpleado data = new DataEmpleado();
    
    public LinkedList<Empleado> listarEmpleados(){
        return data.getEmpleados();
    }
    
    public  LinkedList<Empleado> getListaEmpleadosPorPaginacion(int page, int pageSize) {
        return data.getListaEmpleadosPorPaginacion(page, pageSize);
    }
    
    public Empleado listarEmpleadoPorID(int id){
        return data.getEmpleadosPorId(id);
    }
    
    public boolean guardarEmpleado(Empleado empleado){
        return data.insertatEmpleado(empleado);
    }
    
    public boolean updateEmpleado(Empleado empleado, int idEmpleado){
        return data.actualizarEmpleado(empleado, idEmpleado);
    }
    
    public String searchCredenciales(String username, String password, String tipoUser){
        return data.buscarCredenciales(username, password, tipoUser);
    }
    
    public boolean eliminarEmpleado(int idEmpleado){
        return data.deshabilitarEmpleado(idEmpleado);
    }
    
    public int getTotalGastos() {
        return data.getNumeroTotalEmpleados();
    }
}
