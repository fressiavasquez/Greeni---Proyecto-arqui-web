package pe.edu.upc.greeni.servicesInterfaces;

import pe.edu.upc.greeni.entities.Especie;
import pe.edu.upc.greeni.entities.Planta;

import java.util.List;

public interface IEspecieService {
    public List<Especie> list();
    public void insert(Especie especie);
    Especie listId(int id);
    void delete(int id);
    void update(Especie especie);
    public List<String[]> quantityEspecie();
}
