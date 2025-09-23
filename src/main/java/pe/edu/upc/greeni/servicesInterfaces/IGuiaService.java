package pe.edu.upc.greeni.servicesInterfaces;

import pe.edu.upc.greeni.entities.Guia;

import java.util.List;

public interface IGuiaService {
    public List<Guia> list();

    public void insert(Guia guia);
}
