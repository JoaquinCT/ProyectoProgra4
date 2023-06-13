
package cr.ac.una.tecsolve.service;

import cr.ac.una.tecsolve.domain.Contacto;
import java.util.List;

/**
 *
 * @author Maizeth Cisneros
 */
public interface IContactoService {
    public List<Contacto> listar();
    public Contacto findById(int id);
    public Contacto save(Contacto contacto);
    public void delete(int id);
}
