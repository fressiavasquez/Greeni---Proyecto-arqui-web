package pe.edu.upc.demogreeni.servicesInterfaces;

import pe.edu.upc.demogreeni.entities.Diagnostico;

import java.util.List;

public interface IDiagnosticoService {
    public List<Diagnostico> list();
    public void insert(Diagnostico diagnostico);
}
