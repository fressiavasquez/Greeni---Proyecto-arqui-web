package pe.edu.upc.greeni.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greeni.dtos.RolDTO;
import pe.edu.upc.greeni.entities.Rol;
import pe.edu.upc.greeni.servicesInterfaces.IRolService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/rol")
public class RolController {
    @Autowired
    private IRolService service;

    @PostMapping
    public void insertar(@RequestBody RolDTO sdto)
    {
        ModelMapper m = new ModelMapper();
        Rol s=m.map(sdto, Rol.class);
        service.insert(s);
    }

    @GetMapping
    public List<RolDTO> listar(){
        return service.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y,RolDTO.class);
        }).collect(Collectors.toList());
    }
}
