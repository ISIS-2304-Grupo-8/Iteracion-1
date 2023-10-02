package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.TipoPlanEntity;

public interface TipoPlanRepository extends JpaRepository<TipoPlanEntity, Integer> {

    // Read tipos de plan
    @Query(value = "SELECT * FROM Tipos_plan", nativeQuery = true)
    Collection<TipoPlanEntity> darTiposPlan();

    // Read tipo de plan dado un tipo de plan
    @Query(value = "SELECT * FROM tipos_plan WHERE tipo_plan =:tipo_plan", nativeQuery = true)
    TipoPlanEntity darTipoPlan(@Param("tipo_plan") String tipo_plan);

    // Crear un tipo de plan
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tipos_plan (tipo_plan, vigencia) VALUES (:tipo_plan, :vigencia)", nativeQuery = true)
    void crearTipoPlan(@Param("tipo_plan") String tipo_plan, @Param("vigencia") Integer vigencia);

    // Actualizar un tipo de plan
    @Modifying
    @Transactional
    @Query(value = "UPDATE tipos_plan SET vigencia=:vigencia WHERE tipo_plan=:tipo_plan", nativeQuery = true)
    void actualizarTipoPlan(@Param("tipo_plan") String tipo_plan, @Param("vigencia") Integer vigencia);

    // Eliminar tipo de plan
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tipos_plan WHERE tipo_plan =:tipo_plan", nativeQuery = true)
    void eliminarTipoPlan(@Param("tipo_plan") String tipo_plan);

}
