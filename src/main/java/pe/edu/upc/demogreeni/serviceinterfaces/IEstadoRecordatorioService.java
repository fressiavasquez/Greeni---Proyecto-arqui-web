package pe.edu.upc.demogreeni.serviceinterfaces;

import pe.edu.upc.demogreeni.entities.EstadoRecordatorio;

import java.util.List;

public interface IEstadoRecordatorioService {
    public List<EstadoRecordatorio> list();
    public void insert(EstadoRecordatorio estadoRecordatorio);
    public EstadoRecordatorio listid(int id);
    public void delete(int id);
    public List<Object[]> cantidadRecordatoriosPorEstado();
}
