package cr.ac.una.tecsolve.data;

import cr.ac.una.tecsolve.domain.Factura;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kinco
 */
public class DataFactura extends BaseData {

    public final static String ID = "id";
    public final static String PRODUCTO = "producto";
    public final static String CANTIDAD = "cantidad";
    public final static String PRECIO = "precio";
    public final static String CLIENTE = "cliente";
    public final static String FECHA = "fecha";
    public final static String TBFACTURAS = "tbfactura";

    public boolean insertarFactura(Factura factura) {
        boolean inserto = false;

        String query = "INSERT INTO " + TBFACTURAS + "(" + PRODUCTO + "," + CANTIDAD + "," + PRECIO + "," + CLIENTE + "," + FECHA + ") VALUES(?,?,?,?,?);";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setString(1, factura.getProducto());
            pr.setInt(2, factura.getCantidad());
            pr.setDouble(3, factura.getPrecio());
            pr.setString(4, factura.getCliente());
            java.sql.Date sqlDate = new java.sql.Date(factura.getFecha().getTime());
            pr.setDate(5, sqlDate);
            pr.executeUpdate();
            inserto = true;
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserto;
    }

    public LinkedList<Factura> getListaFacturasPorPaginacion(int numPage, int pageSize) {
        LinkedList<Factura> lista = new LinkedList<>();
        int offset = (numPage - 1) * pageSize;
        String query = "SELECT * FROM " + TBFACTURAS + " LIMIT ? OFFSET ?;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setInt(1, pageSize);
            pr.setInt(2, offset);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Factura f = new Factura();
                f.setId(rs.getInt(ID));
                f.setProducto(rs.getString(PRODUCTO));
                f.setCantidad(rs.getInt(CANTIDAD));
                f.setPrecio(rs.getDouble(PRECIO));
                f.setCliente(rs.getString(CLIENTE));
                f.setFecha(rs.getDate(FECHA));
                lista.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public int getNumeroTotalFacturas() {
        int numeroTotal = 0;

        String sql = "SELECT COUNT(*) FROM " + TBFACTURAS + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                numeroTotal = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataFactura.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numeroTotal;
    }

    public LinkedList<Factura> getListaFacturas() {
        LinkedList<Factura> lista = new LinkedList<>();

        String query = "SELECT * FROM " + TBFACTURAS + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Factura f = new Factura();
                f.setId(rs.getInt(ID));
                f.setProducto(rs.getString(PRODUCTO));
                f.setCantidad(rs.getInt(CANTIDAD));
                f.setPrecio(rs.getDouble(PRECIO));
                f.setCliente(rs.getString(CLIENTE));
                f.setFecha(rs.getDate(FECHA));
                lista.add(f);
            }
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public Factura getFacturaPorId(int idFactura) {
        Factura f = new Factura();

        String query = "SELECT * FROM " + TBFACTURAS + " WHERE id=" + idFactura + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                f.setId(rs.getInt(ID));
                f.setProducto(rs.getString(PRODUCTO));
                f.setCantidad(rs.getInt(CANTIDAD));
                f.setPrecio(rs.getDouble(PRECIO));
                f.setCliente(rs.getString(CLIENTE));
                f.setFecha(rs.getDate(FECHA));
            }
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public boolean actualizarFactura(Factura factura) {
        boolean actualizo = false;

        String query = "UPDATE " + TBFACTURAS + " SET " + PRODUCTO + "=?," + CANTIDAD + "=?," + PRECIO + "=?," + CLIENTE + "=?," + FECHA + "=? WHERE " + ID + "=" + factura.getId() + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setString(1, factura.getProducto());
            pr.setInt(2, factura.getCantidad());
            pr.setDouble(3, factura.getPrecio());
            pr.setString(4, factura.getCliente());
            java.sql.Date sqlDate = new java.sql.Date(factura.getFecha().getTime());
            pr.setDate(5, sqlDate);
            pr.executeUpdate();
            actualizo = true;
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actualizo;
    }

    public boolean eliminarFactura(int idFactura) {
        boolean elimino = false;
        String query = "DELETE FROM " + TBFACTURAS + " WHERE id=" + idFactura + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.executeUpdate();
            elimino = true;
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elimino;
    }

    public LinkedList<Factura> buscarDatos(String descripcion) {
        LinkedList<Factura> lista = new LinkedList<Factura>();
        String query = "SELECT * FROM " + TBFACTURAS + " WHERE producto=?";
        Connection con = getConnection();
        try {
            PreparedStatement prepared = con.prepareStatement(query);
            prepared.setString(1, descripcion);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                Factura f = new Factura();
                f.setId(rs.getInt(ID));
                f.setFecha(rs.getDate(FECHA));
                f.setProducto(rs.getString(PRODUCTO));
                f.setCliente(rs.getString(CLIENTE));
                f.setCantidad(rs.getInt(CANTIDAD));
                f.setPrecio(rs.getDouble(PRECIO));
                lista.add(f);
            }
            prepared.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
