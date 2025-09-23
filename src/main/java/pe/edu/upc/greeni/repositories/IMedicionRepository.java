package pe.edu.upc.greeni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.greeni.entities.Medicion;

public interface IMedicionRepository extends JpaRepository<Medicion, Integer> {
}
