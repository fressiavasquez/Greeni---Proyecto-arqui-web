package pe.edu.upc.demogreeni.servicesInterfaces;

import pe.edu.upc.demogreeni.entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    public List<Usuario> list();
    public void insert(Usuario usuario);
}
