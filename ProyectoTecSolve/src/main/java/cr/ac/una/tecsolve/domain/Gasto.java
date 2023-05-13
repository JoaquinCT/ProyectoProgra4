
package cr.ac.una.tecsolve.domain;

import java.sql.Date;



/**
 *
 * @author Juan Dolmus
 */
public class Gasto {
    private int id;
    private Date fecha;
    private String descripcion;
    private String categoria;
    private double monto;
    private String detalle;

    public Gasto() {
    }

    public Gasto(int id, Date fecha, String descripcion, String categoria, double monto, String detalle) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.monto = monto;
        this.detalle = detalle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
}
