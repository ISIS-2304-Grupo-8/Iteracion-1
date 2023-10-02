package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.IncluyenEntity;

public interface IncluyenRepository extends JpaRepository<IncluyenEntity, Integer> {
        // Read incluyen
        @Query(value = "SELECT * FROM incluyen", nativeQuery = true)
        Collection<IncluyenEntity> darIncluyen();

        // Read incluyen dado un producto y un plan de consumo
        @Query(value = "SELECT * FROM incluyen WHERE planesdeconsumo_id_plan =:planesdeconsumo_id_plan AND productos_id_producto=:productos_id_producto AND productos_tipo_servicio=:productos_tipo_servicio", nativeQuery = true)
        IncluyenEntity darIncluyen(@Param("planesdeconsumo_id_plan") Integer prod_id,
                        @Param("productos_id_producto") Integer pdc_id,
                        @Param("productos_tipo_servicio") String tipo_servicio);

        // Crear un incluyen
        @Modifying
        @Transactional
        @Query(value = "INSERT INTO incluyen (planesdeconsumo_id_plan, productos_id_producto, productos_tipo_servicio) VALUES (:planesdeconsumo_id_plan, :productos_id_producto, :productos_tipo_servicio)", nativeQuery = true)
        void crearIncluyen(@Param("productos_id_producto") Integer prod_id,
                        @Param("planesdeconsumo_id_plan") Integer pdc_id,
                        @Param("productos_tipo_servicio") String tipo_servicio);

        // Eliminar incluyen
        @Modifying
        @Transactional
        @Query(value = "DELETE FROM incluyen WHERE productos_id_producto =:productos_id_producto AND planesdeconsumo_id_plan=:planesdeconsumo_id_plan AND productos_tipo_servicio=:productos_tipo_servicio", nativeQuery = true)
        void eliminarIncluyen(@Param("productos_id_producto") Integer prod_id,
                        @Param("planesdeconsumo_id_plan") Integer pdc_id,
                        @Param("productos_tipo_servicio") String tipo_servicio);
}
