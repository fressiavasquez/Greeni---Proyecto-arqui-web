package pe.edu.upc.greeni.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greeni.dtos.RecordatorioDTO;
import pe.edu.upc.greeni.entities.Recordatorio;
import pe.edu.upc.greeni.servicesInterfaces.IRecordatorioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recordatorio")
public class RecordatorioController {
    @Autowired
    private IRecordatorioService rR;

    @GetMapping
    public List<RecordatorioDTO> listar() {
        return rR.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, RecordatorioDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody RecordatorioDTO dto)
    {
        ModelMapper m = new ModelMapper();
        Recordatorio d = m.map(dto, Recordatorio.class);
        rR.insert(d);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Recordatorio dev = rR.listId(id);
        if (dev == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        RecordatorioDTO dto = m.map(dev, RecordatorioDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Recordatorio d = rR.listId(id);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        rR.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar( @RequestBody RecordatorioDTO dto) {
        ModelMapper m = new ModelMapper();
        Recordatorio dev = m.map(dto, Recordatorio.class);

        Recordatorio existente = rR.listId(dev.getIdRecordatorio());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + dev.getIdRecordatorio());
        }

        rR.update(dev);
        return ResponseEntity.ok("Registro con ID " + dev.getIdRecordatorio() + " modificado correctamente.");
    }

    @GetMapping("/busquedas")
    public ResponseEntity<?> buscar(@RequestParam String t) {
        List<Recordatorio> devices = rR.buscarService(t);

        if (devices.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron dispositivos del tipo: " + t);
        }

        List<RecordatorioDTO> listaDTO = devices.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RecordatorioDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }
}