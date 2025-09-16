package pe.edu.upc.demogreeni.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demogreeni.dtos.DiagnosticoDTO;
import pe.edu.upc.demogreeni.entities.Diagnostico;
import pe.edu.upc.demogreeni.servicesInterfaces.IDiagnosticoService;

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
}
