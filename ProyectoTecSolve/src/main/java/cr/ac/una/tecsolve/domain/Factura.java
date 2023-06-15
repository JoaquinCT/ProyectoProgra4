
package cr.ac.una.tecsolve.domain;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
/**
 *
 * @author kinco
 */
public class Factura {
    private int id;
    private String producto;
    private int cantidad;
    private double precio;
    private String cliente;
    @DateTimeFormat(pattern = "dd-MM-YYYY")
    private Date fecha;

    public Factura(String producto, int cantidad, double precio, String cliente, Date fecha) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.cliente = cliente;
        this.fecha = fecha;
    }

    public Factura() {
        
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
