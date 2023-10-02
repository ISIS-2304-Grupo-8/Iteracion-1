// Repositorio para Tipos_servicio
package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.TipoServicioEntity;

public interface TipoServicioRepository extends JpaRepository<TipoServicioEntity, Integer> {
    
    @Query(value = "SELECT * FROM Tipos_servicio", nativeQuery=true)
    Collection<TipoServicioEntity> darTiposDeServicio();

    @Query(value = "SELECT * FROM Tipos_servicio WHERE tipo_servicio= :tipo_servicio", nativeQuery=true)
    TipoServicioEntity darTipoDeServicio(@Param("tipo_servicio") String tipo_servicio);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Tipos_servicio (tipo_servicio, precio, capacidad) VALUES(:tipo_servicio, :precio, :capacidad)", nativeQuery=true)
    void insertarTipoDeServicio(@Param("tipo_servicio") String tipo_servicio, @Param("precio") Integer precio, @Param("capacidad") Integer capacidad);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Tipos_servicio SET precio=:precio, capacidad=:capacidad WHERE tipo_servicio=:tipo_servicio", nativeQuery = true)
    void actualizarTipoDeServicio(@Param("tipo_servicio") String tipo_servicio, @Param("precio") Integer precio, @Param("capacidad") Integer capacidad);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Tipos_servicio WHERE tipo_servicio = :tipo_servicio", nativeQuery = true)
    void eliminarTipoDeServicio(@Param("tipo_servicio") String tipo_servicio);
}