package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.TipoHabitacionEntity;

public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacionEntity, Integer>{
    //get all instances
    @Query(value = "SELECT * FROM Tipos_habitacion", nativeQuery=true)
    Collection<TipoHabitacionEntity> darTiposDeHabitaciones();

    //get one instance specific by id
    @Query(value = "SELECT * FROM Tipos_habitacion WHERE id_tipo= :id_tipo", nativeQuery=true)
    TipoHabitacionEntity darTipoDeHabitacion(@Param("id_tipo") int id_tipo);

    //create an instance
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Tipos_habitacion (id_tipo, costo, capacidad, descripcion, tipo) VALUES(tipoHab_sequence.nextval, :costo, :capacidad, :descripcion, :tipo)", nativeQuery=true)
    void insertarTipoDeHabitacion(@Param("costo") Integer costo, @Param("capacidad") Integer capacidad,
    @Param("descripcion") String descripcion, @Param("tipo") String tipo);

    //update an instance
    @Modifying
    @Transactional
    @Query(value = "UPDATE Tipos_habitacion SET costo=:costo, capacidad=:capacidad, descripcion=:descripcion, tipo=:tipo WHERE id_tipo=:id_tipo", nativeQuery = true)
    void actualizarTipoDeHabitacion(@Param("id_tipo") Integer id_tipo, @Param("costo") Integer costo, @Param("capacidad") Integer capacidad,
    @Param("descripcion") String descripcion, @Param("tipo") String tipo);

    //eliminate an instance
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Tipos_habitacion WHERE id_tipo = :id_tipo", nativeQuery = true)
    void eliminarTipoDeHabitacion(@Param("id_tipo") Integer id_tipo);

}
