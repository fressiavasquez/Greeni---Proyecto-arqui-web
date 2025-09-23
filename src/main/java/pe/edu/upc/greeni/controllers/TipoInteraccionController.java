package pe.edu.upc.greeni.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greeni.dtos.TipoInteraccionDTO;
import pe.edu.upc.greeni.entities.Tipo_Interaccion;
import pe.edu.upc.greeni.servicesInterfaces.ITipoInteraccionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tipointeraccion")
public class TipoInteraccionController {
    @Autowired
    private ITipoInteraccionService service;

    @GetMapping
    public List<TipoInteraccionDTO> listar(){
        return service.listar().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, TipoInteraccionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody TipoInteraccionDTO tdto) {
        ModelMapper mapper = new ModelMapper();
        Tipo_Interaccion t = mapper.map(tdto, Tipo_Interaccion.class);
        service.insert(t);
    }
}
