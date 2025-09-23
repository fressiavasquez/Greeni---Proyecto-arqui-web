package pe.edu.upc.greeni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.greeni.entities.EstadoRecordatorio;

public interface IEstadoRecordatorioRepository extends JpaRepository<EstadoRecordatorio,Integer> {
}
