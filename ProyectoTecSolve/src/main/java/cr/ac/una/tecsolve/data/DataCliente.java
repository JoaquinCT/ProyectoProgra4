package cr.ac.una.tecsolve.data;

import static cr.ac.una.tecsolve.data.ServiciosData.DESCRIPCION;
import static cr.ac.una.tecsolve.data.ServiciosData.ENCARGADO;
import static cr.ac.una.tecsolve.data.ServiciosData.HORARIO;
import static cr.ac.una.tecsolve.data.ServiciosData.ID;
import static cr.ac.una.tecsolve.data.ServiciosData.NOMBRE;
import static cr.ac.una.tecsolve.data.ServiciosData.PRECIO;
import static cr.ac.una.tecsolve.data.ServiciosData.TBINVENTARIO;
import cr.ac.una.tecsolve.domain.Cliente;
import cr.ac.una.tecsolve.domain.Servicios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maizeth Cisneros
 */
public class DataCliente extends BaseData {

    public static final String TBCLIENTE = "tbcliente";

    public void insertarCliente(Cliente cliente) {
        String query = "INSERT INTO " + TBCLIENTE
                + " (nombre,apellido,cedula,numeroTelefono,correo) VALUES (?,?,?,?,?);";

        Connection conexion = getConnection();
        try {

            PreparedStatement statement = conexion.prepareStatement(query);

            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getCedula());
            statement.setString(4, cliente.getNumeroTelefono());
            statement.setString(5, cliente.getCorreo());

            statement.executeUpdate();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LinkedList<Cliente> listarClientes() {
        String sql = "SELECT * FROM " + TBCLIENTE + ";";
        Connection conexion = getConnection();
        LinkedList<Cliente> clientes = new LinkedList<>();
        try {
            PreparedStatement prepared = conexion.prepareStatement(sql);
            ResultSet rs = prepared.executeQuery();

            while (rs.next()) {
                Cliente temp = new Cliente();
                temp.setId_cliente(rs.getInt("id"));
                temp.setNombre(rs.getString("nombre"));
                temp.setApellido(rs.getString("apellido"));
                temp.setCedula(rs.getString("cedula"));
                temp.setNumeroTelefono(rs.getString("numeroTelefono"));
                temp.setCorreo(rs.getString("correo"));
                clientes.add(temp);
            }
            prepared.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;

    }
    
     public LinkedList<Cliente> getListaClientesPorPaginacion(int numPage, int pageSize) {
        LinkedList<Cliente> lista = new LinkedList<>();
        int offset = (numPage-1) * pageSize;
        String query = "SELECT * FROM tbcliente LIMIT ? OFFSET ?;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setInt(1, pageSize);
            pr.setInt(2, offset);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Cliente temp = new Cliente();
                temp.setId_cliente(rs.getInt("id"));
                temp.setNombre(rs.getString("nombre"));
                temp.setApellido(rs.getString("apellido"));
                temp.setCedula(rs.getString("cedula"));
                temp.setNumeroTelefono(rs.getString("numeroTelefono"));
                temp.setCorreo(rs.getString("correo"));
                lista.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public int getNumeroTotalclientes() {
        int numeroTotal = 0;

        String sql = "SELECT COUNT(*) FROM tbcliente;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                numeroTotal = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numeroTotal;
    }

    public void editarDatosCliente(Cliente cliente) {

        //System.out.println(cliente.getCorreo());
        String sql = "UPDATE tbcliente SET nombre=?, apellido=?, cedula=?, numeroTelefono=?, correo=? WHERE id=" + cliente.getId_cliente() + ";";
        Connection conexion = getConnection();

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCedula());
            ps.setString(4, cliente.getNumeroTelefono());
            ps.setString(5, cliente.getCorreo());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarNumero(int codigo) {

        String sql = "DELETE FROM tbcliente WHERE id=" + codigo + ";";
        Connection conexion = this.getConnection();

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {

        DataCliente data = new DataCliente();
        data.editarDatosCliente(new Cliente(2, "Leo", "lopez", "8273732", "37388733", "leo.lopez@gmail.com"));

        //Cliente c = new Cliente("Mauro", "Alvaro", "7065402352", 87654321, "alvaro235@gmail.com");
    }
public LinkedList<Cliente> buscarDatos(String nombre) {
    LinkedList<Cliente> clientes = new LinkedList<>();
    String query = "SELECT * FROM " + TBCLIENTE + " WHERE nombre=?";
    Connection con = getConnection();
    try {
        PreparedStatement prepared = con.prepareStatement(query);
        prepared.setString(1, nombre);
        ResultSet result = prepared.executeQuery();
        while (result.next()) {
            Cliente temp = new Cliente();
            temp.setId_cliente(result.getInt("id"));
            temp.setNombre(result.getString("nombre"));
            temp.setApellido(result.getString("apellido"));
            temp.setCedula(result.getString("cedula"));
            temp.setNumeroTelefono(result.getString("numeroTelefono"));
            temp.setCorreo(result.getString("correo"));
            clientes.add(temp);
        }
        prepared.close();
        con.close();
    } catch (SQLException ex) {
        Logger.getLogger(DataCliente.class.getName()).log(Level.SEVERE, null, ex);
    }
    return clientes;
}

}
