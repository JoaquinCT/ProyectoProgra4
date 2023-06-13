
package cr.ac.una.tecsolve.jpa;

import cr.ac.una.tecsolve.domain.Gasto;
import cr.ac.una.tecsolve.repository.GastosRepository;
import cr.ac.una.tecsolve.service.IGastoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Dolmus
 */
@Service
@Primary
public class GastoServiceJpa implements IGastoService{

    @Autowired
    GastosRepository gastosRep;
    
    @Override
    public List<Gasto> listar() {
        return (List<Gasto>)gastosRep.findAll();
    }

    @Override
    public Gasto findById(int id) {
        return gastosRep.findById(id).orElse(null);
    }

    @Override
    public Gasto save(Gasto gasto) {
        return gastosRep.save(gasto);
    }

    @Override
    public void delete(int id) {
        gastosRep.deleteById(id);
    }
    
}
