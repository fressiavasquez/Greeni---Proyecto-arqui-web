package com.example.grenniplanta.Controllers;

import com.example.grenniplanta.Entities.Planta;
import com.example.grenniplanta.ServivcesInterfaces.IPlantaService;
import com.example.grenniplanta.dtos.PlantaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Plantas")

public class PlantaController {
    @Autowired
    private IPlantaService p;
    @GetMapping
    public List<PlantaDTO> list() {
        return p.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, PlantaDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insert(@RequestBody PlantaDTO dto) {
        ModelMapper m = new ModelMapper();
        Planta planta = m.map(dto, Planta.class);
        p.insert(planta);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Planta dev = p.listId(id);
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
        Planta dev = p.listId(id);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        p.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody PlantaDTO dto) {
        ModelMapper m = new ModelMapper();
        Planta dev = m.map(dto, Planta.class);

        Planta existente = p.listId(dev.getId());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + dev.getId());
        }
        p.update(dev);
        return ResponseEntity.ok("Registro con ID " + dev.getId() + " modificado correctamente.");
    }
}
