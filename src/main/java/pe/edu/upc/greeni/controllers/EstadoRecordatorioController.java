package pe.edu.upc.greeni.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
}
