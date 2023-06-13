
package cr.ac.una.tecsolve.jpa;

import cr.ac.una.tecsolve.domain.Contacto;
import cr.ac.una.tecsolve.repository.ContactosRepository;
import cr.ac.una.tecsolve.service.IContactoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maizeth Cisneros
 */
@Service
@Primary
public class ContactoServiceJpa implements IContactoService{

    @Autowired
    ContactosRepository contactosRep;
    
    @Override
    public List<Contacto> listar() {
        return (List<Contacto>)contactosRep.findAll();
    }

    @Override
    public Contacto findById(int id) {
        return contactosRep.findById(id).orElse(null);
    }

    @Override
    public Contacto save(Contacto contacto) {
        return contactosRep.save(contacto);
    }

    @Override
    public void delete(int id) {
        contactosRep.deleteById(id);
    }
    
}
