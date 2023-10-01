package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.DescuentoEntity;

public interface DescuentoRepository extends JpaRepository<DescuentoEntity, Integer> {

    // Read descuentos
    @Query(value = "SELECT * FROM descuentos", nativeQuery = true)
    Collection<DescuentoEntity> darDescuentos();

    // Read descuento dado un servicio descuentado y un tipo de plan
    @Query(value = "SELECT * FROM descuentos WHERE servicio_descuentado =:servicio_descuentado AND tipos_plan_tipo_plan=:tipo_plan", nativeQuery = true)
    DescuentoEntity darDescuento(@Param("servicio_descuentado") String servicio_descuentado,
            @Param("tipo_plan") String tipo_plan);

    // Crear un descuento
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO descuentos (servicio_descuentado, tipos_plan_tipo_plan, descuento) VALUES (:servicio_descuentado, :tipo_plan, :descuento)", nativeQuery = true)
    void insertarDescuento(@Param("servicio_descuentado") String servicio_descuentado,
            @Param("tipo_plan") String tipo_plan, @Param("descuento") String descuento);

    // Actualizar un descuento
    @Modifying
    @Transactional
    @Query(value = "UPDATE descuentos SET descuento=:descuento", nativeQuery = true)
    void actualizarDescuento(@Param("servicio_descuentado") String servicio_descuentado,
            @Param("tipo_plan") String tipo_plan, @Param("descuento") String descuento);

    // Eliminar descuento
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM descuentos WHERE servicio_descuentado =:servicio_descuentado AND tipos_plan_tipo_plan=:tipo_plan", nativeQuery = true)
    void eliminarDescuento(@Param("servicio_descuentado") String servicio_descuentado,
            @Param("tipo_plan") String tipo_plan);
}
