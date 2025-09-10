package pe.edu.upc.demogreeni.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.demogreeni.entities.Usuario;
import pe.edu.upc.demogreeni.repositories.IUsuarioRepository;
import pe.edu.upc.demogreeni.serviceinterfaces.IUsuarioService;


import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired
    private IUsuarioRepository us;

    @Override
    public List<Usuario> list() {
        return us.findAll();
    }

    @Override
    public void insert(Usuario usuario) {
        us.save(usuario);
    }
}
