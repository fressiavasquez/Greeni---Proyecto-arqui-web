package pe.edu.upc.demogreeni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demogreeni.entities.EstadoRecordatorio;

@Repository
public interface IEstadoRecordatorioRepository extends JpaRepository<EstadoRecordatorio,Integer> {
}
