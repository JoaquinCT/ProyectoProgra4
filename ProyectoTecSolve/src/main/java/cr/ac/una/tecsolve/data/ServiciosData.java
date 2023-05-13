/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.data;

import com.example.proyecto.domain.Inventario;
import com.example.proyecto.domain.Servicios;
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
public class ServiciosData extends DataBase{
        public final static String TBINVENTARIO = "tbservicios";
    public final static String ID = "id";
    public final static String NOMBRE = "nombre";
    public final static String DESCRIPCION = "descripcion";
    public final static String HORARIO = "horario";
    public final static String PRECIO = "precio";
    public final static String ENCARGADO = "encargado";
    
    
    
    public LinkedList<Servicios> getEspacios(){
        LinkedList<Servicios> inventario = new LinkedList<Servicios>();
        String query = "SELECT * FROM " +TBINVENTARIO + ";" ;
        Connection con = getConexion();
        try {
            PreparedStatement prepared = con.prepareStatement(query);
            
            ResultSet result = prepared.executeQuery();
            Servicios i = null;
            while(result.next()){
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
    //---------------------------------Metodo de insertar a la base de datos---------------------------
    
    public boolean insertar(Servicios inventario){
        boolean inserto = false;
        
        //Sentencia query de la base de datos.
        String query = "INSERT INTO "+ TBINVENTARIO+ "("+NOMBRE+","+DESCRIPCION+","+HORARIO+", "+PRECIO+","+ENCARGADO+") VALUES (?,?,?,?,?)";
        
        //Conexion a la base de datos.
        Connection conexion = this.getConexion();
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

     
     
     
   public LinkedList<Servicios> mostrarDatos(int id) {
  LinkedList<Servicios> inventario = new LinkedList<Servicios>();
        String query = "SELECT * FROM " +TBINVENTARIO + " WHERE id=?" ;
        Connection con = getConexion();
        try {
            PreparedStatement prepared = con.prepareStatement(query);
             prepared.setInt(1, id);
            ResultSet result = prepared.executeQuery();
            Servicios i = null;
            while(result.next()){
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
        conexion = getConexion();
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
        String query = "SELECT * FROM " +TBINVENTARIO + " WHERE nombre=?" ;
        Connection con = getConexion();
        try {
            PreparedStatement prepared = con.prepareStatement(query);
             prepared.setString(1, nombre);
            ResultSet result = prepared.executeQuery();
            Servicios i = null;
            while(result.next()){
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
