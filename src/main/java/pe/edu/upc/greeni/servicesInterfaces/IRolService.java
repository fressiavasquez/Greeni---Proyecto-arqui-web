package pe.edu.upc.greeni.servicesInterfaces;

import pe.edu.upc.greeni.entities.Rol;

import java.util.List;

public interface IRolService {
    public List<Rol> list();
    public void insert(Rol rol);
}
