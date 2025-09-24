package pe.edu.upc.demogreeni.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demogreeni.dtos.UsuarioDTO;
import pe.edu.upc.demogreeni.entities.Recordatorio;
import pe.edu.upc.demogreeni.entities.Usuario;
import pe.edu.upc.demogreeni.serviceinterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")

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
    public void insert(@RequestBody UsuarioDTO dto)
    {
        ModelMapper m = new ModelMapper();
        Usuario u=m.map(dto,Usuario.class);
        us.insert(u);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Usuario dev = this.us.listId(id);
        if (dev == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        } else {
            ModelMapper m = new ModelMapper();
            UsuarioDTO dto = (UsuarioDTO)m.map(dev, UsuarioDTO.class);
            return ResponseEntity.ok(dto);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Usuario u = us.listId(id);
        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        us.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody UsuarioDTO dto) {
        ModelMapper m = new ModelMapper();
        Usuario dev = m.map(dto, Usuario.class);

        // Validación de existencia
        Usuario existente = us.listId(dev.getId());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + dev.getId());
        }

        // Actualización si pasa validaciones
        us.update(dev);
        return ResponseEntity.ok("Registro con ID " + dev.getId() + " modificado correctamente.");
    }


}
