package cr.ac.una.tecsolve.logic;

import cr.ac.una.tecsolve.data.DataContacto;
import cr.ac.una.tecsolve.domain.Contacto;
import java.util.LinkedList;

/**
 *
 * @author Maizeth Cisneros
 */
public class LogicaContactos {

    DataContacto data = new DataContacto();

    public boolean insertarContacto(Contacto c) {
        return data.insertarContacto(c);
    }

    public LinkedList<Contacto> getListaContactosPorPaginacion(int page, int pageSize) {
        return data.getListaContactosPorPaginacion(page, pageSize);
    }

    public LinkedList<Contacto> getListaContactos() {
        return data.getListaContactos();
    }

    public Contacto getContactoPorId(int idContacto) {
        return data.getContactoPorId(idContacto);
    }

    public boolean actualizarContacto(Contacto c) {
        return data.actualizarContacto(c);
    }

    public boolean eliminarContacto(int idContacto) {
        return data.deshabilitarContacto(idContacto);
    }

    public int getTotalContactos() {
        return data.getNumeroTotalContactos();
    }
}
