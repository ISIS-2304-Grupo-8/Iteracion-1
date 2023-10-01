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
    @Query(value = "SELECT * FROM incluyen WHERE prod_id =:prod_id AND pdc_id=:pdc_id AND prod_id_ser=:prod_id_ser", nativeQuery = true)
    IncluyenEntity darIncluyen(@Param("prod_id") Integer prod_id, @Param("pdc_id") Integer pdc_id,
            @Param("prod_id_ser") Integer prod_id_ser);

    // Crear un incluyen
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO incluyen (prod_id, pdc_id, prod_id_ser) VALUES (:prod_id, :pdc_id, :prod_id_ser)", nativeQuery = true)
    void crearIncluyen(@Param("prod_id") Integer prod_id, @Param("pdc_id") Integer pdc_id,
            @Param("prod_id_ser") Integer prod_id_ser);

    // Eliminar incluyen
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM incluyen WHERE prod_id =:prod_id AND pdc_id=:pdc_id AND prod_id_ser=:prod_id_ser", nativeQuery = true)
    void eliminarIncluyen(@Param("prod_id") Integer prod_id, @Param("pdc_id") Integer pdc_id,
            @Param("prod_id_ser") Integer prod_id_ser);
}
