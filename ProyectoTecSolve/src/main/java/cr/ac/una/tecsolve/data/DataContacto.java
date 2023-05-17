package cr.ac.una.tecsolve.data;

import cr.ac.una.proyecto.domain.Contacto;
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
public class DataContactoextends DataBase {

    public static final String TBCONTACTO = "tbcontacto";

    public void insertarContacto(Contacto contacto) {
        String query = "INSERT INTO " + TBCONTACTO
                + " (nombre,apellido,cedula,numeroTelefono,correo) VALUES (?,?,?,?,?);";

        Connection conexion = getConexion();
        try {

            PreparedStatement statement = conexion.prepareStatement(query);

            statement.setString(1, contacto.getNumeroWhatsapp());
            statement.setString(2, contacto.getNumeroTelefono());
            statement.setString(3, contacto.getfacebook());
            statement.setString(4, contacto.getCorreo());
            statement.setString(5, contacto.getInstagram());

            statement.executeUpdate();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataContacto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LinkedList<Contacto> listarContactos() {
        String sql = "SELECT * FROM " + TBCONTACTO + ";";
        Connection conexion = getConexion();
        LinkedList<Contacro> contactos = new LinkedList<>();
        try {
            PreparedStatement prepared = conexion.prepareStatement(sql);
            ResultSet rs = prepared.executeQuery();

            while (rs.next()) {
                Contacto temp = new Contacto();
                temp.setId_contacto(rs.getInt("id"));
                temp.setNumeroWhatsapp(rs.getString("numeroWhatsapp"));
                temp.setNumeroTelefono(rs.getString("numeroTelefono"));
                temp.setFacebook(rs.getString("facebook"));
                temp.setCorreo(rs.getString("correo"));
                temp.setInstagram(rs.getString("instagram"));
                contactos.add(temp);
            }
            prepared.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return contactos;

    }

    public void editarDatosContactos(Contacto contacto) {

        System.out.println(contacto.getCorreo());
        String sql = "UPDATE tbcontacto SET numeroWhatsapp=?, numeroTelefono=?, facebook=?, correo=?, instagram=? WHERE id=" + contacto.getId_contacto() + ";";
        Connection conexion = getConexion();

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, contacto.getNumeroWhatsapp());
            ps.setString(2, contacto.getNumeroTelefono());
            ps.setString(3, contacto.getFacebook());
            ps.setString(4, contacto.getCorreo());
            ps.setString(5, contacto.getInstagram());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataContacto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarNumero(int codigo) {

        String sql = "DELETE FROM tbcontacto WHERE id=" + codigo + ";";
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

        DataContacto data = new DataContacto();
        data.editarDatosContacto(new Contacto(1, "85749632", "27105468", "tecsolve", "tecsolve@gmail.com", "tec_solve"));


    }

}
