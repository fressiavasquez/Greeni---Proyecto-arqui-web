package pe.edu.upc.demogreeni.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demogreeni.dtos.EstadoRecordatorioDTOInsert;
import pe.edu.upc.demogreeni.dtos.EstadoRecordatorioDTOlist;
import pe.edu.upc.demogreeni.entities.EstadoRecordatorio;
import pe.edu.upc.demogreeni.serviceinterfaces.IEstadoRecordatorioService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/estado_recordatorio")
public class EstadoRecordatorioController {
    @Autowired
    private IEstadoRecordatorioService estadoRecordatorioService;

    @PostMapping
    public void insertar(@RequestBody EstadoRecordatorioDTOInsert sdto)
    {
        ModelMapper m = new ModelMapper();
        EstadoRecordatorio s = m.map(sdto, EstadoRecordatorio.class);
        estadoRecordatorioService.insert(s);
    }

    @GetMapping("/dtos")
    public List<EstadoRecordatorioDTOlist> listar() {
        return estadoRecordatorioService.listid().stream().map( y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, EstadoRecordatorioDTOlist.class);
        }).collect(Collectors.toList());
    }
}
