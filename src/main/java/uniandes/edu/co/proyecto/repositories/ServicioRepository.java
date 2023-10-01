// Repositorio para Servicios
package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ServicioEntity;

public interface ServicioRepository extends JpaRepository<ServicioEntity, Integer> {
    
    @Query(value = "SELECT * FROM Servicios", nativeQuery=true)
    Collection<ServicioEntity> darServicios();

    @Query(value = "SELECT * FROM Servicios WHERE id_servicio= :id_servicio", nativeQuery=true)
    ServicioEntity darServicio(@Param("id_servicio") int id_servicio);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Servicios (id_servicio, disponibilidad, nombre, descripcion, id_tipo_ser) VALUES(serv_sequence.nextval, :disponibilidad, :nombre, :descripcion, :id_tipo_ser)", nativeQuery=true)
    void insertarServicio(@Param("disponibilidad") String disponibilidad, @Param("nombre") String nombre,
    @Param("descripcion") String descripcion, @Param("id_tipo_ser") Integer id_tipo_ser);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Servicios SET disponibilidad=:disponibilidad, nombre=:nombre, descripcion=:descripcion, id_tipo_ser=:id_tipo_ser WHERE id_servicio=:id_servicio", nativeQuery = true)
    void actualizarServicio(@Param("id_servicio") Integer id_servicio, @Param("disponibilidad") String disponibilidad, 
    @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("id_tipo_ser") Integer id_tipo_ser);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Servicios WHERE id_servicio = :id_servicio", nativeQuery = true)
    void eliminarServicio(@Param("id_servicio") Integer id_servicio);
}
