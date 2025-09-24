package pe.edu.upc.demogreeni.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demogreeni.dtos.CantidadRecordatorioPorEstadoDTO;
import pe.edu.upc.demogreeni.dtos.EstadoRecordatorioDTO;
import pe.edu.upc.demogreeni.entities.EstadoRecordatorio;
import pe.edu.upc.demogreeni.serviceinterfaces.IEstadoRecordatorioService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/estado_recordatorio")
public class EstadoRecordatorioController {
    @Autowired
    private IEstadoRecordatorioService erR;

    @GetMapping
    public List<EstadoRecordatorioDTO> listar(){
        return erR.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, EstadoRecordatorioDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody EstadoRecordatorioDTO dto)
    {
        ModelMapper m = new ModelMapper();
        EstadoRecordatorio t=m.map(dto,EstadoRecordatorio.class);
        erR.insert(t);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<?> listar(@PathVariable("id") Integer id) {
        EstadoRecordatorio tra = this.erR.listid(id);
        if (tra == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        } else {
            ModelMapper m = new ModelMapper();
            EstadoRecordatorio ta = (EstadoRecordatorio) m.map(tra, EstadoRecordatorio.class);
            return ResponseEntity.ok(ta);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        EstadoRecordatorio trat = this.erR.listid(id);
        if (trat == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        } else {
            this.erR.delete(id);
            return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
        }
    }

    @GetMapping("/cantidadporestado")
    public ResponseEntity<?> cantidadporestado() {
        List<String[]> fila = erR.cantidadRecordatoriosPorEstado();

        if (fila == null || fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron vencimientos en los próximos 7 días");
        }

        List<CantidadRecordatorioPorEstadoDTO> listaDTO = new ArrayList<>(fila.size());
        for (String[] s : fila) {
            CantidadRecordatorioPorEstadoDTO dto = new CantidadRecordatorioPorEstadoDTO();
            dto.setId(Integer.parseInt(s[0]));
            dto.setNombre(s[1]);
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }
}
