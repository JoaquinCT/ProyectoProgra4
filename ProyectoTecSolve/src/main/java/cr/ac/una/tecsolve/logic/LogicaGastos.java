package cr.ac.una.tecsolve.logic;

import cr.ac.una.tecsolve.data.DataGasto;
import cr.ac.una.tecsolve.domain.Gasto;
import java.util.LinkedList;

/**
 *
 * @author Juan Dolmus
 */
public class LogicaGastos {

    DataGasto data = new DataGasto();

    public boolean insertarGasto(Gasto g) {
        return data.insertarGasto(g);
    }

    public LinkedList<Gasto> getListaGastosPorPaginacion(int page, int pageSize) {
        return data.getListaGastosPorPaginacion(page, pageSize);
    }

    public LinkedList<Gasto> getListaGastos() {
        return data.getListaGastos();
    }

    public Gasto getGastoPorId(int idGasto) {
        return data.getGastoPorId(idGasto);
    }

    public boolean actualizarGasto(Gasto g) {
        return data.actualizarGasto(g);
    }

    public boolean eliminarGasto(int idGasto) {
        return data.deshabilitarGasto(idGasto);
    }

    public int getTotalGastos() {
        return data.getNumeroTotalGastos();
    }
}
