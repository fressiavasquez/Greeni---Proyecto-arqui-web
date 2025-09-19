package pe.edu.upc.demogreeni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demogreeni.entities.EstadoRecordatorio;

import java.util.List;

@Repository
public interface IEstadoRecordatorioRepository extends JpaRepository<EstadoRecordatorio,Integer> {
    @Query("Select dev from EstadoRecordatorio dev where dev.tipoER like %:tipoER%")
    public List<EstadoRecordatorio> buscarER(@Param("tipoER") String tipoER);
}
