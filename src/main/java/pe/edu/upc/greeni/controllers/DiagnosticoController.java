package pe.edu.upc.greeni.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
}
