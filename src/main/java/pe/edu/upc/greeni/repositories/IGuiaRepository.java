package pe.edu.upc.greeni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.greeni.entities.Guia;

public interface IGuiaRepository extends JpaRepository<Guia,Integer> {
}
