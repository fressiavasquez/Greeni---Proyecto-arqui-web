package pe.edu.upc.demogreeni.servicesImplements;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.demogreeni.entities.Tratamiento;
import pe.edu.upc.demogreeni.repositories.ITratamientoRepository;
import pe.edu.upc.demogreeni.servicesInterfaces.ITratamientoService;

import java.util.List;

@Service
public class TratamientoServiceImplement implements ITratamientoService {

    @Autowired
    private ITratamientoRepository ta;

    @Override
    public List<Tratamiento> list() {
        return ta.findAll();
    }

    @Override
    public void insert(Tratamiento tratamiento) {
        ta.save(tratamiento);
    }

    @Override
    public Tratamiento listId(int id) {
        return ta.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        ta.deleteById(id);
    }

    @Override
    public List<String[]> venceTratamiento() {
        return ta.venceTratamiento();
    }

}
