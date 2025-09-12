package com.example.grenniplanta.ServivcesInterfaces;

import com.example.grenniplanta.Entities.Planta;

import java.util.List;

public interface IPlantaService {
    public List<Planta> list();
    public void insert(Planta planta);
    public Planta listId(int id);
    public void delete(int id);
    public void update(Planta device);
    public List<Planta> buscarService(String tipo);
}
