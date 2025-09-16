package pe.edu.upc.demogreeni.serviceinterfaces;
import pe.edu.upc.demogreeni.entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    public List<Usuario> list();
    public void insert(Usuario usuario);
    public Usuario listId(int id);
    public void delete(int id);
    public void  update(Usuario usuario);
    public List<Usuario> buscarService(String nombre);
}


