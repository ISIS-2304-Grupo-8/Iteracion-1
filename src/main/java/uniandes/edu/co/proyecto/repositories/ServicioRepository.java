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
    ServicioEntity darServicio(@Param("id_servicio") Integer id_servicio);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Servicios (id_servicio, disponibilidad, nombre, descripcion, Tipos_servicio_id_tipo_ser) VALUES(servicios_sequence.nextval, :disponibilidad, :nombre, :descripcion, :Tipos_servicio_id_tipo_ser)", nativeQuery=true)
    void insertarServicio(@Param("disponibilidad") String disponibilidad, @Param("nombre") String nombre,
    @Param("descripcion") String descripcion, @Param("Tipos_servicio_id_tipo_ser") Integer Tipos_servicio_id_tipo_ser);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Servicios SET disponibilidad=:disponibilidad, nombre=:nombre, descripcion=:descripcion, Tipos_servicio_id_tipo_ser=:Tipos_servicio_id_tipo_ser WHERE id_servicio=:id_servicio", nativeQuery = true)
    void actualizarServicio(@Param("id_servicio") Integer id_servicio, @Param("disponibilidad") String disponibilidad, 
    @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("Tipos_servicio_id_tipo_ser") Integer Tipos_servicio_id_tipo_ser);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Servicios WHERE id_servicio = :id_servicio", nativeQuery = true)
    void eliminarServicio(@Param("id_servicio") Integer id_servicio);

    @Query(value = "SELECT R.servicios_id, COUNT(R.servicios_id)" +//
    "FROM reservan R" +//
    "WHERE R.fecha_inicio>=:f_in AND R.fecha_final<=:f_fin" +//
    "GROUP BY R.servicios_id" +
    "ORDER BY COUNT(R.servicios_id) DESC, R.servicios_id ASC" +
    "FETCH FIRST 20 ROWS ONLY", nativeQuery = true)
    Collection<ServicioEntity> dar20ServiciosMasPopulares(@Param("f_in") String f_in, @Param("f_fin") String f_fin);

}
