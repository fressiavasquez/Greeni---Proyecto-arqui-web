package pe.edu.upc.demogreeni.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demogreeni.dtos.UsuarioDTO;
import pe.edu.upc.demogreeni.entities.Usuario;
import pe.edu.upc.demogreeni.servicesInterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {
    @Autowired
    private IUsuarioService us;
    @GetMapping
    public List<UsuarioDTO> listar(){
        return us.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y,UsuarioDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody UsuarioDTO dto)
    {
        ModelMapper m = new ModelMapper();
        Usuario u=m.map(dto,Usuario.class);
        us.insert(u);
    }
}
