package pe.edu.upc.greeni.servicesInterfaces;

import pe.edu.upc.greeni.entities.Interaccion;

import java.util.List;

public interface IInteraccionService {
    public List<Interaccion> listar();
    public void insert(Interaccion interaccion);
}
