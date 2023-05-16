package cr.ac.una.tecsolve.data;

import cr.ac.una.tecsolve.domain.Proveedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kinco
 */

public class DataProveedor extends BaseData {

    public final static String ID = "id";
    public final static String NOMBRE = "nombre";
    public final static String DIRECCION = "direccion";
    public final static String TELEFONO = "telefono";
    public final static String EMAIL = "email";
    public final static String TBPROVEEDORES = "tbproveedores";

    public boolean insertarProveedor(Proveedor proveedor) {
        boolean inserto = false;

        String query = "INSERT INTO " + TBPROVEEDORES + "(" + NOMBRE + "," + DIRECCION + "," + TELEFONO + "," + EMAIL + ") VALUES(?,?,?,?);";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setString(1, proveedor.getNombre());
            pr.setString(2, proveedor.getDireccion());
            pr.setString(3, proveedor.getTelefono());
            pr.setString(4, proveedor.getEmail());
            pr.executeUpdate();
            inserto = true;
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserto;
    }

    public LinkedList<Proveedor> getListaProveedoresPorPaginacion(int numPage, int pageSize) {
        LinkedList<Proveedor> lista = new LinkedList<>();
        int offset = (numPage-1) * pageSize;
        String query = "SELECT * FROM " + TBPROVEEDORES + " LIMIT ? OFFSET ?;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setInt(1, pageSize);
            pr.setInt(2, offset);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt(ID));
                proveedor.setNombre(rs.getString(NOMBRE));
                proveedor.setDireccion(rs.getString(DIRECCION));
                proveedor.setTelefono(rs.getString(TELEFONO));
                proveedor.setEmail(rs.getString(EMAIL));
                lista.add(proveedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public int getNumeroTotalProveedores() {
        int numeroTotal = 0;

        String sql = "SELECT COUNT(*) FROM " + TBPROVEEDORES + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                numeroTotal = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numeroTotal;
    }

    public LinkedList<Proveedor> getListaProveedores() {
        LinkedList<Proveedor> lista = new LinkedList<>();

        String query = "SELECT * FROM " + TBPROVEEDORES + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt(ID));
                proveedor.setNombre(rs.getString(NOMBRE));
                proveedor.setDireccion(rs.getString(DIRECCION));
                proveedor.setTelefono(rs.getString(TELEFONO));
                proveedor.setEmail(rs.getString(EMAIL));
                lista.add(proveedor);
            }
            pr.close();
        }catch (SQLException ex) {
            Logger.getLogger(DataProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public Proveedor getProveedorPorId(int idProveedor) {
        Proveedor proveedor = new Proveedor();

        String query = "SELECT " + ID + "," + NOMBRE + "," + DIRECCION + "," + TELEFONO + "," + EMAIL + " FROM " + TBPROVEEDORES + " WHERE id=" + idProveedor + ";";

    try {
        PreparedStatement pr = getConnection().prepareStatement(query);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            proveedor.setId(rs.getInt(ID));
            proveedor.setNombre(rs.getString(NOMBRE));
            proveedor.setDireccion(rs.getString(DIRECCION));
            proveedor.setTelefono(rs.getString(TELEFONO));
            proveedor.setEmail(rs.getString(EMAIL));
        }
        pr.close();
    } catch (SQLException ex) {
        Logger.getLogger(DataProveedor.class.getName()).log(Level.SEVERE, null, ex);
    }
        return proveedor;
    }

    public boolean actualizarProveedor(Proveedor proveedor) {
        boolean actualizo = false;

        String query = "UPDATE " + TBPROVEEDORES + " SET " + NOMBRE + "=?," + DIRECCION + "=?," + TELEFONO + "=?," + EMAIL + "=? WHERE " + ID + "=" + proveedor.getId() + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setString(1, proveedor.getNombre());
            pr.setString(2, proveedor.getDireccion());
            pr.setString(3, proveedor.getTelefono());
            pr.setString(4, proveedor.getEmail());
            pr.executeUpdate();
            actualizo = true;
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actualizo;
    }

    public boolean eliminarProveedor(int idProveedor) {
        boolean elimino = false;
        String query = "DELETE FROM " + TBPROVEEDORES + " WHERE id=" + idProveedor + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.executeUpdate();
            elimino = true;
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elimino;
    }

    public static void main(String[] args) {
        LinkedList<Proveedor> lista = new DataProveedor().getListaProveedoresPorPaginacion(1, 2);
        for (Proveedor proveedor : lista) {
            System.out.println(proveedor.getNombre());
            System.out.println("\n");
        }
    }
}
