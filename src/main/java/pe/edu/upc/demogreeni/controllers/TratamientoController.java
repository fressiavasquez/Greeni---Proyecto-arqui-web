package pe.edu.upc.demogreeni.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demogreeni.dtos.TratamientoDTO;
import pe.edu.upc.demogreeni.entities.Tratamiento;
import pe.edu.upc.demogreeni.servicesInterfaces.ITratamientoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tratamiento")
public class TratamientoController {
    @Autowired
    private ITratamientoService ta;
    @GetMapping
    public List<TratamientoDTO> listar(){
        return ta.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, TratamientoDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody TratamientoDTO dto)
    {
        ModelMapper m = new ModelMapper();
        Tratamiento t=m.map(dto,Tratamiento.class);
        ta.insert(t);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<?> listar(@PathVariable("id") Integer id) {
        Tratamiento tra = this.ta.listId(id);
        if (tra == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        } else {
            ModelMapper m = new ModelMapper();
            Tratamiento ta = (Tratamiento) m.map(tra, Tratamiento.class);
            return ResponseEntity.ok(ta);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Tratamiento trat = this.ta.listId(id);
        if (trat == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        } else {
            this.ta.delete(id);
            return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
        }
    }
}
