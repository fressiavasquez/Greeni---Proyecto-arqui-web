package pe.edu.upc.demogreeni.serviceinterfaces;

import pe.edu.upc.demogreeni.entities.EstadoRecordatorio;

import java.util.List;

public interface IEstadoRecordatorioService {
    public List<EstadoRecordatorio> list();
    public void insert(EstadoRecordatorio estadoRecordatorio);
    public EstadoRecordatorio listId(int id);
    public void delete(int id);
    public void update(EstadoRecordatorio estadoRecordatorio);
    public List<EstadoRecordatorio> buscarServiceER(String tipoER);
}
