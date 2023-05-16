package cr.ac.una.tecsolve.data;

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
 * @author Usuario
 */
public class ServiciosData extends BaseData {

    public final static String TBINVENTARIO = "tbservicios";
    public final static String ID = "id";
    public final static String NOMBRE = "nombre";
    public final static String DESCRIPCION = "descripcion";
    public final static String HORARIO = "horario";
    public final static String PRECIO = "precio";
    public final static String ENCARGADO = "encargado";

    public LinkedList<Servicios> getEspacios() {
        LinkedList<Servicios> inventario = new LinkedList<Servicios>();
        String query = "SELECT * FROM " + TBINVENTARIO + ";";
        Connection con = getConnection();
        try {
            PreparedStatement prepared = con.prepareStatement(query);

            ResultSet result = prepared.executeQuery();
            Servicios i = null;
            while (result.next()) {
                i = new Servicios();
                i.setId(result.getInt(ID));
                i.setNombreServicio(result.getString(NOMBRE));
                i.setDescripcion(result.getString(DESCRIPCION));
                i.setHorario(result.getString(HORARIO));
                i.setPrecio(result.getFloat(PRECIO));
                i.setEncargado(result.getString(ENCARGADO));

                inventario.add(i);
            }
            prepared.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(InventarioData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventario;
    }

    public LinkedList<Servicios> getListaServiciosPorPaginacion(int numPage, int pageSize) {
        LinkedList<Servicios> lista = new LinkedList<>();
        int offset = (numPage - 1) * pageSize;
        String query = "SELECT * FROM tbservicios LIMIT ? OFFSET ?;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setInt(1, pageSize);
            pr.setInt(2, offset);
            ResultSet result = pr.executeQuery();
             Servicios i = null;
            while (result.next()) {
                i = new Servicios();
                i.setId(result.getInt(ID));
                i.setNombreServicio(result.getString(NOMBRE));
                i.setDescripcion(result.getString(DESCRIPCION));
                i.setHorario(result.getString(HORARIO));
                i.setPrecio(result.getFloat(PRECIO));
                i.setEncargado(result.getString(ENCARGADO));

                lista.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public int getNumeroTotalServicios() {
        int numeroTotal = 0;

        String sql = "SELECT COUNT(*) FROM tbservicios;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                numeroTotal = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiciosData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numeroTotal;
    }
    //---------------------------------Metodo de insertar a la base de datos---------------------------

    public boolean insertar(Servicios inventario) {
        boolean inserto = false;

        //Sentencia query de la base de datos.
        String query = "INSERT INTO " + TBINVENTARIO + "(" + NOMBRE + "," + DESCRIPCION + "," + HORARIO + ", " + PRECIO + "," + ENCARGADO + ") VALUES (?,?,?,?,?)";

        //Conexion a la base de datos.
        Connection conexion = this.getConnection();
        try {
            PreparedStatement prepared = conexion.prepareStatement(query);
            //Seteo los datos en los valores respectivos de mi base de datos.
            //Con las posiciones respectivas.
            prepared.setString(1, inventario.getNombreServicio());
            prepared.setString(2, inventario.getDescripcion());
            prepared.setString(3, inventario.getHorario());
            prepared.setFloat(4, inventario.getPrecio());
            prepared.setString(5, inventario.getEncargado());

            prepared.executeUpdate(); //Envia la sentencia a la base de datos. -->Sentencia de insertar(insercion)
            inserto = true;           //Para cambiar el valor del inserto a true, para saber si se realizo o no el insert
            prepared.close();
            conexion.close();   //Cierra la conexion con la bd.
        } catch (SQLException ex) {
            Logger.getLogger(InventarioData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserto;
    }

    public boolean eliminar(int id) {
        boolean elimino = false;

        //Sentencia query de la base de datos.
        String query = "DELETE FROM " + TBINVENTARIO + " WHERE id=?";

        //Conexion a la base de datos.
        Connection conexion = getConnection();
        try {
            PreparedStatement prepared = conexion.prepareStatement(query);
            //Seteo los datos en los valores respectivos de mi base de datos.
            //Con las posiciones respectivas.
            prepared.setInt(1, id);

            prepared.executeUpdate(); //Envia la sentencia a la base de datos. -->Sentencia de insertar(insercion)
            elimino = true;           //Para cambiar el valor del inserto a true, para saber si se realizo o no el insert
            prepared.close();
            //conexion.close();   //Cierra la conexion con la bd.
        } catch (SQLException ex) {
            Logger.getLogger(InventarioData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elimino;
    }

    public LinkedList<Servicios> mostrarDatos(int id) {
        LinkedList<Servicios> inventario = new LinkedList<Servicios>();
        String query = "SELECT * FROM " + TBINVENTARIO + " WHERE id=?";
        Connection con = getConnection();
        try {
            PreparedStatement prepared = con.prepareStatement(query);
            prepared.setInt(1, id);
            ResultSet result = prepared.executeQuery();
            Servicios i = null;
            while (result.next()) {
                i = new Servicios();
                i.setId(result.getInt(ID));
                i.setNombreServicio(result.getString(NOMBRE));
                i.setDescripcion(result.getString(DESCRIPCION));
                i.setHorario(result.getString(HORARIO));
                i.setPrecio(result.getFloat(PRECIO));
                i.setEncargado(result.getString(ENCARGADO));

                inventario.add(i);
            }
            prepared.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(InventarioData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventario;
    }

    public boolean actualizar(Servicios inventario, int id) {
        boolean actualizado = false;
        Connection conexion = null;
        PreparedStatement prepared = null;

        try {
            conexion = getConnection();
            String query = "UPDATE " + TBINVENTARIO + " SET nombre=?, descripcion=?,horario=?, precio=?, encargado=? WHERE id=?";
            prepared = conexion.prepareStatement(query);

            prepared.setString(1, inventario.getNombreServicio());
            prepared.setString(2, inventario.getDescripcion());
            prepared.setString(3, inventario.getHorario());
            prepared.setFloat(4, inventario.getPrecio());
            prepared.setString(5, inventario.getEncargado());
            prepared.setInt(6, id);

            int rowsAffected = prepared.executeUpdate();
            if (rowsAffected > 0) {
                actualizado = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventarioData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (prepared != null) {
                try {
                    prepared.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InventarioData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InventarioData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return actualizado;
    }

    public LinkedList<Servicios> BuscarDatos(String nombre) {
        LinkedList<Servicios> inventario = new LinkedList<Servicios>();
        String query = "SELECT * FROM " + TBINVENTARIO + " WHERE nombre=?";
        Connection con = getConnection();
        try {
            PreparedStatement prepared = con.prepareStatement(query);
            prepared.setString(1, nombre);
            ResultSet result = prepared.executeQuery();
            Servicios i = null;
            while (result.next()) {
                i = new Servicios();
                i.setId(result.getInt(ID));
                i.setNombreServicio(result.getString(NOMBRE));
                i.setDescripcion(result.getString(DESCRIPCION));
                i.setHorario(result.getString(HORARIO));
                i.setPrecio(result.getFloat(PRECIO));
                i.setEncargado(result.getString(ENCARGADO));

                inventario.add(i);
            }
            prepared.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(InventarioData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventario;
    }
}
