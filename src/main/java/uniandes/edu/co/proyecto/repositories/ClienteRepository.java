package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity,
    Integer>{

    @Query(value = "SELECT * FROM clientes", nativeQuery = true)
    Collection<ClienteEntity> darClientes();

    @Query(value = "SELECT * FROM clientes WHERE num_doc= :num_doc ", nativeQuery
    = true)
    ClienteEntity darCliente(@Param("num_doc") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO clientes (num_doc, nombre, email, tipo_doc, rol_cliente) VALUES (:num_doc, :nombre, :email, :tipo_doc, :rol_cliente)", nativeQuery = true)
    void insertarCliente(@Param("num_doc ") int num_doc, @Param("nombre") String nombre, @Param("email") String email , 
    @Param("tipo_doc") String tipo_doc, @Param("rol_cliente") String rol_cliente);

    @Modifying
    @Transactional
    @Query(name = "UPDATE clientes SET nombre = :nombre, email = :email, tipo_doc = :tipo_doc, rol_cliente = :rol_cliente", nativeQuery = true)
    void actualizarCliente(@Param("num_doc") int num_doc, @Param("nombre") String nombre, @Param("email") String email , 
    @Param("tipo_doc") String tipo_doc, @Param("rol_cliente") String rol_cliente);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM clientes WHERE num_doc = :num_doc", nativeQuery = true)
    void eliminarCliente(@Param("num_doc") int num_doc);

}
