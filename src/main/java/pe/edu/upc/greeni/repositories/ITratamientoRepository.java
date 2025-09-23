package pe.edu.upc.greeni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.greeni.entities.Tratamiento;

public interface ITratamientoRepository extends JpaRepository<Tratamiento,Integer> {
}

