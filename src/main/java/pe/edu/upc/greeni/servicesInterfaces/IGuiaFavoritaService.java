package pe.edu.upc.greeni.servicesInterfaces;

import pe.edu.upc.greeni.entities.GuiaFavorita;

import java.util.List;

public interface IGuiaFavoritaService {
    List<GuiaFavorita> list();
    void insert(GuiaFavorita guiaFavorita);
}
