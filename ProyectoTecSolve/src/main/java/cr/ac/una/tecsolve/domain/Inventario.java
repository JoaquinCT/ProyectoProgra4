
package cr.ac.una.tecsolve.domain;

import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class Inventario {
            private int id;
            private String categoria;
            private String clasificacion;
            private String nombreProducto;
            private int cantidad;
            private float precio;
            private LinkedList inventario;

    public Inventario() {
        
    }

    
    public Inventario(LinkedList inventario) {
        this.inventario = inventario;
    }

    public Inventario( String categoria, String clasificacion,String nombreProducto, int cantidad, float precio) {
        this.id = id;
        this.categoria = categoria;
        this.clasificacion = clasificacion;
        this.nombreProducto=nombreProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.inventario = inventario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

  
  

            
}
