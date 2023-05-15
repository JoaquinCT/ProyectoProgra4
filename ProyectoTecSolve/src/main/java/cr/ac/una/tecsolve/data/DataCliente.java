package cr.ac.una.proyecto.data;

import cr.ac.una.proyecto.domain.Cliente;
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
public class DataCliente extends DataBase {

    public static final String TBCLIENTE = "tbcliente";

    public void insertarCliente(Cliente cliente) {
        String query = "INSERT INTO " + TBCLIENTE
                + " (nombre,apellido,cedula,numeroTelefono,correo) VALUES (?,?,?,?,?);";

        Connection conexion = getConexion();
        try {

            PreparedStatement statement = conexion.prepareStatement(query);

            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getCedula());
            statement.setString(4, cliente.getTelefono());
            statement.setString(5, cliente.getCorreo());

            statement.executeUpdate();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LinkedList<Cliente> listarClientes() {
        String sql = "SELECT * FROM " + TBCLIENTE + ";";
        Connection conexion = getConexion();
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
                temp.setTelefono(rs.getString("numeroTelefono"));
                temp.setCorreo(rs.getString("correo"));
                clientes.add(temp);
            }
            prepared.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;

    }

    public void editarDatosCliente(Cliente cliente) {

        System.out.println(cliente.getCorreo());
        String sql = "UPDATE tbcliente SET nombre=?, apellido=?, cedula=?, numeroTelefono=?, correo=? WHERE id=" + cliente.getId_cliente() + ";";
        Connection conexion = getConexion();

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCedula());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getCorreo());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarNumero(int codigo) {

        String sql = "DELETE FROM tbcliente WHERE id=" + codigo + ";";
        Connection conexion = this.getConexion();

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

}
