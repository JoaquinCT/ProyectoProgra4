package cr.ac.una.tecsolve.data;

import cr.ac.una.tecsolve.domain.Empleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Dolmus
 */
public class DataEmpleado extends BaseData {

    public final static String ID = "id";
    public final static String CEDULA = "cedula";
    public final static String NOMBRE = "nombre";
    public final static String APELLIDO = "apellido";
    public final static String NUMERO_TELEFONO = "numeroTelefono";
    public final static String CONTRASENIA = "contraseña";
    public final static String PUESTO = "puesto";
    public final static String SALARIO = "salario";

    public String buscarCredenciales(String username, String password, String tipoUser) {
        String typeUserfound = "error";
        String query = "SELECT " + CEDULA + "," + CONTRASENIA +","+PUESTO+ " FROM tbusuario";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                if(rs.getString(CEDULA).equals(username) && rs.getString(CONTRASENIA).equals(password) && rs.getString(PUESTO).equalsIgnoreCase(tipoUser)){
                    typeUserfound = "admin";
                    break;
                }else{
                    if(rs.getString(CEDULA).equals(username) && rs.getString(CONTRASENIA).equals(password) && tipoUser.equalsIgnoreCase("Empleado")){
                        typeUserfound = "empleado";
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return typeUserfound;
    }

    public LinkedList<Empleado> getEmpleados() {
        LinkedList<Empleado> listaEmpleado = new LinkedList<>();

        String query = "SELECT " + ID + "," + CEDULA + "," + NOMBRE + "," + APELLIDO + "," + NUMERO_TELEFONO +","+CONTRASENIA+ "," + PUESTO+","+SALARIO + " FROM tbusuario WHERE status = 1;";

        try {
            PreparedStatement prepared = getConnection().prepareStatement(query);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt(ID));
                empleado.setCedula(rs.getString(CEDULA));
                empleado.setNombre(rs.getString(NOMBRE));
                empleado.setApellido(rs.getString(APELLIDO));
                empleado.setNumeroTelefono(rs.getString(NUMERO_TELEFONO));
                empleado.setContrasenia(rs.getString(CONTRASENIA));
                empleado.setPuesto(rs.getString(PUESTO));
                empleado.setSalario(rs.getDouble(SALARIO));
                listaEmpleado.add(empleado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEmpleado;
    }
    
    public LinkedList<Empleado> getListaEmpleadosPorPaginacion(int numPage, int pageSize) {
        LinkedList<Empleado> lista = new LinkedList<>();
        int offset = (numPage-1) * pageSize;
        String query = "SELECT * FROM tbusuario WHERE status = 1 LIMIT ? OFFSET ?;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setInt(1, pageSize);
            pr.setInt(2, offset);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt(ID));
                empleado.setCedula(rs.getString(CEDULA));
                empleado.setNombre(rs.getString(NOMBRE));
                empleado.setApellido(rs.getString(APELLIDO));
                empleado.setNumeroTelefono(rs.getString(NUMERO_TELEFONO));
                empleado.setContrasenia(rs.getString(CONTRASENIA));
                empleado.setPuesto(rs.getString(PUESTO));
                empleado.setSalario(rs.getDouble(SALARIO));
                lista.add(empleado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public int getNumeroTotalEmpleados() {
        int numeroTotal = 0;

        String sql = "SELECT COUNT(*) FROM tbusuario where status = 1;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                numeroTotal = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numeroTotal;
    }

    public Empleado getEmpleadosPorId(int idEmpleado) {
        Empleado empleado = new Empleado();

        String query = "SELECT " + CEDULA + "," + NOMBRE + "," + APELLIDO + "," + NUMERO_TELEFONO + "," + CONTRASENIA + "," + PUESTO+","+SALARIO + " FROM tbusuario WHERE status = 1 AND " + ID + "=" + idEmpleado + ";";

        try {
            PreparedStatement prepared = getConnection().prepareStatement(query);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {

                empleado.setCedula(rs.getString(CEDULA));
                empleado.setNombre(rs.getString(NOMBRE));
                empleado.setApellido(rs.getString(APELLIDO));
                empleado.setNumeroTelefono(rs.getString(NUMERO_TELEFONO));
                empleado.setContrasenia(rs.getString(CONTRASENIA));
                empleado.setPuesto(rs.getString(PUESTO));
                empleado.setSalario(rs.getDouble(SALARIO));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DataEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleado;
    }

    public boolean insertatEmpleado(Empleado empleado) {
        boolean inserto = false;

        String query = "INSERT INTO tbusuario (" + CEDULA + "," + NOMBRE + "," + APELLIDO + "," + NUMERO_TELEFONO + "," + CONTRASENIA + ",status," + PUESTO+","+SALARIO + ") VALUES (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement prepared = getConnection().prepareStatement(query);
            prepared.setString(1, empleado.getCedula());
            prepared.setString(2, empleado.getNombre());
            prepared.setString(3, empleado.getApellido());
            prepared.setString(4, empleado.getNumeroTelefono());
            prepared.setString(5, empleado.getContrasenia());
            prepared.setInt(6, 1);
            prepared.setString(7, empleado.getPuesto());
            prepared.setDouble(8, empleado.getSalario());
            prepared.executeUpdate();
            inserto = true;
        } catch (SQLException ex) {
            Logger.getLogger(DataEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserto;
    }

    public boolean actualizarEmpleado(Empleado empleado, int idEmpleado) {
        boolean actualizo = false;

        String query = "UPDATE tbusuario SET " + CEDULA + "=?," + NOMBRE + "=?," + APELLIDO + "=?," + NUMERO_TELEFONO + "=?," + CONTRASENIA + "=?," + PUESTO+"=?,"+SALARIO + "=? WHERE " + ID + "=" + idEmpleado + ";";

        try {
            PreparedStatement prepared = getConnection().prepareStatement(query);
            prepared.setString(1, empleado.getCedula());
            prepared.setString(2, empleado.getNombre());
            prepared.setString(3, empleado.getApellido());
            prepared.setString(4, empleado.getNumeroTelefono());
            prepared.setString(5, empleado.getContrasenia());
            prepared.setString(6, empleado.getPuesto());
            prepared.setDouble(7, empleado.getSalario());
            prepared.executeUpdate();
            actualizo = true;
        } catch (SQLException ex) {
            Logger.getLogger(DataEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return actualizo;
    }

    public boolean deshabilitarEmpleado(int idEmpleado) {
        boolean elimino = false;
        String query = "UPDATE tbusuario SET status=? WHERE id=" + idEmpleado + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setInt(1, 0);
            pr.executeUpdate();
            elimino = true;
        } catch (SQLException ex) {
            Logger.getLogger(DataEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elimino;
    }

    public static void main(String[] args) {
        LinkedList<Empleado> lista = new DataEmpleado().getListaEmpleadosPorPaginacion(1, 3);
        
        for(Empleado g:lista){
            System.out.println(g.getNombre());
            System.out.println("\n");
        }
    }
}
