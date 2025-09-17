package pe.edu.upc.greeni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.greeni.entities.Tipo_Interaccion;

@Repository
public interface ITipoInteraccionRepository extends JpaRepository<Tipo_Interaccion,Integer> {

}
