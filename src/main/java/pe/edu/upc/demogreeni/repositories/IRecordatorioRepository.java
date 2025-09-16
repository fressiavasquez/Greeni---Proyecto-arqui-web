package pe.edu.upc.demogreeni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demogreeni.entities.Recordatorio;

import java.util.List;

@Repository
public interface IRecordatorioRepository extends JpaRepository<Recordatorio,Integer> {
    @Query("Select dev from Recordatorio  dev where dev.tipo like %:tipo%")
    public List<Recordatorio> buscarR(@Param("tipo")String tipo);
}
