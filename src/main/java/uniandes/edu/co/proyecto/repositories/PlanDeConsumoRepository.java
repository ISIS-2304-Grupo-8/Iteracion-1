package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.PlanDeConsumoEntity;

public interface PlanDeConsumoRepository extends JpaRepository<PlanDeConsumoEntity, Integer> {

    // Read planes de consumo
    @Query(value = "SELECT * FROM planesdeconsumo", nativeQuery = true)
    Collection<PlanDeConsumoEntity> darPlanesDeConsumo();

    // Read plan de consumo dado un plan de consumo
    @Query(value = "SELECT * FROM planesdeconsumo WHERE id_plan =:id_plan", nativeQuery = true)
    PlanDeConsumoEntity darPlanDeConsumo(@Param("id_plan") Integer id_plan);

    // Crear un plan de consumo
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO planesdeconsumo (id_plan, nombre, descripcion, tipos_plan_tipo_plan) VALUES (planes_sequence.nextval, :nombre, :descripcion, :tipo_plan)", nativeQuery = true)
    void crearPlanDeConsumo(@Param("nombre") String nombre, @Param("descripcion") String descripcion,
            @Param("tipo_plan") String tipo_plan);

    // Actualizar un plan de consumo
    @Modifying
    @Transactional
    @Query(value = "UPDATE planesdeconsumo SET nombre=:nombre, descripcion=:descripcion WHERE id_plan=:id_plan", nativeQuery = true)
    void actualizarPlanDeConsumo(@Param("id_plan") Integer id_plan, @Param("nombre") String nombre,
            @Param("descripcion") String descripcion);

    // Eliminar plan de consumo
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM planesdeconsumo WHERE id_plan =:id_plan", nativeQuery = true)
    void eliminarPlanDeConsumo(@Param("id_plan") Integer id_plan);

}
