/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tecsolve.logic;

import cr.ac.una.tecsolve.data.DataProveedor;
import cr.ac.una.tecsolve.domain.Proveedor;
import java.util.LinkedList;

/**
 *
 * @author kinco
 */

public class LogicaProveedor {

    DataProveedor data = new DataProveedor();

    public boolean insertarProveedor(Proveedor proveedor) {
        return data.insertarProveedor(proveedor);
    }

    public boolean actualizarProveedor(Proveedor proveedor) {
        return data.actualizarProveedor(proveedor);
    }

    public boolean eliminarProveedor(int idProveedor) {
        return data.eliminarProveedor(idProveedor);
    }

    public Proveedor getProveedorPorId(int idProveedor) {
        return data.getProveedorPorId(idProveedor);
    }

    public LinkedList<Proveedor> getListaProveedores() {
        return data.getListaProveedores();
    }
}
