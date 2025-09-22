package pe.edu.upc.greeni.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greeni.dtos.PlantaDTO;
import pe.edu.upc.greeni.entities.Planta;
import pe.edu.upc.greeni.servicesInterfaces.IPlantaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plantas")
public class PlantaController {
    @Autowired
    private IPlantaService ps;

    @GetMapping
    public List<PlantaDTO> listar(){
        return ps.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y,PlantaDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody PlantaDTO dto)
    {
        ModelMapper m = new ModelMapper();
        Planta d=m.map(dto,Planta.class);
        ps.insert(d);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Planta dev = ps.listId(id);
        if (dev == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        PlantaDTO dto = m.map(dev, PlantaDTO.class);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Planta d = ps.listId(id);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ps.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@RequestBody PlantaDTO dto) {
        ModelMapper m = new ModelMapper();
        Planta dev = m.map(dto, Planta.class);

        Planta existente = ps.listId(dev.getIdPlanta());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + dev.getIdPlanta());
        }
        ps.update(dev);
        return ResponseEntity.ok("Registro con ID " + dev.getIdPlanta() + " modificado correctamente.");
    }

}
