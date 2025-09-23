package pe.edu.upc.greeni.servicesImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.greeni.entities.Tipo_Interaccion;
import pe.edu.upc.greeni.repositories.ITipoInteraccionRepository;
import pe.edu.upc.greeni.servicesInterfaces.ITipoInteraccionService;

import java.util.List;
@Service
public class TipoInteraccionServiceImplement  implements ITipoInteraccionService {
    @Autowired
    private ITipoInteraccionRepository repository;

    @Override
    public List<Tipo_Interaccion> listar() {
        return repository.findAll();
    }

    @Override
    public void insert(Tipo_Interaccion tipoInteraccion) {
        repository.save(tipoInteraccion);

    }
}
