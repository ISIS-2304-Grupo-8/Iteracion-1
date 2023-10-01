package uniandes.edu.co.proyecto.repositories;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Estado_reserva;

public interface Estado_reservaRepository extends JpaRepository<Estado_reserva, Integer>{
    
    @Query(value = "SELECT * FROM estados_reservas", nativeQuery = true)
    Collection<Estado_reserva> darEstados_reserva();

    @Query(value = "SELECT * FROM estados_reservas WHERE fecha_inicial = :fecha_inicial AND fecha_fin = :fecha_fin", nativeQuery = true)
    Estado_reserva darEstado_reserva(@Param("fecha_inicial") Date fecha_inicial, @Param("fecha_fin") Date fecha_fin);

    @Modifying
    @Transactional
    @Query(value = "UPDATE estados_reservas SET fecha_inicial = :fecha_inicial_nueva, fecha_fin = :fecha_fin_nueva, estado = :estado WHERE fecha_inicial = :fecha_inicial AND fecha_fin = :fecha_fin", nativeQuery = true)
    void actualizarEstado_Reserva(@Param("fecha_inicial") Date fecha_inicial, @Param("fecha_fin") Date fecha_fin, @Param("estado") char estado,
    @Param("fecha_inicial_nueva") Date fecha_inicial_nueva, @Param("fecha_fin_nueva") Date fecha_fin_nueva);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO estados_reservas (fecha_inicial, fecha_fin, estado) VALUES (fecha_inicial, fecha_fin, estado)", nativeQuery = true)
    void insertarEstado_Reserva(@Param("fecha_inicial") Date fecha_inicial, @Param("fecha_fin") Date fecha_fin, @Param("estado") char estado);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM estados_reservas WHERE fecha_inicial = :fecha_inicial AND fecha_fin = :fecha_fin", nativeQuery = true)
    void eliminarEstado_Reserva(@Param("fecha_inicial") Date fecha_inicial, @Param("fecha_fin") Date fecha_fin);

}
