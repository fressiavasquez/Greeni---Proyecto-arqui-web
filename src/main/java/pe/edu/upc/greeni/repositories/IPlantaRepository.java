package pe.edu.upc.greeni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.greeni.entities.Planta;

public interface IPlantaRepository extends JpaRepository<Planta, Integer> {
}
