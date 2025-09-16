package pe.edu.upc.demogreeni.servicesInterfaces;


import pe.edu.upc.demogreeni.entities.Tratamiento;

import java.util.List;

public interface ITratamientoService {
    public List<Tratamiento> list();
    public void insert(Tratamiento tratamiento);
    public Tratamiento listId(int id);
    public void delete(int id);
}
