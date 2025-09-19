package pe.edu.upc.demogreeni.servicesImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.demogreeni.entities.Diagnostico;
import pe.edu.upc.demogreeni.repositories.IDiagnosticoRepository;
import pe.edu.upc.demogreeni.servicesInterfaces.IDiagnosticoService;

import java.util.List;

@Service
public class DiagnosticoServiceImplement implements IDiagnosticoService {
    @Autowired
    private IDiagnosticoRepository repository;


    @Override
    public List<Diagnostico> list() {
        return repository.findAll();
    }

    @Override
    public void insert(Diagnostico diagnostico) {
        repository.save(diagnostico);
    }

    @Override
    public Diagnostico listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
