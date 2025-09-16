package pe.edu.upc.demogreeni.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.demogreeni.entities.EstadoRecordatorio;
import pe.edu.upc.demogreeni.repositories.IEstadoRecordatorioRepository;
import pe.edu.upc.demogreeni.serviceinterfaces.IEstadoRecordatorioService;

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
