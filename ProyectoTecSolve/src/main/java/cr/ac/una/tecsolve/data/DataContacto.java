package cr.ac.una.tecsolve.data;

import cr.ac.una.tecsolve.domain.Contacto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maizeth Cisneros
 */
public class DataContacto extends BaseData {

    
    public final static String ID = "id";
    public final static String NUMEROWHATSAPP = "numeroWhatsapp";
    public final static String NUMEROTELEFONO = "numeroTelefono";
    public final static String FACEBOOK = "facebook";
    public final static String CORREO = "correo";
    public final static String INSTAGRAM = "instagram";
    public final static String TBCONTACTOS = "tbcontactos";

    public boolean insertarContacto(Contacto contacto) {
        boolean inserto = false;

        String query = "INSERT INTO " + TBCONTACTOS + "(" + NUMEROWHATSAPP + "," + NUMEROTELEFONO + "," + FACEBOOK + "," + CORREO + "," + INSTAGRAM + ",status) VALUES(?,?,?,?,?,?);";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setInt(1, contacto.getNumeroWhatsapp());
            pr.setInt(2, contacto.getNumeroTelefono());
            pr.setString(3, contacto.getFacebook());
            pr.setString(4, contacto.getCorreo());
            pr.setString(5, contacto.getInstagram());
            pr.setBoolean(6, true);
            pr.executeUpdate();
            inserto = true;
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataContacto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserto;
    }

    public LinkedList<Contacto> getListaContactosPorPaginacion(int numPage, int pageSize) {
        LinkedList<Contacto> lista = new LinkedList<>();
        int offset = (numPage - 1) * pageSize;
        String query = "SELECT * FROM " + TBCONTACTOS + " WHERE status = 1 LIMIT ? OFFSET ?;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setInt(1, pageSize);
            pr.setInt(2, offset);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) { 
                
                Contacto c = new Contacto();
                c.setId(rs.getInt(ID));
                c.setNumeroWhatsapp(rs.getInt(NUMEROWHATSAPP));
                c.setNumeroTelefono(rs.getInt(NUMEROTELEFONO));
                c.setFacebook(rs.getString(FACEBOOK));
                c.setCorreo(rs.getString(CORREO));
                c.setInstagram(rs.getString(INSTAGRAM));
                lista.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataContacto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public int getNumeroTotalContactos() {
        int numeroTotal = 0;

        String sql = "SELECT COUNT(*) FROM " + TBCONTACTOS + " where status = 1;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                numeroTotal = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataContacto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numeroTotal;
    }

    public LinkedList<Contacto> getListaContactos() {
        LinkedList<Contacto> lista = new LinkedList<>();

        String query = "SELECT * FROM " + TBCONTACTOS + " WHERE status = 1;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Contacto c = new Contacto();
                c.setId(rs.getInt(ID));
                c.setNumeroWhatsapp(rs.getInt(NUMEROWHATSAPP));
                c.setNumeroTelefono(rs.getInt(NUMEROTELEFONO));
                c.setFacebook(rs.getString(FACEBOOK));
                c.setCorreo(rs.getString(CORREO));
                c.setInstagram(rs.getString(INSTAGRAM));
                lista.add(c);
            }
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataGasto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public Contacto getContactoPorId(int idContacto) {
        Contacto c = new Contacto();

        String query = "SELECT " + ID + "," + NUMEROWHATSAPP + "," + NUMEROTELEFONO + "," + FACEBOOK + "," + CORREO + "," + INSTAGRAM + " FROM " + TBCONTACTOS + " WHERE status = 1 and id=" + idContacto + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt(ID));
                c.setNumeroWhatsapp(rs.getInt(NUMEROWHATSAPP));
                c.setNumeroTelefono(rs.getInt(NUMEROTELEFONO));
                c.setFacebook(rs.getString(FACEBOOK));
                c.setCorreo(rs.getString(CORREO));
                c.setInstagram(rs.getString(INSTAGRAM));
            }
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataContacto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public boolean actualizarContacto(Contacto Contacto) {
        boolean actualizo = false;

        String query = "UPDATE " + TBCONTACTOS + " SET " + NUMEROWHATSAPP + "=?," + NUMEROTELEFONO + "=?," + FACEBOOK + "=?," + CORREO + "=?," + INSTAGRAM + "=? WHERE " + ID + "=" + contacto.getId() + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setInt(1, contacto.getNumeroWhatsapp());
            pr.setInt(2, contacto.getNumeroTelefono());
            pr.setString(3, contacto.getFacebook());
            pr.setString(4, contacto.getCorreo());
            pr.setString(5, contacto.getInstagram());
            pr.executeUpdate();
            actualizo = true;
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataContacto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actualizo;
    }

    public boolean deshabilitarContacto(int idContacto) {
        boolean elimino = false;
        String query = "UPDATE " + TBCONTACTOS + " SET status=? WHERE id=" + idContacto + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setInt(1, 0);
            pr.executeUpdate();
            elimino = true;
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataContacto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elimino;
    }

    public static void main(String[] args) {

        LinkedList<Contacto> lista = new DataContacto().getListaContactosPorPaginacion(1, 2);

        for (Contacto c : lista) {
            System.out.println(c.getNumeroWhatsapp());
            System.out.println("\n");
        }
        
    }
    
}
