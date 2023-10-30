package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.HabitacionEntity;

public interface HabitacionRepository extends JpaRepository<HabitacionEntity, Integer> {

    public interface RespuestaReq1 {
        Integer getID_HABITACION();

        Integer getDINERO_RECOLECTADO();
    }

    // columnas retornadas con el req3 de la 2 parte del reto
    public interface RespuestaOcupacion {
        Integer getID_HABITACION();

        Integer getTOTAL_DIAS_OCUPADOS();

        Double getPORCENTAJE_OCUPACION();
    }

    // get all instances
    @Query(value = "SELECT * FROM habitaciones", nativeQuery = true)
    Collection<HabitacionEntity> darHabitaciones();

    // get one instance specific by id
    @Query(value = "SELECT * FROM habitaciones WHERE id_habitacion= :id_habitacion", nativeQuery = true)
    HabitacionEntity darHabitacion(@Param("id_habitacion") int id_habitacion);

    // create an instance
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO habitaciones (id_habitacion, consumo_acumulado, disponibilidad, Tipos_habitacion_id_tipo) VALUES(habitaciones_sequence.nextval, :consumo_acumulado, :disponibilidad, :Tipos_habitacion_id_tipo)", nativeQuery = true)
    void insertarHabitacion(@Param("consumo_acumulado") Integer consumo_acumulado,
            @Param("disponibilidad") Character disponibilidad,
            @Param("Tipos_habitacion_id_tipo") Integer Tipos_habitacion_id_tipo);

    // update an instance
    @Modifying
    @Transactional
    @Query(value = "UPDATE habitaciones SET consumo_acumulado=:consumo_acumulado, disponibilidad=:disponibilidad, Tipos_habitacion_id_tipo=:Tipos_habitacion_id_tipo WHERE id_habitacion=:id_habitacion", nativeQuery = true)
    void actualizarHabitacion(@Param("id_habitacion") Integer id_habitacion,
            @Param("consumo_acumulado") Integer consumo_acumulado,
            @Param("disponibilidad") Character disponibilidad,
            @Param("Tipos_habitacion_id_tipo") Integer Tipos_habitacion_id_tipo);

    // eliminate an instance
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM habitaciones WHERE id_habitacion = :id_habitacion", nativeQuery = true)
    void eliminarHabitacion(@Param("id_habitacion") Integer id_habitacion);

    // Basicas Entrega 2
    @Query(value = "SELECT R.id_habitacion AS ID_HABITACION, SUM(T.precio) AS DINERO_RECOLECTADO\n" +
            "FROM reservan R\n" +
            "INNER JOIN servicios S ON R.servicios_id = S.id_servicio\n" +
            "INNER JOIN Tipos_servicio T ON S.ts_tipo_servicio = T.tipo_servicio\n" +
            "WHERE EXTRACT(YEAR FROM fecha_inicio) = EXTRACT(YEAR FROM SYSDATE) AND EXTRACT(YEAR FROM fecha_final) = EXTRACT(YEAR FROM SYSDATE)\n"
            +
            "GROUP BY R.id_habitacion\n" +
            "ORDER BY R.id_habitacion " +
            "OFFSET :offset ROWS\n" +
            "FETCH FIRST :size ROWS ONLY", nativeQuery = true)
    Collection<RespuestaReq1> darDineroRecolectadoServiciosPorHabitacion(@Param("size") Integer size,
            @Param("offset") Integer offset);

    // consulta sobre porcentaje de ocupacion ultimo año de habitaciones
    @Query(value = "SELECT apartan.id_habitacion AS ID_HABITACION,\n" +
            "SUM(FECHA_FIN - FECHA_IN) AS TOTAL_DIAS_OCUPADOS,\n" +
            "ROUND((SUM(FECHA_FIN - FECHA_IN) * 100 / 365), 2) AS PORCENTAJE_OCUPACION\n" +
            "FROM APARTAN\n" +
            "WHERE EXTRACT(YEAR FROM FECHA_IN) = EXTRACT(YEAR FROM SYSDATE)\n" +
            "  AND EXTRACT(YEAR FROM FECHA_FIN) = EXTRACT(YEAR FROM SYSDATE)\n" +
            "GROUP BY apartan.id_habitacion\n" +
            "ORDER BY apartan.id_habitacion\n" +
            "OFFSET :offset ROWS\n" +
            "FETCH FIRST :size ROWS ONLY", nativeQuery = true)
    Collection<RespuestaOcupacion> obtenerIndiceOcupacionPorHabitacion(
            @Param("size") Integer size,
            @Param("offset") Integer offset);
}
