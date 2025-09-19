package pe.edu.upc.greeni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.greeni.entities.Tratamiento;

import java.util.List;

public interface ITratamientoRepository extends JpaRepository<Tratamiento,Integer> {
        List<Tratamiento> findByDuracionGreaterThan(int duracion);
        List<Tratamiento> findByDuracionLessThan(int duracion);
    }



