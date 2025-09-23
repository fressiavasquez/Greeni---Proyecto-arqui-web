package pe.edu.upc.greeni.servicesInterfaces;

import pe.edu.upc.greeni.entities.Diagnostico;

import java.util.List;

public interface IDiagnosticoService {
    public List<Diagnostico> list();
    public void insert(Diagnostico diagnostico);
}
