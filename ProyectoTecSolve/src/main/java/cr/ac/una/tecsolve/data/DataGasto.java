package cr.ac.una.tecsolve.data;

import static cr.ac.una.tecsolve.data.ServiciosData.DESCRIPCION;
import static cr.ac.una.tecsolve.data.ServiciosData.ENCARGADO;
import static cr.ac.una.tecsolve.data.ServiciosData.HORARIO;
import static cr.ac.una.tecsolve.data.ServiciosData.ID;
import static cr.ac.una.tecsolve.data.ServiciosData.NOMBRE;
import static cr.ac.una.tecsolve.data.ServiciosData.PRECIO;
import static cr.ac.una.tecsolve.data.ServiciosData.TBINVENTARIO;
import cr.ac.una.tecsolve.domain.Gasto;
import cr.ac.una.tecsolve.domain.Servicios;
import java.sql.Connection;
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
public class DataGasto extends BaseData {

    
    public final static String ID = "id";
    public final static String FECHA = "fecha";
    public final static String DESCRIPCION = "descripcion";
    public final static String CATEGORIA = "categoria";
    public final static String MONTO = "monto";
    public final static String DETALLE = "detalle";
    public final static String TBGASTOS = "tbgastos";

    public boolean insertarGasto(Gasto gasto) {
        boolean inserto = false;

        String query = "INSERT INTO " + TBGASTOS + "(" + FECHA + "," + DESCRIPCION + "," + CATEGORIA + "," + MONTO + "," + DETALLE + ",status) VALUES(?,?,?,?,?,?);";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setDate(1, gasto.getFecha());
            pr.setString(2, gasto.getDescripcion());
            pr.setString(3, gasto.getCategoria());
            pr.setDouble(4, gasto.getMonto());
            pr.setString(5, gasto.getDetalle());
            pr.setBoolean(6, true);
            pr.executeUpdate();
            inserto = true;
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataGasto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserto;
    }

    public LinkedList<Gasto> getListaGastosPorPaginacion(int numPage, int pageSize) {
        LinkedList<Gasto> lista = new LinkedList<>();
        int offset = (numPage - 1) * pageSize;
        String query = "SELECT * FROM " + TBGASTOS + " WHERE status = 1 LIMIT ? OFFSET ?;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setInt(1, pageSize);
            pr.setInt(2, offset);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) { 
                
                Gasto g = new Gasto();
                g.setId(rs.getInt(ID));
                g.setFecha(rs.getDate(FECHA));
                g.setDescripcion(rs.getString(DESCRIPCION));
                g.setCategoria(rs.getString(CATEGORIA));
                g.setMonto(rs.getDouble(MONTO));
                g.setDetalle(rs.getString(DETALLE));
                lista.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataGasto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public int getNumeroTotalGastos() {
        int numeroTotal = 0;

        String sql = "SELECT COUNT(*) FROM " + TBGASTOS + " where status = 1;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                numeroTotal = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataGasto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numeroTotal;
    }

    public LinkedList<Gasto> getListaGastos() {
        LinkedList<Gasto> lista = new LinkedList<>();

        String query = "SELECT * FROM " + TBGASTOS + " WHERE status = 1;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Gasto g = new Gasto();
                g.setId(rs.getInt(ID));
                g.setFecha(rs.getDate(FECHA));
                g.setDescripcion(rs.getString(DESCRIPCION));
                g.setCategoria(rs.getString(CATEGORIA));
                g.setMonto(rs.getDouble(MONTO));
                g.setDetalle(rs.getString(DETALLE));
                lista.add(g);
            }
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataGasto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public Gasto getGastoPorId(int idGasto) {
        Gasto g = new Gasto();

        String query = "SELECT " + ID + "," + FECHA + "," + DESCRIPCION + "," + CATEGORIA + "," + MONTO + "," + DETALLE + " FROM " + TBGASTOS + " WHERE status = 1 and id=" + idGasto + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                g.setId(rs.getInt(ID));
                g.setFecha(rs.getDate(FECHA));
                g.setDescripcion(rs.getString(DESCRIPCION));
                g.setCategoria(rs.getString(CATEGORIA));
                g.setMonto(rs.getDouble(MONTO));
                g.setDetalle(rs.getString(DETALLE));
            }
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataGasto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;
    }

    public boolean actualizarGasto(Gasto gasto) {
        boolean actualizo = false;

        String query = "UPDATE " + TBGASTOS + " SET " + FECHA + "=?," + DESCRIPCION + "=?," + CATEGORIA + "=?," + MONTO + "=?," + DETALLE + "=? WHERE " + ID + "=" + gasto.getId() + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setDate(1, gasto.getFecha());
            pr.setString(2, gasto.getDescripcion());
            pr.setString(3, gasto.getCategoria());
            pr.setDouble(4, gasto.getMonto());
            pr.setString(5, gasto.getDetalle());
            pr.executeUpdate();
            actualizo = true;
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataGasto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actualizo;
    }

    public boolean deshabilitarGasto(int idGasto) {
        boolean elimino = false;
        String query = "UPDATE " + TBGASTOS + " SET status=? WHERE id=" + idGasto + ";";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setInt(1, 0);
            pr.executeUpdate();
            elimino = true;
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataGasto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elimino;
    }

    public static void main(String[] args) {

        LinkedList<Gasto> lista = new DataGasto().getListaGastosPorPaginacion(1, 2);

        for (Gasto g : lista) {
            System.out.println(g.getDescripcion());
            System.out.println("\n");
        }
        
    }
public LinkedList<Gasto> buscarDatos(String descripcion) {
    LinkedList<Gasto> lista = new LinkedList<Gasto>();
    String query = "SELECT * FROM " + TBGASTOS + " WHERE descripcion=?";
    Connection con = getConnection();
    try {
        PreparedStatement prepared = con.prepareStatement(query);
        prepared.setString(1, descripcion);
        ResultSet rs = prepared.executeQuery();
        while (rs.next()) {
            Gasto g = new Gasto();
            g.setId(rs.getInt(ID));
            g.setFecha(rs.getDate(FECHA));
            g.setDescripcion(rs.getString(DESCRIPCION));
            g.setCategoria(rs.getString(CATEGORIA));
            g.setMonto(rs.getDouble(MONTO));
            g.setDetalle(rs.getString(DETALLE));
            lista.add(g);
        }
        prepared.close();
    } catch (SQLException ex) {
        Logger.getLogger(DataGasto.class.getName()).log(Level.SEVERE, null, ex);
    }
    return lista;
}


}
