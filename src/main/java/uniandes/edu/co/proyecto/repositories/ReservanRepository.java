package uniandes.edu.co.proyecto.repositories;

import java.sql.Date;
import java.util.Collection;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ReservanEntity;
import uniandes.edu.co.proyecto.modelo.ReservanPK;

public interface ReservanRepository extends JpaRepository<ReservanEntity, ReservanPK> {

    public interface ResponseOperation {
        String getFECHA_FINAL();
        Integer getCANTIDAD_CONSUMOS();
        
    }
    
    //get all instances
    @Query(value = "SELECT * FROM Reservan", nativeQuery=true)
    Collection<ReservanEntity> darReservas();

    //get one instance specific by primary key
    @Query(value = "SELECT * FROM Reservan WHERE hab_id= :hab_id AND serv_id= :serv_id", nativeQuery=true)
    ReservanEntity darReserva(@Param("hab_id") Integer hab_id, @Param("serv_id") Integer serv_id);

    //create an instance
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Reservan (hab_id, serv_id, fecha_inicio, fecha_final) VALUES(:hab_id, :serv_id, :fecha_inicio, :fecha_final)", nativeQuery=true)
    void insertarReserva(@Param("hab_id") Integer hab_id, @Param("serv_id") Integer serv_id, 
    @Param("fecha_inicio") Date fecha_inicio, @Param("fecha_final") Date fecha_final);

    //update an instance
    @Modifying
    @Transactional
    @Query(value = "UPDATE Reservan SET fecha_inicio=:fecha_inicio, fecha_final=:fecha_final WHERE hab_id=:hab_id AND serv_id=:serv_id", nativeQuery = true)
    void actualizarReserva(@Param("hab_id") Integer hab_id, @Param("serv_id") Integer serv_id, 
    @Param("fecha_inicio") Date fecha_inicio, @Param("fecha_final") Date fecha_final);

    //eliminate an instance
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Reservan WHERE hab_id = :hab_id AND serv_id= :serv_id", nativeQuery = true)
    void eliminarReserva(@Param("hab_id") Integer hab_id, @Param("serv_id") Integer serv_id);

    //RF6 Iteracion 2
    @Query(value="SELECT TO_CHAR(fecha_final, 'YYYY-MM-DD') AS FECHA_FINAL, COUNT(precio) AS CANTIDAD_CONSUMOS " + 
        "FROM reservan " + 
        "INNER JOIN servicios ON servicios.id_servicio = reservan.servicios_id " +
        "INNER JOIN tipos_servicio ON tipos_servicio.tipo_servicio = servicios.ts_tipo_servicio " +
        "GROUP BY fecha_final " +
        "ORDER BY CANTIDAD_CONSUMOS DESC;", nativeQuery=true)
    Collection<ResponseOperation> consultarFechasMayoresIngresos();
}

