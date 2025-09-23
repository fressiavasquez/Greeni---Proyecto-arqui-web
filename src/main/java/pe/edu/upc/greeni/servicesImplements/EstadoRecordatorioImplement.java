package pe.edu.upc.greeni.servicesImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.greeni.entities.EstadoRecordatorio;
import pe.edu.upc.greeni.repositories.IEstadoRecordatorioRepository;
import pe.edu.upc.greeni.servicesInterfaces.IEstadoRecordatorioService;

import java.util.List;
@Service
public class EstadoRecordatorioImplement implements IEstadoRecordatorioService {
    @Autowired
    private IEstadoRecordatorioRepository erR;

    @Override
    public List<EstadoRecordatorio> listid() {
        return erR.findAll();
    }

    @Override
    public void insert(EstadoRecordatorio estadoRecordatorio) {
        erR.save(estadoRecordatorio);
    }
}
