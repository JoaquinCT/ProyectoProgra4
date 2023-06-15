/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tecsolve.logic;

import cr.ac.una.tecsolve.data.DataFactura;
import cr.ac.una.tecsolve.domain.Factura;
import java.util.LinkedList;
/**
 *
 * @author kinco
 */


public class LogicaFactura {
    DataFactura dataFactura = new DataFactura();

    public LogicaFactura() {
        this.dataFactura = new DataFactura();
    }

    public boolean insertarFactura(Factura factura) {
        return dataFactura.insertarFactura(factura);
    }

    public boolean actualizarFactura(Factura factura) {
        return dataFactura.actualizarFactura(factura);
    }

    public boolean eliminarFactura(int idFactura) {
        return dataFactura.eliminarFactura(idFactura);
    }

    public Factura getFacturaPorId(int idFactura) {
        return dataFactura.getFacturaPorId(idFactura);
    }

    public LinkedList<Factura> getListaFacturas() {
        return dataFactura.getListaFacturas();
    }

    public LinkedList<Factura> getListaFacturasPorPaginacion(int numPage, int pageSize) {
        return dataFactura.getListaFacturasPorPaginacion(numPage, pageSize);
    }

    public int getNumeroTotalFacturas() {
        return dataFactura.getNumeroTotalFacturas();
    }
}

