package pe.edu.upc.demogreeni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.demogreeni.entities.Tratamiento;

public interface ITratamientoRepository extends JpaRepository<Tratamiento,Integer> {
}
