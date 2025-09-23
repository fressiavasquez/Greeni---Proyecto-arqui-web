package pe.edu.upc.greeni.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greeni.dtos.EspecieDTO;
import pe.edu.upc.greeni.entities.Especie;
import pe.edu.upc.greeni.servicesInterfaces.IEspecieService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/especies")
public class EspecieController {
    @Autowired
    private IEspecieService es;
    @GetMapping
    public List<EspecieDTO> listar(){
        return es.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y,EspecieDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody EspecieDTO dto)
    {
        ModelMapper m = new ModelMapper();
        Especie r =m.map(dto, Especie.class);
        es.insert(r);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Especie dev = es.listId(id);
        if (dev == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        EspecieDTO dto = m.map(dev, EspecieDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEspecie(@PathVariable("id") Integer id) {
        Especie d = es.listId(id);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        es.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarEspecie(@RequestBody EspecieDTO dto) {
        ModelMapper m = new ModelMapper();
        Especie dev = m.map(dto, Especie.class);

        Especie existente = es.listId(dev.getIdEspecie());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + dev.getIdEspecie());
        }
        es.update(dev);
        return ResponseEntity.ok("Registro con ID " + dev.getIdEspecie() + " modificado correctamente.");
    }
}
