package com.example.grenniplanta.Repositories;

import com.example.grenniplanta.Entities.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlantaRepository extends JpaRepository<Planta, Integer> {
    @Query("Select dev from Planta dev where dev.especie like %:tipo%")
    public List<Planta> buscarR(@Param("tipo") String tipo);
}
