package pe.edu.upc.demogreeni.servicesInterfaces;

import jdk.jshell.Diag;
import pe.edu.upc.demogreeni.entities.Diagnostico;

import java.util.List;

public interface IDiagnosticoService {
    public List<Diagnostico> list();
    public void insert(Diagnostico diagnostico);
    public Diagnostico listId(int id);
    public void delete(int id);
    public List<String[]> quantitySeveridadDiagnostico();
}
