package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity,
    Integer>{

    public interface ResponseGoodClients {
        Integer getCEDULA_CLIENTE();
        Integer getDIAS_ESTADIA();
        Integer getCONSUMO_TOTAL();
    }

    @Query(value = "SELECT * FROM clientes", nativeQuery = true)
    Collection<ClienteEntity> darClientes();

    @Query(value = "SELECT * FROM clientes WHERE num_doc= :num_doc ", nativeQuery
    = true)
    ClienteEntity darCliente(@Param("num_doc") int num_doc);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO clientes (num_doc, nombre, email, tipo_doc, rol_cliente) VALUES (:num_doc, :nombre, :email, :tipo_doc, :rol_cliente)", nativeQuery = true)
    void insertarCliente(@Param("num_doc") int num_doc, @Param("nombre") String nombre, @Param("email") String email , 
    @Param("tipo_doc") String tipo_doc, @Param("rol_cliente") Integer rol_cliente);

    @Modifying
    @Transactional
    @Query(value = "UPDATE clientes SET nombre = :nombre, email = :email, tipo_doc = :tipo_doc, rol_cliente = :rol_cliente WHERE num_doc= :num_doc", nativeQuery = true)
    void actualizarCliente(@Param("num_doc") int num_doc, @Param("nombre") String nombre, @Param("email") String email , 
    @Param("tipo_doc") String tipo_doc, @Param("rol_cliente") Integer rol_cliente);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM clientes WHERE num_doc = :num_doc", nativeQuery = true)
    void eliminarCliente(@Param("num_doc") int num_doc);

    @Query(value = "SELECT clientes_num_doc as cedula_cliente, SUM((A.fecha_fin - A.fecha_in)) as dias_estadia, null as consumo_total " +
    "FROM reservas R " +
    "INNER JOIN apartan A ON R.id_reserva = A.id_reserva " +
    "WHERE EXTRACT(YEAR FROM fecha_in) = EXTRACT(YEAR FROM SYSDATE) AND EXTRACT(YEAR FROM fecha_fin) = EXTRACT(YEAR FROM SYSDATE) " +
    "GROUP BY clientes_num_doc " +
    "HAVING SUM((A.fecha_fin - A.fecha_in))>=14 " +
    "UNION ALL " +
    "SELECT Res.clientes_num_doc as cedula_cliente, null as dias ,SUM(T.precio) as consumo_total " +
    "FROM reservan R " +
    "INNER JOIN servicios S ON R.servicios_id = S.id_servicio " +
    "INNER JOIN Tipos_servicio T ON S.ts_tipo_servicio = T.tipo_servicio " +
    "INNER JOIN apartan A ON R.id_habitacion = A.id_habitacion " +
    "INNER JOIN reservas Res ON Res.id_reserva = A.id_reserva " +
    "WHERE EXTRACT(YEAR FROM fecha_inicio) = EXTRACT(YEAR FROM SYSDATE) AND EXTRACT(YEAR FROM fecha_final) = EXTRACT(YEAR FROM SYSDATE) " +
    "GROUP BY Res.clientes_num_doc  " +
    "HAVING SUM(T.precio)>= 15000000/4300 " +
    "ORDER BY dias_estadia DESC, consumo_total DESC", nativeQuery = true)
    Collection<ResponseGoodClients> darBuenosClientes();


}
