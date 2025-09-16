package pe.edu.upc.demogreeni.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.demogreeni.entities.Diagnostico;

public interface IDiagnosticoRepository extends JpaRepository<Diagnostico, Integer> {
}
