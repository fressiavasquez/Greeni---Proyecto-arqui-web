package pe.edu.upc.greeni.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greeni.dtos.GuiaDTO;
import pe.edu.upc.greeni.dtos.GuiaDTOList;
import pe.edu.upc.greeni.entities.Guia;
import pe.edu.upc.greeni.servicesInterfaces.IGuiaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/guia")
public class GuiaController {
    @Autowired
    private IGuiaService guiaService;
    @PostMapping
    public void insertar(@RequestBody GuiaDTO sdto)
    {
        ModelMapper m = new ModelMapper();
        Guia s=m.map(sdto,Guia.class);
        guiaService.insert(s);
    }

    @GetMapping
    public List<GuiaDTOList> listar(){
        return guiaService.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, GuiaDTOList.class);
        }).collect(Collectors.toList());
    }
}
