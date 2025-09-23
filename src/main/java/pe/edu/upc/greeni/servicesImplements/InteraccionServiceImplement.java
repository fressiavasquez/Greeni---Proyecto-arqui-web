package pe.edu.upc.greeni.servicesImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.greeni.entities.Interaccion;
import pe.edu.upc.greeni.repositories.IInteraccionRepository;
import pe.edu.upc.greeni.servicesInterfaces.IInteraccionService;

import java.util.List;
@Service
public class InteraccionServiceImplement implements IInteraccionService {
    @Autowired
    private IInteraccionRepository repositoryInteraccion;

    @Override
    public List<Interaccion> listar() {
        return repositoryInteraccion.findAll();
    }

    @Override
    public void insert(Interaccion interaccion) {
        repositoryInteraccion.save(interaccion);
    }
}
