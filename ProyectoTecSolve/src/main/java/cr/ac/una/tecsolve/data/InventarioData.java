
package cr.ac.una.tecsolve.data;

import static cr.ac.una.tecsolve.data.ServiciosData.DESCRIPCION;
import static cr.ac.una.tecsolve.data.ServiciosData.ENCARGADO;
import static cr.ac.una.tecsolve.data.ServiciosData.HORARIO;
import static cr.ac.una.tecsolve.data.ServiciosData.ID;
import static cr.ac.una.tecsolve.data.ServiciosData.NOMBRE;
import static cr.ac.una.tecsolve.data.ServiciosData.PRECIO;
import static cr.ac.una.tecsolve.data.ServiciosData.TBINVENTARIO;
import cr.ac.una.tecsolve.domain.Inventario;
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
 * @author Usuario
 */

public class InventarioData extends BaseData {
    public final static String TBINVENTARIO = "tbinventario";
    public final static String ID = "id";
    public final static String CATEGORIA = "categoria";
    public final static String CLASIFICACION = "clasificacion";
    public final static String NOMBRE = "nombre";
    public final static String CANTIDAD = "cantidad";
    public final static String PRECIO = "precio";
    
    
    
    public LinkedList<Inventario> getEspacios(String nombre){
        LinkedList<Inventario> inventario = new LinkedList<Inventario>();
        String query = "SELECT * FROM " +TBINVENTARIO + ";" ;
        Connection con = getConnection();
        try {
            PreparedStatement prepared = con.prepareStatement(query);
            
            ResultSet result = prepared.executeQuery();
            Inventario i = null;
            while(result.next()){
                i = new Inventario();
                i.setId(result.getInt(ID));
                i.setCategoria(result.getString(CATEGORIA));
                i.setClasificacion(result.getString(CLASIFICACION));
                i.setNombreProducto(result.getString(NOMBRE));
                i.setCantidad(result.getInt(CANTIDAD));
                i.setPrecio(result.getFloat(PRECIO));
                
                
                inventario.add(i);
            }
            prepared.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(InventarioData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventario;
    }
    
        public LinkedList<Inventario> getListaInventariosPorPaginacion(int numPage, int pageSize) {
        LinkedList<Inventario> lista = new LinkedList<>();
        int offset = (numPage-1) * pageSize;
        String query = "SELECT * FROM tbinventario LIMIT ? OFFSET ?;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(query);
            pr.setInt(1, pageSize);
            pr.setInt(2, offset);
            ResultSet result = pr.executeQuery();
            Inventario i = null;
            while (result.next()) {
                i = new Inventario();
                i.setId(result.getInt(ID));
                i.setCategoria(result.getString(CATEGORIA));
                i.setClasificacion(result.getString(CLASIFICACION));
                i.setNombreProducto(result.getString(NOMBRE));
                i.setCantidad(result.getInt(CANTIDAD));
                i.setPrecio(result.getFloat(PRECIO));
                
                
                lista.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventarioData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public int getNumeroTotalInventarios() {
        int numeroTotal = 0;

        String sql = "SELECT COUNT(*) FROM tbinventario;";

        try {
            PreparedStatement pr = getConnection().prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                numeroTotal = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventarioData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numeroTotal;
    }
    //---------------------------------Metodo de insertar a la base de datos---------------------------
    
    public boolean insertar(Inventario inventario){
        boolean inserto = false;
        
        //Sentencia query de la base de datos.
        String query = "INSERT INTO "+ TBINVENTARIO+ "("+CATEGORIA+","+NOMBRE+","+PRECIO+", "+CANTIDAD+","+CLASIFICACION+") VALUES (?,?,?,?,?)";
        
        //Conexion a la base de datos.
        Connection conexion = getConnection();
        try {
            PreparedStatement prepared = conexion.prepareStatement(query);
            //Seteo los datos en los valores respectivos de mi base de datos.
            //Con las posiciones respectivas.
            prepared.setString(1, inventario.getCategoria());
            prepared.setString(2, inventario.getNombreProducto());
            prepared.setFloat(3, inventario.getPrecio());
            prepared.setInt(4, inventario.getCantidad());
            prepared.setString(5, inventario.getClasificacion());
           
            prepared.executeUpdate(); //Envia la sentencia a la base de datos. -->Sentencia de insertar(insercion)
            inserto = true;           //Para cambiar el valor del inserto a true, para saber si se realizo o no el insert
            prepared.close();   
            conexion.close();   //Cierra la conexion con la bd.
        } catch (SQLException ex) {
            Logger.getLogger(InventarioData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserto;
    }
    
    
     public  boolean eliminar(int id){
        boolean elimino = false;
        
        //Sentencia query de la base de datos.
        String query = "DELETE FROM "+ TBINVENTARIO+ " WHERE id=?" ;
        
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

     
     
     
   public LinkedList<Inventario> mostrarDatos(int id) {
  LinkedList<Inventario> inventario = new LinkedList<Inventario>();
        String query = "SELECT * FROM " +TBINVENTARIO + " WHERE id=?" ;
        Connection con = getConnection();
        try {
            PreparedStatement prepared = con.prepareStatement(query);
             prepared.setInt(1, id);
            ResultSet result = prepared.executeQuery();
            Inventario i = null;
            while(result.next()){
                i = new Inventario();
                i.setId(result.getInt(ID));
                i.setCategoria(result.getString(CATEGORIA));
                i.setClasificacion(result.getString(CLASIFICACION));
                i.setNombreProducto(result.getString(NOMBRE));
                i.setCantidad(result.getInt(CANTIDAD));
                i.setPrecio(result.getFloat(PRECIO));
                
                
                inventario.add(i);
            }
            prepared.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(InventarioData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventario;
}

   public boolean actualizar(Inventario inventario, int id) {
    boolean actualizado = false;
    Connection conexion = null;
    PreparedStatement prepared = null;
    
    try {
        conexion = getConnection();
        String query = "UPDATE " + TBINVENTARIO + " SET categoria=?, clasificacion=?,nombre=?, cantidad=?, precio=? WHERE id=?";
        prepared = conexion.prepareStatement(query);
        
        prepared.setString(1, inventario.getCategoria());
        prepared.setString(2, inventario.getClasificacion());
        prepared.setString(3, inventario.getNombreProducto());
        prepared.setInt(4, inventario.getCantidad());
        prepared.setFloat(5, inventario.getPrecio());
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
  public LinkedList<Inventario> BuscarDatos(String nombre) {
    LinkedList<Inventario> inventario = new LinkedList<Inventario>();
    String query = "SELECT * FROM " + TBINVENTARIO + " WHERE nombre=?";
    Connection con = getConnection();
    try {
        PreparedStatement prepared = con.prepareStatement(query);
        prepared.setString(1, nombre);
        ResultSet result = prepared.executeQuery();
        Inventario i = null;
        while (result.next()) {
            i = new Inventario();
            i.setId(result.getInt(ID));
            i.setCategoria(result.getString(CATEGORIA));
            i.setClasificacion(result.getString(CLASIFICACION));
            i.setNombreProducto(result.getString(NOMBRE));
            i.setCantidad(result.getInt(CANTIDAD));
            i.setPrecio(result.getFloat(PRECIO));

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
