package uniandes.edu.co.proyecto.repositories;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ReservaEntity;

public interface ReservasRepository extends JpaRepository<ReservaEntity, Integer>{
    
    @Query(value = "SELECT * FROM reservas", nativeQuery = true)
    Collection<ReservaEntity> darReservas();

    @Query(value = "SELECT * FROM reservas WHERE id_reserva = :id_reserva", nativeQuery = true)
    ReservaEntity darReserva(@Param("id_reserva") int id_reserva);

    @Modifying
    @Transactional
    @Query(value = "UPDATE reservas SET num_personas = :num_personas, estados_reservas_fecha_inicial = :estados_reservas_fecha_inicial, estados_reservas_fecha_final = :estados_reservas_fecha_final, planesdeconsumo_id_plan = :planesdeconsumo_id_plan, clientes_num_doc = :clientes_num_doc WHERE id_reserva = :id_reserva", nativeQuery = true)
    void actualizarReserva(@Param("id_reserva") int id_reserva, @Param("num_personas") int num_personas,
    @Param("estados_reservas_fecha_inicial") Date estados_reservas_fecha_inicial, 
    @Param("estados_reservas_fecha_final") Date estados_reservas_fecha_final,
    @Param("planesdeconsumo_id_plan") int planesdeconsumo_id_plan,
    @Param("clientes_num_doc") int clientes_num_doc);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservas (id_reserva, num_personas, estados_reservas_fecha_inicial, estados_reservas_fecha_final, planesdeconsumo_id_plan, clientes_num_doc) VALUES (reservas_sequence.nextval, :num_personas, :estados_reservas_fecha_inicial, :estados_reservas_fecha_final, :planesdeconsumo_id_plan, :clientes_num_doc)", nativeQuery = true)
    void insertarReserva(@Param("num_personas") int num,
    @Param("estados_reservas_fecha_inicial") Date estados_reservas_fecha_inicial, 
    @Param("estados_reservas_fecha_final") Date estados_reservas_fecha_final,
    @Param("planesdeconsumo_id_plan") int planesdeconsumo_id_plan,
    @Param("clientes_num_doc") int clientes_num_doc);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservas WHERE id_reserva = :id_reserva", nativeQuery = true)
    void eliminarReserva(@Param("id_reserva") int id_reserva);


}
