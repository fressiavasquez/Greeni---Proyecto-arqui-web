package com.example.grenniplanta.ServicesImplements;

import com.example.grenniplanta.Entities.Planta;
import com.example.grenniplanta.Repositories.IPlantaRepository;
import com.example.grenniplanta.ServivcesInterfaces.IPlantaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantaServiceImplement implements IPlantaService{
    @Autowired
    private IPlantaRepository p;

    @Override
    public List<Planta> list()
    {
        return p.findAll();
    }

    @Override
    public void insert(Planta planta) {
        p.save(planta);
    }
    @Override
    public Planta listId(int id) {
        return p.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        p.deleteById(id);
    }

    @Override
    public void update(Planta device) {
        p.save(device);
    }

    @Override
    public List<Planta> buscarService(String tipo) {
        return p.buscarR(tipo);
    }
}
