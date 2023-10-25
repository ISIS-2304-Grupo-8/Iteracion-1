package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.HabitacionEntity;
import uniandes.edu.co.proyecto.modelo.ReservanEntity;

public interface HabitacionRepository extends JpaRepository<HabitacionEntity, Integer> {
    
    public interface RespuestaReq1{
        Integer getID_HABITACION();
        Integer getDINERO_RECOLECTADO();
    }
    //get all instances
    @Query(value = "SELECT * FROM Habitaciones", nativeQuery=true)
    Collection<HabitacionEntity> darHabitaciones();

    //get one instance specific by id
    @Query(value = "SELECT * FROM Habitaciones WHERE id_habitacion= :id_habitacion", nativeQuery=true)
    HabitacionEntity darHabitacion(@Param("id_habitacion") int id_habitacion);

    //create an instance
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Habitaciones (id_habitacion, consumo_acumulado, disponibilidad, Reservas_id_reserva, Tipos_habitacion_id_tipo) VALUES(habitaciones_sequence.nextval, :consumo_acumulado, :disponibilidad, :Reservas_id_reserva, :Tipos_habitacion_id_tipo)", nativeQuery=true)
    void insertarHabitacion(@Param("consumo_acumulado") Integer consumo_acumulado, @Param("disponibilidad") Integer disponibilidad,
    @Param("Reservas_id_reserva") Integer Reservas_id_reserva, @Param("Tipos_habitacion_id_tipo") Integer Tipos_habitacion_id_tipo);

    //update an instance
    @Modifying
    @Transactional
    @Query(value = "UPDATE Habitaciones SET consumo_acumulado=:consumo_acumulado, disponibilidad=:disponibilidad, Reservas_id_reserva=:Reservas_id_reserva, Tipos_habitacion_id_tipo=:Tipos_habitacion_id_tipo WHERE id_habitacion=:id_habitacion", nativeQuery = true)
    void actualizarHabitacion(@Param("id_habitacion") Integer id_habitacion, @Param("consumo_acumulado") Integer consumo_acumulado, 
    @Param("disponibilidad") Integer disponibilidad, @Param("Reservas_id_reserva") Integer Reservas_id_reserva, @Param("Tipos_habitacion_id_tipo") Integer Tipos_habitacion_id_tipo);

    //eliminate an instance
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Habitaciones WHERE id_habitacion = :id_habitacion", nativeQuery = true)
    void eliminarHabitacion(@Param("id_habitacion") Integer id_habitacion);

    //Basicas Entrega 2
    @Query(value = "SELECT R.id_habitacion AS ID_HABITACION, SUM(T.precio) AS DINERO_RECOLECTADO\n" +
    "FROM reservan R\n" +
    "INNER JOIN servicios S ON R.servicios_id = S.id_servicio\n" +
    "INNER JOIN Tipos_servicio T ON S.ts_tipo_servicio = T.tipo_servicio\n" +
    "WHERE EXTRACT(YEAR FROM fecha_inicio) = EXTRACT(YEAR FROM SYSDATE) AND EXTRACT(YEAR FROM fecha_final) = EXTRACT(YEAR FROM SYSDATE)\n" +
    "GROUP BY R.id_habitacion\n" +
    "ORDER BY R.id_habitacion"
    , nativeQuery = true)
    Collection<RespuestaReq1> darDineroRecolectadoServiciosPorHabitacion();

}
