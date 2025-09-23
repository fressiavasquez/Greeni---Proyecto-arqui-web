package pe.edu.upc.greeni.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greeni.dtos.DiagnosticoDTO;
import pe.edu.upc.greeni.entities.Diagnostico;
import pe.edu.upc.greeni.servicesInterfaces.IDiagnosticoService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/diagnostico")
public class DiagnosticoController {
        @Autowired
        private IDiagnosticoService service;

        @PostMapping
        public void insertar(@RequestBody DiagnosticoDTO dto) {
            ModelMapper m = new ModelMapper();
            Diagnostico d = m.map(dto, Diagnostico.class);
            service.insert(d);
        }

        @GetMapping
        public List<DiagnosticoDTO> listar()
        {
            return service.list().stream().map(y -> {
                ModelMapper m = new ModelMapper();
                return m.map(y, DiagnosticoDTO.class);
            }).collect(Collectors.toList());
        }
    @GetMapping({"/{id}"})
    public ResponseEntity<?> listar(@PathVariable("id") Integer id) {
        Diagnostico d = this.service.listId(id);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        } else {
            ModelMapper m = new ModelMapper();
            Diagnostico dg = (Diagnostico) m.map(service, Diagnostico.class);
            return ResponseEntity.ok(dg);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Diagnostico dgto = this.service.listId(id);
        if (dgto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        } else {
            this.service.delete(id);
            return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@RequestBody DiagnosticoDTO dto) {
        ModelMapper m = new ModelMapper();
        Diagnostico dia = m.map(dto, Diagnostico.class);

        Diagnostico existente = service.listId(dia.getIdDiagnostico());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + dia.getIdDiagnostico());
        }

        service.update(dia);
        return ResponseEntity.ok("Registro con ID " + dia.getIdDiagnostico() + " modificado correctamente.");
    }



}
