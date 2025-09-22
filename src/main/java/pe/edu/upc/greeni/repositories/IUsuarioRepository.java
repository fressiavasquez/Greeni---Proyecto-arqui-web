package pe.edu.upc.greeni.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.greeni.entities.Usuario;

import java.util.List;

@Repository

public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {

    @Query("select u from Usuario u where u.nombre like %:nombre%")
    public List<Usuario> buscarPorNombreService(@Param("nombre")String nombre);

    @Query(
            value = "SELECT r.tipo AS rol, COUNT(u.id) AS cantidad_usuarios " +
                    "FROM usuario u " +
                    "INNER JOIN rol r ON u.rol_id = r.rol_id " +
                    "GROUP BY r.tipo",
            nativeQuery = true)
    List<String[]> cantidadUsuariosPorRol();


    @Query(value = "SELECT EXTRACT(MONTH FROM u.fecha_ini) AS mes, COUNT(u.id) AS cantidad_usuarios " +
            "FROM usuario u " +
            "WHERE u.activo = true " +
            "AND EXTRACT(YEAR FROM u.fecha_ini) = EXTRACT(YEAR FROM CURRENT_DATE) " +
            "GROUP BY EXTRACT(MONTH FROM u.fecha_ini) " +
            "ORDER BY mes",
            nativeQuery = true)
    public List<String[]> usuariosActivosPorMes();

}
