package pe.edu.upc.greeni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.greeni.entities.Especie;


public interface IEspecieRepository  extends JpaRepository<Especie, Integer> {
}
