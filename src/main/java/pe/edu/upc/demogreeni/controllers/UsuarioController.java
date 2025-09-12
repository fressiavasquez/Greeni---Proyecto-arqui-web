package pe.edu.upc.demogreeni.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping({"/{id}"})
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Usuario usu = this.us.listId(id);
        if (usu == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        } else {
            ModelMapper m = new ModelMapper();
            Usuario u = (Usuario)m.map(usu, Usuario.class);
            return ResponseEntity.ok(u);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Usuario u = this.us.listId(id);
        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        } else {
            this.us.delete(id);
            return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
        }
    }
}
