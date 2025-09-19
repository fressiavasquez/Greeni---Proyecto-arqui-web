package pe.edu.upc.demogreeni.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demogreeni.dtos.EstadoRecordatorioDTO;
import pe.edu.upc.demogreeni.entities.EstadoRecordatorio;
import pe.edu.upc.demogreeni.serviceinterfaces.IEstadoRecordatorioService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/estado_recordatorio")
public class EstadoRecordatorioController {
    @Autowired
    private IEstadoRecordatorioService erR;
    @GetMapping
    public List<EstadoRecordatorioDTO>listar(){
        return erR.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y,EstadoRecordatorioDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody EstadoRecordatorioDTO dto)
    {
        ModelMapper m = new ModelMapper();
        EstadoRecordatorio d=m.map(dto,EstadoRecordatorio.class);
        erR.insert(d);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        EstadoRecordatorio dev = erR.listId(id);
        if (dev == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        EstadoRecordatorioDTO dto = m.map(dev, EstadoRecordatorioDTO.class);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        EstadoRecordatorio d = erR.listId(id);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        erR.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody EstadoRecordatorioDTO dto) {
        ModelMapper m = new ModelMapper();
        EstadoRecordatorio dev = m.map(dto, EstadoRecordatorio.class);

        EstadoRecordatorio existente = erR.listId(dev.getIdEstadoRecordatorio());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + dev.getIdEstadoRecordatorio());
        }
        erR.update(dev);
        return ResponseEntity.ok("Registro con ID " + dev.getIdEstadoRecordatorio() + " modificado correctamente.");
    }
    @GetMapping("/busquedas")
    public ResponseEntity<?> buscar(@RequestParam String t) {
        List<EstadoRecordatorio> devices = erR.buscarServiceER(t);

        if (devices.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron dispositivos del tipo: " + t);
        }

        List<EstadoRecordatorioDTO> listaDTO = devices.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, EstadoRecordatorioDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }
}
