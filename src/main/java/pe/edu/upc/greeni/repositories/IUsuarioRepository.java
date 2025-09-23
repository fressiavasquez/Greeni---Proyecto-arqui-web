package pe.edu.upc.greeni.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.greeni.entities.Usuario;

import java.util.List;

@Repository

public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {
}
