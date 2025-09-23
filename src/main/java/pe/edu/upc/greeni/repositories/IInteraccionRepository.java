package pe.edu.upc.greeni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.greeni.entities.Interaccion;

public interface IInteraccionRepository extends JpaRepository<Interaccion,Integer> {
}
