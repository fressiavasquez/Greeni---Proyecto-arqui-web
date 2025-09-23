package pe.edu.upc.greeni.servicesInterfaces;

import pe.edu.upc.greeni.entities.EstadoRecordatorio;

import java.util.List;

public interface IEstadoRecordatorioService {
    public List<EstadoRecordatorio> listid();
    public void insert(EstadoRecordatorio estadoRecordatorio);
}
