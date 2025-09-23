package pe.edu.upc.greeni.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greeni.dtos.EstadoRecordatorioDTOInsert;
import pe.edu.upc.greeni.dtos.EstadoRecordatorioDTOList;
import pe.edu.upc.greeni.entities.EstadoRecordatorio;
import pe.edu.upc.greeni.servicesInterfaces.IEstadoRecordatorioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estado_recordatorio")
public class EstadoRecordatorioController {
    @Autowired
    private IEstadoRecordatorioService estadoRecordatorioService;

    @PostMapping
    public void insertar(@RequestBody EstadoRecordatorioDTOInsert sdto) {
        ModelMapper m = new ModelMapper();
        EstadoRecordatorio s = m.map(sdto, EstadoRecordatorio.class);
        estadoRecordatorioService.insert(s);
    }

    @GetMapping
    public List<EstadoRecordatorioDTOList> listar() {
        return estadoRecordatorioService.listid().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, EstadoRecordatorioDTOList.class);
        }).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        EstadoRecordatorio dev = estadoRecordatorioService.listId(id);
        if (dev == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        EstadoRecordatorioDTOList dto = m.map(dev, EstadoRecordatorioDTOList.class);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        EstadoRecordatorio d = estadoRecordatorioService.listId(id);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        estadoRecordatorioService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@RequestBody EstadoRecordatorioDTOList dto) {
        ModelMapper m = new ModelMapper();
        EstadoRecordatorio dev = m.map(dto, EstadoRecordatorio.class);

        EstadoRecordatorio existente = estadoRecordatorioService.listId(dev.getIdEstadoRecordatorio());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + dev.getIdEstadoRecordatorio());
        }
        estadoRecordatorioService.update(dev);
        return ResponseEntity.ok("Registro con ID " + dev.getIdEstadoRecordatorio() + " modificado correctamente.");
    }
    @GetMapping("/busquedas")
    public ResponseEntity<?> buscar(@RequestParam String t) {
        List<EstadoRecordatorio> devices = estadoRecordatorioService.buscarServiceER(t);

        if (devices.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron dispositivos del tipo: " + t);
        }

        List<EstadoRecordatorioDTOList> listaDTO = devices.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, EstadoRecordatorioDTOList.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }
}
