package pe.edu.upc.demogreeni.serviceinterfaces;

import pe.edu.upc.demogreeni.entities.Recordatorio;

import java.util.List;

public interface IRecordatorioService {
    public List<Recordatorio> list();
    public void insert(Recordatorio recordatorio);
    public Recordatorio listId(int id);
    public void delete(int id);
    public void update(Recordatorio recordatorio);
    public List<String[]> quantityTipoRecordatorio();
}
