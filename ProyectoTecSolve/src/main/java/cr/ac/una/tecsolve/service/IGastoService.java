
package cr.ac.una.tecsolve.service;

import cr.ac.una.tecsolve.domain.Gasto;
import java.util.List;

/**
 *
 * @author Juan Dolmus
 */
public interface IGastoService {
    public List<Gasto> listar();
    public Gasto findById(int id);
    public Gasto save(Gasto gasto);
    public void delete(int id);
}
