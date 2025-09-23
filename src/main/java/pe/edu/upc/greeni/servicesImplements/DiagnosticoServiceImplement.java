package pe.edu.upc.greeni.servicesImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.greeni.entities.Diagnostico;
import pe.edu.upc.greeni.repositories.IDiagnosticoRepository;
import pe.edu.upc.greeni.servicesInterfaces.IDiagnosticoService;

import java.util.List;


@Service
public class DiagnosticoServiceImplement implements IDiagnosticoService {

    @Autowired
    private IDiagnosticoRepository diagnosticoRepository;

    @Override
    public List<Diagnostico> list() {
        return diagnosticoRepository.findAll();
    }

    @Override
    public void insert(Diagnostico diagnostico) {
        diagnosticoRepository.save(diagnostico);
    }
}
