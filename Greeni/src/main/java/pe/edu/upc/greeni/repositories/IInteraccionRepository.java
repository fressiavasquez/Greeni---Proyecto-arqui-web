package pe.edu.upc.greeni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.greeni.entities.Interaccion;

@Repository
public interface IInteraccionRepository extends JpaRepository<Interaccion,Integer> {

}
