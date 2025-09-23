package pe.edu.upc.greeni.servicesInterfaces;

import pe.edu.upc.greeni.entities.Tipo_Interaccion;

import java.util.List;

public interface ITipoInteraccionService {
    public List<Tipo_Interaccion> listar();
    public void insert(Tipo_Interaccion tipoInteraccion);
}
