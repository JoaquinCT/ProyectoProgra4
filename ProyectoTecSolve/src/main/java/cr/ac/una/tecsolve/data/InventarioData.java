/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.data;

import com.example.proyecto.domain.Inventario;
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
public class InventarioData extends DataBase {
    public final static String TBINVENTARIO = "tbinventario";
    public final static String ID = "id";
    public final static String CATEGORIA = "categoria";
    public final static String CLASIFICACION = "clasificacion";
    public final static String NOMBRE = "nombreProducto";
    public final static String CANTIDAD = "cantidad";
    public final static String PRECIO = "precio";
    
    
    
    public LinkedList<Inventario> getEspacios(){
        LinkedList<Inventario> inventario = new LinkedList<Inventario>();
        String query = "SELECT * FROM " +TBINVENTARIO + ";" ;
        Connection con = getConexion();
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
    //---------------------------------Metodo de insertar a la base de datos---------------------------
    
    public boolean insertar(Inventario inventario){
        boolean inserto = false;
        
        //Sentencia query de la base de datos.
        String query = "INSERT INTO "+ TBINVENTARIO+ "("+CATEGORIA+","+CLASIFICACION+","+NOMBRE+", "+CANTIDAD+","+PRECIO+") VALUES (?,?,?,?,?)";
        
        //Conexion a la base de datos.
        Connection conexion = this.getConexion();
        try {
            PreparedStatement prepared = conexion.prepareStatement(query);
            //Seteo los datos en los valores respectivos de mi base de datos.
            //Con las posiciones respectivas.
            prepared.setString(1, inventario.getCategoria());
            prepared.setString(2, inventario.getClasificacion());
            prepared.setString(3, inventario.getNombreProducto());
            prepared.setInt(4, inventario.getCantidad());
            prepared.setFloat(5, inventario.getPrecio());
            
           
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
        Connection conexion = getConexion();
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
        Connection con = getConexion();
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
        conexion = getConexion();
        String query = "UPDATE " + TBINVENTARIO + " SET categoria=?, clasificacion=?,nombreProducto=?, cantidad=?, precio=? WHERE id=?";
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

}
