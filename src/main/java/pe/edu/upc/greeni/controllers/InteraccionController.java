package pe.edu.upc.greeni.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greeni.dtos.InteraccionDTO;
import pe.edu.upc.greeni.dtos.InteraccionDTOList;
import pe.edu.upc.greeni.dtos.PlantaDTO;
import pe.edu.upc.greeni.entities.Interaccion;
import pe.edu.upc.greeni.entities.Planta;
import pe.edu.upc.greeni.servicesInterfaces.IInteraccionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/interaccion")
public class InteraccionController {
    @Autowired
    private IInteraccionService service;

    @GetMapping
    public List<InteraccionDTOList> listar(){
        return service.listar().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, InteraccionDTOList.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody InteraccionDTO idto) {
        ModelMapper mapper = new ModelMapper();
        Interaccion i = mapper.map(idto, Interaccion.class);
        service.insert(i);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Interaccion inte = service.listId(id);
        if (inte == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        InteraccionDTO dto = m.map(inte, InteraccionDTO.class);
        return ResponseEntity.ok(dto);
    }









    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarInteraccion(@PathVariable("id") Integer id) {
        Interaccion i = service.listId(id);
        if (i == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }
}
